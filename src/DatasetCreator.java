import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;


public class DatasetCreator 
{

	 


	
		
		
		
		
		
		
		
		public static void copyAuthorsWithLOCFileNumber(String test_cpp_dir, String destinationFolder, int LOC, int minFile, boolean exactFileNumber) throws IOException{
			File destFolderParent = new File(destinationFolder) ;
		  	if(!destFolderParent.exists())
	    	{
				///System.out.println(file.getAbsolutePath());
		  		destFolderParent.mkdirs();
			}
			
		   File file = new File(test_cpp_dir);
		   String ext;
		   String[] directories = file.list(new FilenameFilter() 
		   {
		     @Override
		     public boolean accept(File current, String name) 
		     {
		       return new File(current, name).isDirectory();
		     }
		   });
		   System.out.println(Arrays.toString(directories));
		   
		   
		   for(int j=0; j< directories.length; j++)

		    {
			   int fileCounter=0;
			   int fileCounterExact=0;
			   File code=null;
			   System.out.println(directories[j].toString());
			   String author_cpp_dir = test_cpp_dir + directories[j] +"/";
			   List test_file_paths = Util.listCPPFiles(author_cpp_dir);
			   for(int k=0; k< test_file_paths.size(); k++)
			   {
				    code = new File(test_file_paths.get(k).toString());
				   if(code.isFile() && fileCounter <= minFile){
				   int lines = Util.getLines(code);
				   if(lines > LOC){
					   fileCounter++;}
				   }}
					  
				   for(int i=0; i< test_file_paths.size(); i++)
				   {
					   if(((exactFileNumber == true) && fileCounterExact < minFile ) ||
							   (exactFileNumber == false)   ){
					    code = new File(test_file_paths.get(i).toString());
						   int lines = Util.getLines(code);

					   if(fileCounter >= minFile){
						   if(lines > LOC){
							   fileCounterExact++;				
							   System.out.println(directories[j].toString());
							   ext = FilenameUtils.getExtension(test_file_paths.get(i).toString());
							   File srcTXT= null;
							   File srcDEP= null;
							   File srcAST= null;
							   File destFile = null;
							   File destTXT= null;
							   File destDEP= null;
							   File destAST= null;
							   
						   if(ext.length()==3){
							   System.out.println("extension:"+ext);


						   srcTXT= new File(test_file_paths.get(i).toString().substring(0, code.getPath().length()-3)+"txt");
						   srcDEP= new File(test_file_paths.get(i).toString().substring(0, code.getPath().length()-3)+"txt");
						   srcAST= new File(test_file_paths.get(i).toString().substring(0, code.getPath().length()-3)+"txt");

					   
				      	 destFile = new File(destFolderParent +"/"+ directories[j].toString()
				      			+"/"+ code.getName()) ;

				    	 destTXT = new File(destFolderParent +"/"+ directories[j].toString()
				      			+"/"+ code.getName().substring(0,code.getName().length()-3) +"txt") ;
				    	 destDEP =  new File(destFolderParent +"/"+ directories[j].toString()
				      			+"/"+ code.getName().substring(0,code.getName().length()-3) +"dep") ;
				    	 destAST =  new File(destFolderParent +"/"+ directories[j].toString()
				      			+"/"+ code.getName().substring(0,code.getName().length()-3) +"ast") ;
						   }
						   
						   
						   if(ext.length()==2){
							   System.out.println("extension:"+ext);

/*						   srcTXT= new File(test_file_paths.get(i).toString().substring(0, code.getPath().length()-2)+"txt");
						   srcDEP= new File(test_file_paths.get(i).toString().substring(0, code.getPath().length()-2)+"txt");
						   srcAST= new File(test_file_paths.get(i).toString().substring(0, code.getPath().length()-2)+"txt");

					   
				      	 destFile = new File(destFolderParent +"/"+ directories[j].toString()
				      			+"/"+ code.getName()) ;

				    	 destTXT = new File(destFolderParent +"/"+ directories[j].toString()
				      			+"/"+ code.getName().substring(0,code.getName().length()-2) +"txt") ;
				    	 destDEP =  new File(destFolderParent +"/"+ directories[j].toString()
				      			+"/"+ code.getName().substring(0,code.getName().length()-2) +"dep") ;
				    	 destAST =  new File(destFolderParent +"/"+ directories[j].toString()
				      			+"/"+ code.getName().substring(0,code.getName().length()-2) +"ast") ;*/
						   }
						   File srcFolder = new File(author_cpp_dir); 

				    	//make sure source exists
				    	if(!srcFolder.exists())
				    	{
				           System.out.println("Directory does not exist.");
				           //just exit
				           System.exit(0);
				        }
				    	else
				        {
				 
				           try{							   if(ext.length()==3){

				       /* 	   String text =Util.readFile(test_file_paths.get(i).toString());
				        	   Util.writeFile(text, destFolderParent +"/"+ directories[j].toString()
				      			+"/"+ code.getName(), true);*/
						      	    FileUtils.copyFile(code, destFile);
						      	    FileUtils.copyFile(srcTXT, destTXT);
						      	    FileUtils.copyFile(srcDEP, destDEP);
						      	    FileUtils.copyFile(srcAST, destAST);
				           }

						      	} catch (IOException e) {
						      	    e.printStackTrace();
						      		System.out.println(code.getAbsolutePath());

						      	}
				        }
				   }   }
			   }	   
		    }}
		}

		
		

		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public static void copyAuthorsWithAtLeastFileNumber(String test_cpp_dir, int fileCount) throws IOException{

			   File file = new File(test_cpp_dir);
			   String[] directories = file.list(new FilenameFilter() 
			   {
			     @Override
			     public boolean accept(File current, String name) 
			     {
			       return new File(current, name).isDirectory();
			     }
			   });
			   System.out.println(Arrays.toString(directories));
			   for(int j=0; j< directories.length; j++)
			    {
//				   System.out.println(directories[j].toString());
				   String author_cpp_dir = test_cpp_dir + directories[j] +"/";
//				   System.out.println(author_cpp_dir);
				   List test_file_paths = Util.listCPPFiles(author_cpp_dir);
				   for(int i=0; i< test_file_paths.size(); i++)
				   {
//						int testIDlength = test_file_paths.get(i).toString().length();   
					   //if the author has 6 cpp files
					//   int fileCount =6;
					   if(test_file_paths.size() >= fileCount)
					   {
						   System.out.println(author_cpp_dir);

						   String filePath = test_file_paths.get(i).toString();
						   //one empty file in each folder, skip that
						   System.out.println(filePath);  
						   
						   File srcFolder = new File(author_cpp_dir);
					    	File destFolderParent = new File("/Users/Aylin/Desktop/Drexel/2014/ARLInternship/SCAA_Datasets/largeScale/"
						   +fileCount+"FilesAtLeastPerAuthor_2012/") ;
					      	File destFolder = new File(destFolderParent +"/"+ directories[j].toString()) ;
					    	if(!destFolder.exists())
					    	{
								///System.out.println(file.getAbsolutePath());
					    		destFolder.mkdirs();
							}
					    	
					    	
					  
					    	
						    List cpp_file_paths = Util.listCPPFiles(author_cpp_dir);
						    for(int k=0; k< fileCount; k++)
						    	
						    {
						    	File srcFile=new File(cpp_file_paths.get(k).toString());							    	
						    	File destFile= new File(destFolder + "/"+ srcFile.getName());
						    	FileUtils.copyFile(srcFile, destFile);
						     	if(!destFile.exists())
						    	{
									///System.out.println(file.getAbsolutePath());
						    		destFile.mkdirs();
								}
						    }
						    

					    	
					    	
					   }   
				   }	   
			    }
			}

		public static void copyAuthorsRandomlyWithAtLeastFileNumber(String test_cpp_dir, int fileCount, int year) throws IOException{

			   File file = new File(test_cpp_dir);
			   String[] directories = file.list(new FilenameFilter() 
			   {
			     @Override
			     public boolean accept(File current, String name) 
			     {
			       return new File(current, name).isDirectory();
			     }
			   });
			   System.out.println(Arrays.toString(directories));
			   for(int j=0; j< directories.length; j++)
			    {
//				   System.out.println(directories[j].toString());
				   String author_cpp_dir = test_cpp_dir + directories[j] +"/";
//				   System.out.println(author_cpp_dir);
				   List test_file_paths = Util.listCPPFiles(author_cpp_dir);
				   for(int i=0; i< test_file_paths.size(); i++)
				   {
//						int testIDlength = test_file_paths.get(i).toString().length();   
					   //if the author has 6 cpp files
					//   int fileCount =6;
					   if(test_file_paths.size() >= (fileCount+3))
					   {
						   System.out.println(author_cpp_dir);

						   String filePath = test_file_paths.get(i).toString();
						   //one empty file in each folder, skip that
						   System.out.println(filePath);  
						   
						   File srcFolder = new File(author_cpp_dir);
					    	File destFolderParent = new File("/Users/Aylin/Desktop/Drexel/2014/ARLInternship/SCAA_Datasets/bigExperiments/randomAuthorProblems/"
						   +fileCount+"FilesRandomAtLeastPerAuthor"+year) ;
					      	File destFolder = new File(destFolderParent +"/"+ directories[j].toString()) ;
					    	if(!destFolder.exists())
					    	{
								///System.out.println(file.getAbsolutePath());
					    		destFolder.mkdirs();
							}
					    	
					    	
					  
					    	
						    List cpp_file_paths = Util.listCPPFiles(author_cpp_dir);
						    Random no;
						    int use;
						    
						    for(int k=0; k < fileCount; k++)
						    {
						    	no = new Random();

								use = no.nextInt(cpp_file_paths.size());
								System.out.println("Size: "+cpp_file_paths.size() +" Using: "+use);

//						    	File srcFile=new File(cpp_file_paths.get(use).toString());
						   	File srcFile=new File(cpp_file_paths.get(k).toString());

								System.out.println(srcFile.getAbsolutePath());
								System.out.println(cpp_file_paths.get(k).toString());

						    	File destFile= new File(destFolder + "/"+ srcFile.getName());
						    	FileUtils.copyFile(srcFile, destFile);
						     	if(!destFile.exists())
						    	{
									///System.out.println(file.getAbsolutePath());
						    		destFile.mkdirs();
								}
						     	
						    }
						    

					    	
					    	
					   }   
				   }	   
			    }
			}

		
		
		
		
		public static int AvgLineOfCodePerFile(String folder) throws IOException{
/*			
		  	File author = new File(folder);
		    String[] directories = author.list(new FilenameFilter() 
				   {
				     @Override
				     public boolean accept(File current, String name) 
				     {
				       return new File(current, name).isDirectory();
				     }
				   });
		    int authorCount=directories.length;*/
			
			   List test_cpp_paths = Util.listCPPFiles(folder); //use this for preprocessing 
			   int numberFiles=test_cpp_paths.size();
			   int totalLines=0;
			   int avgLines=0;
			   for(int j=0; j< (test_cpp_paths.size()); j++){
		        	
				   FileReader fr=new FileReader(test_cpp_paths.get(j).toString());
				   BufferedReader br=new BufferedReader(fr); 
				   int i=0;
				   boolean isEOF=false;
				   do{
				   String t=br.readLine();
				   if(t!=null){
				   isEOF=true;
				   t=t.replaceAll("\\n|\\t|\\s", "");
				   if((!t.equals("")) && (!t.startsWith("//"))) {
				   i = i + 1;
				   }
				   }
				   else {
				   isEOF=false;
				   }
				   }while(isEOF);
				   br.close();
				   fr.close();
				   totalLines=totalLines+i;
			   }  
			   
			   avgLines=totalLines/numberFiles;
			   
			   
			   
			return avgLines; 
			
		}
		
		
		
		public static void showSameAuthors(String parentDir1, String parentDir2)
		{
			
					  File authors1 = new File(parentDir1);

				   String[] authorDirectories1 = authors1.list(new FilenameFilter() 
				   {
				     @Override
				     public boolean accept(File current, String name) 
				     {
				       return new File(current, name).isDirectory();
				     }
				   });	
				   
				   File authors2 = new File(parentDir2);

				   String[] authorDirectories2 = authors2.list(new FilenameFilter() 
				   {
				     @Override
				     public boolean accept(File current, String name) 
				     {
				       return new File(current, name).isDirectory();
				     }
				   });		
				   
				   for(int k=0; k< authorDirectories1.length; k++)
				    { 		
					   for(int l=0; l< authorDirectories2.length; l++)
					    { 	
						   if(authorDirectories1[k].toString().equals(authorDirectories2[l].toString()))
						   {				
						   List test_cpp_paths = Util.listCPPFiles(parentDir1+authorDirectories1[k]+"/"); //use this for preprocessing 
						   File cpp_file=null;
					        if(test_cpp_paths.size()==8){
					        	   System.out.println(authorDirectories1[k]);
						   	}
					    }
				    }
			    }}
			   			     	
	
			
				   
		
		public static void main(String[] args) throws Exception, IOException, InterruptedException 
		{
		String test_cpp_dir = "githubManySmallSnippets/";	
		int LOC = 50 ;
		int minFile = 2;
		copyAuthorsWithLOCFileNumber(test_cpp_dir, "githubManySmallSnippets" + "_minLOC"+LOC + "_minFiles"+
		minFile+"/", LOC, minFile, true);

		}
}
