package com.my.util;

/**
 * 把天气description、windir、windstrength 转换为数字（0-1）
 * 
 * **/
public class WeatherUtil {
	
	public WeatherUtil(){
		
	}
	public static double getWindStrength(String str){
		
		String[] descs = {"微风","3","4","5","6","7","8"};
		double[] conLevel = {0.2,0.3,0.4,0.5,0.6,0.7,0.8};
		int i =descs.length-1;
		double r = 0.2;
		for(;i>=0;i--){
	
			if(str.contains(descs[i])){			
				r = conLevel[i];
				break;
			}
		} 
	    	return r;	
	}
	
	
		/**
		 * 把风向描述转换为数字；
		 * 
		 *   无持续风向加权0.05；
		 *   东南风~东北风  r=0.1+0.2/10 = 0.12
		 * 
		 * **/
	public static double getWeaWindir(String str){
		
		String[] descs = {"东南风","东北风","西南风","西北风","东风","西风","南风","北风","旋转不定"};
		double[] conLevel = {0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9};
		double index = 1.0; 
		double r = 0.0; //返回的值
		for(String desc : str.split("~")){	
			for(int i =0;i<descs.length;i++){
				if(desc.equals(descs[i])){
					r = r + conLevel[i]/index;
					index = index*10.0;
				}
			}
		}
		//无持续风向加权0.05；
		if(str.contains("无持续风向"))
			r=r+0.05;
		
	    return r;
	}
	
	
	/**
	 * 把风向描述转换为数字；
	 * 
	 *   虹桥机场为南北风向，所以东西风向影响大
	 *   东南风~东北风  r=0.1+0.2/10 = 0.12
	 * 
	 * **/
	public static double getWeaWindir_SHA(String str){
	
	String[] descs = {"东南风","东北风","西南风","西北风","东风","西风","南风","北风","旋转不定"};
	double[] conLevel = {0.3,0.3,0.3,0.3,0.6,0.6,0.1,0.1,0.3};
	double index = 1.0; 
	double r = 0.0; //返回的值
	for(String desc : str.split("~")){	
		for(int i =0;i<descs.length;i++){
			if(desc.equals(descs[i])){
				r = r + conLevel[i]/index;
				index = index*10.0;
			}
		}
	}
	//无持续风向加权0.05；
	if(str.contains("无持续风向"))
		r=r+0.05;
	
    return r;
}
	
	/****
	 * desc1 天气是累计的
	 * 但其他的天气描述desc2、desc3则取最大的影响
	 * 
	 * ******/
	public static double getWeaDesc(String str){

		String[] desc1 = {"晴","多云","阴","雾","霾","浮尘","扬沙","沙尘暴"}; 
		String[] desc2 = {"暴雨","大雨","中雨","小雨","阵雨","雨夹雪","冻雨","雷阵雨"};
		String[] desc3 = {"暴雪","大雪","中雪","小雪"};
		double[] conLevel1 = {0.1,0.2,0.2,0.2,0.2,0.2,0.2,0.2,0.2};
		double[] conLevel2 = {0.4,0.3,0.25,0.2,0.2,0.4,0.4,0.4};
		double[] conLevel3 = {0.35,0.3,0.25,0.2};
		
		double r = 0.0;
		
		int i = 0;
		for(String desc : desc1){			
			if(str.contains(desc)){				
				r = r+conLevel1[i];		
			}
			i++;
		}
		
		i=0;		
		for(String desc: desc2){		
			if(str.contains(desc)){			
				r = r + conLevel2[i];			
				break;
			}
			i++;
		}
		
		i=0;		
		for(String desc: desc3){
			if(str.contains(desc)){
				r = r + conLevel3[i];	
				break;
			}
			i++;
		}
			
		return r;
	}
	
	public static void main(String args[]){
		
		
		
		//System.out.println(w.getWeaWindir("东南风~无持续风向"));
		String str = "晴雾小雨大雨小雨大雪小雪";
		System.out.println( );
		
	
	//	System.out.println("东南风无持续风向".split("~")[0]);
	}

}
