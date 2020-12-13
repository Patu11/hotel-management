package com.pk.hotelmanagement.hotel.room;

import com.pk.hotelmanagement.hotel.Hotel;
import com.pk.hotelmanagement.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private RoomRepository roomRepository;
    private HotelService hotelService;

    @Autowired
    public RoomService(RoomRepository roomRepository, HotelService hotelService) {
        this.roomRepository = roomRepository;
        this.hotelService = hotelService;
    }

    public void createRoom(RoomData roomData) {
        Room room = new Room();
        room.setNumberOfPeople(roomData.getNumberOfPeople());
        room.setPrice(roomData.getPrice());
        room.setDescription(roomData.getDescription());

        Hotel hotel = hotelService.getHotel(roomData.getHotelId());
        hotel.addRoom(room);

        roomRepository.save(room);
    }
}
