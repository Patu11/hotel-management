package com.pk.hotelmanagement.hotel.storage;

import com.pk.hotelmanagement.hotel.Hotel;
import com.pk.hotelmanagement.hotel.HotelService;
import com.pk.hotelmanagement.hotel.NotFoundException;
import com.pk.hotelmanagement.hotel.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StorageService {

    private StorageRepository storageRepository;
    private HotelService hotelService;

    @Autowired
    public StorageService(StorageRepository storageRepository, HotelService hotelService) {
        this.storageRepository = storageRepository;
        this.hotelService = hotelService;
    }

    @Transactional
    public void createStorage(StorageData storageData) {
        Storage storage = new Storage();
        storage.setAddress(storageData.getAddress());
        storage.setCapacity(storageData.getCapacity());

        Hotel hotel = hotelService.getHotel(storageData.getHotelId());
        hotel.addStorage(storage);

        storageRepository.save(storage);
    }

    @Transactional
    public Storage getStorage(int storageId) {
        Optional<Storage> storage = storageRepository.findById(storageId);
        if (storage.isEmpty()) {
            throw new NotFoundException("Storage not found");
        }
        return storage.get();
    }
}
