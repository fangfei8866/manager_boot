package com.zhang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
* @ClassName: DateTimeUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 1032543937@qq.com 
* @date 2017年4月6日 下午7:31:47
 */
public class DateTimeUtil {

	private static final String showFormat = "yyyy-MM-dd HH:mm:ss";
	private static final String storeFormat = "yyyyMMddHHmmssSSS";
	
	private static final SimpleDateFormat showFormater = new SimpleDateFormat(
			showFormat);
	private static final SimpleDateFormat storeFormater = new SimpleDateFormat(
			storeFormat);
	private static final SimpleDateFormat formatter1 = new SimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分ss秒");
	private static final SimpleDateFormat formatter2 = new SimpleDateFormat(
			"yyyy年MM月dd日");
	public static final SimpleDateFormat formatter3 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat formatter4 = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final SimpleDateFormat formatter5 = new SimpleDateFormat(
			"yyyy/MM/dd");
	private static final SimpleDateFormat formatter6 = new SimpleDateFormat(
			"MM-dd HH:mm");
	private static final SimpleDateFormat formatter7 = new SimpleDateFormat(
			"yyyyMMddHHmmssmmm");
	private static final SimpleDateFormat formatter8 = new SimpleDateFormat(
			"HH:mm");
	private static final SimpleDateFormat formatter9 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	public DateTimeUtil() {
	}

	/**
	 * java中对日期的加减操作 
	 * *gc.add(1,-1)表示年份减一. 
	 * *gc.add(2,-1)表示月份减一.
	 * *gc.add(3.-1)表示周减一. 
	 * *gc.add(5,-1)表示天减一. 
	 * *以此类推应该可以精确的毫秒吧.没有再试.大家可以试试.
	 * *GregorianCalendar类的add(int field,int amount)方法表示年月日加减. 
	 * *field参数表示年,月.日等.
	 * *amount参数表示要加减的数量. 
	 * @throws ParseException 
	 */
	public static Date addDayTime(int add) throws ParseException {
		Date d = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(5, +add);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		String date = formatter3.format(gc.getTime());
		Date d1 = formatter3.parse(date);
		return d1;
	}

	public static String getStringTime(Long time) {
		String s = "";
		Long t = (System.currentTimeMillis() - time) / 1000;
		if (t < 60) {
			s = t + "秒前";
		} else if (t < 60 * 60 && t > 60) {
			s = t / 60 + "分钟前";
		} else if (t < 60 * 60 * 3 && t > 60 * 60) {
			s = t / 3600 + "小时前";
		} else if (t > 60 * 60 * 3 && t < 60 * 60 * 24 * 365) {
			s = formatter6.format(new Date(time));
		}
		if ((new Date(time)).getYear() != (new Date(System.currentTimeMillis()))
				.getYear()) {
			s = formatter4.format(new Date(time));
		}

		return s;
	}
	
	/**
	 * @Description: app使用时间格式
	 * @param time
	 * @return
	 * @author luozhifeng
	 * @date 2014-12-3 下午6:41:25 
	 * @version V1.0
	 */
	public static String getStringAppTime(Long time) {
		Long currentTimeMillis = System.currentTimeMillis();
		Long t = (currentTimeMillis - time) / 1000;
		if (t < 60) {
			return t + "秒前";
		} else if (t < 60 * 60 && t > 60) {
			return t / 60 + "分钟前";
		} else{
			Calendar c1 = Calendar.getInstance();
			c1.setTime(new Date(time));
			Calendar c2 = Calendar.getInstance();
			c2.setTime(new Date(currentTimeMillis));
			if(t < 60 * 60 * 24 ){
				//同一天
				if(c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH)){
					return "今天" + formatter8.format(new Date(time));
				}else{
					return "昨天" + formatter8.format(new Date(time));
				}
			}else if( t >= 60 * 60 * 24 && t < 60 * 60 * 48 ){
				c2.add(Calendar.DAY_OF_MONTH, -1);
				if(c1.get(Calendar.DAY_OF_MONTH)==c2.get(Calendar.DAY_OF_MONTH)){
					return "昨天" + formatter8.format(new Date(time));
				}
				c2.add(Calendar.DAY_OF_MONTH, 1);
			}
			if(c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)){
				return formatter6.format(new Date(time));
			}else{
				return formatter9.format(new Date(time));
			}
		}
	}

	public static String getStringTime(Long time, SimpleDateFormat fromatter) {
		String s = "";
		Long t = (System.currentTimeMillis() - time) / 1000;
		if (t < 60) {
			s = t + "秒前";
		} else if (t < 60 * 60 && t > 60) {
			s = t / 60 + "分钟前";
		} else if (t < 60 * 60 * 24 && t > 60 * 60) {
			s = t / 3600 + "小时前";
		} else if (t < 60 * 60 * 24 * 30 && t > 60 * 60 *24) {
			s = t / (3600*24) + "天前";
		} else {
			s = formatter4.format(new Date(time));
		}
		return s;
	}

	public static String FormatTime(int time) {
		String s = "";
		if(time/3600>0){
			s = time/3600+"时"+time%3600/60+"分"+time%60+"秒";
		}else if(time%3600/60 >0){
			s = time%3600/60+"分"+time%60+"秒";
		}else{
			s = time%60+"秒";
		}
		return s;
	}
 
	
	public static String getNow() {
		return showFormater.format(new Date());
	}
	
	public static String gettimes(long seconds){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date(seconds*1000-sdf.getTimeZone().getRawOffset());
		//seconds*1000得到毫秒数，再减去时区偏移的毫秒数
		String str = sdf.format(d); 
		System.out.println(seconds+"秒="+str);
		return str;
	}
	
	public static String getRelativeDate(int days) {
		Calendar c = Calendar.getInstance();
		c.set(5, c.get(5) + days);
		StringBuffer sb = new StringBuffer(17);
		sb.append(c.get(1));
		int tmp[] = { c.get(2) + 1, c.get(5), c.get(11), c.get(12), c.get(13),
				c.get(14) };
		for (int i = 0; i < tmp.length - 1; i++)
			sb.append(tmp[i] >= 10 ? "" : "0").append(tmp[i]);

		if (tmp[tmp.length - 1] < 10)
			sb.append("0");
		if (tmp[tmp.length - 1] < 100)
			sb.append("0");
		sb.append(tmp[tmp.length - 1]);
		return sb.toString();
	}

	public static String getNow(String string) {
		return (new SimpleDateFormat(string)).format(new Date());
	}

	public static String getDisplayTime(long time) {
		return showFormater.format(new Date(time));
	}

	public static String getDisplayTime(long time, String string) {
		return (new SimpleDateFormat(string)).format(new Date(time));
	}

	public static String getShowFormat(String time) {
		try {
			if (time == null || time.equals(""))
				time = showFormater.format(new Date());
			else
				time = showFormater.format(storeFormater.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public static long compare(String t1, String t2) {
		return Long.valueOf(t1).longValue() - Long.valueOf(t2).longValue();
	}

	public static int getYear(String time) {
		return Integer.valueOf(time.substring(0, 4)).intValue();
	}

	public static int getMonth(String time) {
		return Integer.valueOf(time.substring(4, 6)).intValue();
	}

	public static int getDate(String time) {
		return Integer.valueOf(time.substring(6, 8)).intValue();
	}

	public static int getHour(String time) {
		return Integer.valueOf(time.substring(8, 10)).intValue();
	}

	public static int getMinute(String time) {
		return Integer.valueOf(time.substring(10, 12)).intValue();
	}

	public static int getSecond(String time) {
		return Integer.valueOf(time.substring(12, 14)).intValue();
	}

	public static int getMilliSencond(String time) {
		return Integer.valueOf(time.substring(14, 17)).intValue();
	}

	public static long getTimeStamp(String time) throws ParseException {
		return storeFormater.parse(time).getTime();
	}

	public static String formatDate1(Date myDate) {
		return formatter1.format(myDate);
	}

	public static String formatDate2(Date myDate) {
		return formatter2.format(myDate);
	}

	public static String formatDate3(Date myDate) {
		return formatter3.format(myDate);
	}

	public static String formatDate4(Date myDate) {
		if(myDate != null){
			return formatter4.format(myDate);
		}
		return null;
	}

	public static String formatDate5(Date myDate) {
		return formatter5.format(myDate);
	}

	public static String formatDate6(Date myDate) {
		return formatter6.format(myDate);
	}

	public static String formatDate7(Date myDate) {
		return formatter7.format(myDate);
	}

	public static long getLongTime(String time) {
		try {
			return showFormater.parse(time).getTime();
		} catch (ParseException ex) {
			return 0L;
		}
	}

	public static String getVersion() {
		return "1.0.1";
	}

	public static int getMajor() {
		return 1;
	}

	public static int getMinor() {
		return 0;
	}

	public static int getRevision() {
		return 1;
	}
	
	public static String getAgoStringTime(Date myDate){
		Long timestr = getLongTime(showFormater.format(myDate));
		return getStringTime(timestr);
	}
	
	public static String secToTime(int time) {  
        String timeStr = null;  
        int hour = 0;  
        int minute = 0;  
        int second = 0;  
        if (time <= 0)  
            return "00:00";  
        else {  
            minute = time / 60;  
            if (minute < 60) {  
                second = time % 60;  
                timeStr = unitFormat(minute) + ":" + unitFormat(second);  
            } else {  
                hour = minute / 60;  
                if (hour > 99)  
                    return "99:59:59";  
                minute = minute % 60;  
                second = time - hour * 3600 - minute * 60;  
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
            }  
        }  
        return timeStr;  
	}  
	  
	public static String unitFormat(int i) {  
	        String retStr = null;  
	        if (i >= 0 && i < 10)  
	            retStr = "0" + Integer.toString(i);  
	        else  
	            retStr = "" + i;  
	        return retStr;  
	} 
}
