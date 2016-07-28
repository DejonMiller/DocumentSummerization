package document.summary;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Generator {
    
  private static InputDocument inputDoc;


  
  private double compressRatio = 0.3;

  private String[] keywords = null;

  private TermCollection termCollection;

  public static void loadFile(String inputFile) throws IOException{
    inputDoc = new InputDocument();
    inputDoc.loadFile(inputFile);  
  }

  public void setKeywords(String[] keywords){
    
    List<String> processedTermList = new ArrayList<String>();
    TermPreprocessor tp = new TermPreprocessor();
    
    String resultTerm = null;
    for(String term : keywords){
      resultTerm = tp.preprocess(term);

      if(resultTerm !=null)
        processedTermList.add(resultTerm);
    }

    this.keywords = processedTermList.toArray(new String[processedTermList.size()]);

  }

  public Set generateSummary(int CR){
            TextExtractor te = new TextExtractor();

      
      //Set mostFrequentWords = getMostFrequentWords(100, wordFrequencies);
    ArrayList<String> bestWords = te.wordCount(CR);
    
        Set<String> mostFrequentWords = new LinkedHashSet<>(bestWords);


    
        int numSentences = te.seperateSentence().size();
        String[] workingSentences = inputDoc.getContent().split(" . ");
        String[] actualSentences = inputDoc.getContent().split(" . ");

           Set outputSentences = new LinkedHashSet();
        Iterator it = mostFrequentWords.iterator();
        while (it.hasNext()) {
            
            String word = (String) it.next();
            
            for (int i = 0; i < workingSentences.length; i++) {
                if (workingSentences[i].indexOf(word) >= 0) {
                    outputSentences.add(actualSentences[i]);
                    break;
                }
                if (outputSentences.size() >= numSentences) {
                    break;
                }
            }
            if (outputSentences.size() >= numSentences) {
                break;
            }

        }
    return outputSentences;
  }
  
  
  public String generateTest(){
      
      return "string";
  }

  //Sentence which use signigicant words the most. 
  public Set<String> generateSignificantSentences(int CR){
      TextExtractor te = new TextExtractor();
      
    ArrayList<String> allSentences = te.seperateSentence();
    
    String document = inputDoc.getContent();
    
    ArrayList<String> bestWords = te.wordCount(CR);
    
    Set<String> s = new LinkedHashSet<>(bestWords);
    
//    String [] words = (String[]) s.toArray();
    
  
    
    ArrayList<String> summary = new ArrayList<String>();
    
    String word;
    
    String theS="";
    
  // System.out.println(s);
    
    ArrayList<String> list = new ArrayList<String>(s);

    
            for(int j =0; j < list.size(); j++){
    
        word=list.get(j);
        
        
        for(int i =0; i< allSentences.size(); i++){
           
      if(allSentences.get(i).contains(word)){
   
        //System.out.println(allSentences.get(i));
        summary.add(allSentences.get(i));
    }
    }
    }
            Set<String> sum = new LinkedHashSet<>(summary);
            ArrayList<String> summarylist = new ArrayList<String>(sum);
                   System.out.println(sum.size());


  
    System.out.println(theS);
    return sum;
  }

  //Seperates document into seperate sentences. 
  public ArrayList<String> getAllSentences(){
    //TextExtractor extract= new TextExtractor();   
    //return extract.seperateSentence();
        return inputDoc.getAllSentences();    
  }
  
  
  
  
  public ArrayList<String> allStopWords(){
      StopWords stopWords = new StopWords();
      
      ArrayList stopArrayList = new ArrayList<String>();
      
      stopArrayList.add(stopWords.getStopContent());
      return stopArrayList;
  }
  

 
  //Ranks the sentence. 
  public double[] calcAllSentenceScores(){
    // ***TO WRITE REST OF CODE
    double [] calc = {};
    return calc;
  }
  //Uses TFIDF to rank the weight of each word.
  public void WordRank(){
      TextExtractor te = new TextExtractor();
      
      BagOfWords bag = new BagOfWords();
      
      ArrayList<String> allSent = getAllSentences();
      
      ArrayList<String> allWords = te.seperateWords();
      
      ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
      
      
 
      
      ArrayList<String>sList= new ArrayList<String>();
      
           
      String[] words = allSent.toArray(new String[allSent.size()]);
      
   
        TF_IDF Rank = new TF_IDF(words, allSent);
        for (String s : Rank.getWordVector()) {
            System.out.print(s + "\t" + "\t");
        }
        System.out.println("");
        for (double[] docV : Rank.getTF_IDFMatrix()) {
            for (double v : docV) {
            //    System.out.print(v + "\t");
            }
            System.out.println("");
        }
    }
  }
      
  
      
      
    


