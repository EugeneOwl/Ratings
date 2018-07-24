package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class RawDataProcessorImpl implements RawDataProcessor {
    @Override
    public List<Integer> getNumericList(String rawNumbers) {
        rawNumbers = rawNumbers.replaceAll("[^0-9]", " ");

        List<Integer> list = new ArrayList<>();
        Scanner scanner = new Scanner(rawNumbers);
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        return list;
    }
}
