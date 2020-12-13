package com.pk.hotelmanagement.hotel.room;

import com.pk.hotelmanagement.hotel.Hotel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @Column(nullable = false, name = "num_of_people")
    private int numberOfPeople;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private boolean status;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return roomId == room.roomId;
    }

    @Override
    public int hashCode() {
        return roomId;
    }
}
