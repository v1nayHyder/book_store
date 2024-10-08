package tech.harry.online_book_strore.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import tech.harry.online_book_strore.enums.OrderStatus;
import tech.harry.online_book_strore.utils.BaseEntity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "cart_id")
    private Integer cartId;



    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true,fetch =FetchType.LAZY)
    @JsonManagedReference
    private Set<OrderItem> orderItems=new HashSet<>();


}
