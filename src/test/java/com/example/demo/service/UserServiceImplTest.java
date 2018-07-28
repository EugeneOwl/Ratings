package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.transformer.UserTransformer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MVCConfig.class)
//@JsonTest
public class UserServiceImplTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    private UserDto userDto;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleService roleService;

    @MockBean
    private RawDataProcessor rawDataProcessor;

    @MockBean
    private UserTransformer userTransformer;

    @Before
    public void setUp() {
        userDto = new UserDto();
    }

    @After
    public void tearDown() {
        userDto = null;
    }

    @Test
    public void getUserById() {
        User expectedUser = new User();
        expectedUser.setUsername("test username");
        expectedUser.setId(1);

        UserDto expectedUserDto = UserDto.builder()
                .id(expectedUser.getId())
                .username(expectedUser.getUsername())
                .build();

        when(userTransformer.transform(expectedUser))
                .thenReturn(expectedUserDto);

        userDto = userTransformer.transform(expectedUser);

        when(userRepository.getOne(userDto.getId()))
                .thenReturn(expectedUser);
        when(userRepository.existsById(userDto.getId()))
                .thenReturn(true);

        UserDto actualUserDto = userService.getUserById(userDto.getId());

        Assert.assertEquals(expectedUserDto,
                actualUserDto);
    }

    @Test
    public void isUserValid() {
        userDto = null;
        Assert.assertFalse(userService.isUserValid(userDto));

        userDto = new UserDto();
        userDto.setUsername("");
        userDto.setPassword("test password");
        Assert.assertFalse(userService.isUserValid(userDto));

        userDto.setUsername("test username");
        userDto.setPassword("");
        Assert.assertFalse(userService.isUserValid(userDto));

        userDto.setUsername("  ");
        userDto.setPassword("      ");
        Assert.assertFalse(userService.isUserValid(userDto));

        userDto.setUsername("test username");
        userDto.setPassword("test password");
        Assert.assertTrue(userService.isUserValid(userDto));
    }
}