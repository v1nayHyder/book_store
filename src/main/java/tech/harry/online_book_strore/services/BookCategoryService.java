package tech.harry.online_book_strore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.harry.online_book_strore.entities.BookCategories;
import tech.harry.online_book_strore.repositories.BookCategoryRepository;

import java.util.List;

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

    public List<BookCategories> getAllCategory() {
        return bookCategoryRepository.findAll();
    }

    public void deleteCategoryById(Integer categoryId) {
        bookCategoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category is not found with ID:"+categoryId));
        bookCategoryRepository.deleteById(categoryId);
    }

    public BookCategories updateCategoryById(Integer categoryId, BookCategories bookCategories) {
       BookCategories existing= bookCategoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category is not found with ID:"+categoryId));
       existing.setDesc(bookCategories.getDesc());
       existing.setName(bookCategories.getName());
       return bookCategoryRepository.save(existing);

    }
}
