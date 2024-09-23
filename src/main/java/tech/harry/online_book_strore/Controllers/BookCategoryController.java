package tech.harry.online_book_strore.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.harry.online_book_strore.dtos.ApiResponse;
import tech.harry.online_book_strore.entities.BookCategories;
import tech.harry.online_book_strore.services.BookCategoryService;
import tech.harry.online_book_strore.exceptions.BookCategoryAlreadyExistsException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/v1/bookCategory")
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    @Autowired
    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    @PostMapping("/createCategory")
    public ResponseEntity<ApiResponse> createBookCategory(@Valid @RequestBody BookCategories bookCategories) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            BookCategories createdCategory = bookCategoryService.createBookCat(bookCategories);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Book Category created successfully");
            apiResponse.setData(createdCategory.getName());
            return ResponseEntity.ok(apiResponse);
        } catch (BookCategoryAlreadyExistsException e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }
}
