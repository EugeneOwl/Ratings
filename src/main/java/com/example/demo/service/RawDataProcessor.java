package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface RawDataProcessor {

    String delimiter = " ";

    List<Integer> getNumericList(String rawNumbers);

    String getUserRawRoles(User user);

    int getNumeric(String rawNumber);
}
