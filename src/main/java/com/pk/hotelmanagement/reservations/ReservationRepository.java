package com.pk.hotelmanagement.reservations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
