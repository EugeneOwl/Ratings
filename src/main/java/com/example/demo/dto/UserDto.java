package com.example.demo.dto;

import com.example.demo.model.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Dto {
    private int id;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank
    private String password;
    private String rawRoles;
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }
}
