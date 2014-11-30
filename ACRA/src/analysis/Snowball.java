package analysis;

import java.util.ArrayList;

public class Snowball {
	private ArrayList<Pattern> patternList;
	private ArrayList<Tuple> occurences;
	
	
	//sub GeneratePatterns
	public ArrayList<Pattern> generatePatterns{
		ArrayList<Pattern> patterns = new ArrayList<Pattern>();
		//foreach (< oseed; `seed >, str) in Occurrences
		for(Tuple tuple:occurences){
		//(1) tS =< ls; t1 ; ms; t2; rs > = makeOccurrence(str);
			
		//(2) cluster best = FindClosestCluster(tS , sim);
		//if(best)
		//best.Add(tS );
		//best.UpdateCentroid(tS );
		//best.AddTuple(< oseed; `seed >);
		//else
		//CreateNewCluster(< oseed; `seed >, tS);
		//(3)Patterns = FilterPatterns( Clusters, sup);
		//return Patterns;
		}
	}
	
	//sub GenerateTuples(Patterns)
	//foreach text segment in corpus
	//(1) f< o; ` >; < ls; t1; ms; t2; rs >g =
	//= CreateOccurrence(text segment);
	//TC = < o; ` >;
	//SimBest = 0;
	//foreach p in Patterns
	//(2) sim = M atch(< ls; t1 ; ms; t2; rs >; p);
	//if (sim  sim)
	//(3) UpdatePatternSelectivity(p, TC );
	//if(sim  SimBest)
	//SimBest = sim;
	//PBest = p;
	//if(SimBest  sim)
	//CandidateTuples[TC ].Patterns[PBest ] = SimBest ;
	//return CandidateTuples;
}
