package ALL;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.util.Map;

import util.TimeUtil;
import util.ValidData;
import model.Flight;
import model.Weather;
/**
 * 数据库air list_airtype表中存放机型对照表 list_airport存放机场对照表
 * 连接数据库生成训练数据。
 * **/
public class advDBcon {

	// 数据库配置文件
	public static final String url = "jdbc:mysql://localhost:3306/air";;
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123";

	public static String data_log = "*********缺失天气数据***********\n";

	public static int num = 0; // 计数

	// 机型
	public final static List<String> AIRTYPE_List = new ArrayList<String>();
	// 机场
	public final static List<String> AIRPORT_List = new ArrayList<String>();
	

	
	private static BufferedWriter bfwrite;

	public static void main(String args[]) throws ClassNotFoundException,
			SQLException, IOException {
		
		bfwrite = new BufferedWriter(new FileWriter(
				"E:\\air\\SHA\\trainData.csv"));

		Class.forName(name);// 指定连接类型
		Connection conn = DriverManager.getConnection(url, user, password);// 获取连接

		/******* 从数据库读取机型列表 AIRTYPE_List ********/
		String sql_AIRTYPE = "SELECT * FROM list_airtype";

		PreparedStatement pst_AIRTYPE = conn.prepareStatement(sql_AIRTYPE);

		ResultSet r_AIRTYPE = pst_AIRTYPE.executeQuery();
		while (r_AIRTYPE.next()) {
			AIRTYPE_List.add(r_AIRTYPE.getString(1));
		}
		pst_AIRTYPE.close();

		/********* 从数据库读取机场列表 AIRPORT_List ************/
		String sql_AIRPORT = "SELECT * FROM list_airport";

		PreparedStatement pst_AIRPORT = conn.prepareStatement(sql_AIRPORT);

		ResultSet r_AIRPORT = pst_AIRPORT.executeQuery();
		while (r_AIRPORT.next()) {
			AIRPORT_List.add(r_AIRPORT.getString(1));
		}
		pst_AIRPORT.close();
		
	
		/****************** END *****************************/

		  
		
		/***************** 将List加载到CanData用于数据处理 ********************/
		CanData_ALL canData = new CanData_ALL(AIRTYPE_List, AIRPORT_List);

		/**************** 数据查询 *******************/
		//String sql = "SELECT * FROM tb_flight";
		String sql = "SELECT * FROM tb_train";
		PreparedStatement pst = conn.prepareStatement(sql);// 准备执行语句
		ResultSet result = pst.executeQuery();
		Flight flight;
		
		Weather dep_weather = new Weather();
		Weather arr_weather = new Weather();
	
		int adv_count = 0;
		
		while (result.next()) {

			flight = new Flight(result.getInt(1), result.getString(2),
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

	
			// 排除"取消","未知","已備降","已排班","n/a"情况	
			if (ValidData.validData(flight)) {			
				
				/****前序航班到港****/
				
				int adv_delays = 0;  //默认前序航班到港延误为0 
				
					String sql_advf = "SELECT * FROM tb_train WHERE TimeSeries = '"+flight.getTimeSeries()
							+"' AND FlightNo= '"+flight.getCarrier()+(Integer.parseInt(flight.getFlightNoShort())-1)+"'"
							+" AND ArrAirport='"+flight.getDepAirport()+"'" 
							+" AND Acft='"+flight.getAcft()+"'";
					System.out.println(flight.getFlightNo());
					System.out.println(sql_advf);
					PreparedStatement pst_advf = conn.prepareStatement(sql_advf);
					ResultSet result_adv = pst_advf.executeQuery();
					if(result_adv.next()) {
						Flight advf = new Flight(result_adv.getInt(1), result_adv.getString(2),result_adv.getString(3), result_adv.getString(4),
								result_adv.getString(5), result_adv.getString(6),result_adv.getString(7), result_adv.getString(8),
								result_adv.getString(9), result_adv.getString(10),result_adv.getString(11), result_adv.getString(12),
								result_adv.getString(13), result_adv.getString(14),result_adv.getString(15), result_adv.getString(16),
								result_adv.getString(17), result_adv.getString(18),result_adv.getString(19), result_adv.getString(20),
								result_adv.getString(21), result_adv.getString(22),result_adv.getString(23));
						
						int adv = TimeUtil.timeMinus(advf.getArrTime(), flight.getDepTime());
						
						if(adv>29&&adv<200){
							System.out.println(true);
							adv_delays =  TimeUtil.timeMinus(advf.getArrTime(), advf.getActArrTime());
		                	adv_count++;
						}
	               
						
						
					}
					
																			
				
				/**** 查询天气 ****/
				String sql_depW = "SELECT * FROM tb_weather WHERE date='"
						+ flight.getTimeSeries() + "' AND city='"
						+ flight.getDepCity() + "'";// 出发城市天气
				String sql_arrW = "SELECT * FROM tb_weather WHERE date='"
						+ flight.getTimeSeries() + "' AND city='"
						+ flight.getArrCity() + "'";// 到达城市天气

				PreparedStatement pst_dw = conn.prepareStatement(sql_depW);
				ResultSet result_dw = pst_dw.executeQuery();
				if (result_dw.next()) {
					dep_weather.setCity(result_dw.getString(1));
					dep_weather.setDate(result_dw.getString(3));
					dep_weather.setTemphi(result_dw.getString(4));
					dep_weather.setTemplow(result_dw.getString(5));
					dep_weather.setDescription(result_dw.getString(6));
					dep_weather.setWindir(result_dw.getString(7));
					dep_weather.setWindstrength(result_dw.getString(8));
				} else {
					dep_weather.setCity(null);
					dep_weather.setDate(null);
					dep_weather.setTemphi("0.0");
					dep_weather.setTemplow("0.0");
					dep_weather.setDescription("0.0");
					dep_weather.setWindir("0.0");
					dep_weather.setWindstrength("微风"); // 为减小计算影响，这里设置为微风
					data_log = data_log + "id=" + result.getString(1)
							+ " TimeSeries=" + result.getString(2)
							+ " DepCity=" + result.getString(7) + "\n";
				}
				pst_dw.close();

				PreparedStatement pst_aw = conn.prepareStatement(sql_arrW);
				ResultSet result_aw = pst_aw.executeQuery();

				if (result_aw.next()) {
					arr_weather.setCity(result_aw.getString(1));
					arr_weather.setDate(result_aw.getString(3));
					arr_weather.setTemphi(result_aw.getString(4));
					arr_weather.setTemplow(result_aw.getString(5));
					arr_weather.setDescription(result_aw.getString(6));
					arr_weather.setWindir(result_aw.getString(7));
					arr_weather.setWindstrength(result_aw.getString(8));
				} else {
					arr_weather.setCity(null);
					arr_weather.setDate(null);
					arr_weather.setTemphi("0.0");
					arr_weather.setTemplow("0.0");
					arr_weather.setDescription("0.0");
					arr_weather.setWindir("0.0");
					arr_weather.setWindstrength("微风"); // 为减小计算影响，这里设置为微风

					data_log = data_log + "id=" + result.getString(1)
							+ " TimeSeries=" + result.getString(2)
							+ " ArrCity=" + result.getString(9) + "\n";
				}
				pst_aw.close();
				/******* END *********/

				/***** 调用CanData处理数据 ****/
				canData.SetData(flight, dep_weather, arr_weather, adv_delays);
				System.out.println(canData.toString());
				num++;
				bfwrite.write(canData.toString()+","+flight.getId());
				bfwrite.newLine();
				bfwrite.flush();
			}// if

		}// while

		pst.close();
		conn.close();
		
	//	System.out.println(data_log);
		System.out.println(adv_count);
		System.out.println(num);
	}

}
