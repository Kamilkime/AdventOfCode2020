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
        final int[] results = checkPasswords(passwordLines);

        System.out.println("Part 1: " + results[0]); // 396
        System.out.println("Part 2: " + results[1]); // 428
    }

    private static int[] checkPasswords(final List<String> passwordLines) {
        int correctPasswordsOccurrence = 0;
        int correctPasswordsPosition = 0;

        for (final String passwordLine : passwordLines) {
            final String[] split = passwordLine.split(" ");

            final int num1 = Integer.parseInt(split[0].split("-")[0]);
            final int num2 = Integer.parseInt(split[0].split("-")[1]);
            final char neededChar = split[1].charAt(0);
            final String password = split[2];

            // Check character occurrences - part 1 of the puzzle
            int occurrences = 0;
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == neededChar) {
                    occurrences++;
                }
            }

            if (occurrences >= num1 && occurrences <= num2) {
                correctPasswordsOccurrence++;
            }

            // Check character positions - part 2 of the puzzle
            if (password.charAt(num1 - 1) == neededChar ^ password.charAt(num2 - 1) == neededChar) {
                correctPasswordsPosition++;
            }
        }

        return new int[]{correctPasswordsOccurrence, correctPasswordsPosition};
    }

    private Day2() {}

}
