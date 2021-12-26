package library.backend.readingRoom.reservation.controller;


import library.backend.readingRoom.reservation.domain.OrderedBook;
import library.backend.readingRoom.reservation.domain.ReservationStatus;
import library.backend.readingRoom.reservation.domain.dto.ReservationDto;
import library.backend.readingRoom.reservation.service.ReservationService;
import library.backend.readingRoom.reservation.domain.Hour;
import library.backend.readingRoom.reservation.domain.Reservation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/reading-room/reservations")
public class Controller {


    ReservationService reservationService;

    public Controller(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ReservationDto create() {

        return ReservationDto.builder()
                .clientId(1)
                .date(LocalDate.now())
                .hour(Set.of(Hour.HOUR_10_11, Hour.HOUR_11_12, Hour.HOUR_13_14))
                .orderedBook(OrderedBook.builder()
                        .author("Jonathan Smith")
                        .title("Any Title")
                        .build())
                .reservationStatus(ReservationStatus.RESERVED)
                .build();

    }

    @GetMapping(value = "/tests")
    public String test() {
        reservationService.save(new Reservation());
        return "Hello Test :-)";
    }

}
