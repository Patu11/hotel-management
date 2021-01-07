package com.pk.hotelmanagement.hotel.storage.product;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ProductData {

    @NotNull
    private int storageId;

    @NotNull
    private String name;

    @NotNull
    private int amount;

    @NotNull
    private Date expirationDate;
}
