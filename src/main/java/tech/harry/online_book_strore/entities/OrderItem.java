package tech.harry.online_book_strore.entities;

import jakarta.persistence.*;
import lombok.Data;

import tech.harry.online_book_strore.utils.BaseEntity;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @ManyToOne()
    @JoinColumn(name= "order_id",nullable = false,referencedColumnName = "id")
    private Order order;

    @ManyToOne()
    @JoinColumn(name= "book_id",nullable = false,referencedColumnName = "id")
    private Books book;


}
