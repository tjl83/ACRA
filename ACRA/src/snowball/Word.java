package analysis;

public class Word {
	private String word;
	private double coefficient;
	
	public Word(String word, double coefficient){
		this.word = word;
		this.coefficient = coefficient;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	
}
