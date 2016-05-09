package com.my.train;

import java.io.IOException;

public class Run {

	public static void main(String[] args) throws IOException {
	
		Train t = new Train();
		double[] learningRate = {1e-5,1.5e-5 };
		int[] nEpochs = {1,10, 100, 200, 500, 1000 };
		int[] batchSize = {200,100,60};
		int[] nHidden = { 20 ,40 ,60 };
	
		for(double c:learningRate)
			for(int a:nEpochs)
				for(int b:batchSize)
					for(int d:nHidden)
						t.train(a, b, c, d);
		
	}

}
