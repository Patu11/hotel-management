package com.pk.hotelmanagement;

import com.pk.hotelmanagement.users.registration.UserRegistrationService;
import com.pk.hotelmanagement.users.vo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Startup implements CommandLineRunner {

    private final UserRegistrationService userRegistrationService;

    @Autowired
    public Startup(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public void run(String... args) throws Exception {
        userRegistrationService.registerAdmin(new Email("admin@hotel.com"), "password");
    }


}
