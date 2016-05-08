import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/*
 * @author Aylin Caliskan-Islam (aylinc@princeton.edu)
 * */


public class EnsemblePredictionsOrderedCleanDistribution{
	

	public static void main(String[] args) throws Exception 
	{
    	String [] arr;
    	Set<String> authorSet = new HashSet<String>();

    	String line="";
    	String lineOrg="";
    	int setSize=9;
    	double counter=0;
    	double accuracyCounter=0;
    	double misclassificationCounter=0;

		String predictionDistribution="";
		BufferedReader reader = new BufferedReader(new FileReader(predictionDistribution));
    	BufferedWriter writer  = new BufferedWriter(new FileWriter(predictionDistribution+"Ordered"));


   		double[] array = new double[120];
   		String authors="";

   		int firstPredictionOffset=3;
   	    	while((line = reader.readLine())!= null){
   	    		
   	    		counter++;
   	    		lineOrg=line;
   	    		line = line.trim();
   	    		line = line.replace("+", "");
   	    		line = line.replace("*", "");
   	    		line = line.replaceAll("\\s+", ",");

   	    		System.out.println(counter);
   	    		arr= line.split(",");
//   	    		System.out.println("line0 "+arr[0]);//instance number
   	    		//System.out.println("line1 "+arr[1]);//original author class and authorname
   	    		authors = authors + " "+ arr[1];
   	    		authorSet.add(arr[1]);
   	    		
   	    	
   	    		//System.out.println("line2 "+arr[2]); // predicted class
//   	    		System.out.println("line3 "+arr[3]);
//   	    		System.out.println("line5 "+arr[firstPredictionOffset]);

   	    	//	System.out.println(arr[1] +": "+StringUtils.countMatches(authors, arr[1]));
   	    
}   	  
   	    	reader.close();
   	    	
   	    	
	    		//writer.write(authors +"\n");
	     Iterator<String> iterator = authorSet.iterator();
	    while(iterator.hasNext()) {
	        String setElement = iterator.next();
	   //  System.out.println(setElement +" "+StringUtils.countMatches(authors, setElement));
			 reader = new BufferedReader(new FileReader(predictionDistribution));
	    		System.out.println(setElement);

   	    	while((line = reader.readLine())!= null){
   	    		
   	    		lineOrg=line;
   	    		line = line.trim();
   	    		line = line.replace("+", "");
   	    		line = line.replace("*", "");
   	    		line = line.replaceAll("\\s+", ",");

   	    		arr= line.split(",");
//   	    		System.out.println("line0 "+arr[0]);//instance number
   	    		//System.out.println("line1 "+arr[1]);//original author class and authorname

   	    		if(!(arr[1].contains("author1")) && !( arr[1].contains("author2"))){
   	    			if(setElement.equals(arr[1])){
   	    	    		System.out.println("writing for: "+setElement);

   	    			writer.write(lineOrg +"\n");
   	    			}
   	    		}   	    	   	    	       	       	    
	}
   	    		reader.close();

	    }		writer.close();
	    
   	    	
	}
	

}