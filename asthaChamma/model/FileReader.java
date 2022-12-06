package asthaChamma.model;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import asthaChamma.FileReaderInterface;

import java.io.FileWriter;

public class FileReader implements FileReaderInterface
{
	
	protected String name;
	protected String score; 
	protected File fileObj;
	protected String fileName;
	public FileReader()
	{  
		try 
		{
			this.fileName =  "HighestScoreRecord.txt";
			fileObj = new File(fileName);
			Scanner DictionartTextFile = new Scanner(fileObj);//reading file
			String content = "";
			 while (DictionartTextFile.hasNextLine())
		      {
				 content+=DictionartTextFile.nextLine();
		      }
			
			 this.name = content.split(",")[0];
			 this.score = content.split(",")[1];
		}
		catch(IOException e) 
		{
			System.out.println("please enter correct path for file");   
		}
		
	}
	
	public String getfileName() 
	{
		return this.fileName;
	}
	public  String getHighestScorerName()
	{
      return this.name;
	}
   
	 public  int getScore()
	 {
	      return Integer.parseInt(this.score);
	 }
	 
	 public void writeThisFile(String text) 
	 {
		 Path fileName = Path.of(this.getfileName());
		 try
		 {
			Files.writeString(fileName,text);
		 } 
		 catch (IOException e)
		 {
			e.printStackTrace();
		 }	 
	 }
}
