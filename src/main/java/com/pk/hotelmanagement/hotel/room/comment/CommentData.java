package com.pk.hotelmanagement.hotel.room.comment;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentData {

    @NotNull
    private int roomId;

    @NotNull
    private String content;

    @NotNull
    private String author;

    @NotNull
    private int rating;
}
