package com.pk.hotelmanagement.reservations;

import com.pk.hotelmanagement.users.vo.Email;
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

    @Query(value = "SELECT room_id AS roomId, start_date AS startDate, end_date AS endDate FROM reservations JOIN room_reservation ON reservations.reservation_id = room_reservation.reservation_id WHERE email = :email", nativeQuery = true)
    List<ReservationDTO> getAllReservationsByEmail(@Param("email") String email);

    @Query(value = "SELECT room_id AS roomId, start_date AS startDate, end_date AS endDate, email FROM reservations JOIN room_reservation ON reservations.reservation_id = room_reservation.reservation_id", nativeQuery = true)
    List<ReservationDTO> getAll();
}
