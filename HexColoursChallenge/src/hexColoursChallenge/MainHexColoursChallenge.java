package hexColoursChallenge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainHexColoursChallenge 
{
	static HashMap<String, String> allowedCharsToHexTransMap = new HashMap<String, String>();
	static List<String[]> records = retrieveData("words.csv"); //gets words from Excel file
	
	public static void main(String[] args) 
	{
		init(); 
		for(String[] recordline : records)
		{
		    for(String record : recordline) 
		    {
		    	if(record.length() <= 6 && record.length() >= 1) //finding words with count less then 7
		    	{
		    		String word = "";
		    		String[] characters = record.toUpperCase().split("");
		    	    for(String character : characters)
		    	    {
		    	    	if(allowedCharsToHexTransMap.get(character) != null)
		    	    	{
		    	    		word = word+character; 
		    	    	}
		    	    	else
		    	    	{
		    	    		word = ""; 
		    	    		break; 
		    	    	}
		    	    }
		    	    
		    	    if(word.length() > 0)
		    	    {
		    	    	System.out.println(covertWordToHexcode(word)+" ("+word.toLowerCase()+")");
		    	    }	    
		    	}
		    }
		}
	}
	
	public static void init()
	{
		// Allowed character map and hex translation
		//numbers that will be changed
		allowedCharsToHexTransMap.put("O", "0");
		allowedCharsToHexTransMap.put("S", "5");
		allowedCharsToHexTransMap.put("T", "7"); 
		allowedCharsToHexTransMap.put("I", "1");
		allowedCharsToHexTransMap.put("L", "1"); 
		allowedCharsToHexTransMap.put("Z", "2");
		allowedCharsToHexTransMap.put("H", "4");
		
		//numbers and letters inside the hex decimal system
		allowedCharsToHexTransMap.put("A", "A"); 
		allowedCharsToHexTransMap.put("B", "B"); 
		allowedCharsToHexTransMap.put("C", "C"); 
		allowedCharsToHexTransMap.put("D", "D"); 
		allowedCharsToHexTransMap.put("E", "E"); 
		allowedCharsToHexTransMap.put("F", "F"); 
		allowedCharsToHexTransMap.put("0", "0"); 
		allowedCharsToHexTransMap.put("1", "1"); 
		allowedCharsToHexTransMap.put("2", "2");
		allowedCharsToHexTransMap.put("3", "3"); 
		allowedCharsToHexTransMap.put("4", "4"); 
		allowedCharsToHexTransMap.put("5", "5"); 
		allowedCharsToHexTransMap.put("6", "6"); 
		allowedCharsToHexTransMap.put("7", "7"); 
		allowedCharsToHexTransMap.put("8", "8"); 
		allowedCharsToHexTransMap.put("9", "9"); 
	}
	
	public static String covertWordToHexcode(String word)
	{
		String[] characters = word.split(""); //splits words into characters
		String hexCode = "#";  //adds # to make hexadecimal 
	    for(String character : characters)
	    {
	    	String letter = allowedCharsToHexTransMap.get(character); 
	    	hexCode = hexCode + letter;
	    }
		return hexCode; 
	}

	public static List<String[]> retrieveData(String filename)
	{
		Path file = Paths.get(filename);
		List<String[]> records = new ArrayList<>();
		try 
		{
			List<String> lines = Files.readAllLines(file);
			for (String line : lines) 
			{
				if (line != null) 
				{
					records.add(line.split("\r\n"));
				}
			}
		} 
		catch (FileNotFoundException e)  //verify checks if it can't find records or the csv file.
		{
			System.out.println("CSV-file does not exist.");
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			System.out.println("Record does not exist.");
			e.printStackTrace();
		}
		return records;
	}
}