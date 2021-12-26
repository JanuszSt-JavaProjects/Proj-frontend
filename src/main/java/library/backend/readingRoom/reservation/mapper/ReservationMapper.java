package library.backend.readingRoom.reservation.mapper;

import library.backend.readingRoom.reservation.domain.dto.ReservationDto;
import library.backend.readingRoom.reservation.domain.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper {

    public ReservationDto mapReservationToReservationDto(Reservation reservation) {

        return ReservationDto.builder()
                .id(reservation.getId())
                .clientId(reservation.getClientId())
                .orderedBook(reservation.getOrderedBook())
                .hour(reservation.getHour())
                .reservationStatus(reservation.getReservationStatus())
                .build();
    }

    public Reservation mapReservationDtoToReservation(ReservationDto reservationDto) {

        return Reservation.builder()
                .Id(reservationDto.getId())
                .clientId(reservationDto.getClientId())
                .orderedBook(reservationDto.getOrderedBook())
                .hour(reservationDto.getHour())
                .reservationStatus(reservationDto.getReservationStatus())
                .build();
    }
}
