package me.kamilki.aoc2020.day4.validator.impl;

import me.kamilki.aoc2020.day4.validator.FieldValidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EclFieldValidator implements FieldValidator {

    private final Set<String> allowedValues = new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));

    @Override
    public boolean isValid(final String value) {
        return this.allowedValues.contains(value);
    }

}
