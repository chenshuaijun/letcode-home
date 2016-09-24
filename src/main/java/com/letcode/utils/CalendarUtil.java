package com.letcode.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具,请以更好的方式优化升级<br>
 * <b>注意: <i>请不要使用Date()类</i></b> <br>
 * yyyy(年)MM(月)dd(日)HH(时)mm(分)ss(秒) <br>
 * G 年代标志符<br>
 * y 年<br>
 * M 月<br>
 * d 日<br>
 * h 时 在上午或下午 (1~12)<br>
 * H 时 在一天中 (0~23)<br>
 * m 分<br>
 * s 秒<br>
 * S 毫秒<br>
 * E 星期<br>
 * D 一年中的第几天<br>
 * F 一月中第几个星期几<br>
 * w 一年中第几个星期<br>
 * W 一月中第几个星期<br>
 * a 上午 / 下午 标记符 <br>
 * k 时 在一天中 (1~24)<br>
 * K 时 在上午或下午 (0~11)<br>
 * z 时区<br>
 * 除了上述具有实际含义的字母之外，还可以配合诸如：空格、：、-等进行格式化设置。
 * 
 * @author chensj
 *
 */
public class CalendarUtil {
	private static String	defaultDateFormat		= "yyyyMMdd";
	private static String	defaultTimeFormat		= "HHmmss";
	private static String	defaultDateTimeFormat	= "yyyyMMddHHmmss";

	/**
	 * 获取当前时间 <br>
	 * 默认格式 : yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		Calendar cal = Calendar.getInstance();
		return new SimpleDateFormat(defaultDateTimeFormat).format(cal.getTime());
	}

	/**
	 * 获取制定格式的当前日期时间
	 * 
	 * @param dateFormat
	 * @return
	 */
	public static String getCurrentDateTime(String dateFormat) {
		return formatDateTime(getCurrentDateTime(), defaultDateTimeFormat, dateFormat);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String formatDate(String date) {
		return formatDate(date, defaultDateFormat);
	}

	/**
	 * 格式化时间
	 * 
	 * @param time
	 * @return HH:mm:ss
	 */
	public static String formatTime(String time) {
		return formatTime(time, defaultTimeFormat);
	}

	/**
	 * 格式化日期
	 * 
	 * @param datetime
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateTime(String datetime) {
		return formatDateTime(datetime, defaultDateTimeFormat);
	}

	/**
	 * 获取当前时间
	 * 
	 * @param date
	 *            日期时间字符串
	 * @param dateformat
	 * @return 默认格式
	 */
	public static String formatDate(String date, String dateFormat) {
		return formatDateTime(date, defaultDateFormat, dateFormat);
	}

	/**
	 * 获取当前时间
	 * 
	 * @param time
	 *            日期时间字符串
	 * @param timeFormat
	 * 
	 * @return 默认格式
	 */
	public static String formatTime(String time, String timeFormat) {
		return formatDateTime(time, defaultTimeFormat, timeFormat);
	}

	/**
	 * 获取当前时间
	 * 
	 * @param datetime
	 *            日期时间字符串
	 * @param dateformat
	 * @return 默认格式
	 */
	public static String formatDateTime(String datetime, String dateFormat) {
		return formatDateTime(datetime, defaultDateTimeFormat, dateFormat);
	}

	/**
	 * 格式化日期
	 * 
	 * @param datetime
	 *            需要格式化的日期(例如 20150813 / 2015-08-13 / yyyy-mm-dd HH:mm:ss)
	 * @param oldFormat
	 *            原数据格式
	 * @param newFormat
	 *            新数据格式
	 * @return 目标字符串
	 */
	public static String formatDateTime(String datetime, String oldFormat, String newFormat) {
		Date date;
		try {
			date = new SimpleDateFormat(oldFormat).parse(datetime);
			return new SimpleDateFormat(newFormat).format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datetime;
	}

	/**
	 * 使用测方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println("1." + formatDateTime("2015-08-19 23:23:12",
		// "yyyy-mm-dd HH:mm:ss", "yyyy/mm/dd HHmmss"));
		System.out.println("2." + getCurrentDateTime());
		System.out.println("3." + getCurrentDateTime("yyyy-MM-dd"));
		System.out.println("4." + getCurrentDateTime("HH:mm:ss"));
		System.out.println("5." + getCurrentDateTime("yyyy:MM:dd HH-mm-ss"));
		System.out.println("6." + formatDate(getCurrentDateTime("yyyyMMdd"), "yyyy-MM-dd"));
	}
}
