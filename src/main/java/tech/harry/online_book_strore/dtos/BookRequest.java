package tech.harry.online_book_strore.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;


@Data
public class BookRequest {

    private Integer id;
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title can be at most 255 characters long")
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 255, message = "Author name can be at most 255 characters long")
    private String author;

    @NotBlank(message = "ISBN cannot be blank")
    @JsonProperty("ISBN")
    private String ISBN;

    @Size(max = 1000, message = "Description can be at most 1000 characters long")
    private String name;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Stock cannot be null")
    @Positive(message = "Stock must be greater than zero")
    private Long stock;

    @NotNull(message = "Category ID cannot be null")
    private Integer categoryId;
}
