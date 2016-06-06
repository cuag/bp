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
 *从数据中获取 机型、机场种类
 * 
 * *****/
public class Comparison {
	
	
	public final static String INPUT_CSV="E:\\air\\DATA\\1_20160505100559_7go0a\\flight_sha_20161_201604_mod.csv";

	
	public final static String OUTPUT_AIRPORT = "E:\\air\\DATA\\AIRPORT.csv";
	public final static String OUTPUT_AIRTYPE = "E:\\air\\DATA\\AIRTYPE.csv";
	public final static String OUTPUT_AIRADV = "E:\\air\\DATA\\AIRADV.csv";
	
	public final static HashSet<String> AIRPORT_HashSet = new HashSet<String>();
	
	public final static HashSet<String> AIRTYPE_HashSet = new HashSet<String>();
	
	//机型
	public final static List<String> AIRTYPE_List = new ArrayList<String>();
    //机场
	public final static List<String> AIRPORT_List = new ArrayList<String>();
	
	//前序航班
	
	/***
	 * 用于从CSV文件中获取airport、airtype的种类；
	 * ***/
	public static void getCodeFromCSV(){

		try {  
			BufferedReader reader = new BufferedReader(new FileReader(INPUT_CSV));
		
			BufferedWriter writer_AIRPORT= new BufferedWriter(new FileWriter(OUTPUT_AIRPORT)); 
			BufferedWriter writer_AIRTYPE= new BufferedWriter(new FileWriter(OUTPUT_AIRTYPE)); 
			
			
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
         		writer_AIRPORT.write(str);  
         		writer_AIRPORT.newLine();  
         	}
         	writer_AIRPORT.close();
         	
        	for(String str : AIRTYPE_List){
        		writer_AIRTYPE.write(str);  
        		writer_AIRTYPE.newLine();  
        	}         	        	       
        	writer_AIRTYPE.close();
        	
        	
		
		} catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		
	
	}
	
	public static void main(String args[]){
		getCodeFromCSV();
	//	System.out.println(TimeUtil.timeMinus("11:25", "11:45"));
	}
	

}
