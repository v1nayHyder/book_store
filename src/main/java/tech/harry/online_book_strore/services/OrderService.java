package tech.harry.online_book_strore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.harry.online_book_strore.entities.*;
import tech.harry.online_book_strore.enums.OrderStatus;
import tech.harry.online_book_strore.repositories.*;
import tech.harry.online_book_strore.utils.EmailUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final EmailService emailService;

    public Order createOrder(Integer userId, Integer cartId) {

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found."));
        if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot create an order.");
        }
        Order order = new Order();
        order.setUserId(userId);
        order.setCartId(cartId);
        order.setTotalPrice(cart.getTotalAmount());
        order.setOrderStatus(OrderStatus.PROCESSING);

        List<OrderItem> orderItems = new ArrayList<>();

        List<CartItem> itemsToRemove = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {
            Books book = bookRepository.findById(cartItem.getBook().getId())
                    .orElseThrow(() -> new RuntimeException("Book not found: " + cartItem.getBook().getTitle()));

            if (book.getStock() >= cartItem.getQuantity()) {
                Long updatedStock = book.getStock() - cartItem.getQuantity();
                book.setStock(updatedStock);
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setBook(book);
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(cartItem.getBook().getPrice());
//                order.getOrderItems().add(orderItem);
                orderItems.add(orderItem);

                bookRepository.save(book);
                itemsToRemove.add(cartItem);
            } else {
                throw new RuntimeException("Insufficient stock for book: " + book.getTitle());
            }
        }
        order.setOrderItems(new HashSet<>(orderItems));
        Order savedOrder = orderRepository.save(order);

        // Sending confirmation email
        String subject = "Order Confirmation";
        String body = EmailUtil.createOrderConfirmationEmail(savedOrder);
        emailService.sendOrderConfirmationEmail("vinayprakashyadav32@gmail.com", subject, body);

        for (OrderItem orderItem : orderItems) {
            orderItemRepository.save(orderItem);
        }

        for (CartItem cartItem : itemsToRemove) {
            cart.getCartItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        }

        cart.setTotalAmount(BigDecimal.ZERO);
        cartRepository.save(cart);
        return savedOrder;
    }

    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order is not found with order ID: "+orderId));
    }
}





