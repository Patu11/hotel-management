package com.pk.hotelmanagement.users.vo;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Email implements Serializable {
    private String email;

    protected Email() {
    }

    public Email(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Empty email");
        }

        if (!isEmail(email)) {
            throw new IllegalArgumentException("Wrong email");
        }

        this.email = email;
    }

    private boolean isEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email1 = (Email) o;

        return email.equals(email1.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return this.email;
    }
}
