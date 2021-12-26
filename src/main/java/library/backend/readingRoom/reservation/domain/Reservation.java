package library.backend.readingRoom.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

@Builder
public class Reservation {
    @Id
    @GeneratedValue
    private long Id;

    private long clientId;

    @OneToOne
    private OrderedBook orderedBook;

    private LocalDate date;

    @OneToMany
    @Enumerated(EnumType.STRING)
    private Set<Hour> hour;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
}
