package analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class SentenceChecker{ 
	private static int totalOccurences = 0;
	
	public static void countSentences(File reviews, File termsList, String target) throws IOException{
	
		FileReader input = new FileReader(reviews);
		BufferedReader buffer = new BufferedReader(input);
		PrintWriter file = new PrintWriter("C:\\Users\\Joshua\\Documents\\GitHub\\ACRA\\SPIEDPatternsout\\Data\\" + target + "data.txt", "UTF-8");
		String myLine = null;
		ArrayList<String> terms = getTerms(termsList);
		int reviewNumber = 0;
		
		while ( (myLine = buffer.readLine()) != null)
		{
			int occurences = 0;
			reviewNumber++;
			String[] sentences = myLine.split(". ");
			int sents = 0;
			for(int i = 0; i<sentences.length; i++){
				sents++;
			}
		    for(String term : terms){
		    	for(int i=0;i<myLine.length() - i;i++){
		    		if(myLine.substring(i, i+term.length()).equals(term)){
		    			occurences++;
		    		}
		    	}
		    }
		    totalOccurences += occurences;
		    file.println("Review Number:" + reviewNumber + "  Sentences:"+ sentences.length + " Occurences of Terms:" + occurences + " TotalOccurences:" + totalOccurences);
		}
		file.close();
	}
	
	public static void run(final File reviewFolder,final File termFolder) {
		File[] termList = termFolder.listFiles();
		int i = 0;
	    for (final File fileEntry : reviewFolder.listFiles()) {
	        try {
				countSentences(fileEntry, termList[i], "review" + i);
				i++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	public static ArrayList<String> getTerms(File filename) throws IOException{	
		FileReader input = new FileReader(filename);
		BufferedReader buffer = new BufferedReader(input);
		ArrayList<String> terms = new ArrayList<String>();
		String line = null;
		while ( (line = buffer.readLine()) != null){
			terms.add(line);
		}
		terms.remove(0);
		return terms;
	}
	
	public static void main(String... args){
		File reviewFolder = new File("C:\\Users\\Joshua\\Documents\\GitHub\\ACRA\\ACRA\\Reviews");
		File termsFolder = new File("C:\\Users\\Joshua\\Documents\\GitHub\\ACRA\\SPIEDPatternsout\\useNERRestriction\\terms");
		run(reviewFolder, termsFolder);
	}
}
	