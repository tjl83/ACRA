package analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dipre {

	public static String findPattern(File review, String subject, String object) throws FileNotFoundException{
		String reviews = read(review);
		String[] words = reviews.split("\\s+");
		for(int i = 0; i < words.length;i++){
			if(words[i].equals(subject)){
				StringBuilder buffer = new StringBuilder();
				for(int j = i; j < i + 10; i++){
					buffer.append(words[j]);
					if(words[j].equals(object)){
						return  buffer.toString();
					}
				}
			}
		}
		return null;
	}

	public static Map<String, String> findFacts(File review,String pattern) throws IOException {
		Map<String, String> result = new HashMap<>();
		String reviews = read(review);
		String[] words = reviews.split("\\s+");
		String[] pat = pattern.split("\\s+");
		for(int i = 0; i < words.length;i++){
			boolean found = false;
			for(int j = i; j < i + pat.length; j++){
				if(words[j].equals(pat[j-i])){
					found = true;
				}
				else{
					found = false;
					break;
				}
			}
			if(found){
				result.put(words[i], words[i + words.length]);
			}
		}
		return result;
	}
	
	public static String read(File file) throws FileNotFoundException{
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		StringBuilder content = new StringBuilder();
	      String line;
	      try {
			while ((line = buffer.readLine()) != null && line.length() > 0) {
			    content.append(line).append(" ");
			  }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	public static void main(String args[]) throws IOException {
		File review = new File(args[0]);
		String pattern = findPattern(review, args[1],args[2]);
		System.out.println("Pattern: " + pattern);

		Map<String, String> facts = findFacts(review, pattern);
		System.out.println("Facts: " + facts);

	}
}