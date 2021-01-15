package com.pk.hotelmanagement.hotel.room;

import com.pk.hotelmanagement.hotel.Hotel;
import com.pk.hotelmanagement.hotel.room.comment.Comment;
import com.pk.hotelmanagement.hotel.room.photo.Photo;
import com.pk.hotelmanagement.reservations.Reservation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    
    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToMany(mappedBy = "rooms")
    private List<Reservation> reservations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setRoom(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setRoom(null);
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
        photo.setRoom(this);
    }

    public void removePhoto(Photo photo) {
        photos.remove(photo);
        photo.setRoom(null);
    }

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
