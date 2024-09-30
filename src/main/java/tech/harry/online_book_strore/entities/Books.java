package tech.harry.online_book_strore.entities;

import jakarta.persistence.*;
import lombok.Data;
import tech.harry.online_book_strore.utils.BaseEntity;

import java.math.BigDecimal;

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

    @Column(name = "isbn_number",nullable = false)
    private String ISBN;

    @Column(name = "book_price", nullable = false)
    private BigDecimal price;

    @Column(name = "total_stock", nullable = false)
    private long stock;

    @Column(name = "book_name", columnDefinition = "TEXT")
    private String name;



   @ManyToOne(cascade=CascadeType.ALL)
   @JoinColumn(name = "category_id",referencedColumnName = "id")
    private  BookCategories categories;
}
//    @OneToMany(mappedBy = "books",cascade =CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
//    private List<Image> images;