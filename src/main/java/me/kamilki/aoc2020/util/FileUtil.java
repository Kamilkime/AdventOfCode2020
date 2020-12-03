package me.kamilki.aoc2020.util;

import java.io.File;

public final class FileUtil {

    public static File getFileForDay(final Class<?> dayClass, final String fileName) {
        return new File("src/main/java/me/kamilki/aoc2020/" + dayClass.getSimpleName().toLowerCase() + "/" + fileName);
    }

    private FileUtil() {}

}
