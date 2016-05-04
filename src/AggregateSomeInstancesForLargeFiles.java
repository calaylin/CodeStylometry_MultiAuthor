import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import weka.core.Instances;
import org.apache.commons.lang3.StringUtils;

public class AggregateSomeInstancesForLargeFiles {
	
	
	/*
	 * read in instances
	 * 1) write features to a new file
	 * 2) write mergedInst per class to new arff for authors classes
	 * possible number of classes is: inst
	 * 
	 * for aggregation: read feature vector except the first and last vector
	 * add attribute values for merging instances and divide by setSize
	 * once complete write the aggregate to new arff
	 * */
	 public static void main(String[] args) throws Exception, IOException, InterruptedException {

		 	double setSize =10; //merge setSize instances into one instance
	    	boolean order = true;//false for mixed aggregation, true for in order aggregation

	    	String fileName = "githubManySmallSnippets_minLOC5_minFiles90partial_subset";
	    	File arffFile1 = new File(fileName+".arff");
	    	String finalArff = null;
    	    double[] array = new double[2];


	    	if(order == false){
			 finalArff = fileName+"_mixedMerged"+
	    	setSize+"LargeInstancesTrainNormalized.arff";
			 }

	    	if(order == true){
	    		 finalArff = fileName+"_orderedMerged"+
	    			    	setSize+"LargeInstancesTrainNormalized.arff";
	    		 }	    
	    	
	    	BufferedReader br = new BufferedReader(new FileReader(arffFile1));
    	    String line;
    	    int numFeatures=-2;
    	    int instanceCounter=0;

    	    while ((line = br.readLine()) != null) {
    	    	
    	    	if(order == true){

    	    if((line.startsWith("@") && !(line.contains("@attribute instanceID")))){
    	    	Util.writeFile(line + "\n", finalArff, true);
    	    	System.out.println("features: "+numFeatures);
    	    	numFeatures++;
//                Thread.sleep(1000);

    	    	}
    	        	    
    	    if((StringUtils.countMatches(line, ",") == (numFeatures))&&!(line.startsWith("@") )){
        	    double[] arrayLine =new double[numFeatures];        	    
    	    	instanceCounter++;    	    	
				String[] arrayString = line.split(",");
    	    	System.out.println("instanceNo: "+instanceCounter);
    	    	System.out.println("author: "+arrayString[arrayString.length-1]);
    	    	
				if(((instanceCounter-1)%setSize) < setSize){
					
				for(int j=1; j< numFeatures; j++){
					arrayLine[j-1]=Double.parseDouble(arrayString[j]); 
					if(array.length < numFeatures-1 ){
		        	    array =new double[numFeatures-1];
					}
					array[j-1]= array[j-1]+ arrayLine[j-1];	
					}
				}
					
					if(((instanceCounter-1)%setSize) == (setSize-1)){
						for(int j=1; j< numFeatures; j++){
							Util.writeFile(((array[j-1])/setSize)+",", finalArff, true);							
							}	
						Util.writeFile(arrayString[arrayString.length-1]+"\n", finalArff, true);
		        	    array =new double[numFeatures-1];
		    	    	System.out.println("Wrote instance for author: "+arrayString[arrayString.length-1]);

				}
    	    }    	    
	 }}
    	    br.close();
	
	 }
}
