package tech.harry.online_book_strore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.harry.online_book_strore.entities.Order;
import tech.harry.online_book_strore.entities.OrderItem;
import tech.harry.online_book_strore.exceptions.ResourceNotFoundException;
import tech.harry.online_book_strore.repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

   private final OrderRepository orderRepository;

   @Autowired
   public  OrderService(OrderRepository orderRepository){
      this.orderRepository=orderRepository;

   }
   public Order placeOrder(Integer userId){
   return  null;
   }

//   public Order createOrderItmes(Order order,Cart cart){
//
//   }

   public BigDecimal calculateTotalAmount(List<OrderItem> orderItems){

      BigDecimal totalAmount=BigDecimal.ZERO;
      for(OrderItem item:orderItems){
         totalAmount=totalAmount.add(BigDecimal.valueOf(item.getPrice()*item.getQuantity()));
      }
      return totalAmount;
   }

   public Order getOrder(Integer orderId){
     return orderRepository.findById(orderId)
             .orElseThrow(()->new ResourceNotFoundException("Order not Found!."));
   }
}
