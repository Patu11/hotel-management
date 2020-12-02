package com.pk.hotelmanagement.users.login;

import com.pk.hotelmanagement.users.Email;
import com.pk.hotelmanagement.users.User;
import com.pk.hotelmanagement.users.registration.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserLoginService {

    private UserRegistrationRepository userRegistrationRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserLoginService(UserRegistrationRepository userRegistrationRepository, PasswordEncoder passwordEncoder) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public String createToken(String header) {
        String[] loginData = BaseDecoder.decode(header);
        Email email = new Email(loginData[0]);
        String password = loginData[1];
        Optional<User> user = userRegistrationRepository.findById(email);

//        String encodedPass = passwordEncoder.encode(password);

        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new UserNotFoundException();
        }

        return Token.create(user.get());
    }
}