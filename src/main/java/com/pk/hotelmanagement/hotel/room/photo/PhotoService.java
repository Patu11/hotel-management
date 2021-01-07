package com.pk.hotelmanagement.hotel.room.photo;

import com.cloudinary.Cloudinary;
import com.pk.hotelmanagement.hotel.room.Room;
import com.pk.hotelmanagement.hotel.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class PhotoService {

    private PhotoRepository photoRepository;
    private RoomService roomService;
    Cloudinary cloudinary;

    @Autowired
    public PhotoService(PhotoRepository photoRepository, RoomService roomService) {
        this.photoRepository = photoRepository;
        this.roomService = roomService;
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", System.getenv("CLOUD_NAME"));
        config.put("api_key", System.getenv("API_KEY"));
        config.put("api_secret", System.getenv("API_SECRET"));
        cloudinary = new Cloudinary(config);
    }

    public void addPhoto(MultipartFile file, String roomId) throws IOException {
        Photo photo = new Photo();

        byte[] image = file.getBytes();
        photo.setUrl(createUrl(image));
        photo.setPic(image);
        photo.setCreationDate(new Date());

        Room room = roomService.getRoom(Integer.parseInt(roomId));
        room.addPhoto(photo);

        photoRepository.save(photo);
    }

    private String createUrl(byte[] fileBytes) {
        Map<String, String> uploadConfig = new HashMap<>();

        Map response = null;
        try {
            response = cloudinary.uploader().upload(fileBytes, uploadConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.get("url").toString();
    }
}
