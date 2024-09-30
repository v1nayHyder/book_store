package tech.harry.online_book_strore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.harry.online_book_strore.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
}
