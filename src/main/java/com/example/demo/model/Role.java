package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
    @Column(name = "value")
    private String value;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
