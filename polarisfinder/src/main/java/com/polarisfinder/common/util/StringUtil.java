package com.polarisfinder.common.util;


import java.util.List;



/**
 * ========================================================
 * 주서비스 : kams
 * 서브시스템 : kams
 * 프로그램명 : String 관련 Util
 * 설명 :
 * 작성자 : @author Jung mi kyoung
 * 작성일 : 2014. 11. 12.
 * ========================================================
 * 수정자/수정일 :
 * 수정사유/내역 :
 * ========================================================
 */
public class StringUtil {

	
	
	/**
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {

		if (str != null) {
			str = str.trim();
		}

		return (str == null || "".equals(str));
	}

	/**
	 * @param str
	 * @return
	 */
	public static boolean isAlpha(String str) {

		if (str == null) {
			return false;
		}

		int size = str.length();

		if (size == 0)
			return false;

		for (int i = 0; i < size; i++) {
			if (!Character.isLetter(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @param str
	 * @return
	 */
	public static boolean isAlphaNumeric(String str) {

		if (str == null) {
			return false;
		}

		int size = str.length();

		if (size == 0)
			return false;

		for (int i = 0; i < size; i++) {
			if (!Character.isLetterOrDigit(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}
	
	
	

	/**
	 * @param integer
	 * @return
	 */
	public static String integer2string(int integer) {
		return ("" + integer);
	}

	/**
	 * @param data
	 * @return
	 */
	public static String long2string(long longdata) {
		return String.valueOf(longdata);
	}

	public static String float2string(float floatdata) {
		return String.valueOf(floatdata);
	}

	public static String double2string(double doubledata) {
		return String.valueOf(doubledata);
	}

	/**
	 * @param str
	 * @return
	 */
	public static String null2void(String str) {

		if (isNull(str)) {
			str = "";
		}

		return str;
	}

	/**
	 * @param str
	 * @return
	 */
	public static int string2integer(String str) {

		if (isNull(str)) {
			return 0;
		}

		return Integer.parseInt(str);
	}

	/**
	 * @param str
	 * @return
	 */
	public static float string2float(String str) {

		if (isNull(str)) {
			return 0.0F;
		}

		return Float.parseFloat(str);
	}

	/**
	 * @param str
	 * @return
	 */
	public static double string2double(String str) {

		if (isNull(str)) {
			return 0.0D;
		}

		return Double.parseDouble(str);
	}

	/**
	 * @param str
	 * @return
	 */
	public static long string2long(String str) {

		if (isNull(str)) {
			return 0L;
		}

		return Long.parseLong(str);
	}

	/**
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static String null2string(String str, String defaultValue) {

		if (isNull(str)) {
			return defaultValue;
		}

		return str;
	}
	
	/**
	 * @param object
	 * @param defaultValue
	 * @return
	 */
	public static String nullObject2String(Object obj, String defaultValue) {

		if (obj == null) {
			return defaultValue;
		}

		return obj.toString();
	}

	/**
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static int string2integer(String str, int defaultValue) {

		if (isNull(str)) {
			return defaultValue;
		}

		return Integer.parseInt(str);
	}

	/**
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static float string2float(String str, float defaultValue) {

		if (isNull(str)) {
			return defaultValue;
		}

		return Float.parseFloat(str);
	}

	/**
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static double string2double(String str, double defaultValue) {

		if (isNull(str)) {
			return defaultValue;
		}

		return Double.parseDouble(str);
	}

	/**
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	public static long string2long(String str, long defaultValue) {

		if (isNull(str)) {
			return defaultValue;
		}

		return Long.parseLong(str);
	}

	/**
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean equals(String source, String target) {

		return null2void(source).equals(null2void(target));

	}
	
	public static String sum(String[] i_cnts) {
		
		int sum = 0;
		
		for (int i = 0; i < i_cnts.length; i++) {
			sum += Integer.parseInt(i_cnts[i]);
		}
		
		String result = Integer.toString(sum);
		
		return result;
	}
}
