package com.pk.hotelmanagement.users.registration;

import com.pk.hotelmanagement.users.Email;
import com.pk.hotelmanagement.users.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<User, Email> {

}
