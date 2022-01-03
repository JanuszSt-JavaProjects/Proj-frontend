package library.view.nationalLibrary;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
public class ReservationDto {
    private long id;
    private int clientId;
    private OrderedBook orderedBook;
    private LocalDate date;
    private Set<Hour> hour;
    private ReservationStatus  reservationStatus;
}
