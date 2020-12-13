package com.pk.hotelmanagement.reservations.controllers;

import com.pk.hotelmanagement.reservations.ReservationData;
import com.pk.hotelmanagement.reservations.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationData reservationData) {
        reservationService.createReservation(reservationData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}