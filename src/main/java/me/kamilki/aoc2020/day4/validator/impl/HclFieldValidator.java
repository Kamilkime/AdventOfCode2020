package me.kamilki.aoc2020.day4.validator.impl;

import me.kamilki.aoc2020.day4.validator.FieldValidator;

import java.util.regex.Pattern;

public class HclFieldValidator implements FieldValidator {

    private static final Pattern FIELD_PATTERN = Pattern.compile("#[0-9a-f]{6}");

    @Override
    public boolean isValid(final String value) {
        return FIELD_PATTERN.matcher(value).matches();
    }

}
