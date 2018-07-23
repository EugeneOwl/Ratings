package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(exclude = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }
}
