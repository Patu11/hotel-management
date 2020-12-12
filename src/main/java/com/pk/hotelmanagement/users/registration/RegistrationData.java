package com.pk.hotelmanagement.users.registration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pk.hotelmanagement.users.vo.Address;
import com.pk.hotelmanagement.users.vo.Email;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationData {

    @NotNull
    private Email email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private int phoneNumber;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    @NotNull
    private Address address;

}
