package com.ADV;

import com.my.model.Flight;
import com.my.util.TimeUtil;

public class ValidData {
	

	//状态17 是否是实际承运航班23  离港延误
	public static boolean validData(Flight flight){
		String comment = flight.getComment();
		String opCar = flight.getOpCar();
		String [] vali_comment ={"取消","未知","已備降","已排班","n/a","正在滑行","途中"};
		for(String str : vali_comment){
			if(str.equals(comment)){
			
				return false;
			}
		}
	
		if(!flight.getDepAirport().equals("SHA")){
	
			return false;
		}
		
		if(opCar.equals("N")){
		
			return false;
		}
		
		if(TimeUtil.timeMinus(flight.getDepTime(),flight.getActDepTime())<-30){
		
			return false;
		}
	
		
		//按照1200KM/S，即20KM/s来算
		int actFlyTime = Integer.parseInt(flight.getActFlyingTime());
		int distKm = Integer.parseInt(flight.getDistKm());
		if(actFlyTime ==0){
			return false;
		}
		if(distKm/actFlyTime>20){
			return false;
		}
		
		return true;
	}
	
	public static boolean validData(String comment){
		
//		String opCar = flight.getOpCar();
		String [] vali_comment ={"取消","未知","已備降","已排班","n/a"};
		for(String str : vali_comment){
			if(str.equals(comment)){
				return false;
			}
		}
		
		
		/*	if(opCar.equals("N"))
			return false;
		
		if(TimeUtil.timeMinus(flight.getDepTime(),flight.getActDepTime())<-30)
			return false;*/
		
		return true;
	}
	
	public static boolean checkADVflight(Flight advf,Flight f){
		
	//	if(f.getAcft().equals(advf.getAcft())&&f.getDepAirport().equals(advf.getArrAirport())){
			int adv = TimeUtil.timeMinus(advf.getArrTime(), f.getDepTime());
			
			if(adv>29&&adv<200){
				return true;
			}
	//	}
		
		
		return false;
	}
	
	public static void main(String argsp[]){
		Flight flight = new Flight();
	//	flight.setComment("已排班");
		flight.setDepAirport("SHA");
		System.out.println(validData(flight));
		
		
	}

}
