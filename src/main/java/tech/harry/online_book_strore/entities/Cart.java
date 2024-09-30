package tech.harry.online_book_strore.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@Table(name="carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="total_amount")
    private BigDecimal totalAmount=BigDecimal.ZERO;


    @OneToMany(mappedBy = "carts",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CartItem> cartItems;

}
