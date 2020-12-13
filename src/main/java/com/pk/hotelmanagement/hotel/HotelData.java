package com.pk.hotelmanagement.hotel;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HotelData {

    @NotNull
    private String address;

    @NotNull
    private int numberOfRooms;

    @NotNull
    private int numberOfStars;
}
