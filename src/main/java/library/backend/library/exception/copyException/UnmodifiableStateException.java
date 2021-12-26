package library.backend.library.exception.copyException;

import library.backend.library.domain.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnmodifiableStateException extends ResponseStatusException {

    public UnmodifiableStateException(Status status) {
        super(HttpStatus.CONFLICT, status + " status of copy can't be changed");
    }
}
