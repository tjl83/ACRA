package analysis;

import java.util.ArrayList;

//import de.jungblut.clustering.Cluster;
//import de.jungblut.clustering.OnePassExclusiveClustering;

public class Snowball {
	private ArrayList<Pattern> patternList;
	private ArrayList<Tuple> tag;
	
	
	//sub GeneratePatterns
		//foreach (< oseed; `seed >, str) in Occurrences
		//(1) tS =< ls; t1 ; ms; t2; rs > = makeOccurrence(str);
		//(2) cluster best = FindClosestCluster(tS , sim);
		//best.Add(tS );
		//best.UpdateCentroid(tS );
		//best.AddTuple(< oseed; `seed >);
		//else
		//CreateNewCluster(< oseed; `seed >, tS);
		//(3)Patterns = FilterPatterns( Clusters, sup);
		//return Patterns;
	
	public static ArrayList<Pattern> findPatterns(String review, String subject, String object){
		String[] words = review.split("\\s+");
		ArrayList<Pattern> pList =  new ArrayList<Pattern>();
		for(int i = 0; i < words.length;i++){
			if(words[i].equals(subject)){
				for(int j = i; j < i + 5; i++){
					if(words[j].equals(object)){
						ArrayList<Word> prefix = new ArrayList<Word>();
						if(i > 0){
							Word word  = new Word(words[i-1], .2);
							prefix.add(word);
						}
						ArrayList<Word> middle = new ArrayList<Word>();
						for(int k = i; k < j; k++){
							Word word = new Word(words[k], .5); // supposed to use frequency function to determine weights 
							middle.add(word);					//but it's not defined anywhere
						}
						ArrayList<Word> suffix = new ArrayList<Word>();
						if(j < words.length){
							Word word  = new Word(words[i-1], .2);
							suffix.add(word);
						}
						Pattern pattern = new Pattern(prefix,subject,middle,object,suffix);
						pList.add(pattern);
					}
				}
			}
		}
		return pList;
	}
	
	//sub GenerateTuples(Patterns)
		//foreach text segment in corpus
			//(1) f< o; ` >; < ls; t1; ms; t2; rs >g = CreateOccurrence(text segment);
			//TC = < o; ` >;
			//SimBest = 0;
			//foreach p in Patterns
				//(2) sim = M atch(< ls; t1 ; ms; t2; rs >; p);
				//if (sim >= sim)
					//(3) UpdatePatternSelectivity(p, TC );
					//if(sim >= SimBest)
						//SimBest = sim;
						//PBest = p;
			//if(SimBest  sim)
				//CandidateTuples[TC ].Patterns[PBest ] = SimBest ;
		//return CandidateTuples;
	public ArrayList<Tuple> generateTuples(String review, ArrayList<Pattern> patterns){
		return new ArrayList<Tuple>();

	}
		
	
}
