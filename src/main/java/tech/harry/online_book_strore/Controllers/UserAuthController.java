package tech.harry.online_book_strore.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.harry.online_book_strore.dtos.ApiResponse;
import tech.harry.online_book_strore.dtos.UserRegistrationRequest;

@RestController
@RequestMapping(path = "/rest/v1/auth")
public class UserAuthController {


    @PostMapping(path = "/register")
    public ResponseEntity<ApiResponse> register(@Validated @RequestBody UserRegistrationRequest userRegistrationRequest) {
        ApiResponse apiResponse = new ApiResponse();

        try {

            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("User is created successfully");
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("Something went wrong: " + e.getMessage());
            return ResponseEntity.status(500).body(apiResponse);
        }
    }
}
