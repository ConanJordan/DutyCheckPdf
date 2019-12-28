package main.com.co.jp.netwisdom.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件的工具类
 */
public final class ReadProperty {
	
	private static Properties PROPERTIES = new Properties();
	
	private static FileInputStream IS;
	
	/**
	 * 初始化
	 */
	static {
		try {
			IS = new FileInputStream("setting.properties");
			PROPERTIES.load(IS);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据key读取配置文件的相应的value,没有则返回defValue
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getProperty (String key, String defValue) {
		return CommonUtil.isNull(PROPERTIES.getProperty(key, defValue)) ?
				defValue : PROPERTIES.getProperty(key, defValue);
	}
	
}
