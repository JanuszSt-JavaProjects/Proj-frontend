package library.backend.readingRoom.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotAvailableHourException extends ResponseStatusException {
    public NotAvailableHourException() {
        super(HttpStatus.CONFLICT, "This hour is not available to reserve!");
    }
}
