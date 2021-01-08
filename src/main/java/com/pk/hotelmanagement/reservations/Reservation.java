package com.pk.hotelmanagement.reservations;

import com.pk.hotelmanagement.hotel.room.Room;
import com.pk.hotelmanagement.hotel.room.comment.Comment;
import com.pk.hotelmanagement.hotel.room.photo.Photo;
import com.pk.hotelmanagement.users.vo.Price;
import com.pk.hotelmanagement.users.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int reservationId;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Embedded
    private Price price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
        room.setReservation(this);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
        room.setReservation(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        return reservationId == that.reservationId;
    }

    @Override
    public int hashCode() {
        return reservationId;
    }
}
