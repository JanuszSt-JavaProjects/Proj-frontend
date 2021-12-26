package library.backend.library.exception.clientException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClientAlreadyExistsException extends ResponseStatusException {
    public ClientAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Customer already exists!");
    }
}
