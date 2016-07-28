package document.summary;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputDocument{

        protected String name;

        protected static String content;
        
        StopWords stopWords = new StopWords();
        
        protected static ArrayList<String> documentArray = new ArrayList<String>();
        
        TextExtractor te = new TextExtractor();


        public static void loadFile(String filePath) throws IOException{
            
    BufferedReader br = null;
                try {
                br = new BufferedReader(new FileReader(filePath));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();
                    
                    while (line != null) {
                        sb.append(line);
                        sb.append("\n");
                        line = br.readLine();
                    }
                    String inputDocument = sb.toString();
                    
                    setContent(inputDocument);
                    documentArray.add(inputDocument);
                    setDocumentArray(documentArray);
                } finally {
                    br.close();
                }
          
}           catch (FileNotFoundException ex) {
                Logger.getLogger(InputDocument.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(InputDocument.class.getName()).log(Level.SEVERE, null, ex);
                }
                
              
     }

        }

        public static String getContent() {
                return content.toLowerCase();
               
        }

        public static void setContent(String content) {
                InputDocument.content = content;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        
        public String[] getAllTerms() {
                return createTextExtractor().extractTerms();
        }

        
        public ArrayList<String> getAllSentences() {
                return createTextExtractor().seperateSentence();
        }
        
        
        public ArrayList<String> getWordFrequency(){
            return createTextExtractor().seperateSentence();
        }
        
        
        
         private static void setDocumentArray(ArrayList<String> documentArray) {
             InputDocument.documentArray = documentArray;
    }
         
         
         public static ArrayList<String> getDocumentArray(){
             return documentArray;
         }
         
              
        public String getReducedSentence(){
                                  
           ArrayList<String> stopwords = stopWords.getStopContent();
           String document = content;
          for(int i =0; i < stopwords.size(); i++)
               
              document.replace(stopwords.get(i), "");
              document = content;
           
                 
           return document;
            
        }
        
       
        
        private TextExtractor createTextExtractor() {
                TextExtractor b = new TextExtractor();
                b.setText(getContent());
                return b;
        }

}
