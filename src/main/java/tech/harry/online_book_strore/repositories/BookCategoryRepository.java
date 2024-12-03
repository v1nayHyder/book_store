package tech.harry.online_book_strore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.harry.online_book_strore.entities.BookCategories;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategories,Integer> {

    BookCategories findByNameIgnoreCase(String name);



}
