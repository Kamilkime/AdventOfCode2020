package me.kamilki.aoc2020.day6;

import me.kamilki.aoc2020.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Day6 {

    private static final File dataFile = FileUtil.getFileForDay(Day6.class, "data.txt");

    public static void main(final String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(dataFile.toPath());
        final int[] results = checkAnswers(lines);

        System.out.println("Part 1: " + results[0]); // 6437
        System.out.println("Part 2: " + results[1]); // 3229
    }

    private static int[] checkAnswers(final List<String> lines) {
        int anyPositiveAnswers = 0;
        int allPositiveAnswers = 0;

        final Set<Character> anyAnswers = new HashSet<>();
        final Set<Character> allAnswers = new HashSet<>();

        final Set<Character> answerCharacters = new HashSet<>();
        boolean firstLine = true;

        for (final String answerLine : lines) {
            if (answerLine.isEmpty()) {
                anyPositiveAnswers += anyAnswers.size();
                allPositiveAnswers += allAnswers.size();

                anyAnswers.clear();
                allAnswers.clear();
                firstLine = true;

                continue;
            }

            for (final char c : answerLine.toCharArray()) {
                answerCharacters.add(c);
            }

            anyAnswers.addAll(answerCharacters);

            if (firstLine) {
                allAnswers.addAll(answerCharacters);
                firstLine = false;
            } else {
                allAnswers.removeIf(c -> !answerCharacters.contains(c));
            }

            answerCharacters.clear();
        }

        return new int[]{anyPositiveAnswers, allPositiveAnswers};
    }

    private Day6() {}

}
