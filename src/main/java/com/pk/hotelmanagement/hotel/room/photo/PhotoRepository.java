package com.pk.hotelmanagement.hotel.room.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    @Query(value = "SELECT url FROM photo WHERE room_id = :roomId", nativeQuery = true)
    List<String> findAllByRoomId(@Param("roomId") int roomId);

}
