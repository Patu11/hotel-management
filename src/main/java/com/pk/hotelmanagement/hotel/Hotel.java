package com.pk.hotelmanagement.hotel;

import com.pk.hotelmanagement.hotel.room.Room;
import com.pk.hotelmanagement.hotel.storage.Storage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name = "num_of_rooms")
    private int numberOfRooms;

    @Column(name = "num_of_stars")
    private int numberOfStars;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Storage> storages = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
        room.setHotel(this);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
        room.setHotel(null);
    }

    public void addStorage(Storage storage) {
        storages.add(storage);
        storage.setHotel(this);
    }

    public void removeStorage(Storage storage) {
        storages.remove(storage);
        storage.setHotel(null);
    }
}
