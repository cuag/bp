package com.my.predict;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import com.my.config.Modulus;
import com.my.model.PredictData;

/**
 * 预测
 * **/
public class Predict {
	
	
	public static double predict(PredictData predictData) throws IOException{
		//Load network configuration from disk:
        MultiLayerConfiguration confFromJson = MultiLayerConfiguration.fromJson(FileUtils.readFileToString(new File("re\\conf.json")));

        //Load parameters from disk:
        INDArray newParams;
        DataInputStream dis = new DataInputStream(new FileInputStream("re\\coefficients.bin"));
         newParams = Nd4j.read(dis);
 
        //Create a MultiLayerNetwork from the saved configuration and parameters
        MultiLayerNetwork savedNetwork = new MultiLayerNetwork(confFromJson);
        savedNetwork.init();
        savedNetwork.setParameters(newParams);    
        
        final INDArray input = Nd4j.create(new double[] {      			             	
    			  
          	  Double.valueOf(predictData.getMonth()),
          	  Double.valueOf(predictData.getWeek()),
          	  Double.valueOf(predictData.getFlightTime()),
          	  Double.valueOf(predictData.getDepTime()),
          	  Double.valueOf(predictData.getArrTime()),
          	  Double.valueOf(predictData.getAirType()),
          	   Double.valueOf(predictData.getDistKm()),
          	  Double.valueOf(predictData.getDepAirport()),
          	  Double.valueOf(predictData.getArrAirport()),
          	  Double.valueOf(predictData.getDepTemphi()),
          	 Double.valueOf(predictData.getDepTemplow()),
          	  Double.valueOf(predictData.getDepDesc()),
          	  Double.valueOf(predictData.getDepWindir()), 
          	  Double.valueOf(predictData.getDepWindstrength()),
        	  Double.valueOf(predictData.getArrTemphi()),
           	 Double.valueOf(predictData.getArrTemplow()),
           	  Double.valueOf(predictData.getArrDesc()),
           	  Double.valueOf(predictData.getArrWindir()), 
           	  Double.valueOf(predictData.getArrWindstrength()),
       
    	  });
  	  
        INDArray out = savedNetwork.output(input, false);
        
        return out.getDouble(0);
		
	}

	public static void main(String[] args) throws IOException {
		PredictData predictData = new PredictData();
		System.out.println(predict(predictData));
   
         

	}

}
