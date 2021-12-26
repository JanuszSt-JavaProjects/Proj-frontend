package library.backend.library.exception.borrowException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BorrowAlreadyExistsException extends ResponseStatusException {
    public BorrowAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Borrow already exists!");
    }
}
