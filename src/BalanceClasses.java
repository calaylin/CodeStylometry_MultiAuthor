import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import weka.core.Instances;

public class BalanceClasses {
	
	/*
	 * Each class ends up having setSize number of instances.
	 * All the instances are ordered by class
	 * */
	 public static void main(String[] args) throws Exception, IOException, InterruptedException {

		 	String arffSRC = "arffs/"+"";
	    	File arffFile1 = new File(arffSRC+".arff");
			String finalArff = arffSRC+"_orderedClasses.arff";
			int noClasses=49;
			int setSize =90; //instances per class

			Instances data1 = new Instances(new FileReader(arffFile1));
			Instances data = new Instances(new FileReader(arffFile1));

			//balance classes
			
			/*    	Instances mergedData = Instances.mergeInstances( data1 ,data2);  
	    	ArffSaver saver = new ArffSaver();
	    	saver.setInstances(mergedData);
	    	saver.setFile(new File(finalArff));
	    	saver.setDestination(new File(finalArff));
	    	saver.writeBatch();*/

	    	//the following does not scale
/*			BufferedWriter writer = new BufferedWriter(new FileWriter(finalArff));
			writer.write(data1.toString());
			writer.flush();
			writer.close();*/
			
			data1.setClassIndex(data1.numAttributes() - 1);			
			String [] temp= data1.attribute(data1.numAttributes() - 1).toString().split(",");
			for(int i=0; i< temp.length;i++){
				temp[i]=data1.classAttribute().value(i);
			}
	//		System.out.println(data1.attribute(data1.numAttributes() - 1).value(data1.numAttributes()-1).toString());
			int[] counter = new int [noClasses];
			int index;
			for(int i=0; i< temp.length; i++){
				 index = Arrays.asList(temp).indexOf(temp[i]);
				System.out.println("index:"+index);
			}
			for (int instanceNo=data1.numInstances()-1; instanceNo>=0; instanceNo--){
		//		for (int instanceNo=1; instanceNo>=0; instanceNo--){


		//		String authName=(data1.classAttribute().value((int)         data1.instance(instanceNo).classValue()));
				String authName =  data1.instance(instanceNo).stringValue(data1.numAttributes()-1).toString();
				System.out.println("authName:"+authName);

				 index = Arrays.asList(temp).indexOf(authName);
					System.out.println("index:"+index);
					System.out.println("counter"+Arrays.toString(counter));
				counter[index]=counter[index]+1;

				
				if(counter[index]>setSize){
					data.delete(instanceNo);
					System.out.println(instanceNo +"-deleted" +" counter-"+counter[index]);

				}
			
				System.out.println("authName:"+authName);
				System.out.println("tmpauth:"+temp[index]);
				System.out.println("counter:"+counter[index]);
				System.out.println(instanceNo);

			}
			
			data1 =data;
			data1.setClassIndex(data1.numAttributes()-1);
		
			
			//order classes and write to arff
			Util.writeFile("@relation balanced"+"\n", finalArff, true);

			for(int j=0; j< data.numAttributes(); j++){
		    	Util.writeFile(data.attribute(j).toString()+"\n", finalArff, true);
			}
	    	Util.writeFile("@data"+"\n", finalArff, true);

	    	for(int i = 0; i< data1.numClasses(); i++){
		    	for(int j = 0; j< data.numInstances(); j++){

	    		if((data1.instance(j).stringValue(data1.numAttributes()-1).toString()).equals(temp[i])){
	    	    	Util.writeFile(data.instance(j).toString()+"\n", finalArff, true);
	    		}
	    		
	    	}}

	/*		BufferedWriter writer = new BufferedWriter(new FileWriter(finalArff));
			 writer.write(data.toString());
			 writer.flush();
			 writer.close();*/
			
	 }
	
}
