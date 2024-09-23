package tech.harry.online_book_strore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.harry.online_book_strore.entities.BookCategories;

public interface BookCategoryRepository extends JpaRepository<BookCategories,Integer> {

    BookCategories findByNameIgnoreCase(String name);

}
