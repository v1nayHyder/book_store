package tech.harry.online_book_strore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.harry.online_book_strore.entities.CartItem;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

    @Query(name="SELECT ci FROM cart_item ci WHERE ci.cart_id = :cartId")
    List<CartItem> findCartItemsByCartId(@Param("cartId") Integer cartId);


    CartItem findByBookId(Integer bookId);

//    List<CartItem> getAllCartItem();
}

