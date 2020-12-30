package com.pk.hotelmanagement.hotel.room.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    private PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping
    public ResponseEntity<?> addPhoto(@RequestParam("file") MultipartFile file, @RequestParam("id") String roomId) throws IOException {
        photoService.addPhoto(file, roomId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

