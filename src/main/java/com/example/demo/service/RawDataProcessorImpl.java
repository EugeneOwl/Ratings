package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class RawDataProcessorImpl implements RawDataProcessor {
    @Override
    public List<Integer> getNumericList(String rawNumbers) {
        rawNumbers = rawNumbers.replaceAll("[^0-9]", RawDataProcessor.delimiter);

        List<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(rawNumbers);
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        return list;
    }

    @Override
    public String getUserRawRoles(User user) {
        StringBuilder rawRoles = new StringBuilder();
        for (Role role : user.getRoles()) {
            rawRoles.append(role.getId());
            rawRoles.append(RawDataProcessor.delimiter);
        }
        return rawRoles.toString();
    }
}
