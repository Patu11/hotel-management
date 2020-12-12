package com.pk.hotelmanagement.users.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Price {
    private final double price;
    private Price() {
        this.price = 0;
    }

    public Price(double price) {
        if(price < 0) {
            throw new IllegalArgumentException("Price can not be less than 0");
        }
        this.price = price;
    }
}
