package tech.harry.online_book_strore.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message){

        super(message);
    }
}
