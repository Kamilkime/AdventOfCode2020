package me.kamilki.aoc2020.day9;

import me.kamilki.aoc2020.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Day9 {

    private static final File dataFile = FileUtil.getFileForDay(Day9.class, "data.txt");

    public static void main(final String[] args) throws IOException {
        final List<Long> numbers = Files.lines(dataFile.toPath()).map(Long::parseLong).collect(Collectors.toList());

        System.out.println("Part 1: " + findIncorrectNumber(numbers, 25, 0)); // 400480901
        System.out.println("Part 2: " + breakEncryption(numbers, 400480901L)); // 35196801
    }

    private static long findIncorrectNumber(final List<Long> numbers, final int preambleSize, final int indexOffset) {
        if (preambleSize + indexOffset >= numbers.size()) {
            return -1L;
        }

        final List<Long> preamble = new ArrayList<>(numbers.subList(indexOffset, preambleSize + indexOffset));
        Collections.sort(preamble);

        final long sum = numbers.get(preambleSize + indexOffset);
        for (int i = 0; i < preambleSize; i++) {
            final long remainder = sum - preamble.get(i);

            final int foundIndex = Collections.binarySearch(preamble, remainder);
            if (foundIndex == i || foundIndex == -1) {
                continue;
            }

            return findIncorrectNumber(numbers, preambleSize, indexOffset + 1);
        }

        return sum;
    }

    private static long breakEncryption(final List<Long> numbers, final long key) {
        for (int i = 0; i < numbers.size(); i++) {
            final List<Long> foundNumbers = new ArrayList<>(Collections.singleton(numbers.get(i)));

            long sum = numbers.get(i);
            if (sum >= key) {
                return -1L;
            }

            for (int j = i + 1; j < numbers.size(); j++) {
                sum += numbers.get(j);
                foundNumbers.add(numbers.get(j));

                if (sum >= key) {
                    break;
                }
            }

            if (sum == key) {
                Collections.sort(foundNumbers);
                return foundNumbers.get(0) + foundNumbers.get(foundNumbers.size() - 1);
            }
        }

        return -1L;
    }

    private Day9() {}

}
