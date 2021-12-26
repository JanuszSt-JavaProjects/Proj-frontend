package library.backend.library.exception.copyException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoAvailableCopyException extends ResponseStatusException {
    public NoAvailableCopyException(){
        super(HttpStatus.NOT_FOUND, "No available copy of required book right now!");
    }
}
