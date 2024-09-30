package tech.harry.online_book_strore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.harry.online_book_strore.entities.Books;
import tech.harry.online_book_strore.entities.CartItem;
import tech.harry.online_book_strore.repositories.CardRepository;
import tech.harry.online_book_strore.repositories.CartItemRepository;

@Service
@RequiredArgsConstructor
public class CartItemService {

     private  final CartItemRepository cartItemRepository;
     private final CardRepository cardRepository;

    public CartItem saveItemToCart(Books books, Integer quantity) {
        CartItem cartItem=new CartItem();
        if (quantity <= 0) {
            throw new RuntimeException(String.format("Invalid quantity for Book ID %d: Quantity must be greater than zero.", books));
        }
        else if (books.getStock()>=quantity) {
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(books.getPrice());
            cartItem.setBooks(books);

        }
           return cartItemRepository.save(cartItem);

    }
}
