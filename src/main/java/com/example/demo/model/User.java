package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@ToString(callSuper = true, exclude = {"roles", "ratingsRecipient", "ratingsSender"})
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "recipient", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Rating> ratingsRecipient = new ArrayList<>();

    @OneToMany(mappedBy = "sender", orphanRemoval = true)
    private List<Rating> ratingsSender = new ArrayList<>();

    public void addRole(Role role) {
        roles.add(role);
    }
}
