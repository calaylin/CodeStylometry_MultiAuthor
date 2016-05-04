
	import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
	import java.util.List;


	public class AverageLOC {

	    public static void main(String[] args) throws IOException
		{
	    	String testFolder = "";	
	    	List test_file_paths = Util.listCPPFiles(testFolder); //use this for preprocessing
	    	int counterLOC=0;
	    	int counterChar=0;

	    	for(int i=0; i< test_file_paths.size(); i++)
	    	{
			String fileName = test_file_paths.get(i).toString();  
		//	System.out.println(fileName);
		//	String sourceCode = Util.readFile(fileName);   
			
			BufferedReader br = new BufferedReader(new FileReader(fileName));
    	    String line;
    	    int counterLOCAuth=0;
	    	int counterCharAuth=0;
    	    while ((line = br.readLine()) != null) {
    	    	if((!(line.contains("main("))) && (!(line.trim().length()<3))){
    	    	//	System.out.println("line: "+line);
    	    	counterLOC++;
    	    	counterChar=counterChar+line.length();
    	    	counterLOCAuth++;
    	    	counterCharAuth=counterCharAuth+line.length();
    
    	    }
    	    }
	    	System.out.println("LOC per file: "+ counterLOCAuth);
	    	System.out.println("Number of chars per file: "+ counterCharAuth);
	    	Util.writeFile("LOC per file: "+ counterLOCAuth +"\n", "/Users/aylin/Desktop/results2.txt",true);
	    	Util.writeFile("Number of chars per file: "+ counterCharAuth +"\n", "/Users/aylin/Desktop/results1.txt",true);


	  //    System.out.println(sourceCode.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)",""));
			//does not catch Gleb.kalachev's comments, removed them manually.  Has a lot of commented code.
		//	Util.writeFile(sourceCode.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)",""), fileName, false);
	    	}
	      	System.out.println("Average number of LOC per file"+ counterLOC/test_file_paths.size());
	    	System.out.println("Average number of chars per file"+ counterChar/test_file_paths.size());
	    	System.out.println("no files"+test_file_paths.size());

		}
	    	
	}

