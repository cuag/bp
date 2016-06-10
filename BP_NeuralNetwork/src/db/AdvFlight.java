package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import util.TimeUtil;
import model.Flight;

public class AdvFlight {
	

		public static final String url = "jdbc:mysql://localhost:3306/air";;
		public static final String name = "com.mysql.jdbc.Driver";
		public static final String user = "root";
		public static final String password = "123";
		
		public static void main(String args[]) throws ClassNotFoundException, SQLException{
			
			Class.forName(name);
			Connection conn = DriverManager.getConnection(url, user, password);

			/******* 从数据库读取机型列表 AIRTYPE_List ********/
			String sql_advair = "SELECT * FROM tb_flight WHERE OpCar = 'O' GROUP BY tb_flight.FlightNo";

			PreparedStatement pst_advair = conn.prepareStatement(sql_advair);

			ResultSet result = pst_advair.executeQuery();
		    Map map =  new HashMap(); 
		    
			while (result.next()) {
			Flight	flight = new Flight(result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4),
						result.getString(5), result.getString(6),
						result.getString(7), result.getString(8),
						result.getString(9), result.getString(10),
						result.getString(11), result.getString(12),
						result.getString(13), result.getString(14),
						result.getString(15), result.getString(16),
						result.getString(17), result.getString(18),
						result.getString(19), result.getString(20),
						result.getString(21), result.getString(22),
						result.getString(23));
			map.put(result.getString(3), flight);
			}			
			System.out.println("共有"+map.size()+"个航班号");
			Iterator iter = map.entrySet().iterator();
			int count = 0;
			while(iter.hasNext()){
				Entry e  =  (Entry) iter.next();
				Flight f = (Flight) e.getValue();
				String adv_flightNo = f.getCarrier() + (Integer.parseInt(f.getFlightNoShort())-1);
				if(map.containsKey(adv_flightNo)){
					
					Flight af = (Flight) map.get(adv_flightNo);
					
					 //到达机场 =出发机场   机型一样
					if(af.getArrAirport().equals(f.getDepAirport())&&af.getAcft().equals(f.getAcft())){   
						
				    int adv_time = TimeUtil.timeMinus(af.getArrTime(),f.getDepTime());
					 //两个航班到达出发时间差
				    if(adv_time>50&&adv_time<200){
						String str = f.getFlightNo() +","+ af.getFlightNo() +"," +af.getDepTime()+","+af.getArrTime()+","+ f.getDepTime()+","+f.getArrTime()
								+","+af.getDepAirport()+","+af.getArrAirport()+","+f.getDepAirport()+","+f.getArrAirport()+","+adv_time;
						System.out.println(str);
						count++;
					}
					}
				}
			
		//	System.out.println(f.getFlightNo());
			}
			Flight flight  = (Flight) map.get("abc");
			System.out.println(count);
			
		}
	

}
