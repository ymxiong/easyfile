package cc.eamon.easyfile.utils;

import cc.eamon.easyfile.FileConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-28 23:56:37
 */
public class Reader {

    /**
     * 读取指定深度的所有文件
     *
     * @param filePath 文件路径
     * @param depth    查询深度 0未无深度 1为当前文件夹深度 2为包含下层文件夹深度
     * @return 文件列表
     */
    public static List<File> readFiles(String filePath, int depth) {
        if (depth == 0) {
            return new ArrayList<>();
        }
        List<File> files = new ArrayList<>();
        File file = new File(filePath);
        if (!file.isDirectory()) {
            files.add(file);
        } else {
            String[] fileList = file.list();
            if (fileList == null) {
                return new ArrayList<>();
            }
            for (String fileItem : fileList) {
                File readFile = new File(filePath + FileConstants.SEPARATOR + fileItem);
                if (!readFile.isDirectory()) {
                    files.add(readFile);
                } else {
                    files.addAll(readFiles(filePath + FileConstants.SEPARATOR + fileItem, depth - 1));
                }
            }
        }
        return files;
    }


    public static List<File> readFolders(String folderPath, int depth) {
        if (depth == 0) {
            return new ArrayList<>();
        }
        List<File> files = new ArrayList<>();
        File file = new File(folderPath);
        if (file.isDirectory()) {
            String[] folderList = file.list();
            if (folderList == null) {
                return new ArrayList<>();
            }
            for (String folderItem : folderList) {
                File readFolder = new File(folderPath + FileConstants.SEPARATOR + folderItem);
                if (readFolder.isDirectory()) {
                    files.add(readFolder);
                    files.addAll(readFolders(folderPath + FileConstants.SEPARATOR + folderItem, depth - 1));
                }
            }
        }
        return files;
    }

}
