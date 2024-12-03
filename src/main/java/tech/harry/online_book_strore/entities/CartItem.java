package tech.harry.online_book_strore.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name="cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name="book_id")
    private Books books;


    @Column(name="quantity")
    private Integer quantity;

    @Column(name="unit_price")
    private BigDecimal unitPrice;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart carts;


}
