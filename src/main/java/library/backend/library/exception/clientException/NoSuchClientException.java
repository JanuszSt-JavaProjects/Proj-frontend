package library.backend.library.exception.clientException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchClientException extends ResponseStatusException {
    public NoSuchClientException() {
        super(HttpStatus.NOT_FOUND, "There is no client with given ID!");
    }
}
