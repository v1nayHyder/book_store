package tech.harry.online_book_strore.Controllers;


import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.harry.online_book_strore.dtos.ApiResponse;
import tech.harry.online_book_strore.entities.Order;
import tech.harry.online_book_strore.services.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}/{cartId}")
    public ResponseEntity<ApiResponse> placeOrder(@PathVariable Integer userId, @PathVariable Integer cartId) {
        ApiResponse apiResponse = new ApiResponse();

        try {
            Order order = orderService.createOrder(userId, cartId);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Order created successfully.");
            apiResponse.setData(order);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An error occurred while creating the order: " + e.getMessage());
            return ResponseEntity.ok(apiResponse);
        }
    }

    //get all orders
    @GetMapping("/allOrders")
    public ResponseEntity<ApiResponse> getAllOrder(){
        ApiResponse apiResponse=new ApiResponse();

        try {
            List<Order> orderList=orderService.findAllOrder();
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("All orders received successfully");
            apiResponse.setData(orderList);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An error occure while retrieving orders. "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

  // get order by order ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse> getOrder(@PathVariable Integer orderId){
        ApiResponse apiResponse=new ApiResponse();
        try {
            Order order=orderService.getOrderById(orderId);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Orders is retrieved successfully");
            apiResponse.setData(order);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An error occured while retrieving order. "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

}
