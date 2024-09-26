package tech.harry.online_book_strore.entities;

import jakarta.persistence.*;
import lombok.Data;
import tech.harry.online_book_strore.utils.BaseEntity;

import java.util.List;

@Entity
@Data
@Table(name = "books")
public class Books extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_title", nullable = false)
    private String title;

    @Column(name = "book_author", nullable = false)
    private String author;

    @Column(name = "isbn_number", nullable = false)
    private String ISBN;

    @Column(name = "book_name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "book_price", nullable = false)
    private double price;

    @Column(name = "total_stock", nullable = false)
    private long stock;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private BookCategories bookCategory;

//   @ManyToMany
//   @JoinTable(name = "books_category",
//             joinColumns = @JoinColumn(name = "book_id"),
//
//           inverseJoinColumns = @JoinColumn(name = "category_id")
//   )
//    private List< BookCategories> categories;
}
