package tech.harry.online_book_strore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.harry.online_book_strore.entities.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer> {


}
