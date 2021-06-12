package cc.eamon.easyfile.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-29 00:01:36
 */
public class Loader {

    public static String loadFile(File file) {
        String encoding = "UTF-8";
        long fileLength = file.length();
        byte[] fileContent = new byte[(int) fileLength];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
            return new String(fileContent, encoding);
        } catch (IOException e) {
            return null;
        }
    }

}
