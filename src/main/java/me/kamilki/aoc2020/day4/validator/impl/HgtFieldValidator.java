package me.kamilki.aoc2020.day4.validator.impl;

import me.kamilki.aoc2020.day4.validator.FieldValidator;

public class HgtFieldValidator implements FieldValidator {

    @Override
    public boolean isValid(final String value) {
        final String unit = value.substring(value.length() - 2);
        final int unitValue;

        try {
            unitValue = Integer.parseInt(value.substring(0, value.length() - 2));
        } catch (final NumberFormatException exception) {
            return false;
        }

        if ("cm".equals(unit)) {
            return unitValue >= 150 && unitValue <= 193;
        } else if ("in".equals(unit)) {
            return unitValue >= 59 && unitValue <= 76;
        }

        return false;
    }

}
