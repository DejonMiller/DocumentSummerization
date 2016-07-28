package document.summary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;


class SummaryListener implements ActionListener {
    JTextField compression;
    JTextArea Doc;
    JTextField Search;
    JTextArea Summary;
   // JButton start;
    JComboBox List;

  
    
    public SummaryListener(JTextField compression, JTextArea Doc, 
             JTextField Search, JTextArea Summary, JComboBox List) {
        
        this.compression = compression;
        this.Doc = Doc;
        this.Search = Search;
        this.Summary = Summary;
       // this.start = start;
        this.List = List;
    
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== List){
             
            
            Summary.setText("This is the summary");
            String [] fileArray = {"0"};
               
    String filePath = fileArray[0];
    

    if(filePath == null){
      filePath = "./Sample.txt";
    }

   
    InputDocument input = new InputDocument();
    Generator gen = new Generator();
    StopWords stop = new StopWords();
    BagOfWords bow = new BagOfWords();     
    TextExtractor te = new TextExtractor();


            String document = Doc.getText().toString();
           JComboBox cb = (JComboBox)e.getSource();
        String file = (String)cb.getSelectedItem();
            try {
                gen.loadFile(file);
            } catch (IOException ex) {
                Logger.getLogger(SummaryListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            

           try {
                
                stop.loadStopWords();                
            } catch (IOException ex1) {
                Logger.getLogger(SummaryListener.class.getName()).log(Level.SEVERE, null, ex1);
                
            }
                   

           String ratio = compression.getText();
           int CR = Integer.parseInt(ratio);
           if(CR <1 || CR>6){
               compression.setText("Enter a value between 1 and 6");
           }
           
           
           Summary.setText(input.getContent());
          Preprocess pre = new Preprocess();

     Stemmer stem = new Stemmer();
  
   // gen.WordRank();
    stem.add(input.getContent().toCharArray(), input.getContent().length());
    stem.stem();
  // stem.stem(input.getContent());
   
    Doc.setText(gen.generateSummary(CR).toString());
   
        
      
        }
             
    }
    
      
}


