package com.SHA;

import java.util.List;

import com.my.config.Modulus;
import com.my.model.Flight;
import com.my.model.Weather;
import com.my.util.TimeUtil;
import com.my.util.WeatherUtil;


/**
 * 归一化
 * **/
public class CanData {
	
	private double month;
	private double day;
	private double week;    
	private double flightTime;        //计划飞行时间
	private double depTime;           //计划出发时间
	private double arrTime;           //计划到达时间
	
	private double airType;           //机型
	private double distKm;            //飞行距离 
	private double depAirport;        //出发机场 
	private double arrAirport;        //到达机场
	
	private double depTemphi;         //出发机场最高温度
	private double depTemplow;        //出发机场最低温度
	private double depDesc;
	private double depWindir;
	private double depWindstrength;   //出发机场风力
	
	private double arrTemphi;         //到达机场最高温度
	private double arrTemplow;        //到达机场最低温度
	private double arrDesc;
	private double arrWindir;
	private double arrWindstrength;   //到达机场风力
		
	private double delays;            //离港延误
	
	private double advDelays;         //前序航班离港延误


	
	private List<?> AIRTYPE_List;
	private List<?> AIRPORT_List;
	
	//加载机场机型List
	public CanData(List<?> AIRTYPE_List,List<?> AIRPORT_List){
	    this.AIRPORT_List = AIRPORT_List;
	    this.AIRTYPE_List = AIRTYPE_List;
	}
	
	/**
	 * 将数据归一化，作为可以训练的数据
	 * 
	 * TimeUtil、WeatherUtil
	 * 
	 * @return 返回flight , dep_weather, arr_weather 可以用于生成csv文件
	 * **/
	public void SetData(Flight flight , Weather dep_weather , Weather arr_weather , int adv_delays){
		
			
	
		this.month = TimeUtil.getMonthByDate(flight.getTimeSeries())*0.08;  
		this.day = TimeUtil.getDayByDate(flight.getTimeSeries())*0.03;
		this.week = TimeUtil.getWeekByDate(flight.getTimeSeries())*0.14;
		this.depTime = TimeUtil.getMinByHHSS(flight.getDepTime())*6.9e-4; 
		this.arrTime = TimeUtil.getMinByHHSS(flight.getArrTime())*6.9e-4; 
		
	/***以下系数可能需要根据实际情况更改***/
		this.airType=Double.parseDouble(AIRTYPE_List.indexOf(flight.getAcft())+1+"")*Modulus.AIRTYPE; //避免从0开始，所以第一个+1
		this.distKm=Double.parseDouble(flight.getDistKm())*Modulus.DISTKM;   //0.0003由最远距离决定
		this.flightTime = Double.parseDouble(flight.getFlyingTime())*0.00285;   
		this.depAirport = Double.parseDouble(AIRPORT_List.indexOf(flight.getDepAirport())+1+"")*Modulus.AIRPORT;
		this.arrAirport = Double.parseDouble(AIRPORT_List.indexOf(flight.getArrAirport())+1+"")*Modulus.AIRPORT;
		
		//出发机场天气
		this.depTemphi = Double.parseDouble(dep_weather.getTemphi())*Modulus.TEMPHI;
	    this.depTemplow = Double.parseDouble(dep_weather.getTemplow())*Modulus.TEMPLOW;  //数据为 -1 到 1 之间
	    this.depDesc = WeatherUtil.getWeaDesc(dep_weather.getDescription()) ;  //获取的数据已经介于 0-1之间
	    this.depWindir = WeatherUtil.getWeaWindir(dep_weather.getWindir()) ;
	    this.depWindstrength = WeatherUtil.getWindStrength(dep_weather.getWindstrength());
	    
	    //到达机场天气
		this.arrTemphi = Double.parseDouble(arr_weather.getTemphi())* Modulus.TEMPHI;
	    this.arrTemplow = Double.parseDouble(arr_weather.getTemplow())* Modulus.TEMPLOW; 
	    this.arrDesc = WeatherUtil.getWeaDesc(arr_weather.getDescription()) ; 
	    this.arrWindir = WeatherUtil.getWeaWindir(arr_weather.getWindir()) ;
	    this.arrWindstrength = WeatherUtil.getWindStrength(arr_weather.getWindstrength());
	    
	    this.advDelays  = adv_delays*0.01;
	  
	
	    //离港延误 小于-30的已经在ValidData中排除，如果delays>120则算作120
		this.delays = TimeUtil.timeMinus(flight.getDepTime(),flight.getActDepTime());  //-30到120之间
		if(delays>120.0){
			delays=120.0;
		}
		//delays = delays*Modulus.DELAYS;
		
	}
	
	/**
	 * 归一化的数据
	 * **/
	@Override
	public String toString() {
		return  month+","+ week + "," + day + "," + flightTime + "," + depTime + "," + arrTime + "," 	
				+ airType + ","+ distKm + "," + depAirport + "," + arrAirport+ "," 
				+ depTemphi + ","+ depTemplow + "," + depDesc+ "," + depWindir + "," +depWindstrength + "," 
				+ arrTemphi + ","+ arrTemplow + "," + arrDesc+ "," + arrWindir + "," +arrWindstrength + "," 
				+ advDelays + ","
				+ delays; 
	}
	
	
	
	



}
