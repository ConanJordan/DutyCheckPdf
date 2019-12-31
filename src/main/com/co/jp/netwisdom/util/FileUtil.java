package main.com.co.jp.netwisdom.util;

import java.io.File;
import java.io.IOException;

/**
 * 文件的UTIL类
 */
public class FileUtil {

    /**
     * 新建文件
     * @param filePath
     * @return
     * @throws IOException
     */
    public static boolean create (String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists() == false) {  // 当目标文件不存在的时候
            File directory = file.getParentFile();
            if (directory.exists() == false) {  // 当目标文件的目录不存在的时候
                directory.mkdirs();
                return file.createNewFile();
            } else {  // 当目标文件的目录存在的时候
                return file.createNewFile();
            }
        }
        
        return false;
    }
    
}
