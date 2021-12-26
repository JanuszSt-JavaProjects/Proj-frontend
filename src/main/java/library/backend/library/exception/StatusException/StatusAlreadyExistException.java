package library.backend.library.exception.StatusException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StatusAlreadyExistException extends ResponseStatusException {
    public StatusAlreadyExistException() {
        super(HttpStatus.CONFLICT, "Status already exists!");
    }
}
