import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import reader.JSONReader;

/**
 * This class runs the processing of the data post Stanford NLP Core's SPIED analysis.
 * @author Thomas
 *
 */
public class PostAnalysis {
	public static void main(String[] args) throws JSONException, IOException{
		HashMap<String,HashSet<String>> productTerms = getTerms();
		
		HashMap<String,HashMap<String, ArrayList<String>>> productReviewSentances = getSentances();
		for(String product:productReviewSentances.keySet()){
			System.out.println(product + ":");
			
			HashMap<String, ArrayList<String>> reviews = productReviewSentances.get(product);
			HashSet<String> terms = productTerms.get(product);
			
			int productCount = 0;
			int bothCount = 0;
			int serviceCount = 0;
			
			for(ArrayList<String> review:reviews.values()){
				double termCount = 0;
				for(String sentance:review){
					for(String term:terms){
						if(sentance.contains(term)){
							termCount++;
						}
					}
				}
				
				double productTermFrequency = termCount/(double)review.size();
				if(productTermFrequency < 0.3){
					serviceCount++;
				}
				else if(productTermFrequency < 0.6){
					bothCount++;
				}
				else{
					productCount++;
				}
			}
			
			System.out.println("Product: " + productCount);
			System.out.println("Both: " + bothCount);
			System.out.println("Service: " + serviceCount + "\n");
		}
	}
	
	/**
	 * Gets all the terms extracted from a product for each product
	 */
	public static HashMap<String,HashSet<String>> getTerms() throws IOException{
		HashMap<String,HashSet<String>> productTerms = new HashMap<String,HashSet<String>>();
		
		Files.walk(Paths.get("stanford-corenlp-full-2014-10-31/SPIEDPatternsout/useNERRestriction")).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
		    	File termFile = filePath.toFile();
		    	
		    	String filename = filePath.getFileName().toString();
		    	if(filename.equals("learnedwords.txt")){
			    	HashSet<String> terms = new HashSet<String>();
			    	try {
						Scanner scan = new Scanner(termFile);
						while(scan.hasNext()){
							String term = scan.nextLine();
							if(!term.isEmpty()){
								terms.add(term);
							}
						}
						scan.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
			    	String productName = filePath.getParent().toString();
			    	productName = productName.substring(productName.lastIndexOf('\\')+1, productName.lastIndexOf('-'));
			    	productTerms.put(productName, terms);
		    	}
		    }
		});
		
		return productTerms;
	}
	
	/**
	 * Gets a list of sentences of a review for each review per product
	 */
	public static HashMap<String,HashMap<String, ArrayList<String>>> getSentances() throws IOException{
		HashMap<String,HashMap<String, ArrayList<String>>> productReviewSentances = new HashMap<String,HashMap<String, ArrayList<String>>>();
		
		Files.walk(Paths.get("Reviews")).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
		    	String fileP = filePath.toString();
		    	File reviewContainerFile = new File(fileP);
		    	
		    	JSONReader reader = new JSONReader(reviewContainerFile);
				ArrayList<JSONObject> reviewContainers = reader.getJSONObjects();
				
				HashMap<String, ArrayList<String>> reviewSentances = new HashMap<String, ArrayList<String>>();
				
				Scanner scan = null;
				
				for(JSONObject reviewContainer:reviewContainers){
					try {
						scan = new Scanner(reviewContainer.get("Review").toString());
						scan.useDelimiter("[\\.]");
						
						ArrayList<String> sentances = new ArrayList<String>();
						while(scan.hasNext()){
							String sentance = scan.next();
							if(!sentance.isEmpty()){
								sentances.add(sentance);
							}
						}
						reviewSentances.put(reviewContainer.get("Username").toString(), sentances);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				String productName = reviewContainerFile.getName();
				productName = productName.substring(0, productName.lastIndexOf('.'));
				productReviewSentances.put(productName, reviewSentances);
		    }
		});
		
		return productReviewSentances;
	}
}