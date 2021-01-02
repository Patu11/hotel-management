package com.pk.hotelmanagement.users.registration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pk.hotelmanagement.users.vo.Address;
import com.pk.hotelmanagement.users.vo.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationData {

    @NotNull
    private Email email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotNull
    private int phoneNr;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    @NotNull
    private Address address;

}
