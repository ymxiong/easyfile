package cc.eamon.easyfile;

import cc.eamon.easyfile.utils.Loader;
import cc.eamon.easyfile.utils.Reader;
import cc.eamon.easyfile.utils.Writer;

import java.io.*;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2019-06-22 23:45:41
 */
public class FileTools {

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
        return Reader.readFiles(filePath, depth);
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
        return Reader.readFolders(folderPath, depth);
    }

    /**
     * 读取文件
     *
     * @param file 文件
     * @return 文件内容
     */
    public static String loadFile(File file) {
        return Loader.loadFile(file);
    }

    /**
     * 写出文件
     *
     * @param pathname 文件路径
     * @param content  文件内容
     * @return 文件
     */
    public static File writeFile(String pathname, String content) {
        return Writer.writeFile(pathname, content);
    }


    /**
     * 拷贝文件
     *
     * @param source    源
     * @param target    目标
     * @param overwrite 是否覆盖
     * @throws IOException IO错误
     */
    public static void copyFiles(File source, File target, boolean overwrite) throws IOException {
        Writer.copy(source, target, overwrite);
    }

    /**
     * 输入流转字符串
     *
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
