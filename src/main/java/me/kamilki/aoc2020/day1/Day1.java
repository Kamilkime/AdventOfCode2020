package me.kamilki.aoc2020.day1;

import me.kamilki.aoc2020.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Day1 {

    private static final File dataFile = FileUtil.getFileForDay(Day1.class, "data.txt");

    public static void main(final String[] args) throws IOException {
        final List<Integer> numbers = Files.lines(dataFile.toPath()).map(Integer::parseInt).sorted().collect(Collectors.toList());

        final int[] part1 = findTwo(numbers, 2020, -1);
        System.out.println("Part 1: " + (part1[0] * part1[1])); // 719796

        final int[] part2 = findThree(numbers, 2020);
        System.out.println("Part 2: " + (part2[0] * part2[1] * part2[2])); // 144554112
    }

    private static int[] findTwo(final List<Integer> numbers, final int sum, final int ignoreIndex) {
        for (int i = 0; i < numbers.size(); i++) {
            if (i == ignoreIndex) {
                continue;
            }

            final int remainder = sum - numbers.get(i);
            final int foundIndex = Collections.binarySearch(numbers, remainder);

            if (foundIndex == ignoreIndex || foundIndex == i || foundIndex == -1) {
                continue;
            }

            return new int[]{numbers.get(i), remainder};
        }

        return new int[]{-1, -1};
    }

    private static int[] findThree(final List<Integer> numbers, final int sum) {
        for (int i = 0; i < numbers.size(); i++) {
            final int partialSum = sum - numbers.get(i);
            final int[] twoAddends = findTwo(numbers, partialSum, i);

            if (twoAddends[0] != -1) {
                return new int[]{numbers.get(i), twoAddends[0], twoAddends[1]};
            }
        }

        return new int[]{-1, -1, -1};
    }

    private Day1() {
    }

}
