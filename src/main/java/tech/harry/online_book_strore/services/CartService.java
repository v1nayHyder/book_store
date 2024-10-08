package tech.harry.online_book_strore.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.harry.online_book_strore.entities.Cart;
import tech.harry.online_book_strore.exceptions.DatabaseException;
import tech.harry.online_book_strore.repositories.CartItemRepository;
import tech.harry.online_book_strore.repositories.CartRepository;



@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final BookService bookService;
    private final CartItemRepository cartItemRepository;

    private final Logger logger=LoggerFactory.getLogger(Cart.class);

    public Cart createCart() {
        try {
            Cart newCart = new Cart();
            return cartRepository.save(newCart);
        } catch (DatabaseException e) {
//            logger.error("Database error occurred while creating the cart: {}", e.getMessage());
            throw new DatabaseException("Unable to create cart due to a database error.", e);
        }
    }

    public void removeById(Integer id) {
        cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart is not found with Cart ID: " + id));
        cartRepository.deleteById(id);
    }

    public Cart findCartById(Integer cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart with ID " + cartId + " not found."));
    }

    public void deleteCartById(Integer cartId) {
        cartRepository.findById(cartId).orElseThrow(()->new RuntimeException("Cart is not found with Cart ID:"+cartId));
        cartRepository.deleteById(cartId);
    }
}
