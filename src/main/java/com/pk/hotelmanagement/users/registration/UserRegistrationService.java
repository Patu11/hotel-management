package com.pk.hotelmanagement.users.registration;

import com.pk.hotelmanagement.users.User;
import com.pk.hotelmanagement.users.login.exceptions.UserNotFoundException;
import com.pk.hotelmanagement.users.registration.persons.Person;
import com.pk.hotelmanagement.users.regularClients.RegularClientService;
import com.pk.hotelmanagement.users.roles.Role;
import com.pk.hotelmanagement.users.roles.RoleEntity;
import com.pk.hotelmanagement.users.vo.Email;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserRegistrationService {

    private final UserRegistrationRepository userRegistrationRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegularClientService regularClientService;

    public UserRegistrationService(UserRegistrationRepository userRegistrationRepository, PasswordEncoder passwordEncoder, RegularClientService regularClientService) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.passwordEncoder = passwordEncoder;
        this.regularClientService = regularClientService;
    }

    @Transactional
    public void register(RegistrationData registrationData) {
        if (!exists(registrationData)) {
            User user = createUser(registrationData);
            userRegistrationRepository.save(user);
            regularClientService.createAndAssignToUser(user);
        }
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(Email email) {
        return userRegistrationRepository.findById(email).orElseThrow(
                () -> new UserNotFoundException("User with email " + email.toString() + " does not exist"));
    }

    private User createUser(RegistrationData registrationData) {
        User user = new User();
        user.setEmail(registrationData.getEmail());
        user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
        user.setRegular(false);
        user.setRole(createRole());
        user.setPerson(createPerson(registrationData));
        return user;
    }

    private Person createPerson(RegistrationData registrationData) {
        Person person = new Person();
        BeanUtils.copyProperties(registrationData, person);
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
