package me.kamilki.aoc2020.day8;

import me.kamilki.aoc2020.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public final class Day8 {

    private static final File dataFile = FileUtil.getFileForDay(Day8.class, "data.txt");

    public static void main(final String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(dataFile.toPath());
        final List<Map.Entry<String, Integer>> instructions = decodeInstructions(lines);

        System.out.println("Part 1: " + executeInstructions(instructions).getValue()); // 1262
        System.out.println("Part 2: " + terminateProgram(instructions)); // 1643
    }

    private static List<Map.Entry<String, Integer>> decodeInstructions(final List<String> lines) {
        final List<Map.Entry<String, Integer>> instructions = new ArrayList<>();

        for (final String instructionLine : lines) {
            final String[] instructionSplit = instructionLine.split(" ");
            instructions.add(new AbstractMap.SimpleImmutableEntry<>(instructionSplit[0], Integer.parseInt(instructionSplit[1])));
        }

        return instructions;
    }

    private static Map.Entry<ExitCode, Integer> executeInstructions(final List<Map.Entry<String, Integer>> instructions) {
        final Set<Integer> usedInstructions = new HashSet<>();
        int acc = 0;

        int currentInstruction = 0;
        while (!usedInstructions.contains(currentInstruction)) {
            if (currentInstruction >= instructions.size()) {
                return new AbstractMap.SimpleImmutableEntry<>(ExitCode.CORRECT, acc);
            }

            usedInstructions.add(currentInstruction);

            final Map.Entry<String, Integer> instruction = instructions.get(currentInstruction);
            if ("acc".equals(instruction.getKey())) {
                acc += instruction.getValue();
                currentInstruction++;
            } else if ("jmp".equals(instruction.getKey())) {
                currentInstruction += instruction.getValue();
            } else if ("nop".equals(instruction.getKey())) {
                currentInstruction++;
            } else {
                break;
            }
        }

        return new AbstractMap.SimpleImmutableEntry<>(ExitCode.LOOP, acc);
    }

    private static int terminateProgram(final List<Map.Entry<String, Integer>> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            final Map.Entry<String, Integer> instruction = instructions.get(i);

            if ("acc".equals(instruction.getKey())) {
                continue;
            }

            final String newInstruction = "jmp".equals(instruction.getKey()) ? "nop" : "jmp";
            instructions.set(i, new AbstractMap.SimpleImmutableEntry<>(newInstruction, instruction.getValue()));

            final Map.Entry<ExitCode, Integer> result = executeInstructions(instructions);
            if (result.getKey() == ExitCode.CORRECT) {
                return result.getValue();
            }

            instructions.set(i, instruction);
        }

        return -1;
    }

    private enum ExitCode {
        CORRECT, LOOP
    }

    private Day8() {}

}
