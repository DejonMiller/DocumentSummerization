package document.summary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;


public class Preprocess {
    StopWords stopwords = new StopWords();
    InputDocument inputDoc = new InputDocument();
    
    
    ArrayList<String> SW = new ArrayList<String>(stopwords.getStopContent());
      String [] stopWord = SW.toArray(new String[SW.size()]);

    public Set<String> stopWordSet = new HashSet<>(Arrays.asList(stopWord));
	//public static Set<String> stemmedStopWordSet = stemStringSet(stopWordSet);
    
    
    public  String removeStopWords() {
        InputDocument input = new InputDocument();
        
        String document = input.getContent();
        
		String result = "";
                
		String[] words = document.split("\\s+");
		for(String word : words) {
			if(word.isEmpty()) continue;
			if(isStopword(word)) continue; //remove stopwords
			result += (word+" ");
		}
		return result;
	}

    public  boolean isStopword(String word) {
		if(word.length() < 2) return true;
		if(word.charAt(0) >= '0' && word.charAt(0) <= '9') return true; //remove numbers, "25th", etc
		if(stopWordSet.contains(word)) return true;
		else return false;
	}
    
    //public String


}
    
    

