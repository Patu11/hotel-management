package com.pk.hotelmanagement.users.regularClients;

import com.pk.hotelmanagement.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegularClientService {
    private final RegularClientRepository repository;

    @Autowired
    public RegularClientService(RegularClientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createAndAssignToUser(User user) {
        RegularClient regularClient = new RegularClient();
        regularClient.setDiscount(0);
        regularClient.setEmail(user.getEmail());
        regularClient.setNumberOfReservations(0);
        regularClient.setRegularCustomerThreshold(10);
        regularClient.setUser(user);
        repository.save(regularClient);
    }
}
