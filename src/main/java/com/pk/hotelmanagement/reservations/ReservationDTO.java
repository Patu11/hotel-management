package com.pk.hotelmanagement.reservations;

import java.util.Date;

public interface ReservationDTO {

    int getRoomId();

    Date getStartDate();

    Date getEndDate();

    String getEmail();
}
