package com.pk.hotelmanagement.admin.controllers;

import com.pk.hotelmanagement.reservations.ReservationDTO;
import com.pk.hotelmanagement.reservations.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ReservationManagementController {

    private ReservationService reservationService;

    @Autowired
    public ReservationManagementController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<?> getByAlll() {
        List<ReservationDTO> res = reservationService.getAll();
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("reservations/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable int reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
