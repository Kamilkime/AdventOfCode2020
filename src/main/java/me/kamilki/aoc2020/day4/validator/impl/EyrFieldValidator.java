package me.kamilki.aoc2020.day4.validator.impl;

import me.kamilki.aoc2020.day4.validator.FieldValidator;

public class EyrFieldValidator implements FieldValidator {

    @Override
    public boolean isValid(final String value) {
        try {
            final int expirationYear = Integer.parseInt(value);
            return expirationYear >= 2020 && expirationYear <= 2030;
        } catch (final NumberFormatException exception) {
            return false;
        }
    }

}
