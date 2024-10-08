package tech.harry.online_book_strore.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.harry.online_book_strore.dtos.ApiResponse;
import tech.harry.online_book_strore.dtos.BookRequest;
import tech.harry.online_book_strore.entities.Books;
import tech.harry.online_book_strore.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    //Create new book
    @PostMapping("/createBook")
    public ResponseEntity<ApiResponse> createBook(@Validated @RequestBody BookRequest bookRequest){
        ApiResponse apiResponse=new ApiResponse();
        try {
                Books bookCreated=bookService.bookCreated(bookRequest);
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                apiResponse.setMessage("New book created successfully");
                apiResponse.setData(bookCreated);
                return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("Error occurred while creating Book: {}" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }

    }
    // Get all the books
    @GetMapping("/allBooks")
    public ResponseEntity<ApiResponse> getAllBooks() {
        ApiResponse apiResponse = new ApiResponse();
        try {

            List<Books> books = bookService.getAllBooks();

            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("All books are retrieved successfully");
            apiResponse.setData(books);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An error occurred while retrieving books");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    //get book By bookId
    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse> getBooksById(@PathVariable Integer bookId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Books books = bookService.getBookById(bookId);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Book is retrieved successfully");
            apiResponse.setData(books);

            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {

            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An error occurred while retrieving books");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    //
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            bookService.deleteBooksById(id);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Book deleted successfully");
            return ResponseEntity.ok(apiResponse);
        } catch (RuntimeException e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }
    // update book Details;
    @PutMapping("/updateBook/{bookId}")
    public ResponseEntity<ApiResponse> updateBookById(@Validated
            @PathVariable Integer bookId,
            @RequestBody BookRequest updatedBook) {
        ApiResponse apiResponse = new ApiResponse();
        try {

            Books book = bookService.updateBookById(bookId, updatedBook);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("Book updated successfully");
            apiResponse.setData(book);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

}
