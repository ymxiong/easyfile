package cc.eamon.easyfile.utils;

import java.io.*;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-29 00:02:21
 */
public class Writer {

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
                boolean ignored = fileParent.mkdirs();
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
     * 复制文件或者文件夹
     *
     * @param source      源文件
     * @param target      目的文件
     * @param overwrite 是否覆盖文件
     * @throws java.io.IOException 文件IO错误
     */
    public static void copy(File source, File target, boolean overwrite)
            throws IOException {
        if (source == null) return;
        if (!source.exists()) throw new RuntimeException(source.getPath() + " source file not exists");
        if (source.isFile()) {
            copyFile(source, target, overwrite);
        } else {
            if (!target.exists()) target.mkdirs();
            File[] children = source.listFiles();
            if (children == null) return;
            for (File child : children) {
                copy(child, new File(target.getPath() + "/" + child.getName()), overwrite);
            }
        }
    }


    /**
     * 复制单个文件
     *
     * @param source   源文件
     * @param target  目的文件
     * @param overwrite 是否覆盖
     */
    private static void copyFile(File source, File target, boolean overwrite) throws IOException {
        if (target.exists() && overwrite && !target.delete()) {
            throw new RuntimeException(target.getPath() + "can not overwrite");
        } else if (target.exists() && !overwrite) {
            return;
        }

        InputStream in = new FileInputStream(source);
        OutputStream out = new FileOutputStream(target);
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        out.close();
    }

}
