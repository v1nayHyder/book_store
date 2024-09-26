package tech.harry.online_book_strore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.harry.online_book_strore.entities.Books;

public interface BookRepository extends JpaRepository<Books,Integer> {
    Books findByAuthor(String author);
}
