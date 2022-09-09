package com.github.pingia.mvpcodegen;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

public final class Utils {
    /**
     * 是否空白字符
     * @param target
     * @return
     */
    public static boolean isEmptyOrWhitespace(String target) {
        if (target == null) {
            return true;
        } else {
            int targetLen = target.length();
            if (targetLen == 0) {
                return true;
            } else {
                char c0 = target.charAt(0);
                if ((c0 < 'a' || c0 > 'z') && (c0 < 'A' || c0 > 'Z')) {
                    for(int i = 0; i < targetLen; ++i) {
                        char c = target.charAt(i);
                        if (c != ' ' && !Character.isWhitespace(c)) {
                            return false;
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * 输出对象的属性显示
     * 
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String modelToString(T t) {
        StringBuilder result = new StringBuilder("[");

        for (Field declaredField : t.getClass().getDeclaredFields()) {
            try {
                result
                        .append(declaredField.getName())
                        .append("=")
                        .append(declaredField.get(t))
                        .append(",");
            } catch (IllegalAccessException e) {
                declaredField.setAccessible(true);
                try {
                    result
                            .append(declaredField.get(t))
                            .append(",");
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
                declaredField.setAccessible(false);
            }
        }

        return result.substring(0, result.length() - 1) + "]";
    }

    /**
     * 获取apt编译的基路径，如：”xx/build/generated/source/apt/debug
     * 
     * @param filer
     * @return
     */
    public static final String getAptSourceDirPath(Filer filer) {
        try {
            FileObject src_output_testfileobj = filer.getResource(StandardLocation.SOURCE_OUTPUT, "", "test");
            if (null == src_output_testfileobj) {
                src_output_testfileobj = filer.createResource(StandardLocation.SOURCE_OUTPUT, "", "test");
            }
            return new File(src_output_testfileobj.toUri().getPath()).getParent();
        }catch (IOException ioe){
            return null;
        }
    }

    /**
     * 给定一个目录，给定一个文件名；如果没有文件，则生成一个文件，最后返回文件对象
     * 
     * @param parentPath
     * @param filename
     * @return
     * @throws IOException
     */
    public static File createFileNotExists(String parentPath, String filename) throws IOException{
        File file = new File(parentPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        File outputFile = new File(file, filename);
        if (outputFile.exists()) {
            outputFile.delete();
        }

        outputFile.createNewFile();
        return outputFile;
    }

    /**
     * 判断前一个文件是否是后一个文件的子孙文件
     *
     * @param file1
     * @param file2
     * @return 如果file1文件对象的路径包括file2文件对象的路径，则说明file1是file2的子孙文件
     */
    public static boolean isDescendantFile(File file1, File file2){
        if(file1 == null || null == file2) return false;
        return file1.getAbsolutePath().contains(file2.getAbsolutePath());
    }

    /**
     * 小写字母转大写字母
     * @param str
     * @return
     */
    public static final String getFirstLetterUpper(String str){
        byte[] items = str.getBytes();
        if('a' <= items[0] && items[0] <= 'z') {    //如果是小写，才转化
            items[0] = (byte) ((char) items[0] - 'a' + 'A');
        }
        return new String(items);
    }

}
