package tech.harry.online_book_strore.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.harry.online_book_strore.utils.BaseEntity;

import java.math.BigDecimal;

@Entity
//@Data
@Getter
@Setter
@Table(name = "books")
public class Books extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_title", nullable = false)
    private String title;

    @Column(name = "book_author", nullable = false)
    private String author;

    @Column(name = "isbn_number")
    @JsonProperty("ISBN")
    private String ISBN;

    @Column(name = "book_price", nullable = false)
    private BigDecimal price;

    @Column(name = "total_stock", nullable = false)
    private long stock;

    @Column(name = "book_name", columnDefinition = "TEXT")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private  BookCategories categories;
}

