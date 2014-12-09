import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

import org.json.JSONException;
import org.json.JSONObject;

import reader.JSONReader;

/**
 * This class runs the processing of the data pre Stanford NLP Core's SPIED analysis.
 * @author Thomas
 *
 */
public class PreAnalysis {
	public static void main(String[] args) throws IOException, JSONException{
		if(!isProcessed()){
			getReviews();
		}
	}
	
	/**
	 * Checks if the reviews have been extracted from the JSONObjects yet
	 */
	public static boolean isProcessed() throws IOException{
		HashSet<String> filenames = new HashSet<String>();
		
		Files.walk(Paths.get("Reviews")).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
		    	String filename = filePath.getFileName().toString();
		    	filename = filename.substring(0, filename.lastIndexOf('.'));
		    	filenames.add(filename);
		    }
		});
		
		Files.walk(Paths.get("stanford-corenlp-full-2014-10-31/patterns")).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
		    	String filename = filePath.getFileName().toString();
		    	if(filename.contains("-terms.txt")){
		    		filename = filename.substring(0, filename.lastIndexOf('-'));
		    		if(filenames.contains(filename)){
		    			filenames.remove(filename);
		    		}
		    	}
		    }
		});
		
		return filenames.isEmpty();
	}
	
	/**
	 * This function fetches JSONObjects in a text file and extracts all the reviews from each object
	 * and writes it one large document containing all the reviews together.
	 */
	public static void getReviews() throws IOException, JSONException{
		Files.walk(Paths.get("Reviews")).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
		    	String filename = filePath.getFileName().toString();
		    	String productCode = filename.substring(0, filename.lastIndexOf(','));
		    	
		    	String fileP = filePath.toString();
		    	File reviewContainerFile = new File(fileP);
		    	
		    	JSONReader reader = new JSONReader(reviewContainerFile);
				ArrayList<JSONObject> reviewContainers = reader.getJSONObjects();
				
				try{
		        FileWriter file = new FileWriter("stanford-corenlp-full-2014-10-31/patterns/" + productCode + "-reviews.txt");

				for(JSONObject reviewContainer:reviewContainers){
					file.write(reviewContainer.get("Review").toString());
					file.flush();
					file.write(" ");
					file.flush();
				}
				file.close();
				}
				catch(Exception e){
					System.err.println(e.toString());
				}
		    }
		});
	}
}
