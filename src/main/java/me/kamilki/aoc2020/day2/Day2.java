package me.kamilki.aoc2020.day2;

import me.kamilki.aoc2020.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public final class Day2 {

    private static final File dataFile = FileUtil.getFileForDay(Day2.class, "data.txt");

    public static void main(final String[] args) throws IOException {
        final List<String> passwordLines = Files.readAllLines(dataFile.toPath());

        System.out.println("Part 1: " + checkPasswordsOccurrences(passwordLines));
        System.out.println("Part 2: " + checkPasswordsPositions(passwordLines));
    }

    private static int checkPasswordsOccurrences(final List<String> passwordLines) {
        int correctPasswords = 0;

        for (final String passwordLine : passwordLines) {
            final String[] split = passwordLine.split(" ");

            final int minOccurrences = Integer.parseInt(split[0].split("-")[0]);
            final int maxOccurrences = Integer.parseInt(split[0].split("-")[1]);
            final char neededChar = split[1].charAt(0);
            final String password = split[2];

            int occurrences = 0;
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == neededChar) {
                    occurrences++;
                }
            }

            if (occurrences >= minOccurrences && occurrences <= maxOccurrences) {
                correctPasswords++;
            }
        }

        return correctPasswords;
    }

    private static int checkPasswordsPositions(final List<String> passwordLines) {
        int correctPasswords = 0;

        for (final String passwordLine : passwordLines) {
            final String[] split = passwordLine.split(" ");

            final int firstPosition = Integer.parseInt(split[0].split("-")[0]) - 1;
            final int secondPosition = Integer.parseInt(split[0].split("-")[1]) - 1;
            final char neededChar = split[1].charAt(0);
            final String password = split[2];

            if (password.charAt(firstPosition) == neededChar ^ password.charAt(secondPosition) == neededChar) {
                correctPasswords++;
            }
        }

        return correctPasswords;
    }

    private Day2() {}

}
