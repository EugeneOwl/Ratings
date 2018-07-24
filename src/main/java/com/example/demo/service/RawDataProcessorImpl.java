package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RawDataProcessorImpl implements RawDataProcessor {

    private String cleanNumericString(String numericString) {
        return numericString.replaceAll("[^0-9]", RawDataProcessor.delimiter);
    }

    @Override
    public List<Integer> getNumericList(String rawNumbers) {
        rawNumbers = cleanNumericString(rawNumbers);

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

    @Override
    public int getNumeric(String rawNumber) {
        rawNumber = cleanNumericString(rawNumber);

        Scanner scanner = new Scanner(rawNumber);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        return -1;
    }
}
