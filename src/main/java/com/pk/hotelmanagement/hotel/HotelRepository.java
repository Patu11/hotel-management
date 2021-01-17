package com.pk.hotelmanagement.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    @Query(value = "SELECT hotel_id AS hotelId, address, num_of_rooms AS numberOfRooms, num_of_stars AS numberOfStars FROM hotel WHERE hotel_id = :hotelId", nativeQuery = true)
    Optional<HotelDTO> findDTOById(@Param("hotelId") int hotelId);

    @Query(value = "SELECT hotel_id AS hotelId, address, num_of_rooms AS numberOfRooms, num_of_stars AS numberOfStars FROM hotel", nativeQuery = true)
    List<HotelDTO> finAllHotels();
}
