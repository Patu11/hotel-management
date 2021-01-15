package com.pk.hotelmanagement.hotel.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody RoomData roomData) {
        roomService.createRoom(roomData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/available")
    public ResponseEntity<?> getAvailableRooms(@RequestBody AvailableRoomData availableRoomData) {
        List<RoomDTO> rooms = roomService.getAvailableRooms(availableRoomData);
        return ResponseEntity.ok(rooms);
    }
}
