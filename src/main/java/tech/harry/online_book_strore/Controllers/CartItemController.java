package tech.harry.online_book_strore.Controllers;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.harry.online_book_strore.dtos.ApiResponse;
import tech.harry.online_book_strore.entities.Cart;
import tech.harry.online_book_strore.entities.CartItem;
import tech.harry.online_book_strore.repositories.CartItemRepository;
import tech.harry.online_book_strore.services.CartItemService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/api/cart")
@RequiredArgsConstructor
@Slf4j
public class CartItemController {

    private final CartItemService cartItemService;
    private final CartItemRepository cartItemRepository;

    @PostMapping("/add/{cartId}/items")
    public ResponseEntity<ApiResponse> addItemToCart(
            @PathVariable Integer cartId,
            @RequestParam Integer bookId,
            @RequestParam int quantity) {

        ApiResponse apiResponse = new ApiResponse();
        try {
            Cart cart = cartItemService.addItemToCart(cartId, bookId, quantity);

//        logger.info("Successfully added item with ID {} to cart", bookId);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Item is added to cart successfully");
            apiResponse.setData(cart);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
//          logger.error("Cart creation failed: {}", e.getMessage());
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("Failed to create cart: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);

        }

    }
    @GetMapping("/{userId}/carts/{cartId}/items")
    public ResponseEntity<?> getCartItems(@PathVariable Integer userId, @PathVariable Integer cartId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            List<CartItem> cartItemList = cartItemService.getCartItems(userId, cartId);

            if (!cartItemList.isEmpty()) {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                apiResponse.setMessage("Cart items retrieved successfully.");
                apiResponse.setData(cartItemList);
                return ResponseEntity.ok(apiResponse);
            }
            else {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                apiResponse.setMessage("No items found in the cart with cart ID:."+cartId);
                apiResponse.setData(Collections.emptyList());
                return ResponseEntity.ok(apiResponse);
            }
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    //Delete CartItem by cart items id
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemById(@PathVariable Integer cartItemId) {
        ApiResponse apiResponse = new ApiResponse();

        try {
            cartItemService.deleteCartItemById(cartItemId);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Cart Item is deleted with Cart Item ID: " + cartItemId);
            return ResponseEntity.ok(apiResponse);
        } catch (RuntimeException e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An error occurred while deleting cart Item ID: " + cartItemId + ". " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }
    // update Cart Items by cart id
        @PutMapping("/{cartItemId}")
        public ResponseEntity<ApiResponse> updateCartItemById(@PathVariable Integer cartItemId) {
            ApiResponse apiResponse = new ApiResponse();

            try {
                CartItem savedItem=cartItemService.updateCartItemById(cartItemId);
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                apiResponse.setMessage("Cart Item is updated with Cart Item ID: " + cartItemId);
                apiResponse.setData(savedItem);
                return ResponseEntity.ok(apiResponse);
            } catch (RuntimeException e) {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                apiResponse.setMessage("An error occurred while update cart Item ID: " + cartItemId + ". " + e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
            } catch (Exception e) {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                apiResponse.setMessage("An unexpected error occurred: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
            }
        }

    // get all Cart items from cart Items list
    @GetMapping("/allCartItem")
    public ResponseEntity<ApiResponse> getAllCartItem() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            List<CartItem> cartItemList = cartItemService.getAllCartItem();
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("All cart items retrieved successfully");
            apiResponse.setData(cartItemList);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("Failed to retrieve cart items: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }


}