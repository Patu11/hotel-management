package com.pk.hotelmanagement.users.registration.persons;

import com.pk.hotelmanagement.users.User;
import com.pk.hotelmanagement.users.vo.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persons")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private int phoneNr;
    @Column(nullable = false)
    private Date birthdate;
    @Embedded
    private Address address;
    @OneToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;
}
