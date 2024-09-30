package tech.harry.online_book_strore.Controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.harry.online_book_strore.dtos.ApiResponse;
import tech.harry.online_book_strore.entities.Books;
import tech.harry.online_book_strore.entities.CartItem;
import tech.harry.online_book_strore.services.BookService;
import tech.harry.online_book_strore.services.CartItemService;



@RestController
@RequestMapping("/rest/api/cart")
@RequiredArgsConstructor
public class CartController {
    private static final Logger logger= LoggerFactory.getLogger(CartController.class);

    private final CartItemService itemService;
    private final BookService bookService;
    @PostMapping("/addItemCart")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Integer bookId,@RequestParam Integer quantity,@RequestParam Integer userId){
//        logger.info("Fetching book with ID {}", bookId);

        ApiResponse apiResponse=new ApiResponse();
        Books book=bookService.getBookById(bookId);
        CartItem cartItem=itemService.saveItemToCart(book,quantity);
//        logger.info("Successfully added item with ID {} to cart", bookId);

        apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
        apiResponse.setMessage("Item is added to cart successfully");
        apiResponse.setData(cartItem);
        return ResponseEntity.ok(apiResponse);

    }
}
