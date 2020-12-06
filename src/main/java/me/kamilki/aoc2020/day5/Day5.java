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

            seatIDs.add(decodePosition(seatLine));
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

    private static int decodePosition(final String positionString) {
        return Integer.parseInt(positionString.replaceAll("[FL]", "0").replaceAll("[BR]", "1"), 2);
    }

    private Day5() {}

}
