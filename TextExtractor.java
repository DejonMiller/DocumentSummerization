
package document.summary;

import java.text.BreakIterator;
import java.util.ArrayList;
import static java.util.Arrays.stream;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextExtractor {

        private String text;
        
        private int compressionRate;
        

        public String getText() {
                return text;
        } 
        

        public void setText(String text) {
                this.text = text;
        }  
        

        public String[] extractTerms() {
                BreakIterator boundary = BreakIterator.getWordInstance(Locale.US);
                boundary.setText(getText());

                List<String> words = new ArrayList<String>();
                int start = boundary.first();
                int end = boundary.next();
                while (end != BreakIterator.DONE) {
                        String word = getText().substring(start, end).trim();
                        if (!word.isEmpty()) {
                                words.add(word);
                        }
                        start = end;
                        end = boundary.next();
                }

                return words.toArray(new String[words.size()]);
        }
               
        
        public ArrayList<String> seperateWords(){
            Preprocess pre = new Preprocess();
            ArrayList<String> words = new ArrayList<String>();
            String document = pre.removeStopWords();
            
            for(String word : document.split(" ")) {
                
                words.add(word);
                
                }
           
            
            return words;
        } 
        
        //Sperates Snetence
        public ArrayList<String> seperateSentence(){
            Preprocess pre = new Preprocess();

            //String document = pre.removeStopWords();
           String document = InputDocument.getContent().toLowerCase();
            
            ArrayList<String> sentence = new ArrayList<String>();
            
            Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?]"
                    
                    + "(?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)", Pattern.MULTILINE | Pattern.COMMENTS);
    Matcher reMatcher = re.matcher(document);
    while (reMatcher.find()) {
       // System.out.println(reMatcher.group());
        sentence.add(String.valueOf(reMatcher.group()));
    }
 
           // System.out.println(sentence.size());
            return sentence;
        }
        
        //Gets the number of each word.   Uses Parallel Computing
        
        public ArrayList<String> wordCount(int CR)throws NullPointerException
{
            String document = InputDocument.getContent();
            TextExtractor ex = new TextExtractor();
            ArrayList<String> reducedDocument = ex.removeStop();
        
            ArrayList<String> sents = seperateWords();
            ArrayList<String> bestWords = new ArrayList<String>();
            ArrayList<String> places = new ArrayList<String>();
            String word;
            String place;
          /*  for(int i =0; i< reducedDocument.size(); i++){
                place = reducedDocument.get(i);
                if(place.contains("."))
                    
            }*/
           
           //Converts ArrayList<String> to string 
            String listString = String.join(", ", reducedDocument);

            String betterList = listString;
          
                        
         
            Stream <String> stream = Stream.of(betterList.toLowerCase().split("\\W+")).parallel();

            Map<String, Long> wordFreq = stream
     .collect(Collectors.groupingBy(String::toString,Collectors.counting()));

            System.out.println(wordFreq);
            
            for(int i =0; i< sents.size(); i++){
                
               word = sents.get(i);
               System.out.println(word);
               if(wordFreq.get(word) >= CR){
          
                bestWords.add(word);
               // System.out.println(word);
                  
               }
                           
            }
            
            return bestWords;
        
        }


        //Removes stopwords from document.
        
        private ArrayList<String> removeStop(){
            
            StopWords stopWords = new StopWords();
            
            ArrayList<String> input = seperateWords();
            ArrayList<String> better = new ArrayList<String>();
            String words;
            for(int i =0; i < input.size(); i++){
                
                words = input.get(i);
                words.replaceAll("[^a-z\\sA-Z\\s[0-9]]","");
            better.add(words);
            //System.out.println(better.get(i));
        }
            
            //ArrayList<String>input = seperateSentence();
            
            ArrayList<String> stopList = stopWords.getStopContent();
            
            
            for (String word : stopList) 
                
                 while(input.contains(word)) 
                     
                 
                     input.remove(word);

            
            return input;
                            }    
        
}