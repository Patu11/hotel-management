package com.pk.hotelmanagement.users;

import com.pk.hotelmanagement.users.registration.persons.Person;
import com.pk.hotelmanagement.users.roles.RoleEntity;
import com.pk.hotelmanagement.users.vo.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @EmbeddedId
    private Email email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean regular;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private RoleEntity role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private Person person;

    public void setRole(RoleEntity role) {
        if (role == null) {
            if (this.role != null) {
                this.role.setUser(null);
            }
        } else {
            role.setUser(this);
        }
        this.role = role;
    }

    public void setPerson(Person person) {
        if (person == null) {
            if (this.person != null) {
                this.person.setUser(null);
            }
        } else {
            person.setUser(this);
        }
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
