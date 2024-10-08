package tech.harry.online_book_strore.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
//@Data
@Table(name="carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="total_amount")
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CartItem> cartItems = new ArrayList<>();

        public void addItem(CartItem item) {
            if (item != null) {

            cartItems.add(item);
            item.setCart(this);
            totalAmount=totalAmount.add(item.getTotalPrice());
        }
    }

    public void removeItem(CartItem item) {
        if (item != null && cartItems.contains(item)) {
            cartItems.remove(item);
            item.setCart(null);
//            calculateTotalPrice();
        }
    }


}
