package com.pk.hotelmanagement.hotel.room;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomData {

    @NotNull
    private int hotelId;

    @NotNull
    private int numberOfPeople;

    @NotNull
    private double price;

    @NotNull
    private String description;
}
