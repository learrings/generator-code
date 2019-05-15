package com.generator.util;

import java.util.stream.Stream;

/**
 * <p>Description:[驼峰实例转换]</p>
 * Create on 2019/5/15
 *
 * @author learrings
 */
public class CamelCaseUtils {

	/**
	 * <p>首字母小写</p>
	 *
	 * @param str-
	 */
	public static String toLowerCamelCase(String str) {
		str = toUpperCamelCase(str);
		return str.length() > 0 ? (str.length() == 1 ? Character.toLowerCase(str.charAt(0)) + "" : Character.toLowerCase(str.charAt(0)) + str.substring(1)) : "";
	}

	/**
	 * <p>首字母大写</p>
	 *
	 * @param str-
	 */
	public static String toUpperCamelCase(String str) {
		if (str == null || "".equals(str.trim())) {
			return "";
		}
		return Stream.of(str.toLowerCase().split("_")).map(s -> {
			StringBuilder sb = new StringBuilder("");
			if (s.length() > 0) {
				sb.append(Character.toUpperCase(s.charAt(0)));
				if (s.length() > 1) {
					sb.append(s.substring(1));
				}
			}
			return sb.toString();
		}).reduce("", String::concat);
	}
}