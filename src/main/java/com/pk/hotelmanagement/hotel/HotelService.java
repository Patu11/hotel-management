package com.pk.hotelmanagement.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void createHotel(HotelData hotelData) {
        Hotel hotel = new Hotel();
        hotel.setAddress(hotelData.getAddress());
        hotel.setNumberOfRooms(hotelData.getNumberOfRooms());
        hotel.setNumberOfStars(hotelData.getNumberOfStars());
        hotelRepository.save(hotel);
    }

    public Hotel getHotel(int hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isEmpty()) {
            throw new NotFoundException("Hotel not found");
        }
        return hotel.get();
    }

    public void updateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

}
