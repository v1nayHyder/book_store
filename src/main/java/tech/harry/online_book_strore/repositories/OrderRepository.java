package tech.harry.online_book_strore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.harry.online_book_strore.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
