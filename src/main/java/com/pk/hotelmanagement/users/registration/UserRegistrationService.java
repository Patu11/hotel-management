package com.pk.hotelmanagement.users.registration;

import com.pk.hotelmanagement.users.User;
import com.pk.hotelmanagement.users.registration.persons.Person;
import com.pk.hotelmanagement.users.roles.Role;
import com.pk.hotelmanagement.users.roles.RoleEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserRegistrationService {

    private UserRegistrationRepository userRegistrationRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationService(UserRegistrationRepository userRegistrationRepository, PasswordEncoder passwordEncoder) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegistrationData registrationData) {
        if (!exists(registrationData)) {
            User user = new User();
            user.setEmail(registrationData.getEmail());
            user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
            user.setRegular(false);
            user.setRole(createRole());
            user.setPerson(createPerson(registrationData));
            userRegistrationRepository.save(user);
        }
    }

    private Person createPerson(RegistrationData registrationData) {
        Person person = new Person();
        BeanUtils.copyProperties(registrationData,person);
        return person;
    }

    private boolean exists(RegistrationData registrationData) {
        Optional<User> user = userRegistrationRepository.findById(registrationData.getEmail());
        return user.isPresent();
    }

    private RoleEntity createRole() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(Role.USER);
        return roleEntity;
    }

}
