package com.pk.hotelmanagement.hotel.room.photo;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class PhotoData {

    @NotNull
    private int roomId;

    @NotNull
    private MultipartFile pic;
}
