package tech.harry.online_book_strore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.harry.online_book_strore.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);


    User findByEmail(String email);
}
