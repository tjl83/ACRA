package analysis;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.patterns.surface.GetPatternsFromDataMultiClass;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;


public class Analyzer {
	private static List<String> phrases;
	private static Properties props = new Properties();
	private static  Map<String,List<CoreMap>> sents;
	
	public static void createStanfordCoreNLP(String text){
		 // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
	    props.put("annotators","ssplit" ); //"tokenize, ssplit, pos, lemma, ner, parse, dcoref"
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    
	    // create an empty Annotation just with the given text
	    Annotation document = new Annotation(text);
	    
	    // run all Annotators on this text
	    pipeline.annotate(document);
	    
	    // these are all the sentences in this document
	    // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
	    List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
	    
	    for(CoreMap sentence: sentences) {
	      // traversing the words in the current sentence
	      // a CoreLabel is a CoreMap with additional token-specific methods
	      for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
	        // this is the text of the token
	        String word = token.get(CoreAnnotations.TextAnnotation.class);
	        // this is the POS tag of the token
	        String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
	        // this is the NER label of the token
	        String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);       
	      }
	
	      // this is the parse tree of the current sentence
	      Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
	
	      // this is the Stanford dependency graph of the current sentence
	      SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
	    }
	}
	
	public static void PatternGeneration(Map<String,List<CoreLabel>> sents, Map<String, Set<String>> seedSets, boolean labelUsingSeedSets){
		try {
			GetPatternsFromDataMultiClass patternGenerator = new GetPatternsFromDataMultiClass(props, sents, seedSets, labelUsingSeedSets) ;
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException | IOException | InterruptedException
				| ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
	}
}
