package me.kamilki.aoc2020.day4.validator.impl;

import me.kamilki.aoc2020.day4.validator.FieldValidator;

public class IyrFieldValidator implements FieldValidator {

    @Override
    public boolean isValid(final String value) {
        try {
            final int issueYear = Integer.parseInt(value);
            return issueYear >= 2010 && issueYear <= 2020;
        } catch (final NumberFormatException exception) {
            return false;
        }
    }

}
