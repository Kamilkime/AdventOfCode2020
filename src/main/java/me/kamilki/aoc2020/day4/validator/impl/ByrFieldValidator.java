package me.kamilki.aoc2020.day4.validator.impl;

import me.kamilki.aoc2020.day4.validator.FieldValidator;

public class ByrFieldValidator implements FieldValidator {

    @Override
    public boolean isValid(final String value) {
        try {
            final int birthYear = Integer.parseInt(value);
            return birthYear >= 1920 && birthYear <= 2002;
        } catch (final NumberFormatException exception) {
            return false;
        }
    }

}
