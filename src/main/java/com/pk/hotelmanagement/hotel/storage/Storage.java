package com.pk.hotelmanagement.hotel.storage;

import com.pk.hotelmanagement.hotel.Hotel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storageId;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private float capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storage storage = (Storage) o;

        return storageId == storage.storageId;
    }

    @Override
    public int hashCode() {
        return storageId;
    }
}
