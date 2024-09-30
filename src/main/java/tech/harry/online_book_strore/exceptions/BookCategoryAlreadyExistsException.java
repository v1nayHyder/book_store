package tech.harry.online_book_strore.exceptions;

public class BookCategoryAlreadyExistsException extends RuntimeException {

    public BookCategoryAlreadyExistsException(String message){

        super(message);
    }
}
