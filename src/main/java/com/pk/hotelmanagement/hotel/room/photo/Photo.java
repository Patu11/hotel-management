package com.pk.hotelmanagement.hotel.room.photo;

import com.pk.hotelmanagement.hotel.room.Room;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int photoId;

    @Column(nullable = false)
    private String url;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] pic;

    @Column
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        return photoId == photo.photoId;
    }

    @Override
    public int hashCode() {
        return photoId;
    }
}
