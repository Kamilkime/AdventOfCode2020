package me.kamilki.aoc2020.day4;

import me.kamilki.aoc2020.day4.validator.FieldValidator;
import me.kamilki.aoc2020.day4.validator.impl.*;
import me.kamilki.aoc2020.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Day4 {

    private static final File dataFile = FileUtil.getFileForDay(Day4.class, "data.txt");
    private static final Map<String, FieldValidator> obligatoryFields = new HashMap<>();

    static {
        obligatoryFields.put("byr", new ByrFieldValidator());
        obligatoryFields.put("iyr", new IyrFieldValidator());
        obligatoryFields.put("eyr", new EyrFieldValidator());
        obligatoryFields.put("hgt", new HgtFieldValidator());
        obligatoryFields.put("hcl", new HclFieldValidator());
        obligatoryFields.put("ecl", new EclFieldValidator());
        obligatoryFields.put("pid", new PidFieldValidator());
    }

    public static void main(final String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(dataFile.toPath());
        final int[] results = checkPassports(lines);

        System.out.println("Part 1: " + results[0]); // 200
        System.out.println("Part 2: " + results[1]); // 116
    }

    private static int[] checkPassports(final List<String> lines) {
        int correctPassportsNoValidation = 0;
        int correctPassportsWithValidation = 0;

        final Map<String, String> currentPassportFields = new HashMap<>();
        linesLoop: for (final String line : lines) {
            if (!line.isEmpty()) {
                for (final String field : line.split(" ")) {
                    final String[] fieldSplit = field.split(":");
                    currentPassportFields.put(fieldSplit[0], fieldSplit[1]);
                }

                continue;
            }

            try {
                for (final String obligatoryField : obligatoryFields.keySet()) {
                    if (!currentPassportFields.containsKey(obligatoryField)) {
                        continue linesLoop;
                    }
                }

                correctPassportsNoValidation++;

                for (final Map.Entry<String, FieldValidator> obligatoryFieldEntry : obligatoryFields.entrySet()) {
                    if (!obligatoryFieldEntry.getValue().isValid(currentPassportFields.get(obligatoryFieldEntry.getKey()))) {
                        continue linesLoop;
                    }
                }

                correctPassportsWithValidation++;
            } finally {
                currentPassportFields.clear();
            }
        }

        return new int[]{correctPassportsNoValidation, correctPassportsWithValidation};
    }

    private Day4() {}

}
