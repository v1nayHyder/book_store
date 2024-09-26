package tech.harry.online_book_strore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tech.harry.online_book_strore.utils.BaseEntity;

import java.util.List;

@Entity
@Data
@Table(name = "book_categories")
public class BookCategories extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Size(max = 100, message = "Description must not exceed 50 characters")
    @Column(name = "description", columnDefinition = "TEXT")
    private String desc;

//    @ManyToMany(mappedBy = "categories")
//    private List<Books> books;


}