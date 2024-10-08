package tech.harry.online_book_strore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.harry.online_book_strore.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
