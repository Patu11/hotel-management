package com.pk.hotelmanagement.users.regularClients;

import com.pk.hotelmanagement.users.User;
import com.pk.hotelmanagement.users.vo.Email;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "regular_clients")
public class RegularClient {
    @EmbeddedId
    private Email email;

    @Column(nullable = false)
    private int numberOfReservations;

    @Column(nullable = false)
    private int regularCustomerThreshold;

    @Column(nullable = false)
    private float discount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email",nullable = false)
    @MapsId
    private User user;
}
