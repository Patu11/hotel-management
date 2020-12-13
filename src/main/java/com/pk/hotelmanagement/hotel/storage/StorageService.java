package com.pk.hotelmanagement.hotel.storage;

import com.pk.hotelmanagement.hotel.Hotel;
import com.pk.hotelmanagement.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
