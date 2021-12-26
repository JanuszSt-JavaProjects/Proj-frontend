package library.backend.readingRoom.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchReservationException extends ResponseStatusException {

    public NoSuchReservationException() {
        super(HttpStatus.NOT_FOUND, "There is no reservation with given Id!");
    }
}
