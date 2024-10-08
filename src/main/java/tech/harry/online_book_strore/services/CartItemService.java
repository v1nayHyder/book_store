package tech.harry.online_book_strore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.harry.online_book_strore.entities.Books;
import tech.harry.online_book_strore.entities.Cart;
import tech.harry.online_book_strore.entities.CartItem;
import tech.harry.online_book_strore.repositories.BookRepository;
import tech.harry.online_book_strore.repositories.CartItemRepository;
import tech.harry.online_book_strore.repositories.CartRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {

     private  final CartItemRepository cartItemRepository;
     private final CartRepository cartRepository;
     private final BookRepository bookRepository;
     private  final BookService bookService;
    public Cart addItemToCart(Integer cartId, Integer bookId, int quantity) {

        Cart cart = getCartById(cartId);
        Books book = bookService.getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book with ID " + bookId + " not found.");
        }

        CartItem existingCartItem = cartItemRepository.findByBookId(bookId);

        if (existingCartItem != null && book.getPrice().compareTo(existingCartItem.getUnitPrice())==0) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            BigDecimal updatedTotalPrice = book.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity()));
            BigDecimal totalAmountDifference = updatedTotalPrice.subtract(existingCartItem.getTotalPrice());
            existingCartItem.setTotalPrice(updatedTotalPrice);

            cart.setTotalAmount(cart.getTotalAmount().add(totalAmountDifference));
            cartItemRepository.save(existingCartItem);
        }
        else {

            CartItem cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(book.getPrice());
            cartItem.setCart(cart);
            cartItem.setTotalPrice(book.getPrice().multiply(BigDecimal.valueOf(quantity)));
            cartItemRepository.save(cartItem);

            cart.addItem(cartItem);
        }
        return cartRepository.save(cart);

    }
    public List<CartItem> getCartItems(Integer userId, Integer cartId) {
        return cartItemRepository.findCartItemsByCartId(cartId);
    }

    public Cart getCartById(Integer cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public void deleteCartItemById(Integer cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart Item is not found with Cart Item ID: " + cartItemId));
        cartItemRepository.delete(cartItem);
    }

    public CartItem updateCartItemById(Integer cartItemId) {
        CartItem existingCartItem=cartItemRepository.findById(cartItemId).orElseThrow(()->new RuntimeException("Cart Item is not found with Cart Item ID: " + cartItemId));
        return  cartItemRepository.save(existingCartItem);
    }

    public List<CartItem> getAllCartItem() {
        return cartItemRepository.findAll();
    }
}
