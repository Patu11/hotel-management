package com.pk.hotelmanagement.users.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Getter
@Embeddable
public class Address {
    @Column(nullable = false)
    private final String address;
    @Transient
    private String houseNr;
    @Transient
    private String street;
    @Transient
    private String town;
    @Transient
    private PostCode postCode;

    public Address(String allInOne) {
        if (allInOne == null || allInOne.isBlank()) {
            throw new IllegalArgumentException("Given string is null or empty");
        }
        verify(allInOne);
        this.address = allInOne;
    }

    private Address() {
        this.address = null;
        this.houseNr = null;
        this.street = null;
        this.town = null;
        this.postCode = null;
    }

    private void verify(String address) {
        String[] split = address.split(";");
        if (split.length != 4) {
            throw new IllegalArgumentException("Wrong address structure");
        }
        checkHouseNr(split[0]);
        checkStreet(split[1]);
        checkTown(split[2]);
        checkPostCode(split[3]);
    }

    private void checkTown(String town) {
        if (town == null || town.isBlank()) {
            throw new IllegalArgumentException("Town can not be null or empty");
        }
        this.town = town;
    }

    private void checkHouseNr(String houseNr) {
        try {
            Integer.parseInt(houseNr);
            this.houseNr = houseNr;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Wrong house number");
        }
    }

    private void checkStreet(String street) {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Wrong street");
        }
        this.street = street;
    }

    private void checkPostCode(String postCode) {
        this.postCode = new PostCode(postCode);
    }
}
