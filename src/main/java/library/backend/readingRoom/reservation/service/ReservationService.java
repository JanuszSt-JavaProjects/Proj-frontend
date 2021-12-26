package library.backend.readingRoom.reservation.service;


import library.backend.library.service.ClientService;
import library.backend.readingRoom.reservation.domain.Reservation;
import library.backend.readingRoom.reservation.exception.NoRequiredInformation;
import library.backend.readingRoom.reservation.exception.NoSuchReservationException;
import library.backend.readingRoom.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservationService {
    ReservationRepository reservationRepo;
    ClientService clientService;

    public ReservationService(ReservationRepository reservationRepo,
                              ClientService clientService) {
        this.reservationRepo = reservationRepo;
        this.clientService = clientService;
    }

    public Reservation save(Reservation reservation) {
        Optional.ofNullable(reservation).orElseThrow(NoRequiredInformation::new);
        return reservationRepo.save(reservation);
    }

    public void remove(long id) {
        reservationRepo.findById(id).orElseThrow(NoSuchReservationException::new);
        reservationRepo.deleteById(id);
    }

    public Reservation update(Reservation reservation) {
        reservationRepo.findById(reservation.getId()).orElseThrow(NoSuchReservationException::new);
        return reservationRepo.save(reservation);
    }

    public Reservation getOne(long id) {
        return reservationRepo.findById(id).orElseThrow(NoSuchReservationException::new);
    }

    public Iterable<Reservation> getAll() {
        return reservationRepo.findAll();
    }

    public Iterable<Reservation> getAllForClient(long clientId) {
        clientService.getOne(clientId);
        return reservationRepo.findAllByClientId(clientId);
    }

    public Iterable<Reservation> getAllForDay(LocalDate date) {
        return reservationRepo.findAllByDate(date);
    }
}
