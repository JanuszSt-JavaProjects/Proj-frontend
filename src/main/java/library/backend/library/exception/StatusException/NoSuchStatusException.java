package library.backend.library.exception.StatusException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchStatusException extends ResponseStatusException {
    public NoSuchStatusException() {
        super(HttpStatus.NOT_FOUND, "WRONG STATUS!");
    }
}
