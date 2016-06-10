package predict;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import config.Modulus;
/**
 * 批量预测
 * **/
public class PredictAll {

	public static void main(String[] args) throws IOException {
	    //Load network configuration from disk:
        MultiLayerConfiguration confFromJson = MultiLayerConfiguration.fromJson(FileUtils.readFileToString(new File("E:\\air\\config\\conf.json")));

        //Load parameters from disk:
        INDArray newParams;
        try(DataInputStream dis = new DataInputStream(new FileInputStream("E:\\air\\config\\coefficients.bin"))){
            newParams = Nd4j.read(dis);
        }

        //Create a MultiLayerNetwork from the saved configuration and parameters
        MultiLayerNetwork savedNetwork = new MultiLayerNetwork(confFromJson);
        savedNetwork.init();
        savedNetwork.setParameters(newParams);       
     
        int num=0;
        int num2=0;
        int num3=0;
        double sum=0;
        BufferedReader reader;
  		try {
  			reader = new BufferedReader(new FileReader("E:\\air\\test\\testData.csv"));
  	
           String line = null;  
           int i = 0;
           while((line=reader.readLine())!=null){
          	  String[] item = line.split(",");
          //	 reader.readLine();
          	  final INDArray input = Nd4j.create(new double[] {      			             	
          			  
                	
                	  Double.valueOf(item[1]),
                	  Double.valueOf(item[2]),
                	  Double.valueOf(item[3]),
                	  Double.valueOf(item[4]),
                	  Double.valueOf(item[5]),
                	   Double.valueOf(item[6]),
                	  Double.valueOf(item[7]),
                	  Double.valueOf(item[8]),
                	  Double.valueOf(item[9]),
                	 Double.valueOf(item[10]),
                	  Double.valueOf(item[11]),
                	  Double.valueOf(item[12]), 
                	  Double.valueOf(item[13]),
                	  Double.valueOf(item[14]),
                	  Double.valueOf(item[15]),
                	  Double.valueOf(item[16]),
                	  Double.valueOf(item[17]),
                	  Double.valueOf(item[18]),
                	  Double.valueOf(item[19])                
                	 
          	  });
          	  
          	  INDArray out = savedNetwork.output(input, false);
              System.out.println(out.getDouble(0));
              double dd = out.getDouble(0)- Double.valueOf(item[20]); 
              sum= sum+dd;
              if(dd>-10&&dd<10){
            	
              num++;
              }
              if(dd>-20&&dd<20){
              	
                  num2++;
                  }
              if(dd>-30&&dd<30){
              	
                  num3++;
                  }
          	  }
          	  
  		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        
    
  	  System.out.println("---------10------------");
  	  System.out.println(num);
  	  System.out.println(num/51341.0);
  	System.out.println("----------20-----------");
  	  System.out.println(num2);
  	  System.out.println(num2/51341.0);
  	System.out.println("----------30-----------");
  	  System.out.println(num3);
  	  System.out.println(num3/51341.0);        
    	System.out.println("----------平均误差-----------");
    	  System.out.println(sum/51341.0);
	}

}
