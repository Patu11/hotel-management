package com.pk.hotelmanagement.hotel.storage.product;

import com.pk.hotelmanagement.hotel.storage.Storage;
import com.pk.hotelmanagement.hotel.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private StorageService storageService;

    @Autowired
    public ProductService(ProductRepository productRepository, StorageService storageService) {
        this.productRepository = productRepository;
        this.storageService = storageService;
    }

    @Transactional
    public void createProduct(ProductData productData) {
        Product product = new Product();
        product.setName(productData.getName());
        product.setAmount(productData.getAmount());
        product.setExpirationDate(productData.getExpirationDate());

        Storage storage = storageService.getStorage(productData.getStorageId());
        storage.addProduct(product);

        productRepository.save(product);
    }
}
