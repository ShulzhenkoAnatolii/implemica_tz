package ua.com.shulzhenko.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadInputFile {
    /**
     *
     * @param nameFile - Name of the file being read
     * @return the read file in String notation
     */
    public static String readInputFile(String nameFile) {
        StringBuilder line = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nameFile));
            while (reader.ready()) {
                line.append(reader.readLine()).append("\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return line.toString();
    }
}
