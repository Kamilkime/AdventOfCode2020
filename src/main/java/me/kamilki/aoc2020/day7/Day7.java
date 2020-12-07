package me.kamilki.aoc2020.day7;

import me.kamilki.aoc2020.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Day7 {

    private static final File dataFile = FileUtil.getFileForDay(Day7.class, "data.txt");
    private static final Map<String, Map<String, Integer>> luggageTypes = new HashMap<>();

    public static void main(final String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(dataFile.toPath());
        loadLuggageTypes(lines);

        System.out.println("Part 1: " + countEventualContent("shiny gold")); // 131
        System.out.println("Part 2: " + countTotalContents("shiny gold")); // 11261
    }

    private static void loadLuggageTypes(final List<String> lines) {
        for (final String line : lines) {
            final String[] lineSplit = line.substring(0, line.length() - 1).split("contain");
            final String luggageType = lineSplit[0].substring(0, lineSplit[0].length() - 6);

            final Map<String, Integer> contents = new HashMap<>();
            if (!" no other bags".equals(lineSplit[1])) {
                final String[] contentsSplit = lineSplit[1].trim().split(", ");
                for (final String contentLine : contentsSplit) {
                    try {
                        final String[] contentSplit = contentLine.split(" ");

                        final int amount = Integer.parseInt(contentSplit[0]);
                        final String typeName = String.join(" ", Arrays.copyOfRange(contentSplit, 1, contentSplit.length - 1));

                        contents.put(typeName, amount);
                    } catch (final NumberFormatException ignored) {}
                }
            }

            luggageTypes.put(luggageType, contents);
        }
    }

    private static final Map<String, Boolean> canContainCache = new HashMap<>();
    private static int countEventualContent(final String searchedType) {
        canContainCache.clear();

        int count = 0;
        for (final String luggageType : luggageTypes.keySet()) {
            if (canContain(luggageType, searchedType)) {
                count++;
            }
        }

        return count;
    }

    private static boolean canContain(final String luggageType, final String searchedType) {
        final Boolean cached = canContainCache.get(luggageType);
        if (cached != null) {
            return cached;
        }

        final Set<String> contents = luggageTypes.get(luggageType).keySet();
        if (contents.isEmpty()) {
            canContainCache.put(luggageType, false);
            return false;
        }

        if (contents.contains(searchedType)) {
            canContainCache.put(luggageType, true);
            return true;
        }

        for (final String contentType : contents) {
            if (canContain(contentType, searchedType)) {
                canContainCache.put(luggageType, true);
                return true;
            }
        }

        canContainCache.put(luggageType, false);
        return false;
    }

    private static final Map<String, Integer> totalContentsCache = new HashMap<>();
    private static int countTotalContents(final String luggageType) {
        if (totalContentsCache.containsKey(luggageType)) {
            return totalContentsCache.get(luggageType);
        }

        int total = 0;
        for (final Map.Entry<String, Integer> content : luggageTypes.get(luggageType).entrySet()) {
            total += countTotalContents(content.getKey()) * content.getValue() + content.getValue();
        }

        totalContentsCache.put(luggageType, total);
        return total;
    }


    private Day7() {}

}
