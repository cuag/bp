package com.my.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {
	

	 
	/**
	 * 通过日期yyyy-M-d 得到M
	 * **/
	public static int getMonthByDate(String date){
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-d");
		 Calendar cal = Calendar.getInstance();
		 try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		return  cal.get(Calendar.MONTH)+1;
		
	}
	
	
	/**
	 * 通过日期yyyy-M-d 得到week
	 * **/
	public static int getWeekByDate(String date){
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 Calendar cal = Calendar.getInstance();
		 try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		 int w =  cal.get(Calendar.DAY_OF_WEEK)-1;
		 if(w==0)
			 w=7;
		 
		return w;
		
	}
	
	/**
	 * 通过日期yyyy-M-d 得到d
	 * **/
	public static int getDayByDate(String date){
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-d");
		 Calendar cal = Calendar.getInstance();
		 try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		return  cal.get(Calendar.DAY_OF_MONTH);
		
	}
	


	/**
	 * 将时间转换为分钟 HH:ss -> s
	 * **/
	public static int getMinByHHSS(String time){	
		int a,b;
		
		String t[]=null;
		t=time.split(":");
		a=Integer.parseInt(t[0]);
		b=Integer.parseInt(t[1]);
			
		return a*60 + b;
	}
	
	/**
	 * 获取2个HH:SS时间的分钟差值；
	 * **/
	public static int timeMinus (String time1,String time2){
		int a1,a2,b1,b2;
		a1= Integer.parseInt(time1.split(":")[0]);
		a2= Integer.parseInt(time1.split(":")[1]);
		b1= Integer.parseInt(time2.split(":")[0]); 		
		b2= Integer.parseInt(time2.split(":")[1]);
		if(b1<6)   //更加数据得出，如果飞机计划是6点前起飞则为推迟到隔天起飞。
			b1=b1+24;
		
		return b1*60+b2-(a1*60+a2);
	}


	public static void main(String[] args) {
		
	//	d.dateHandle("2016/4/9");
    //   System.out.println(d.getWeek());
   //    System.out.println(d.getMonth());
    //   System.out.println(d.getDay());
   //    System.out.println( d.minuteHandle("23:20", "01:30"));
       
 
   //    System.out.println(getDayByDate("2015-01-29"));
		System.out.println(timeMinus("23:15","00:34"));
    
	}






}
