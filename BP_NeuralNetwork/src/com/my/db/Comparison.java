package com.my.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/***
 * 从flight_sha_201511_20163.v2.csv、histweather_201511_20163.csv中获取
 * 
 * *****/
public class Comparison {
	
	
	public final static String INPUT_CSV="E:\\air\\DATA\\1_20160505100559_7go0a\\flight_sha_20161_201604_mod.csv";
	public final static String OUTPUT_CSV="E:\\air\\DATA\\1.csv";
	
	public final static HashSet<String> AIRPORT_HashSet = new HashSet<String>();
	
	public final static HashSet<String> AIRTYPE_HashSet = new HashSet<String>();
	
	//机型
	public final static List<String> AIRTYPE_List = new ArrayList<String>();
    //机场
	public final static List<String> AIRPORT_List = new ArrayList<String>();
	
	
	/***
	 * 用于从CSV文件中获取airport、airtype的种类；
	 * ***/
	public static void getCodeFromCSV(){

		try {  
			BufferedReader reader = new BufferedReader(new FileReader(INPUT_CSV));
			BufferedWriter writer= new BufferedWriter(new FileWriter(OUTPUT_CSV)); 
			
			
            reader.readLine();//读第一行
            String line = null;  
            
           /*****机场、机型编号****/
            while((line=reader.readLine())!=null){
            	  String item[] = line.split(",");          
            		AIRPORT_HashSet.add(item[5]);
                	AIRPORT_HashSet.add(item[7]);
                	AIRTYPE_HashSet.add(item[20]);
               
          
     
            
            }
            
            Iterator<String> iterator =  AIRPORT_HashSet.iterator();
        	while(iterator.hasNext()){
        		AIRPORT_List.add(iterator.next());
        	}
        	
        	 Iterator<String> iterator2 =  AIRTYPE_HashSet.iterator();
         	while(iterator2.hasNext()){
         		AIRTYPE_List.add(iterator2.next());
         	}
         	
         	for(String str : AIRPORT_List){
         		 writer.write(str);  
         		 writer.newLine();  
         	}
         	 writer.newLine();  
        	for(String str : AIRTYPE_List){
        		 writer.write(str);  
        		 writer.newLine();  
        	}
         	
         	 
              
              writer.close();
		
		} catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		
	
	}
	
	public static void main(String args[]){
		getCodeFromCSV();
	//	System.out.println(TimeUtil.timeMinus("11:25", "11:45"));
	}
	

}
