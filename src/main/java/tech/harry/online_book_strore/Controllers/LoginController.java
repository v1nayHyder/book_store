package tech.harry.online_book_strore.Controllers;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.harry.online_book_strore.dtos.ApiResponse;
import tech.harry.online_book_strore.dtos.UserLoginRequest;
import tech.harry.online_book_strore.services.LoginService;

@RestController
@RequestMapping("/rest/v1/userLogin")
public class LoginController {

    private final LoginService loginService;
//    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    public LoginController(LoginService loginService){
        this.loginService=loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@Valid @RequestBody UserLoginRequest userLoginRequest){
        ApiResponse apiResponse=new ApiResponse();

        try {
            boolean isAuthenticated=loginService.authenticate(userLoginRequest);
            if (isAuthenticated){
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.SUCCESS);
                apiResponse.setMessage("Login successful :"+isAuthenticated);
//                logger.info("User logged in successfully: {}", userLoginRequest.getEmail());

                return ResponseEntity.ok(apiResponse);
            }
            else {
                apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.UNAUTHORIZED);
                apiResponse.setMessage("Invalid email or password :"+isAuthenticated);
//                logger.warn("Login attempt failed for user: {}", userLoginRequest.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
            }

        } catch (Exception e) {
            apiResponse.setStatus(ApiResponse.ResponseStatusTypeEnum.FAIL);
            apiResponse.setMessage("An error occurred: " + e.getMessage());
//            logger.error("Unexpected error: {}", e.getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);        }


    }
}
