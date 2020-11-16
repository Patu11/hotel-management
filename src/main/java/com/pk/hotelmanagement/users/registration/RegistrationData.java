package com.pk.hotelmanagement.users.registration;

import com.pk.hotelmanagement.users.Email;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationData {

    @NotNull
    private Email email;

    @NotNull
    private String password;

}
