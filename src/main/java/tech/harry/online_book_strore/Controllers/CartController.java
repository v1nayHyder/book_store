package tech.harry.online_book_strore.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.harry.online_book_strore.dtos.ApiResponse;
import tech.harry.online_book_strore.entities.Cart;
import tech.harry.online_book_strore.exceptions.CartCreationException;
import tech.harry.online_book_strore.services.CartItemService;
import tech.harry.online_book_strore.services.CartService;


@RestController
@RequestMapping("rest/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;
    @PostMapping("/createCart")
    public ResponseEntity<ApiResponse> createCart() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Cart cart = cartService.createCart();
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Cart created successfully");
            apiResponse.setData(cart);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        } catch (CartCreationException e) {
            // Specific error handling for cart creation
//                logger.error("Cart creation failed: {}", e.getMessage());
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("Failed to create cart: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            // General error handling
//                logger.error("Unexpected error occurred while creating cart: ", e);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An unexpected error occurred while creating the cart.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }


    @GetMapping("/{cartId}")
    public ResponseEntity<ApiResponse> getCartById(@PathVariable Integer cartId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Cart existingCart = cartService.findCartById(cartId);
            if (existingCart != null) {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                apiResponse.setMessage("Cart retrieved successfully.");
                apiResponse.setData(existingCart);
                return ResponseEntity.ok(apiResponse);
            } else {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                apiResponse.setMessage("Cart not found with cart ID: " + cartId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
            }
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An unexpected error occurred while fetching the cart: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    // detele cart by Cart ID
    public ResponseEntity<ApiResponse> deleteCartById(@PathVariable Integer cartId){
        ApiResponse apiResponse=new ApiResponse();

        try {
            cartService.deleteCartById(cartId);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Cart is deleted successfull with cart ID:"+cartId);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An error while deleting cart with card ID:"+cartId+". "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }

    }

}



