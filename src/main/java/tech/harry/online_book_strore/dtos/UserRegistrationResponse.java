package tech.harry.online_book_strore.dtos;

import lombok.Data;

@Data
public class UserRegistrationResponse {

    private String email;
    private String username;
    private String role;

}
