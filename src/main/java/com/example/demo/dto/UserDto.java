package com.example.demo.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Dto {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank
    private String password;
    private String rawRoles;
}
