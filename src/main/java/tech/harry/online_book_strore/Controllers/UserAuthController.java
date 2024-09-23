package tech.harry.online_book_strore.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.harry.online_book_strore.dtos.ApiResponse;
import tech.harry.online_book_strore.dtos.UserRegistrationRequest;
import tech.harry.online_book_strore.dtos.UserRegistrationResponse;
import tech.harry.online_book_strore.exceptions.UserRegistrationException;
import tech.harry.online_book_strore.services.UserService;

@RestController
@RequestMapping(path = "/rest/v1/auth")
public class UserAuthController {

//    private static final Logger logger = LoggerFactory.getLogger(UserAuthController.class);

    @Autowired
    private UserService userService;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @PostMapping(path = "/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        ApiResponse apiResponse = new ApiResponse();
        try {

           UserRegistrationResponse userResponse = userService.registerUser(userRegistrationRequest);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
            apiResponse.setMessage("User created successfully");
//            ObjectMapper objectMapper = new ObjectMapper();
//            String userResponseJson = objectMapper.writeValueAsString(userResponse);
            apiResponse.setData(userResponse);
//            logger.info("User created successfully with email: {}", user.getEmail());

            return ResponseEntity.ok(apiResponse);

        } catch (UserRegistrationException e) {

//            logger.error("User registration failed: {}", e.getMessage());
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("User registration failed: " + e.getMessage());

            return ResponseEntity.badRequest().body(apiResponse);
        } catch (Exception e) {

//            logger.error("An unexpected error occurred during registration: {}", e.getMessage(), e);
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable("userId") Integer userById ){
        ApiResponse apiResponse=new ApiResponse();

        try {
            UserRegistrationResponse userRegistrationResponse=userService.findById(userById);
            if(userRegistrationResponse!=null) {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                apiResponse.setMessage("User retrieve successfully");
                 apiResponse.setData(userRegistrationResponse);
                 return ResponseEntity.ok(apiResponse);
            }
            else {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
                apiResponse.setMessage("User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
            }

        } catch (Exception e) {
             apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
             apiResponse.setMessage("An error occurred: " + e.getMessage());
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }


    }
}
