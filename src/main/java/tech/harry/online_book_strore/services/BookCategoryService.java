package tech.harry.online_book_strore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.harry.online_book_strore.entities.BookCategories;
import tech.harry.online_book_strore.repositories.BookCategoryRepository;

@Service
public class BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    @Autowired
    public BookCategoryService(BookCategoryRepository bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    public BookCategories createBookCat(BookCategories bookCategories) {
        String formattedName = bookCategories.getName().toLowerCase();

        BookCategories existingCategory = bookCategoryRepository.findByNameIgnoreCase(formattedName);
        if (existingCategory != null) {
            throw new RuntimeException("Book Category already exists: " + existingCategory.getName());
        }
        return bookCategoryRepository.save(bookCategories);
    }
}
