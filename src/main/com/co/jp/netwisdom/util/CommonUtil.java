package main.com.co.jp.netwisdom.util;

public final class CommonUtil {
	
	public static boolean isNull (Object obj) {
		return obj == null || "".equals(obj.toString());
	}
	
}
