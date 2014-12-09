package analysis;

import java.util.ArrayList;

public class SPattern {
	
	public final String tagX; 
	public final String tagY;
	public final ArrayList<Word> prefix;
	public final ArrayList<Word> suffix;
	public final ArrayList<Word> middle;
	
	  
	public SPattern(ArrayList<Word> prefix, String x, ArrayList<Word> middle, String y, ArrayList<Word> suffix ) { 
	  this.tagX = x; 
	  this.tagY = y; 
	  this.prefix = prefix;
	  this.suffix = suffix;
	  this.middle = middle;
	}
	
	public double match(SPattern pattern){
		if(pattern.tagX.equals(this.tagX) && pattern.tagY.equals(this.tagY)){
			return dotProduct(prefix, pattern.prefix) + dotProduct(middle, pattern.middle) + dotProduct(suffix, pattern.suffix); 
		}
		else{
			return 0;
		}
	}
	
	private double dotProduct(ArrayList<Word> v1, ArrayList<Word> v2) {
		double total = 0; 
		for(int i = 0; i < v1.size() && i < v2.size(); i++){
			total += v1.get(i).getCoefficient()*v2.get(i).getCoefficient();
		}
		return total;
	}
}
