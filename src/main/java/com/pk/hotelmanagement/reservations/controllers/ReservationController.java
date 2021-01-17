package com.pk.hotelmanagement.reservations.controllers;

import com.pk.hotelmanagement.reservations.ReservationDTO;
import com.pk.hotelmanagement.reservations.ReservationData;
import com.pk.hotelmanagement.reservations.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable int reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<?> getByEmail() {
        List<ReservationDTO> res = reservationService.getAllByEmail();
        return ResponseEntity.ok(res);
    }


}
