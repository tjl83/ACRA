package analysis;

public class Tuple { 
	
	public final String x; 
	public final String y;
	  
	public Tuple(String x, String y) { 
	  this.x = x; 
	  this.y = y; 
	}
	
	@Override
	public boolean equals(Object o){
		if(((Tuple)o).x.equals(this.x) && ((Tuple)o).y.equals(this.y)){
			return true;
		}
		else{
			return false;
		}
	}
} 