package me.kamilki.aoc2020.day3;

import me.kamilki.aoc2020.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public final class Day3 {

    private static final File dataFile = FileUtil.getFileForDay(Day3.class, "data.txt");

    public static void main(final String[] args) throws IOException {
        final List<String> map = Files.readAllLines(dataFile.toPath());

        System.out.println("Part 1, modulo lines: " + countTreesModulo(map, 1, 3));
        System.out.println("Part 1, extended lines: " + countTreesExtend(map, 1, 3));

        System.out.println("Part 2, modulo lines: " + (countTreesModulo(map, 1, 1) * countTreesModulo(map, 1, 3) *
                countTreesModulo(map, 1, 5) * countTreesModulo(map, 1, 7) * countTreesModulo(map, 2, 1)));
        System.out.println("Part 2, extended lines: " + (countTreesExtend(map, 1, 1) * countTreesExtend(map, 1, 3) *
                countTreesExtend(map, 1, 5) * countTreesExtend(map, 1, 7) * countTreesExtend(map, 2, 1)));
    }

    private static long countTreesModulo(final List<String> map, final int moveDown, final int moveRight) {
        long trees = 0;
        for (int v = 0, h = 0; v < map.size(); v += moveDown, h += moveRight) {
            if (map.get(v).charAt(h % map.get(v).length()) == '#') {
                trees++;
            }
        }

        return trees;
    }

    private static long countTreesExtend(final List<String> map, final int moveDown, final int moveRight) {
        final List<String> extendedMap = new ArrayList<>();

        final int extensionMultiplier = ((map.size() - 1) / moveDown) / ((map.get(0).length() - 1) / moveRight);
        for (final String mapLine : map) {
            final StringBuilder extendedLine = new StringBuilder(mapLine);

            for (int i = 0; i < extensionMultiplier; i++) {
                extendedLine.append(mapLine);
            }

            extendedMap.add(extendedLine.toString());
        }

        long trees = 0;
        for (int v = 0, h = 0; v < extendedMap.size(); v += moveDown, h += moveRight) {
            if (extendedMap.get(v).charAt(h) == '#') {
                trees++;
            }
        }

        return trees;
    }

    private Day3() {}

}
