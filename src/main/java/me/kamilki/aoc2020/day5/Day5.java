package me.kamilki.aoc2020.day5;

import me.kamilki.aoc2020.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Day5 {

    private static final File dataFile = FileUtil.getFileForDay(Day5.class, "data.txt");

    public static void main(final String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(dataFile.toPath());
        final int[] results = getSeatIDs(lines);

        System.out.println("Part 1: " + results[0]); // 885
        System.out.println("Part 2: " + results[1]); // 623
    }

    private static int[] getSeatIDs(final List<String> lines) {
        final List<Integer> seatIDs = new ArrayList<>();

        for (final String seatLine : lines) {
            if (seatLine.length() != 10) {
                continue;
            }

            seatIDs.add(decodePosition(0, 127, seatLine.substring(0, 7)) * 8 + decodePosition(0, 7, seatLine.substring(7, 10)));
        }

        Collections.sort(seatIDs);

        for (int i = 0; i < seatIDs.size() - 1; i++) {
            final int currentID = seatIDs.get(i);
            if (seatIDs.get(i + 1) - currentID == 2) {
                return new int[]{seatIDs.get(seatIDs.size() - 1), currentID + 1};
            }
        }

        return new int[]{-1, -1};
    }

    private static int decodePosition(final int low, final int high, final String positionString) {
        if (low == high) {
            return low;
        }

        final int middle = (high - low) / 2 + low;

        final char splitType = positionString.charAt(0);
        if (splitType == 'F' || splitType == 'L') {
            return decodePosition(low, middle, positionString.substring(1));
        }

        if (splitType == 'B' || splitType == 'R') {
            return decodePosition(middle + 1, high, positionString.substring(1));
        }

        return -1;
    }

    private Day5() {}

}
