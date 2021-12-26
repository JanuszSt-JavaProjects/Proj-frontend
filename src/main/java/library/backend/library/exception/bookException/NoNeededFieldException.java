package library.backend.library.exception.bookException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoNeededFieldException extends ResponseStatusException {

    public NoNeededFieldException() {
        super(HttpStatus.BAD_REQUEST,"Not all required data has been completed!");
    }
}
