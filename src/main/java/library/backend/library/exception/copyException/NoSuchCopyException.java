package library.backend.library.exception.copyException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchCopyException extends ResponseStatusException {
    public NoSuchCopyException() {
        super(HttpStatus.NOT_FOUND, "No copy with given ID was found!");
    }
}
