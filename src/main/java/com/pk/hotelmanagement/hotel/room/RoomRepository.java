package com.pk.hotelmanagement.hotel.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(value = "SELECT room_id AS roomId, num_of_people AS numberOfPeople, price, description FROM room WHERE room_id NOT IN(SELECT room_id FROM reservations JOIN room_reservation ON reservations.reservation_id = room_reservation.reservation_id WHERE end_date >= :sDate AND start_date <= :eDate) AND hotel_id = :hotelId", nativeQuery = true)
    List<RoomDTO> getAvailableRooms(@Param("hotelId") int hotelId, @Param("sDate") Date startDate, @Param("eDate") Date endDate);
}
