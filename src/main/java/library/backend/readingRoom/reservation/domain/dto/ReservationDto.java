package library.backend.readingRoom.reservation.domain.dto;

import library.backend.readingRoom.reservation.domain.OrderedBook;
import library.backend.readingRoom.reservation.domain.Hour;
import library.backend.readingRoom.reservation.domain.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
public class ReservationDto {
    private long id;
    private long clientId;
    private OrderedBook orderedBook;
    private LocalDate date;
    private Set<Hour> hour;
    private ReservationStatus reservationStatus;
}
