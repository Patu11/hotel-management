package com.pk.hotelmanagement.reservations;

import com.pk.hotelmanagement.hotel.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT SUM(r.price) FROM Room r WHERE r.roomId IN (:roomsId)")
    double getRoomsPriceById(@Param("roomsId") List<Integer> roomsId);
}
