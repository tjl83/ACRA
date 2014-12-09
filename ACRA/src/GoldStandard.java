import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONObject;

import reader.JSONReader;

public class GoldStandard {
	public static void main(String[] args) throws IOException{
		setGoldStandard();
		
	}
	
	public static void setGoldStandard() throws IOException{
		HashMap<String,ArrayList<JSONObject>> goldReviewsByProduct = new HashMap<String,ArrayList<JSONObject>>();
		
		Scanner scan = new Scanner(System.in);
		Files.walk(Paths.get("Reviews")).forEach(filePath -> {
		    if (Files.isRegularFile(filePath)) {
				String filename = filePath.getFileName().toString();
				String productCode = filename.substring(0, filename.lastIndexOf('.'));
				
	        	try {
			    	File reviewContainerFile = filePath.toFile();
					FileWriter file = new FileWriter(new File("Reviews-gold/" + productCode + "-gold.txt"));
			    	
			    	JSONReader reader = new JSONReader(reviewContainerFile);
					ArrayList<JSONObject> reviewContainers = reader.getJSONObjects();
					ArrayList<JSONObject> goldReviews = reader.getJSONObjects();
					StringBuilder jsonGoldReviews = new StringBuilder();
					
					int reviewCount = 0;
					for(JSONObject reviewContainer:reviewContainers){
						if(reviewCount < 50){
							try {
								System.out.println("\nProduct: {l} Both: {;} Service: {'}");
								System.out.println(reviewContainer.get("Review"));
								String reviewCategory = "";
								switch(scan.next()){
									case "f":
										reviewCategory = "Product";
										break;
									case "d":
										reviewCategory = "Both";
										break;
									case "s":
										reviewCategory = "Service";
										break;
									default:
										break;
								}
								JSONObject goldReview = new JSONObject();
								goldReview.append("Username", reviewContainer.get("Username").toString());
								goldReview.append("Review", reviewContainer.get("Review").toString());
								goldReview.append("Review Category", reviewCategory);
								goldReviews.add(goldReview);
								jsonGoldReviews.append(goldReviews.toString());
					        	jsonGoldReviews.append("\n");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							reviewCount++;
						}
						else{
							break;
						}
					}
					file.write(jsonGoldReviews.toString());
					file.flush();
					file.close();
					
					goldReviewsByProduct.put(productCode, goldReviews);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
	    });
		scan.close();
		
		
	}
}
