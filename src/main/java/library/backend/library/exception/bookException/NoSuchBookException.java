package library.backend.library.exception.bookException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchBookException extends ResponseStatusException {
    public NoSuchBookException() {
        super(HttpStatus.NOT_FOUND, "There is no book with given ID!");
    }
}
