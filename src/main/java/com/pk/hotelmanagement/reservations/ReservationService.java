package com.pk.hotelmanagement.reservations;

import com.pk.hotelmanagement.hotel.room.RoomService;
import com.pk.hotelmanagement.security.SecurityConfig;
import com.pk.hotelmanagement.users.User;
import com.pk.hotelmanagement.users.registration.UserRegistrationService;
import com.pk.hotelmanagement.users.vo.Email;
import com.pk.hotelmanagement.users.vo.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRegistrationService userService;
    private final RoomService roomService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRegistrationService userService, RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.roomService = roomService;
    }

    @Transactional
    public void createReservation(ReservationData reservationData) {
        if (reservationRepository.chechIfDateIntersets(reservationData.getInterval().getStartDate(), reservationData.getInterval().getEndDate()) == 1) {
            throw new IllegalArgumentException("Selected rooms already reserved");
        }
        Reservation reservation = new Reservation();
        reservation.setStartDate(reservationData.getInterval().getStartDate());
        reservation.setEndDate(reservationData.getInterval().getEndDate());
        reservation.setPrice(new Price(reservationRepository.getRoomsPriceById(reservationData.getRoomIds()))); // TODO: SET BASED ON LIST OF RESERVED ROOMS
        reservation.addRooms(roomService.getRoomsById(reservationData.getRoomIds()));
        Email principalEmail = SecurityConfig.getPrincipal();
        User user = userService.getUserByEmail(principalEmail);
        user.addReservation(reservation);
    }

    @Transactional
    public void deleteReservation(int reservationId) {
        if (reservationRepository.findById(reservationId).isPresent()) {
            reservationRepository.deleteById(reservationId);
        }
    }
}
