package com.pk.hotelmanagement.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<?> createHotel(@RequestBody HotelData hotelData) {
        hotelService.createHotel(hotelData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getHotelById(@PathVariable int hotelId) {
        HotelDTO hotel = hotelService.getHotelDTO(hotelId);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping
    public ResponseEntity<?> getAllHotels() {
        List<HotelDTO> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }
}
