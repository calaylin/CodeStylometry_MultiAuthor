import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.*;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.evaluation.output.prediction.AbstractOutput;
import weka.classifiers.evaluation.output.prediction.PlainText;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.AttributeStats;
import weka.core.Instances;
import weka.core.Range;
import weka.core.Utils;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.RemoveRange;
import weka.filters.unsupervised.instance.RemoveWithValues;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
/*
 * @author Aylin Caliskan-Islam (aylinc@princeton.edu)
 * printing prediction distribution in plaintext works with weka3.7
 * */
public class AuthorClassificationBasic {


	public static void main(String[] args) throws Exception 
	{
		double accuracy=0;
		int endRelax = 1;
		int cores=0; //0 for auto detect num cores
		int numberFolds=2;
		int numFeatures=1; //0 is the default logM+1
		int seedNumber=0;;
		double total =0;
		double average =0;

		String fileName  ="results.txt";				
		String arffFile ="arffs/"+".arff";
		
			  Util.writeFile(numberFolds+"FilesPerAuthor: \n",fileName, true);	
			  for(int relaxPar = 1; relaxPar<=endRelax; relaxPar++){
				  total=0;				  
				  average=0;

				  for(seedNumber=1; seedNumber<2; seedNumber++){


 
		RandomForest cls = new RandomForest();
		Instances data = new Instances(new FileReader(arffFile));
		data.delete(1);
		data.setClassIndex(data.numAttributes() - 1);
		System.out.println("read data");
		data.stratify(numberFolds);
		System.out.println("data stratified");
		System.out.println("instances "+data.numInstances());


		 
	     System.out.println("before building classifier");
		 String[] options = weka.core.Utils.splitOptions("-I 500"+" -num-slots "+cores+" -K "+numFeatures+" -S "+seedNumber);
			cls.setOptions(options);
		
		Evaluation eval=null;
		


		
		StringBuffer predictionSB = new StringBuffer();
		Range attributesToShow = null;
		Boolean outputDistributions = new Boolean(true);

		PlainText predictionOutput = new PlainText();
		predictionOutput.setBuffer(predictionSB);
		predictionOutput.setOutputDistribution(true);


		if(endRelax==1)
		 {eval = new Evaluation(data);}
		
		System.out.println(predictionOutput.getBuffer());
		
		
		eval.crossValidateModel(cls, data,numberFolds , new Random(seedNumber),predictionOutput, attributesToShow,
		        outputDistributions);
		System.out.println(predictionOutput.getBuffer());


	     accuracy=eval.pctCorrect();
	     total =total+accuracy;
	     average = total/seedNumber;

		  System.out.println("accuracy is "+eval.pctCorrect());
		  System.out.println("\nThe accuracy with  is "+eval.pctCorrect()+", relaxed by, "+relaxPar+", \n");
		  System.out.println("total is "+total);
		  System.out.println("avg is "+average);
		  System.out.println("accuracy is "+accuracy);
		  System.out.println("\nThe average accuracy with "+numberFolds+"files is "+average+"\n");	


	     }}	
	}
}