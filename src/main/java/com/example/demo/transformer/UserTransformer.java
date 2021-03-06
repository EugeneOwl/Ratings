package com.example.demo.transformer;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RawDataProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserTransformer implements Transformer<User, UserDto> {

    @Override
    public User transform(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .roles(userDto.getRoles())
                .build();
        user.setId(userDto.getId());
        user.setRatingsRecipient(new ArrayList<>());
        user.setRatingsSender(new ArrayList<>());

        return user;
    }

    @Override
    public UserDto transform(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .rawRoles(getRawRolesFromRoles(user))
                .roles(user.getRoles())
                .build();
    }

    private String getRawRolesFromRoles(User user) {
        StringBuilder rawRoles = new StringBuilder();
        for (Role role : user.getRoles()) {
            rawRoles.append(role.getId());
            rawRoles.append(RawDataProcessor.delimiter);
        }

        return rawRoles.toString();
    }
}
