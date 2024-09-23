package tech.harry.online_book_strore.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_title", nullable = false)
    private String title;

    @Column(name = "book_author", nullable = false)
    private String author;

    @Column(name = "isbn_number", unique = true, nullable = false)
    private String ISBN;

    @Column(name = "book_description", columnDefinition = "TEXT")
    private String desc;

    @Column(name = "book_price", nullable = false)
    private double price;

    @Column(name = "total_stock", nullable = false)
    private long stock;

   @ManyToMany
   @JoinColumn(name = "category_id",referencedColumnName = "id")
    private List< BookCategories> categories;
}
