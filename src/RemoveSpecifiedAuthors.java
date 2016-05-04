import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import weka.core.Instances;

public class RemoveSpecifiedAuthors {
	
	/*
	 * 
	 * Remove authorToRemove from dataset
	 * */
	 public static void main(String[] args) throws Exception, IOException, InterruptedException {



	    	String fileNameSRC = "filename";
	    	File arffFile1 = new File(fileNameSRC+".arff");
	    	String fileNameDEST = fileNameSRC+"_subset.arff";
	    	String authorToRemove ="className";
	    	
			BufferedReader br = new BufferedReader(new FileReader(arffFile1));
    	    String line;
    	    int counter=0;
    	    while ((line = br.readLine()) != null) {
    	    	counter++;
    	    if(!(line.endsWith(authorToRemove))){
    	    	System.out.println("line: "+counter);
    	    	if(line.length()>10){
    	    		//to check author order
    	    	System.out.println("line: "+line.substring(line.length()-10, line.length()));
    	    	}
    	    	//Util.writeFile(line + "\n", fileNameDEST, true);
    	    	}			
    	    if((line.endsWith(authorToRemove))){
    	    	System.out.println("found author at line: "+counter);
	 }
	 }
	
    	    
}}
