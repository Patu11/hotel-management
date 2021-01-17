package com.pk.hotelmanagement.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Transactional
    public void createHotel(HotelData hotelData) {
        Hotel hotel = new Hotel();
        hotel.setAddress(hotelData.getAddress());
        hotel.setNumberOfRooms(hotelData.getNumberOfRooms());
        hotel.setNumberOfStars(hotelData.getNumberOfStars());
        hotelRepository.save(hotel);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Hotel getHotel(int hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isEmpty()) {
            throw new NotFoundException("Hotel not found");
        }
        return hotel.get();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public HotelDTO getHotelDTO(int hotelId) {
        Optional<HotelDTO> hotel = hotelRepository.findDTOById(hotelId);
        if (hotel.isEmpty()) {
            throw new NotFoundException("Hotel not found");
        }
        return hotel.get();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<HotelDTO> getAllHotels() {
        List<HotelDTO> hotels = hotelRepository.finAllHotels();
        if (hotels.isEmpty()) {
            throw new NotFoundException("Hotels not found");
        }
        return hotels;
    }

}
