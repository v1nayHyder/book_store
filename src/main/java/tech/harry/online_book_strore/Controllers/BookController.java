package tech.harry.online_book_strore.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.harry.online_book_strore.dtos.ApiResponse;
import tech.harry.online_book_strore.dtos.BookRequest;
import tech.harry.online_book_strore.entities.Books;
import tech.harry.online_book_strore.services.BookService;

@RestController
@RequestMapping("/rest/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/createBook")
    public ResponseEntity<ApiResponse> createBook(@Valid @RequestBody BookRequest bookRequest){
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
}
