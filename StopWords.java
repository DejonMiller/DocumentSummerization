package document.summary;

import static document.summary.InputDocument.setContent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StopWords {
    
   protected static ArrayList<String> stopContent;

   protected String name;
   
   public static void loadStopWords() throws FileNotFoundException, IOException{
       
    ArrayList<String> lines = new ArrayList<String>();
    String line = null;
    try {
        BufferedReader reader = new BufferedReader(new FileReader("StopWord.txt"));
        while((line = reader.readLine()) != null){
            lines.add(line) ;

            setStopContent(lines);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
     }

    
   
  
  public static void setStopContent(ArrayList<String> content){
       StopWords.stopContent = content;
   }
   
   
   public static ArrayList<String> getStopContent(){
       
       return stopContent;
   }
  
}
 