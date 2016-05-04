import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import weka.core.Instances;

public class AggregateInstances {
	
	/*
	 * read in instances
	 * 1) write features to a new file
	 * 2) write inst per class to new arff for authors classes
	 * 
	 * for aggregation: read feature vector except the first and last vector
	 * add attribute values for merging instances and divide by setSize
	 * once complete write the aggregate to new arff
	 * */
	 public static void main(String[] args) throws Exception, IOException, InterruptedException {

		 	int setSize =2; //merge setSize instances into one instance
			int inst = 5; //total number of possible instances for each class
			int authors = 14;  //number of classes in the dataset
	    	boolean order = false; //false for mixed aggregation, true for in order aggregation

	    	String fileName = "";
	    	File arffFile1 = new File(fileName+".arff");
	    	
	    	String finalArff = null;
	    	if(order == false){
			 finalArff = fileName+"_mixedMerged"+setSize+".arff";}

	    	if(order == true){
			 finalArff = fileName+"_orderedMerged"+setSize+".arff";}
	    	
			Instances data1 = new Instances(new FileReader(arffFile1));
			
			Util.writeFile("@relation merged"+finalArff+"\n", finalArff, true);

			for(int j=1; j< data1.numAttributes(); j++){
		    	Util.writeFile(data1.attribute(j).toString()+"\n", finalArff, true);
				}

	    	Util.writeFile("@data"+"\n", finalArff, true);

			for (int instanceNo=0; instanceNo<(authors*inst); instanceNo++){
				System.out.println(instanceNo);
				String[] array = new String[data1.numAttributes()];
				double[][] array1 = new double[authors*inst][data1.numAttributes()];

		    	if(order == false){
		    		//mixed aggregation
				for(int i=((instanceNo/inst)*setSize*inst)+(instanceNo%inst); i<((setSize*inst*((instanceNo/inst)+1)));i=i+inst){
				
					System.out.println(instanceNo + " vs i"+ i);
				array[(data1.numAttributes())-1]= data1.instance(i).stringValue(data1.numAttributes()-1).toString();
				System.out.println("auth "+array[(data1.numAttributes())-1]);
				

				for(int j=1; j< data1.numAttributes()-1; j++){
					array1[instanceNo][j]= array1[instanceNo][j]+ 	data1.instance(i).value(j);						    	
		    }		  				
			}

			for(int j=1; j< data1.numAttributes()-1; j++){
	    	Util.writeFile(array1[instanceNo][j]+",", finalArff, true);
			}
	    	Util.writeFile(array[data1.numAttributes()-1]+"\n", finalArff, true);

			
			}
		    	
		    	
		    	if(order == true){
				//in order aggregation
			for(int i=(instanceNo*setSize); i<(setSize*(instanceNo+1));i++){
				
					System.out.println(instanceNo + " vs i"+ i);
				array[(data1.numAttributes())-1]= data1.instance(i).stringValue(data1.numAttributes()-1).toString();
				System.out.println("auth "+array[(data1.numAttributes())-1]);
				

				for(int j=1; j< data1.numAttributes()-1; j++){
					array1[instanceNo][j]= array1[instanceNo][j]+ 	data1.instance(i).value(j);						    	
		    }		  				
			}

			for(int j=1; j< data1.numAttributes()-1; j++){
	    	Util.writeFile(array1[instanceNo][j]+",", finalArff, true);
			}
	    	Util.writeFile(array[data1.numAttributes()-1]+"\n", finalArff, true);

			
			}
		    	
			
			}
	 }
	
}
