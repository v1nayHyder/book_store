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


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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

        BookCategories bookCategories = bookCategoryRepository.findById(bookRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + bookRequest.getCategoryId()));

        Books existingBook = bookRepository.findByAuthorAndNameAndISBN(bookRequest.getAuthor(), bookRequest.getName(), bookRequest.getISBN());
        if (existingBook != null) {
            throw new RuntimeException("Book is already present: " + existingBook.getTitle());
        }
        Books book = new Books();
        modelMapper.map(bookRequest, book);
        book.getCreatedAt();
        book.setCategories(bookCategories);
        Books save= bookRepository.save(book);
         return save;

    }

        public Books getBookById(Integer bookId) {
                return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found with Book ID: "+bookId));

        }
        public List<Books> getAllBooks() {
          return bookRepository.findAll();
        }

    public void deleteBooksById(Integer bookId) {
        Books book = getBookById(bookId);
        bookRepository.deleteById(bookId);
    }

    public Books updateBookById(Integer bookId, BookRequest updatedBook) {
        Books book = getBookById(bookId);

        BookCategories bookCategories = bookCategoryRepository.findById(updatedBook.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + updatedBook.getCategoryId()));
        modelMapper.map(updatedBook, book);
        book.setCategories(bookCategories);
        return bookRepository.save(book);

    }

}
