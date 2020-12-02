package com.pk.hotelmanagement.users.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private int status;
    private String message;

    public ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }


}
