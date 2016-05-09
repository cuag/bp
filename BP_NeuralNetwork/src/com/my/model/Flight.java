package com.my.model;


public class Flight {

	private Integer id;            // 主键
	private String timeSeries;     //日期
	private String flightNo;       //航班�?
	private String carrier;        //航空公司代码
	private String flightNoShort;  //航班�?
	private String depAirport;     //出发机场三字�?
	private String depCity;        //出发城市三字�?
	private String arrAirport;     //目的机场三字�?
	private String arrCity;        //目的城市三字�?
	private String depTime;        //计划出发时间
	private String arrTime;        //计划到达时间
	private String arrday;         //计划到达�?
	private String flyingTime;     //计划飞行时间
	private String actDepTime;     //实际出发时间
	private String actArrTime;     //实际到达时间
	private String actArrday;      //实际到达�?
	private String actFlyingTime;  //实际飞行时间
	private String comment;        //备注
	private String stops;          //经停次数
	private String routing;        //航线
	private String acft;           //机型
	private String distKm;         //距离
	private String opCar;          //是否是实际承运的航班
	
	
	public Flight(){
		
	}


	public Flight(Integer id, String timeSeries, String flightNo,
			String carrier, String flightNoShort, String depAirport,
			String depCity, String arrAirport, String arrCity, String depTime,
			String arrTime, String arrday, String flyingTime,
			String actDepTime, String actArrTime, String actArrday,
			String actFlyingTime, String comment, String stops, String routing,
			String acft, String distKm, String opCar) {
		super();
		this.id = id;
		this.timeSeries = timeSeries;
		this.flightNo = flightNo;
		this.carrier = carrier;
		this.flightNoShort = flightNoShort;
		this.depAirport = depAirport;
		this.depCity = depCity;
		this.arrAirport = arrAirport;
		this.arrCity = arrCity;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.arrday = arrday;
		this.flyingTime = flyingTime;
		this.actDepTime = actDepTime;
		this.actArrTime = actArrTime;
		this.actArrday = actArrday;
		this.actFlyingTime = actFlyingTime;
		this.comment = comment;
		this.stops = stops;
		this.routing = routing;
		this.acft = acft;
		this.distKm = distKm;
		this.opCar = opCar;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}





	public String getTimeSeries() {
		return timeSeries;
	}


	public void setTimeSeries(String timeSeries) {
		this.timeSeries = timeSeries;
	}


	public String getFlightNo() {
		return flightNo;
	}


	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}


	public String getCarrier() {
		return carrier;
	}


	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}


	public String getFlightNoShort() {
		return flightNoShort;
	}


	public void setFlightNoShort(String flightNoShort) {
		this.flightNoShort = flightNoShort;
	}


	public String getDepAirport() {
		return depAirport;
	}


	public void setDepAirport(String depAirport) {
		this.depAirport = depAirport;
	}


	public String getDepCity() {
		return depCity;
	}


	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}


	public String getArrAirport() {
		return arrAirport;
	}


	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}


	public String getArrCity() {
		return arrCity;
	}


	public void setArrCity(String arrCity) {
		this.arrCity = arrCity;
	}


	public String getDepTime() {
		return depTime;
	}


	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}


	public String getArrTime() {
		return arrTime;
	}


	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}


	public String getArrday() {
		return arrday;
	}


	public void setArrday(String arrday) {
		this.arrday = arrday;
	}


	public String getFlyingTime() {
		return flyingTime;
	}


	public void setFlyingTime(String flyingTime) {
		this.flyingTime = flyingTime;
	}


	public String getActDepTime() {
		return actDepTime;
	}


	public void setActDepTime(String actDepTime) {
		this.actDepTime = actDepTime;
	}


	public String getActArrTime() {
		return actArrTime;
	}


	public void setActArrTime(String actArrTime) {
		this.actArrTime = actArrTime;
	}


	public String getActArrday() {
		return actArrday;
	}


	public void setActArrday(String actArrday) {
		this.actArrday = actArrday;
	}


	public String getActFlyingTime() {
		return actFlyingTime;
	}


	public void setActFlyingTime(String actFlyingTime) {
		this.actFlyingTime = actFlyingTime;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getStops() {
		return stops;
	}


	public void setStops(String stops) {
		this.stops = stops;
	}


	public String getRouting() {
		return routing;
	}


	public void setRouting(String routing) {
		this.routing = routing;
	}


	public String getAcft() {
		return acft;
	}


	public void setAcft(String acft) {
		this.acft = acft;
	}


	public String getDistKm() {
		return distKm;
	}


	public void setDistKm(String distKm) {
		this.distKm = distKm;
	}


	public String getOpCar() {
		return opCar;
	}


	public void setOpCar(String opCar) {
		this.opCar = opCar;
	}

}
