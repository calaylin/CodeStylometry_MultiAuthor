import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import weka.core.Instances;

public class RemoveNonUniqueInstances {
	
	/*
	 * Remove instances that are not unique within authors or across authors
	 * */
	 public static void main(String[] args) throws Exception, IOException, InterruptedException {	    	
	    	
		 	String fileNameSRC = "arffs/"+"filename";	    	
	    	File arffFile1 = new File(fileNameSRC+".arff");
			String finalArff = fileNameSRC+ "_noID_unique.arff";
			String[] s_array;
	    	
			Instances data1 = new Instances(new FileReader(arffFile1));
			data1.setClassIndex(data1.numAttributes()-1);
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
			
			for (int instanceNo=0; instanceNo< data1.numInstances(); instanceNo++){
				int matchCount=0;
				System.out.println("instanceNo: "+instanceNo);
				String array = Arrays.toString(data1.instance(instanceNo).toDoubleArray());
				s_array = array.split(",");
				String[] s_array1 = array.split(",");
				s_array[0]=data1.instance(instanceNo).attribute(0).value(0).toString();
				s_array[s_array1.length-1]=data1.instance(instanceNo).attribute(s_array1.length-1).value(0).toString();
			//	System.out.println("original array1: "+Arrays.toString(s_array));
				


			//	System.out.println("array: "+Arrays.toString(s_array1));
			//	s_array1[0]="instanceID";
				s_array1[s_array1.length-1]="authorName";
			//	System.out.println("array: "+Arrays.toString(s_array1));


				String authName=(data1.classAttribute().value((int)         data1.instance(instanceNo).classValue()));

				System.out.println("authName: "+authName);

				
				
  				//checking for across author matches on a balanced dataset
				for (int instanceNo2=0; instanceNo2< data1.numInstances(); instanceNo2++){
				
					String authName2=(data1.classAttribute().value((int)         data1.instance(instanceNo2).classValue()));

					if(!(authName.equals(authName2))){
					//no check for across author
					//	if(authorNo=authorNo){

						String array2 = Arrays.toString(data1.instance(instanceNo2).toDoubleArray());
						String[] s_array2 = array2.split(",");
					//	s_array2[0]="instanceID";
						s_array2[s_array2.length-1]="authorName";

						
						if(Arrays.equals(s_array1,s_array2)){
							matchCount++;
							System.out.println("there is a match: "+ matchCount);
							instanceNo2=data1.numInstances();
						}						
					}
				}				
				if(matchCount >0){					
					data1.delete(instanceNo);				
				}
				//end across author matches in balanced dataset
			
				
				
				     int counter=0;

					
					for(int i=0;i<s_array1.length;i++) {
						if(!(s_array[i].equals(" 0.0"))) {
						//	System.out.println(s_array[i]);
					        counter++; 
					     }}
						if(counter < 30){
							data1.delete(instanceNo);											
						//	System.out.println("nonZero attribute counter "+counter);
					//		System.out.println("Original array"+Arrays.toString(s_array));
			    //	Util.writeFile(((Arrays.toString(s_array)).replace("[", "")).replace("]", "")+"\n", finalArff, true);
						}											
			}
			
			 BufferedWriter writer = new BufferedWriter(new FileWriter(finalArff));
			 writer.write(data1.toString());
			 writer.flush();
			 writer.close();
	 }
	
}
