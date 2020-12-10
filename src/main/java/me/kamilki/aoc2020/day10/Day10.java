package me.kamilki.aoc2020.day10;

import me.kamilki.aoc2020.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Day10 {

    private static final File dataFile = FileUtil.getFileForDay(Day10.class, "data.txt");

    public static void main(final String[] args) throws IOException {
        final List<Integer> numbers = new ArrayList<>();

        numbers.add(0);
        numbers.addAll(Files.lines(dataFile.toPath()).map(Integer::parseInt).sorted().collect(Collectors.toList()));
        numbers.add(numbers.get(numbers.size() - 1) + 3);

        System.out.println("Part 1: " + countJoltageDifferences(numbers)); // 2482
        System.out.println("Part 2: " + getPossibilities(numbers, numbers.size() - 1)); // 96717311574016
    }

    private static int countJoltageDifferences(final List<Integer> numbers) {
        final int[] differences = new int[3];

        for (int i = 0; i < numbers.size() - 1; i++) {
            final int difference = numbers.get(i + 1) - numbers.get(i);
            if (difference <= 0 || difference > 3) {
                return -1;
            }

            differences[difference - 1]++;
        }

        return differences[0] * differences[2];
    }

    private static final Map<Integer, Long> possibilitiesCache = new HashMap<>();
    private static long getPossibilities(final List<Integer> numbers, final int targetIndex) {
        final Long cachedPossibilities = possibilitiesCache.get(targetIndex);
        if (cachedPossibilities != null) {
            return cachedPossibilities;
        }

        if (targetIndex == 0) {
            return 1;
        }

        long possibilities = 0;
        for (int i = targetIndex - 1; i >= 0; i--) {
            final long difference = numbers.get(targetIndex) - numbers.get(i);
            if (difference > 3) {
                break;
            }

            possibilities += getPossibilities(numbers, i);
        }

        possibilitiesCache.put(targetIndex, possibilities);
        return possibilities;
    }

    private Day10() {}
}
