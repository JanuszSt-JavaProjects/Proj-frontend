package library.backend.library.exception.borrowException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchBorrowException extends ResponseStatusException {
    public NoSuchBorrowException() {
        super(HttpStatus.NOT_FOUND, "There is no Borrow with given Id!");
    }
}
