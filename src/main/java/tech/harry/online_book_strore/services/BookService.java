package tech.harry.online_book_strore.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.harry.online_book_strore.dtos.BookRequest;
import tech.harry.online_book_strore.entities.BookCategories;
import tech.harry.online_book_strore.entities.Books;
import tech.harry.online_book_strore.repositories.BookCategoryRepository;
import tech.harry.online_book_strore.repositories.BookRepository;

import java.util.Optional;

@Service
public class BookService    {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;
    public Books bookCreated(BookRequest bookRequest) {
        Optional<BookCategories> bookCategories = bookCategoryRepository.findById(bookRequest.getCategoryId());
        if (bookCategories.isEmpty()) {
            throw new RuntimeException("Category not found for Category ID: " + bookRequest.getCategoryId());
        }
        
        Books existingBook = bookRepository.findByAuthor(bookRequest.getAuthor());
        if (existingBook != null &&
                bookRequest.getName().equals(existingBook.getName()) &&
                bookRequest.getISBN().equals(existingBook.getISBN())) {
            existingBook.setStock(bookRequest.getStock() + existingBook.getStock());
            return bookRepository.save(existingBook);
        } else {
            Books newBook = modelMapper.map(bookRequest, Books.class);
            return bookRepository.save(newBook);
        }
    }

}
