package ua.com.shulzhenko.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteOutFile {
    /**
     *
     * @param path - The full name of the file to be written
     * @param str - String to write
     */
    public static void write(String path, String str) {
        Path current = Paths.get(path);
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(path + " cannot be written");
        }
    }
}
