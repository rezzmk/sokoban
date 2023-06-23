package pt.iscte.madco.poo.sokoban.helpers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileHelper {
    private FileHelper() {
        throw new IllegalStateException("FileHelper is a Utility class, should not be instantiated");
    }

    public static char[][] get2DArrayFromFile(String fileName) throws IOException {
        Path filePath = new File(fileName).toPath();

        List <String> stringList = Files.readAllLines(filePath);

        String[] stringArray = stringList.toArray(new String[]{});

        char[][] result = new char[stringArray[0].length()][stringArray.length];

        for(int x = 0; x < stringArray.length; x++) {
            for(int y = 0; y < stringArray[0].length(); y++) {
                if (!stringArray[x].isEmpty())
                    result[y][x] = stringArray[x].charAt(y);
            }
        }

        return result;
    }

    public static boolean fileExists(String fullPath) {
        File file = new File(fullPath);
        return file.exists();
    }
}
