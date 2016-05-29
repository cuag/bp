package com.my.train;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.deeplearning4j.datasets.iterator.DataSetIterator;
import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.Layer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import com.my.config.Modulus;

/**
 * 被Run调用，用于训练
 * **/
public class Train {
	// Random number generator seed, for reproducability
	public static final int seed = 12345;
	public static final Random rng = new Random(seed);
    //Number of iterations per minibatch
    public static final int iterations = 1; 
    
	private static final String PATH_config = "E:\\air\\config\\";
	public static final int nSamples = 81074;
	private static final String PATH_DATA = "E:\\air\\train\\trainData-5-18.csv";
	public static SplitTestAndTrain trainAndTest;

	private static DataSetIterator getTrainingData(int batchSize) {

		double[] output = new double[nSamples];
		double[] input0 = new double[nSamples];
		double[] input1 = new double[nSamples];
		double[] input2 = new double[nSamples];
		double[] input3 = new double[nSamples];
		double[] input4 = new double[nSamples];
		double[] input5 = new double[nSamples];
		double[] input6 = new double[nSamples];
		double[] input7 = new double[nSamples];
		double[] input8 = new double[nSamples];
		double[] input9 = new double[nSamples];
		double[] input10 = new double[nSamples];
		double[] input11 = new double[nSamples];
		double[] input12 = new double[nSamples];
		double[] input13 = new double[nSamples];
		double[] input14 = new double[nSamples];
		double[] input15 = new double[nSamples];
		double[] input16 = new double[nSamples];
		double[] input17 = new double[nSamples];
		double[] input18 = new double[nSamples];
		double[] input19 = new double[nSamples];

		BufferedReader reader;
		try {

			reader = new BufferedReader(new FileReader(PATH_DATA));
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				String[] item = line.split(",");

				input0[i] = Double.valueOf(item[0]); // 月份
				input1[i] = Double.valueOf(item[1]); // 星期
				input2[i] = Double.valueOf(item[2]); // 日期
				input3[i] = Double.valueOf(item[3]); // 机型
				input4[i] = Double.valueOf(item[4]); // 距离
				input5[i] = Double.valueOf(item[5]); // 计划飞行时间
				input6[i] = Double.valueOf(item[6]); // 出发机场
				input7[i] = Double.valueOf(item[7]); // 到达机场
				input8[i] = Double.valueOf(item[8]); // 计划出发时间
				input9[i] = Double.valueOf(item[9]); // 计划到达时间
				input10[i] = Double.valueOf(item[10]);
				input11[i] = Double.valueOf(item[11]);
				input12[i] = Double.valueOf(item[12]);
				input13[i] = Double.valueOf(item[13]);
				input14[i] = Double.valueOf(item[14]);
				input15[i] = Double.valueOf(item[15]);
				input16[i] = Double.valueOf(item[16]);
				input17[i] = Double.valueOf(item[17]);
				input18[i] = Double.valueOf(item[18]);
				input19[i] = Double.valueOf(item[19]);

				output[i] = Double.valueOf(item[20]); // 离港延误

				i++;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		INDArray inputNDArray0 = Nd4j.create(input0, new int[] { nSamples, 1 });
		INDArray inputNDArray1 = Nd4j.create(input1, new int[] { nSamples, 1 });
		INDArray inputNDArray2 = Nd4j.create(input2, new int[] { nSamples, 1 });
		INDArray inputNDArray3 = Nd4j.create(input3, new int[] { nSamples, 1 });
		INDArray inputNDArray4 = Nd4j.create(input4, new int[] { nSamples, 1 });
		INDArray inputNDArray5 = Nd4j.create(input5, new int[] { nSamples, 1 });
		INDArray inputNDArray6 = Nd4j.create(input6, new int[] { nSamples, 1 });
		INDArray inputNDArray7 = Nd4j.create(input7, new int[] { nSamples, 1 });
		INDArray inputNDArray8 = Nd4j.create(input8, new int[] { nSamples, 1 });
		INDArray inputNDArray9 = Nd4j.create(input9, new int[] { nSamples, 1 });
		INDArray inputNDArray10 = Nd4j.create(input10,
				new int[] { nSamples, 1 });
		INDArray inputNDArray11 = Nd4j.create(input11,
				new int[] { nSamples, 1 });
		INDArray inputNDArray12 = Nd4j.create(input12,
				new int[] { nSamples, 1 });
		INDArray inputNDArray13 = Nd4j.create(input13,
				new int[] { nSamples, 1 });
		INDArray inputNDArray14 = Nd4j.create(input14,
				new int[] { nSamples, 1 });
		INDArray inputNDArray15 = Nd4j.create(input15,
				new int[] { nSamples, 1 });
		INDArray inputNDArray16 = Nd4j.create(input16,
				new int[] { nSamples, 1 });
		INDArray inputNDArray17 = Nd4j.create(input17,
				new int[] { nSamples, 1 });
		INDArray inputNDArray18 = Nd4j.create(input18,
				new int[] { nSamples, 1 });
		INDArray inputNDArray19 = Nd4j.create(input19,
				new int[] { nSamples, 1 });

		INDArray inputNDArray = Nd4j.hstack(inputNDArray0, inputNDArray1,
				inputNDArray2, inputNDArray3, inputNDArray4, inputNDArray5,
				inputNDArray6, inputNDArray7, inputNDArray8, inputNDArray9,
				inputNDArray10, inputNDArray11, inputNDArray12, inputNDArray13,
				inputNDArray14, inputNDArray15, inputNDArray16, inputNDArray17,
				inputNDArray18, inputNDArray19);

		INDArray outPut = Nd4j.create(output, new int[] { nSamples, 1 });
		DataSet dataSet = new DataSet(inputNDArray, outPut);

		trainAndTest = dataSet.splitTestAndTrain(0.7);

		List<DataSet> listDs = trainAndTest.getTrain().asList();
	
		Collections.shuffle(listDs, rng);

		return new ListDataSetIterator(listDs, batchSize);

	}
	
	public void train(int nEpochs,int batchSize,double learningRate,int nHidden) throws IOException{

		System.out.println("当前进行到："+nEpochs+","+batchSize+","+learningRate+","+nHidden);
		//计时
		long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
    	

    	
    	//Generate the training data
        DataSetIterator iterator = getTrainingData(batchSize);
      
        //Create the network
        int numInput = 20;
        int numOutputs = 1;
      
        
        //sigmoid relu  relu + tanh tanh+relu

        

        //.dropOut(0.5)   使得部分权重被随机设置为0，使得网络稀疏化，能够避免过拟合 
        //.weight- decay：权重衰减，也能够避免训练过拟合
        //.activation("tanh")  激活函数
        //.learningRate(0.1)   学习率
        //.iterations(1) 迭代次数用于验证之前的结果
       //.L1 实现稀疏  (趋向于产生少量的特征，而其他的特征都是0)
        //.L2 防止过拟合，提升模型的泛化能力(L2会选择更多的特征，这些特征都会接近于0)。
    
        MultiLayerNetwork net = new MultiLayerNetwork(new NeuralNetConfiguration.Builder()
                .seed(seed)
                .iterations(iterations)                
              .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)   
              //   .optimizationAlgo(OptimizationAlgorithm.LINE_GRADIENT_DESCENT)              
             // .optimizationAlgo(OptimizationAlgorithm.CONJUGATE_GRADIENT)             
              //.optimizationAlgo(OptimizationAlgorithm.HESSIAN_FREE)   
                .learningRate(learningRate)
                .weightInit(WeightInit.XAVIER)//权值初始化
                .updater(Updater.NESTEROVS)   //权值更新方式
           //    .updater(Updater.ADAGRAD)
                .momentum(0.9)  //动量参数 parameter for momentum (used with Updater.NESTEROVS)
                .list(3)
                .layer(0, new DenseLayer.Builder().nIn(numInput).nOut(nHidden) 
                        .activation("tanh")
                        .build())   
                .layer(1, new DenseLayer.Builder().nIn(nHidden).nOut(nHidden)  
                        .activation("sigmoid")
                        .build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                        .activation("identity")                          
                        .nIn(nHidden).nOut(numOutputs).build())           
                .pretrain(false)//是否预先训练
                .backprop(true) //是否支持后向传播
                .build()
        );
        net.init();
     //   net.setListeners(new ScoreIterationListener(1)); //显示

        
        //Train the network on the full data set, and evaluate in periodically
        for( int i=0; i<nEpochs; i++ ){
            iterator.reset();
            net.fit(iterator);
        }
        
      
        try(DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get(PATH_config+"coefficients.bin")))){
            Nd4j.write(net.params(),dos);
        }

        //Write the network configuration:
        FileUtils.write(new File(PATH_config+"conf.json"), net.getLayerWiseConfigurations().toJson());
        
        System.out.println("------------END---------------");
        
        //evaluate the model on the test set
      
        DataSet test = trainAndTest.getTest();
        INDArray output = net.output(test.getFeatureMatrix());
       
        int rnum=0;
        int rnum2=0;
        int rnum3=0;
        
        double num=0.0;
        double average=0.0;
        
        int size = trainAndTest.getTest().getLabels().length();
        for(int i=0;i<size;i++){
        	double label = trainAndTest.getTest().getLabels().getDouble(i);
        	double out = output.getDouble(i);
        	double c = out-label;
        	
        	average = Math.abs(c) + average;
        	num++;
        
       // 	System.out.println(out +"\t" +label+"\t"+c);
        	if(c>-10.0&&c<10.0){
        		rnum++;
        	}
            if(c>-20.0&&c<20.0){
                rnum2++;
            }
            if(c>-30.0&&c<30.0){
                rnum3++;
            }
                        
       
        }
      	   	 
      	 System.out.println("-------------------------------");
      	 System.out.println("训练数据量："+(nSamples-num));
      	 System.out.println("验证数据量："+num);
        System.out.println("-----------平均误差---------------");
        System.out.println(average/num);
        
        System.out.println("----------10分钟误差-------------");
        System.out.println(rnum);
        System.out.println(rnum/(double)size);

        System.out.println("-----------20分钟误差----------------");
        System.out.println(rnum2);
        System.out.println(rnum2/(double)size);
        
        System.out.println("-----------30分钟误差----------------");
        System.out.println(rnum3);
        System.out.println(rnum3/(double)size);
      

     //计时结束
        long endMili=System.currentTimeMillis();
    	System.out.println("总耗时为："+(endMili-startMili)/1000.0+"秒");
    	
    	BufferedWriter bfwrite= new BufferedWriter(new FileWriter("E:\\air\\config\\result.csv",true));
    	
    	String results = nEpochs+","+batchSize+","+learningRate+","+nHidden+","+rnum/(double)size+","
    			+rnum2/(double)size+","+rnum3/(double)size+","+average/num+","+(endMili-startMili)/1000.0;
    	System.out.println(results);
    	bfwrite.write(results);
    	bfwrite.newLine();
    	bfwrite.close();
	}

}
