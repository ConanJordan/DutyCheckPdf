package main.com.co.jp.netwisdom.util;

/**
 * 共同UTIL类
 */
public final class CommonUtil {

    /**
     * 判断对象是否为空
     * 
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return obj == null || "".equals(obj.toString());
    }      
    
}
