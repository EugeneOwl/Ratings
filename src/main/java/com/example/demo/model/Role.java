package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString(exclude = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
    @Column(name = "value")
    private String value;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    @JoinTable(
            name = "users_roles",
            inverseJoinColumns = { @JoinColumn(name = "user_id") },
            joinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        users.add(user);
    }
}
