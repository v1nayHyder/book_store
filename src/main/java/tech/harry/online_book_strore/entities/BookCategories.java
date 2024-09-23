package tech.harry.online_book_strore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "book_categories")
public class BookCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    @Column(name = "description", columnDefinition = "TEXT")
    private String desc;
}