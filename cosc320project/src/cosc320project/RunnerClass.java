package cosc320project;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
 

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;


public class RunnerClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//load data set of tweets and abbreviations into respective lists
		List<Abbreviation> listofAbs = readAbsFromCSV("dataset.csv");
		List<Tweet> tweets = readTwFromCSV("datasetwithtw.csv");
		
		for(Tweet t: tweets)
		{
			t.setText(preProcessTweet(t.getText()));
		}
		
		//initialize a new list to hold modified tweets
		List<Tweet> newTweets = new ArrayList<>();
		
		
		//start counting time after loading
		long start = System.currentTimeMillis();
		
		//initialize hashmao that holds words and their meanings
		HashMap<String,String> mapOfAbs = new HashMap<>();
		//fill hashmap with key-value pairs where key is abbreviation and value is the meaning
	
		for(int i=0; i<listofAbs.size();i++)
		{
		mapOfAbs.put(listofAbs.get(i).getWord(),listofAbs.get(i).getMeaning());
		}
		
		// for each tweet, call function to swap abbreviations
		//and add to the list of new tweets
		for(int i=0;i<tweets.size();i++)
		{
			String tweet = tweets.get(i).getText();
			String[] wordsInTweet = swapWordsForMeaning(mapOfAbs, tweet);
			String newTweet = makeStringOutOfWords(wordsInTweet);
			newTweets.add(new Tweet(newTweet));
		
		}
		
		//get finish time
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.print("Time to do abbreviations swaps: "+timeElapsed+" ms");
		
		//print words
		System.out.println();
		
		for(int i=0; i<10;i++)
		{
			System.out.println(tweets.get(i).getText());
			System.out.println(newTweets.get(i).getText());
		}
		
		
		//write to csv file all the changed tweets
		writeToCSV(newTweets);


	}
	
	//function to convert array of strings into sigular string while adding white spaces
	public static String makeStringOutOfWords(String[] words)
	{
		StringBuilder sb= new StringBuilder();
		for(int i=0;i<words.length;i++)
		{
			words[i] = words[i].trim() ;
			if(words[i].equals(".") || words[i].equals("-")|| words[i].equals("!") || words[i].equals("?") || words[i].equals("#") || words[i].equals("'")|| words[i].equals(","))
				{
				sb.deleteCharAt(sb.length()-1);
				sb.append(words[i].trim());
				}
			else
				sb.append(words[i].trim()+" ");
		}
		return sb.toString();
	
	}
	private static String preProcessTweet(String tweet2)
	{
		tweet2 = tweet2.replace("#", " # ");
		tweet2 = tweet2.replace("'"," ' ");
		tweet2 = tweet2.replace("!"," ! ");
		tweet2 = tweet2.replace("."," . ");
		tweet2 = tweet2.replace("?"," ? ");
		tweet2 = tweet2.replace("-"," - ");
		tweet2 = tweet2.replace(","," , ");
		return tweet2;
	}
	public static String[] swapWordsForMeaning(HashMap<String, String> mapOfAbs, String tweet2) {
		
		String tweet = tweet2.toLowerCase();
		//split the tweet into words, each as a seperate element in array of strings (use delimeter as both //white spaces and special characters such as ,)
		String[] wordsInTweet = tweet.split(" ");
		
		String[] wordsOriginal = tweet2.split(" ");
		//loop through the array of words, look for an abbreviation in the hashmap, if present, swap //values, if not, move on to next word
		for(int i=0; i<wordsInTweet.length;i++)
		{
			if(mapOfAbs.containsKey(wordsInTweet[i]))
			{
				wordsInTweet[i] = mapOfAbs.get(wordsInTweet[i]);
			}
			else
			{
				wordsInTweet[i]=wordsOriginal[i];
			}
		}
		return wordsInTweet;
	}
	//function to read from dataset of abbreviations
	private static List<Abbreviation> readAbsFromCSV(String fileName)
	{ List<Abbreviation> abbreviations = new ArrayList<>(); 
		Path pathToFile = Paths.get(fileName);
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
			
			String line = br.readLine();
			
			while (line != null) { 
				String[] attributes = line.split(",");
				Abbreviation ab = new Abbreviation (attributes[0].trim().toLowerCase(),attributes[1].trim());
				abbreviations.add(ab); 
				line = br.readLine(); 
				} 
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
			return abbreviations;
		}

	//function to read from dataset of tweets
private static List<Tweet> readTwFromCSV(String fileName)
{ 
	
	List<Tweet> tws = new ArrayList<>();

	//limiting number of tweets n
	int n = 1000000;
	int i=0; //counter


	// read line by line
	String[] record = null;

	try {
		CSVReader reader = new CSVReader(new FileReader(fileName));
		while ((record = reader.readNext()) != null && i<n) {
		
			tws.add(new Tweet(record[5]));
			i++;
		}
		reader.close();
	} catch (CsvValidationException | IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

return tws;
	
	
	
}  

//function to write modified tweets to new cvs file
public static void writeToCSV(List<Tweet> tws)
{
	FileWriter writer;
	try {
		writer = new FileWriter("newTweets.csv");
		
		//using custom delimiter and quote character
		CSVWriter csvWriter = new CSVWriter(writer);


		List<String[]> data = toStringArray(tws);

		csvWriter.writeAll(data);

		csvWriter.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}

private static List<String[]> toStringArray(List<Tweet> tws) {
	List<String[]> records = new ArrayList<String[]>();

	// adding header record
	records.add(new String[] { "Text" });

	Iterator<Tweet> it = tws.iterator();
	while (it.hasNext()) {
		Tweet tw = it.next();
		records.add(new String[] { tw.getText() });
	}
	return records;
}



}



	
	

