package cc.eamon.easyfile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2019-06-22 23:45:41
 */
public class FileTools {

    private static final String SEPARATOR = "/";

    /**
     * 读取所有文件
     *
     * @param filePath 文件路径
     * @return 文件列表
     */
    public static List<File> readFiles(String filePath) {
        return readFiles(filePath, -1);
    }

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
                File readFile = new File(filePath + SEPARATOR + fileItem);
                if (!readFile.isDirectory()) {
                    files.add(readFile);
                } else {
                    files.addAll(readFiles(filePath + SEPARATOR + fileItem, depth - 1));
                }
            }
        }
        return files;
    }

    /**
     * 读取所有文件夹
     *
     * @param folderPath 文件夹路径
     * @return 文件夹
     */
    public static List<File> readFolders(String folderPath) {
        return readFolders(folderPath, -1);
    }

    /**
     * 读取指定深度的文件夹
     *
     * @param folderPath 文件夹路径
     * @param depth      查询深度 0未无深度 1为当前文件夹深度 2为包含下层文件夹深度
     * @return 文件夹
     */
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
                File readFolder = new File(folderPath + SEPARATOR + folderItem);
                if (readFolder.isDirectory()) {
                    files.add(readFolder);
                    files.addAll(readFolders(folderPath + SEPARATOR + folderItem, depth - 1));
                }
            }
        }
        return files;
    }

    /**
     * 读取文件
     *
     * @param file 文件
     * @return 文件内容
     */
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

    /**
     * 写出文件
     *
     * @param pathname 文件路径
     * @param content  文件内容
     * @return 文件
     */
    public static File writeFile(String pathname, String content) {
        // 建立文件对象
        File file = new File(pathname);
        try {
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(file, false);
            byte[] b = content.getBytes();
            for (byte value : b) {
                // 内部强制转换
                out.write(value);
            }
        } catch (IOException e) {
            return null;
        }
        return file;
    }

    /**
     * 输入流转字符串
     * @param is 输入流
     * @return 字符串
     * @throws IOException IO错误
     */
    public static String inputStream2Str(InputStream is) throws IOException {
        StringBuffer sb;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is));

            sb = new StringBuffer();

            String data;
            while ((data = br.readLine()) != null) {
                sb.append(data).append("\n");
            }
        } finally {
            br.close();
        }

        return sb.toString();
    }

}
