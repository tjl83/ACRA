package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONReader {
	Scanner scan;
	
	public JSONReader(File file){
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<JSONObject> getJSONObjects(){
		ArrayList<JSONObject> reviewContainers = new ArrayList<JSONObject>();
		while(scan.hasNext()){
			JSONObject reviewContainer = new JSONObject();
			try {
				reviewContainer = new JSONObject(scan.nextLine());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reviewContainers.add(reviewContainer);
		}
		return reviewContainers;
	}
}
