package tech.harry.online_book_strore.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.harry.online_book_strore.dtos.BookRequest;
import tech.harry.online_book_strore.entities.BookCategories;
import tech.harry.online_book_strore.entities.Books;
import tech.harry.online_book_strore.exceptions.ResourceNotFoundException;
import tech.harry.online_book_strore.repositories.BookCategoryRepository;
import tech.harry.online_book_strore.repositories.BookRepository;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService    {

    private static final Logger logger =  LoggerFactory.getLogger(UserService.class);


    @Autowired
    private BookCategoryRepository bookCategoryRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;
    public Books bookCreated(BookRequest bookRequest) {

//        BookCategories bookCategories= bookCategoryRepository.findById(bookRequest.getCategoryId())
//                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + bookRequest.getCategoryId()));
//        if (bookCategories==null) {
//            throw new RuntimeException("Category not found for Category ID: " + bookRequest.getCategoryId());
//        }
//
//        Books existingBook = bookRepository.findByAuthor(bookRequest.getAuthor());
//        if (existingBook != null &&
//                bookRequest.getName().equals(existingBook.getName()) &&
//                bookRequest.getISBN().equals(existingBook.getISBN())) {
//            existingBook.setStock(bookRequest.getStock() + existingBook.getStock());
//            return bookRepository.save(existingBook);
//        } else {
//
//            Books newBook = modelMapper.map(bookRequest, Books.class);
//            newBook.setCategories(bookCategories);
//
//            System.out.println("++++++++++++++++++"+newBook);
//            return bookRepository.save(newBook);
//        }

        Optional<BookCategories> optionalBookCategories = bookCategoryRepository.findById(bookRequest.getCategoryId());

        if (optionalBookCategories.isPresent()) {
            System.out.println("Category found with ID: " + bookRequest.getCategoryId());

            Books existingBook = bookRepository.findByAuthorAndNameAndISBN(bookRequest.getAuthor(),bookRequest.getName(),bookRequest.getISBN());

            if (existingBook != null) {
                System.out.println("Updating existing book stock");
                existingBook.setStock(bookRequest.getStock() + existingBook.getStock());
                return bookRepository.save(existingBook);
            } else {
                System.out.println("Creating a new book");
                Books newBook = modelMapper.map(bookRequest, Books.class);
                newBook.setCategories(optionalBookCategories.get());
                Books sb=bookRepository.save(newBook);
                System.out.println("New book created: " + sb);
                return bookRepository.save(sb);
            }
        } else {
            throw new RuntimeException("Category not found with ID: " + bookRequest.getCategoryId());
        }

    }



        public Books getBookById(Integer id) {

        if (id == null) {
//            logger.error("Book ID is null");
            throw new IllegalArgumentException("Book ID cannot be null");
        }

//        logger.info("Fetching book by id: {}", id);

        return bookRepository.findById(id)
                .orElseThrow(() -> {
//                    logger.error("Book not found with id: {}", id);
                    return new ResourceNotFoundException("Book not found with id: " + id);
                });
    }

}
