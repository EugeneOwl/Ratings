package com.example.demo.service;

import com.example.demo.conf.MVCConfig;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MVCConfig.class)
public class RawDataProcessorImplTest {
    private String rawString;

    @Autowired
    RawDataProcessor rawDataProcessor;

    @After
    public void tearDown() throws Exception {
        rawString = "";
    }

    @Test
    public void getNumericList() {
        List<Integer> expectedNumerics = Arrays.asList(1, 2, 3);

        rawString = "1 2 3";
        Assert.assertEquals(
                rawDataProcessor.getNumericList(rawString),
                expectedNumerics
        );

        rawString = "1, 2, 3.";
        Assert.assertEquals(
                rawDataProcessor.getNumericList(rawString),
                expectedNumerics
        );

        rawString = "1   2 , test3rubbish .";
        Assert.assertEquals(
                rawDataProcessor.getNumericList(rawString),
                expectedNumerics
        );

        rawString = "test empty dirty string with no numerals.";
        Assert.assertEquals(
                rawDataProcessor.getNumericList(rawString),
                new ArrayList<>()
        );
    }

    @Test
    public void getUserRawRoles() {
        String expectedRawRoles = "5 10";
        String delimiter = " ";

        Role role1 = new Role();
        role1.setId(5);
        Role role2 = new Role();
        role2.setId(10);

        User user = new User();

        Assert.assertEquals(
                "",
                rawDataProcessor.getUserRawRoles(user).trim()
        );

        user.addRole(role1);
        user.addRole(role2);

        List<Integer> expectedNumerals =
                rawDataProcessor.getNumericList(expectedRawRoles.trim());
        Collections.sort(expectedNumerals);

        List<Integer> actualNumerals =
                rawDataProcessor.getNumericList(
                        rawDataProcessor.getUserRawRoles(user).trim()
                );
        Collections.sort(actualNumerals);

        Assert.assertEquals(expectedNumerals, actualNumerals);
    }

    @Test
    public void getNumeric() {
        int expectedNumeric = 5;

        rawString = "5 ";
        Assert.assertEquals(
                expectedNumeric,
                rawDataProcessor.getNumeric(rawString)
        );

        rawString = "  test5, rubbish";
        Assert.assertEquals(
                expectedNumeric,
                rawDataProcessor.getNumeric(rawString)
        );

        rawString = "  test, rubbish string without numeric";
        Assert.assertEquals(
                -1,
                rawDataProcessor.getNumeric(rawString)
        );
    }
}