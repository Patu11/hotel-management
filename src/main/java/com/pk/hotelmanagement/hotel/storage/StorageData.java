package com.pk.hotelmanagement.hotel.storage;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StorageData {
    @NotNull
    private int hotelId;

    @NotNull
    private String address;

    @NotNull
    private float capacity;
}
