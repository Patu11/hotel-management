package com.pk.hotelmanagement.users.regularClients;

import com.pk.hotelmanagement.users.vo.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularClientRepository extends JpaRepository<RegularClient,Email> {
}
