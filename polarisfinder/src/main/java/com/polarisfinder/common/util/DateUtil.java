package com.polarisfinder.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * 날짜관련 유틸
 * @author Yunjung
 *
 */
public class DateUtil {

	/**
	 * 현재연도 조회(yyyy)
	 * @return
	 */
	public static String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return Integer.toString(year);
	}
	
	/**
	 * 현재날짜 조회(yyyyMMdd)
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(new Date());
	}
	
	/**
	 * 현재날짜시간 조회(yyyyMMddHHmmss)
	 * @return
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date());
	}
	
	/**
	 * 현재날짜시간 조회(yyyyMMddHHmmssSSS)
	 * @return
	 */
	public static String getCurrentDateMillisecondTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(new Date());
	}
	
	/**
	 * 날짜포맷으로 변환(yyyy.MM.dd)
	 * @param date
	 * @return
	 */
	public static String getFormattedDate(String date) {
		return getFormattedDate(date, ".");
	}
	
	/**
	 * 날짜포맷으로 변환(delimiter 사용)
	 * @param date
	 * @param delimiter
	 * @return
	 */
	public static String getFormattedDate(String date, String delimiter) {
		
		if (StringUtils.isBlank(date) || StringUtils.trim(date).length() < 8) {
			return null;
		}
		
		StringBuilder formattedDate = new StringBuilder();
		formattedDate.append(date.substring(0, 4));
		formattedDate.append(delimiter);
		formattedDate.append(date.substring(4, 6));
		formattedDate.append(delimiter);
		formattedDate.append(date.substring(6, 8));
		
		return formattedDate.toString();
	}
	
	/**
	 * 날짜시간포맷으로 변환(yyyy.MM.dd HH:mm:ss)
	 * @param date
	 * @return
	 */
	public static String getFormattedDateTime(String date) {
		
		if (StringUtils.isBlank(date) || StringUtils.trim(date).length() < 14) {
			return null;
		}
		
		StringBuilder formattedDate = new StringBuilder();
		formattedDate.append(date.substring(0, 4));
		formattedDate.append(".");
		formattedDate.append(date.substring(4, 6));
		formattedDate.append(".");
		formattedDate.append(date.substring(6, 8));
		formattedDate.append(" ");
		formattedDate.append(date.substring(8, 10));
		formattedDate.append(":");
		formattedDate.append(date.substring(10, 12));
		formattedDate.append(":");
		formattedDate.append(date.substring(12, 14));
		
		return formattedDate.toString();
	}
	
	

	/**
	 *	현재 일 부분 구하기
	 * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
	 *
	 * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with  your pattern.
	 */
	public static int getDay() {
		return getNumberByPattern("dd");
	}
	
	
	
	/**
	 * 현재 일자 구하기
	 *
	 * @return formatted string representation of current day with  "yyyy.MM.dd".
	 */
	public static String getDateString(String format) 
	{
		// 형식 지정
		java.text.SimpleDateFormat formatter =	new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);

		return formatter.format(new java.util.Date());
	}
	
	
	/**
	 * 현재 일자를 지정된 형식으로 int형으로 변환
	 *
	 * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
	 *
	 * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with  your pattern.
	 */
	public static int getNumberByPattern(String pattern) 
	{
		// 형식 지정
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern, java.util.Locale.KOREA);

		// 형식에 따라서 현재 일자를 가져옴 
		String dateString = formatter.format(new java.util.Date());
		
		return Integer.parseInt(dateString);
	}
	
	
	/**
	 * 지정일을 지정된 형식으로 변환
	 *
	 * 작성 날짜: (2001-04-16 오후 12:29:51)
	 * @return java.lang.String
	 * @param s java.lang.String
	 * @param format java.lang.String
	 * @exception java.lang.Exception 예외 설명.
	 */
	public static String getDisDate(String s, String format)
	{
		if ( s == null || s.length() == 0 ) return "";
		
		// 날짜 형식 지정
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		
		// 날짜 검사	
		java.util.Date targetDate = null;

		try 
		{
			targetDate = check(s);
		} 
		catch(java.text.ParseException pe)
		{
			// 현재일로 Setting
			// targetDate = new java.util.Date();	
			return "";	
		}

		return formatter.format(targetDate);
	}
	
	/**
	 * 날짜 유효성 검사(yyyyMMdd)
	 * @param date
	 * @return
	 */
	public static boolean vaidateDate(String date) {
		boolean dateValidity = true;

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);
		df.setLenient(false); // false 로 설정해야 엄밀한 해석을 함.

		try {
			Date dt = df.parse(date);
		}
		catch(ParseException pe){
			dateValidity = false;
		}catch(IllegalArgumentException ae){
			dateValidity = false;
		}

		return dateValidity;
	}
	
	
	/**
	 * 날짜 검사
	 * 날짜의 형식(Format) 없음.
	 *
	 * check date string validation with the default format "yyyyMMdd".
	 * @param s date string you want to check with default format "yyyyMMdd".
	 * @return date java.util.Date
	 */
	public static java.util.Date check(String s) throws java.text.ParseException {
		return check(s, "yyyyMMdd");
	}
	
	
	/**
	 * 일자 검사
	 * 
	 * check date string validation with an user defined format.
	 * @param s date string you want to check.
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return date java.util.Date
	 */
	public static java.util.Date check(String s, String format) throws java.text.ParseException 
	{
		// 파라메터 검사
		if ( s == null )
		{
			throw new java.text.ParseException("date string to check is null", 0);
		}
		if ( format == null )
		{
			throw new java.text.ParseException("format string to check date is null", 0);
		}
		
		// 날짜 형식 지정
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);

		// 검사
		java.util.Date date = null;
		
		try 
		{
			date = formatter.parse(s);
		}
		catch(java.text.ParseException e) 
		{
			throw new java.text.ParseException(" wrong date:\"" + s + "\" with format \"" + format + "\"", 0);
		}

		// 날짜 포멧이 틀린 경우
		if ( !formatter.format(date).equals(s))
		{
			throw new java.text.ParseException("Out of bound date:\"" + s + "\" with format \"" + format + "\"",0);
		}
		
		// 리턴
		return date;
	}

	/**
	 * 오늘날짜에서 해당 일수 만큼 가감하여 String 날짜형식으로 반환 
	 * 
	 * @param day
	 * @return
	 */
	public static String getDateString(int day) {

		return getDateString(day, "yyyyMMdd");

	}

	public static String getDateString(int day, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, java.util.Locale.KOREA);
		Date dt = new Date();
		dt.setTime(dt.getTime() + ((long) day * 1000 * 60 * 60 * 24));
		return sdf.format(dt);
	}
	
	public static String getAfterMonth(int month) {
		return getAfterMonth(month, "yyyyMM");
	}
	
	public static String getAfterMonth(int month, String format) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(format, Locale.KOREA);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, month);
		return simpleFormat.format(now.getTime());
	}
	
	public static String getBeforeMonth(int month) {
		return getBeforeMonth(month, "yyyyMM");
	}
	
	public static String getBeforeMonth(int month, String format) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(format, Locale.KOREA);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, -month);
		return simpleFormat.format(now.getTime());
	}
	
	public static String getThisMonth() {
		return getThisMonth("yyyyMM");
	}
	
	public static String getThisMonth(String format) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(format, Locale.KOREA);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MONTH, 0);
		return simpleFormat.format(now.getTime());
	}
	
	public static String getBeforeYearMonthByYM(String yearMonth, int minVal){
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		  Calendar cal = Calendar.getInstance();
		  int year = Integer.parseInt(yearMonth.substring(0,4));
		  int month = Integer.parseInt(yearMonth.substring(4,6));

		  cal.set(year, month-minVal, 0);

		  String beforeYear = dateFormat.format(cal.getTime()).substring(0,4); 
		  String beforeMonth = dateFormat.format(cal.getTime()).substring(4,6); 
		  String retStr = beforeYear + beforeMonth;
		  return retStr;
	}

	/**
	 * 현재 월의 날자수 
	 * 
	 * @param 
	 * @return int 날자수(현재 월의 마지막 일자)
	 */
	public static int getThisMonthDates() {
		Calendar calendar = Calendar.getInstance ();
		return calendar.getActualMaximum (Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 해당 월의 날자수 
	 * 
	 * @param 
	 * @return int 날자수(해당 월의 마지막 일자)
	 */
	public static int getMonthDates(String year, String month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set (Integer.parseInt (year), Integer.parseInt(month) - 1, 1);
		return calendar.getActualMaximum (Calendar.DATE);
	}
	
	/**
	 * 두 년월의 개월수 
	 * 
	 * @param 
	 * @return int 개월수
	 */
	public static int getMonthCount(String startYearMonth, String endYearMonth) {
		if(startYearMonth.length() != 6 || endYearMonth.length() != 6) return 0;
		
		int strtYear = Integer.parseInt(startYearMonth.substring(0,4)); 
		int strtMonth = Integer.parseInt(startYearMonth.substring(4,6));
		int endYear = Integer.parseInt(endYearMonth.substring(0,4)); 
		int endMonth = Integer.parseInt(endYearMonth.substring(4,6));
		
		return ((endYear - strtYear) * 12 + (endMonth - strtMonth)) + 1;
	}
	
	public static int monthsBetween(String from, String to, String format)
			throws java.text.ParseException {
			java.util.Date fromDate = check(from, format);
			java.util.Date toDate = check(to, format);

			// if two date are same, return 0.
			if (fromDate.compareTo(toDate) == 0)
				return 0;

			java.text.SimpleDateFormat yearFormat =
				new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
			java.text.SimpleDateFormat monthFormat =
				new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
			java.text.SimpleDateFormat dayFormat =
				new java.text.SimpleDateFormat("dd", java.util.Locale.KOREA);

			int fromYear = Integer.parseInt(yearFormat.format(fromDate));
			int toYear = Integer.parseInt(yearFormat.format(toDate));
			int fromMonth = Integer.parseInt(monthFormat.format(fromDate));
			int toMonth = Integer.parseInt(monthFormat.format(toDate));
			int fromDay = Integer.parseInt(dayFormat.format(fromDate));
			int toDay = Integer.parseInt(dayFormat.format(toDate));

			int result = 0;
			result += ((toYear - fromYear) * 12);
			result += (toMonth - fromMonth);

			if (((toDay - fromDay) > 0))
				result += toDate.compareTo(fromDate);

			return result;
		}
	
	/**
	 * 재분류년도 조회
	 * @param endyear
	 * @param keepterm
	 * @return
	 */
	public static String getRedivdeYear(String endyear, String keepterm) {
		
		int year = Integer.parseInt(endyear);
		
		//보존기간별로 재분류년도 조회
		int f_redivdeyy = 0;
		if ("01".equals(keepterm)) { //01	1년
			f_redivdeyy = year + 1 + 1;
		} else if ("03".equals(keepterm)) { //03	3년
			f_redivdeyy = year + 3 + 1;
		} else if ("05".equals(keepterm)) { //05	5년
			f_redivdeyy = year + 5 + 1;
		} else if ("10".equals(keepterm)) { //10	10년
			f_redivdeyy = year + 10 + 1;
		} else if ("20".equals(keepterm)) { //20	30년
			f_redivdeyy = year + 30 + 1;
		} else if ("30".equals(keepterm)) { //30	준영구
			f_redivdeyy = year + 40 + 1;
		} else if ("40".equals(keepterm)) { //40	영구
		}
		
		if (f_redivdeyy > 0) {
			return Integer.toString(f_redivdeyy);
		}
		
		return null;
	}

	
}
