import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import reader.JSONReader;

public class StuffRunner {
	public static void main(String[] args) throws JSONException, IOException{
		getReviews("B000WGD13U");
		getReviews("B00DC7G2W8");
		getReviews("B0002ZQB4M");
		getReviews("B003ZTVDBS");
		getReviews("B0012H549W");
	}
	
	public static void getReviews(String productCode) throws IOException, JSONException{
		File reviewContainerFile = new File("Reviews/" + productCode + ".txt");
		JSONReader reader = new JSONReader(reviewContainerFile);
		ArrayList<JSONObject> reviewContainers = reader.getJSONObjects();
		
        FileWriter file = new FileWriter("patterns/" + productCode + "-reviews.txt");

		for(JSONObject reviewContainer:reviewContainers){
			file.write(reviewContainer.get("Review").toString());
			file.flush();
			file.write(" ");
			file.flush();
		}
		file.close();
	}
}