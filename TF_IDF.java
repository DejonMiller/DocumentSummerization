/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package document.summary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import javax.swing.text.Document;

public class TF_IDF {
 
    int numOfWords;
    double[] idfVector;
    double[][] tfIdfMatrix;
    double[][] tfMatrix;
    String[] wordVector;
    int docLength[];
 
    public TF_IDF(String[] docs, ArrayList<String> allSent) {
        
        
        // STEP 1, scan all words and count the number of different words
        // mapWordToIdx maps word to its vector index
        HashMap<String, Integer> mapWordToIdx = new HashMap<>();
        int nextIdx = 0;
        for (String doc : docs) {
            for (String word : doc.split(" ")) {
                if (!mapWordToIdx.containsKey(word)) {
                    mapWordToIdx.put(word, nextIdx);
                   System.out.println(mapWordToIdx);
                    nextIdx++;
                }
            }                 //  System.out.println(mapWordToIdx);

        }
        //System.out.println(mapWOrdToIdx);
 
        numOfWords = mapWordToIdx.size();
 
        // STEP 2, create word vector where wordVector[i] is the actual word
        wordVector = new String[numOfWords];
        for (String word : mapWordToIdx.keySet()) {
            int wordIdx = mapWordToIdx.get(word);
            wordVector[wordIdx] = word;
           System.out.println(wordVector[wordIdx]);
        }
 
        // STEP 3, create doc word vector where docCountVector[i] is number of
        // docs containing word of index i
        // and doc length vector
        int[] docCountVector = new int[numOfWords];
        docLength = new int[docs.length];
        // lastDocWordVector is auxilary vector keeping track of last doc index
        // containing the word
        int[] lastDocWordVector = new int[numOfWords];
        for (int wordIdx = 0; wordIdx < numOfWords; wordIdx++) {
            lastDocWordVector[wordIdx] = -1;
        }
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            String doc = docs[docIdx];
            String[] words = doc.split(" ");
            for (String word : words) {
                docLength[docIdx] = words.length;
                int wordIdx = mapWordToIdx.get(word);
                if (lastDocWordVector[wordIdx] < docIdx) {
                    lastDocWordVector[wordIdx] = docIdx;
                    docCountVector[wordIdx]++;
                }
            }
        }
 
        // STEP 4, compute IDF vector based on docCountVector
        idfVector = new double[numOfWords];
        for (int wordIdx = 0; wordIdx < numOfWords; wordIdx++) {
            idfVector[wordIdx] = Math.log10(1 + (double) docs.length / (docCountVector[wordIdx]));
        }
 
        // STEP 5, compute term frequency matrix, tfMatrix[docIdx][wordIdx]
        tfMatrix = new double[docs.length][];
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            tfMatrix[docIdx] = new double[numOfWords];
        }
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            String doc = docs[docIdx];
            for (String word : doc.split(" ")) {
                int wordIdx = mapWordToIdx.get(word);
                tfMatrix[docIdx][wordIdx] = tfMatrix[docIdx][wordIdx] + 1;
            }
        }
        // normalize idfMatrix by deviding corresponding doc length
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            for (int wordIdx = 0; wordIdx < numOfWords; wordIdx++) {
                tfMatrix[docIdx][wordIdx] = tfMatrix[docIdx][wordIdx] / docLength[docIdx];
            }
        }
 
        // STEP 6, compute final TF-IDF matrix
        //tfIdfMatrix[docIdx][wordIdx] = tfMatrix[docIdx][wordIdx] * idfVector[wordIdx]
        tfIdfMatrix = new double[docs.length][];
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            tfIdfMatrix[docIdx] = new double[numOfWords];
        }
 
        for (int docIdx = 0; docIdx < docs.length; docIdx++) {
            for (int wordIdx = 0; wordIdx < numOfWords; wordIdx++) {
             // System.out.println(tfIdfMatrix[docIdx][wordIdx] = tfMatrix[docIdx][wordIdx] * idfVector[wordIdx]);
               tfIdfMatrix[docIdx][wordIdx] = tfMatrix[docIdx][wordIdx] * idfVector[wordIdx];
            }
                    //System.out.println(tfIdfMatrix[docIdx][6]);
                    
                                      
                    
            }

    }
        
            public double[][] getTF_IDFMatrix() {
        return tfIdfMatrix;
    }

    public String[] getWordVector() {
        return wordVector;
    }

}
    
   
