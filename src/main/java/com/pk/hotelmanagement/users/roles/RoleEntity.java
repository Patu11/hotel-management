package com.pk.hotelmanagement.users.roles;

import com.pk.hotelmanagement.users.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int roleId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;
}
