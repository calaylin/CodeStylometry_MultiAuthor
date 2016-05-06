import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;


public class EnsemblePredictions{
	public static void main(String[] args) throws Exception 
	{
    	String [] arr;
    	String line="";
    	int setSize=9;
    	double counter=0;
    	double accuracyCounter=0;
    	double misclassificationCounter=0;

		String predictionDistribution="";
		BufferedReader reader = new BufferedReader(new FileReader(predictionDistribution));
    	BufferedWriter writer  = new BufferedWriter(new FileWriter(predictionDistribution+"Ensemble"+setSize+"Instances.txt"));


   		double[] array = new double[120];

		writer.write("classNo:className,aggregateProbability,predictedClass,originalClass"+"\n");
   		int firstPredictionOffset=3;
   	    	while((line = reader.readLine())!= null){
   	    		counter++;
   	    		line = line.trim();
   	    		line = line.replace("+", "");
   	    		line = line.replace("*", "");
   	    		line = line.replaceAll("\\s+", ",");

   	    		System.out.println(counter);
   	    		arr= line.split(",");
//   	    		System.out.println("line0 "+arr[0]);//instance number
   	    		System.out.println("line1 "+arr[1]);//original author class and authorname
//   	    		System.out.println("line2 "+arr[2]); // predicted class
//   	    		System.out.println("line3 "+arr[3]);
//   	    		System.out.println("line5 "+arr[firstPredictionOffset]);

	
   	    			for(int i=firstPredictionOffset;i< arr.length-1;i++){
   	   	    			array[i-firstPredictionOffset]=array[i-firstPredictionOffset]+Double.parseDouble(arr[i]);
   	   	    		}
   	    			
   	    			double max = 0;  
   	    			int classValue=0;
   	    			
   	    			if(counter%setSize ==0){
   	    				for(int i=firstPredictionOffset;i< arr.length;i++){
   	   	   	    			array[i-firstPredictionOffset]=array[i-firstPredictionOffset]/setSize;
   	   	   	    			arr[i]= Double.toString(array[i-firstPredictionOffset]);
   	   	   	    			if (array[i-firstPredictionOffset] > max) {
   	   	   	    				max = array[i-firstPredictionOffset];
   	   	   	    				classValue= (i-firstPredictionOffset+1);
 	    			    }
   	   	   	    		}
	    	    		//	writer.write(Arrays.toString(arr)+","+max+","+","+classValue+"\n");
   	    				String[] orgClass = arr[1].split(":");
	    	    			writer.write(arr[1]+","+max+","+classValue+","+orgClass[0]+"\n");
	    	    		//System.out.println((arr[1]+","+max+","+classValue+","+orgClass[0]));
	    	   	    	if(Integer.toString(classValue).equals(orgClass[0].trim())){
	    	   	    		accuracyCounter++;
	    	   	    	}
	    	   	    	if(!(Integer.toString(classValue).equals(orgClass[0].trim()))){
	    	   	    		accuracyCounter++;
		    	    		System.out.println((arr[1]+","+max+","+classValue+","+orgClass[0]));
		    	    		System.out.println("FALSE");
		    	    		misclassificationCounter++;
		    	    		System.out.println(misclassificationCounter);
	    	   	    	}
   	    		}		
	}
   	    	System.out.println("Accuracy"+accuracyCounter/(counter/setSize));		
   	    	writer.write("Accuracy:" +accuracyCounter/(counter/setSize));
    		System.out.println("False classifications"+ misclassificationCounter);
    		System.out.println("Total classifications"+ (counter/setSize));   	 	
   	    	reader.close();
   	    	writer.close();
}}