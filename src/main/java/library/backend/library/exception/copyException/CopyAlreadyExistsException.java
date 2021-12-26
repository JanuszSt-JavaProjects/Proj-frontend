package library.backend.library.exception.copyException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CopyAlreadyExistsException extends ResponseStatusException {
    public CopyAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Customer already exists!");
    }
}
