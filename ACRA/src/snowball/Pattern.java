package analysis;

import java.util.ArrayList;

public class Pattern {
	
	public final String tagX; 
	public final String tagY;
	public final ArrayList<Word> prefix;
	public final ArrayList<Word> suffix;
	public final ArrayList<Word> middle;
	
	  
	public Pattern(ArrayList<Word> prefix, String x, ArrayList<Word> middle, String y, ArrayList<Word> suffix ) { 
	  this.tagX = x; 
	  this.tagY = y; 
	  this.prefix = prefix;
	  this.suffix = suffix;
	  this.middle = middle;
	}
	
	/*public double match(Pattern pattern){
		if(pattern.tagX.equals(this.tagX) && pattern.tagY.equals(this.tagY)){
			
		}
		else{
			return 0;
		}
	}*/
}
