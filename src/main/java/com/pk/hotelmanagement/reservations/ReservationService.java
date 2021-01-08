package com.pk.hotelmanagement.reservations;

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

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRegistrationService userService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
    }

    @Transactional
    public void createReservation(ReservationData reservationData) {
        Reservation reservation = new Reservation();
        reservation.setStartDate(reservationData.getInterval().getStartDate());
        reservation.setEndDate(reservationData.getInterval().getEndDate());
        reservation.setPrice(new Price(reservationRepository.getRoomsPriceById(reservationData.getRoomIds()))); // TODO: SET BASED ON LIST OF RESERVED ROOMS
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
