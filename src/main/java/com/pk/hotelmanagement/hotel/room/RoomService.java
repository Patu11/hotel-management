package com.pk.hotelmanagement.hotel.room;

import com.pk.hotelmanagement.hotel.Hotel;
import com.pk.hotelmanagement.hotel.HotelService;
import com.pk.hotelmanagement.hotel.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private RoomRepository roomRepository;
    private HotelService hotelService;

    @Autowired
    public RoomService(RoomRepository roomRepository, HotelService hotelService) {
        this.roomRepository = roomRepository;
        this.hotelService = hotelService;
    }

    @Transactional
    public void createRoom(RoomData roomData) {
        Room room = new Room();
        room.setNumberOfPeople(roomData.getNumberOfPeople());
        room.setPrice(roomData.getPrice());
        room.setDescription(roomData.getDescription());

        Hotel hotel = hotelService.getHotel(roomData.getHotelId());
        hotel.addRoom(room);

        roomRepository.save(room);
    }

    @Transactional
    public Room getRoom(int roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isEmpty()) {
            throw new NotFoundException("Room not found");
        }
        return room.get();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<RoomDTO> getAvailableRooms(AvailableRoomData availableRoomData) {
        return roomRepository.getAvailableRooms(availableRoomData.getHotelId(), availableRoomData.getInterval().getStartDate(), availableRoomData.getInterval().getEndDate());
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Room> getRoomsById(List<Integer> roomsId) {
        List<Room> rooms = roomRepository.findAllById(roomsId);
        if (rooms.isEmpty()) {
            throw new NotFoundException("Rooms not found");
        }
        return roomRepository.findAllById(roomsId);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<RoomDTO> getRoomsByHotelId(int hotelId) {
        List<RoomDTO> rooms = roomRepository.findAllByHotelId(hotelId);
        if (rooms.isEmpty()) {
            throw new NotFoundException("Rooms not found");
        }
        return rooms;
    }

}
