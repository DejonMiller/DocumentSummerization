package document.summary;
//Going to use tf-idf
//Tf is term frequencey. 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* Rank words
Generate Best words
Generate senteces with best words.
Display summary
*/
public class BagOfWords {
    
    Generator gen = new Generator();
    
//    Preprocess pre = new Preprocess();
    
    //TF(t) = (Number of times term t appears in a sentence) / (Total number of terms in the sentence).
    
   public double TermFrequency(List<String> doc, String term) {
      
    double result = 0;
   
    for (String word : doc) {
       if (term.equalsIgnoreCase(word))
              result++;
       }
    return result / doc.size();
}
   
        // IDF(t) = log_e(Total number of sentences / Number of sentences with term t in it).
    public double InverseTermFrequency(ArrayList<ArrayList<String>> docs, String term) {
        
    double numSent = gen.getAllSentences().size();
    
    double n = 0;
    for (List<String> doc : docs) {
        for (String word : doc) {
            if (term.equalsIgnoreCase(word)) {
                n++;
                break;
            }
        }
    }
    return Math.log(docs.size() / n);
}
    
    // Sentence, All sentences(without stopwords), Word    //List<List<String>>
     public double tfIdf(String Sentence, 
             ArrayList<ArrayList<String>> allSentences, String term) {
         
             ArrayList<String> sent = new ArrayList<String>();
             sent.add(Sentence);
         
               return TermFrequency(sent, term) * InverseTermFrequency(allSentences, term);


    }

    
    
    
}
