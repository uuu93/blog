package org.lxb.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 说明：日期处理 创建人：FH Q313596790 修改时间：2015年11月24日
 * 
 * @version
 */
public class DateUtil
{

	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getSdfTimes()
	{
		return sdfTimes.format(new Date());
	}

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear()
	{
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay()
	{
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays()
	{
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime()
	{
		return sdfTime.format(new Date());
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws
	 * @author fh
	 */
	public static boolean compareDate(String s, String e)
	{
		if (fomatDate(s) == null || fomatDate(e) == null)
		{
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(时间比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws
	 * @author fan
	 */
	public static boolean compareTime(String s, String e)
	{
		if (fomatTime(s) == null || fomatTime(e) == null)
		{
			return false;
		}
		return fomatTime(s).getTime() >= fomatTime(e).getTime();
	}

	/**
	 * 格式化时间
	 * 
	 * @return
	 */
	public static Date fomatTime(String date)
	{
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			return fmt.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date)
	{
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			return fmt.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s)
	{
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			fmt.parse(s);
			return true;
		}
		catch (Exception e)
		{
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime, String endTime)
	{
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			// long aa=0;
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
			return years;
		}
		catch (Exception e)
		{
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr)
	{
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try
		{
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days)
	{
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDateyyyyMMdd(int days)
	{
		
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();
		
		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdfd.format(date);
		
		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days)
	{
		int daysInt = Integer.parseInt(days);
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);
		return dateStr;
	}

	/**
	 * UTC时间转为本地时间
	 * 
	 * @param utcTime
	 * @param utcTimePatten
	 * @param localTimePatten
	 * @return
	 */
	public static String utcToLocal(String utcTime, String utcTimePatten, String localTimePatten)
	{
		SimpleDateFormat utcFormater = new SimpleDateFormat(utcTimePatten);
		utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date gpsUTCDate = null;
		try
		{
			gpsUTCDate = utcFormater.parse(utcTime);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		SimpleDateFormat localFormater = new SimpleDateFormat(localTimePatten);
		localFormater.setTimeZone(TimeZone.getDefault());
		String localTime = localFormater.format(gpsUTCDate.getTime());
		return localTime;
	}

	/**
	 * 根据指定格式解析日期字符串
	 */
	public static Date parse(String patten, String date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		try
		{
			return sdf.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据指定格式,格式化日期对象
	 */
	public static String format(String patten, Date date) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		return sdf.format(date);
	}

	/**
	 * 获得两个时间之间相差的时间间隔（秒）
	 */
	public static long getSecond(Date d1, Date d2)
	{
		return Math.abs((d1.getTime() - d2.getTime()) / 1000);
	}

	/**
	 * 倒计时为 小时制的
	 */
	public static String time2Date(long time)
	{
		if (time <= 0)
		{
			return "00:00:00";
		}

		String timeCount = "";
		long hourc = time / 3600000;
		String hour = "0" + hourc;

		if (time >= 360000000)
		{
			hour = hour.substring(hour.length() - 3, hour.length());
		}
		else
		{
			hour = hour.substring(hour.length() - 2, hour.length());
		}

		long minuec = (time - hourc * 3600000) / (60000);
		String minue = "0" + minuec;
		minue = minue.substring(minue.length() - 2, minue.length());

		long secc = (time - hourc * 3600000 - minuec * 60000) / 1000;
		String sec = "0" + secc;
		sec = sec.substring(sec.length() - 2, sec.length());
		timeCount = hour + ":" + minue + ":" + sec;
		return timeCount;
	}

	/**
	 * 获取相对于当前时间的第几周的开始时间 wangyue 2016年8月24日
	 */
	public static Date getWeekDayStart(int count)
	{
		Calendar currentDate = new GregorianCalendar();
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);
		currentDate.add(Calendar.DATE, 7 * count);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return currentDate.getTime();
	}

	/**
	 * 获取相对于当前时间的第几月的开始时间 wangyue 2016年8月24日
	 */
	public static Date getMonthDayStart(int count)
	{
		Calendar currentDate = new GregorianCalendar();
		currentDate.add(Calendar.MONTH, count);
		currentDate.set(Calendar.DAY_OF_MONTH, 1);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}

	public static void main(String[] args)
	{
		getWeekDayStart(1);
		getWeekDayStart(-1);
		getWeekDayStart(-2);
	}
}
