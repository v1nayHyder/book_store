package tech.harry.online_book_strore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.harry.online_book_strore.entities.Cart;

public interface CardRepository extends JpaRepository<Cart,Integer> {

}
