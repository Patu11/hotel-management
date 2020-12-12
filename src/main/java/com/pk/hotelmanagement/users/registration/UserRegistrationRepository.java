package com.pk.hotelmanagement.users.registration;

import com.pk.hotelmanagement.users.vo.Email;
import com.pk.hotelmanagement.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<User, Email> {
}
