package com.pk.hotelmanagement.reservations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT SUM(r.price) FROM Room r WHERE r.roomId IN (:roomsId)")
    double getRoomsPriceById(@Param("roomsId") List<Integer> roomsId);

    @Query(value = "SELECT COUNT(*) > 0 FROM room_reservation WHERE room_id IN :roomId AND reservation_id IN (SELECT reservation_id FROM reservations WHERE end_date >= :sDate AND start_date <= :eDate)", nativeQuery = true)
    int chechIfDateIntersets(@Param("sDate") Date startDate, @Param("eDate") Date endDate, @Param("roomId") List<Integer> roomId);
}
