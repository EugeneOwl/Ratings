package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.transformer.UserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTransformer userTransformer;

    @Override
    public UserDto getUserById(int id) {
        if (userRepository.existsById(id)) {
            User user = userRepository.getOne(id);
            log.info("User was taken by id: " + user);

            return userTransformer.transform(user);
        }
        log.info("Attempt to take not existing user with id = {}", id);

        return null;
    }

    @Override
    public void addUser(UserDto userDto) {
        User user = userTransformer.transform(userDto);
        userRepository.save(user);
        log.info("User was added: " + user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userTransformer.transform(userDto);
        System.out.println("TRANSFORMED USER: " + user);
        userRepository.save(user);
        log.info("User was updated: " + user);
    }

    @Override
    public void removeUser(int id) {
        userRepository.deleteById(id);
        log.info(MessageFormat.format("User with id = {0} was removed: ", id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> list = userRepository.findAll(Sort.by("id"));
        for (User user : list) {
            log.info("User was taken: " + user);
        }

        return list.stream()
                .map(userTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isUserValid(UserDto userDto) {
        return (
                userDto != null &&
                userDto.getUsername().trim().length() != 0 &&
                userDto.getPassword().trim().length() != 0
                );
    }
}
