import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class Project4 {
  
  public static void main(String[] args) {
    
    File txt = new File("C:/Users/Barack/Documents/GitHub/409Assignment1/Assignment_4/Project4_sentences/sentences.txt");
    File stopFile = new File("C:/Users/Barack/Documents/GitHub/409Assignment1/Assignment_4/Project4_sentences/stop_words.txt");
    Scanner sentences, stoppies, stemDoc;
    String[] words, stopWords;
    Object[] stems;
    
    try {
      
      sentences = new Scanner(txt);
      stoppies = new Scanner(stopFile);
      
      String wordString = "";
      String stopString = "";
      /* while(sentences.hasNext()) {
        wordString = wordString + sentences.nextLine() + " ";
      } */
      while(stoppies.hasNext()) {
        stopString = stopString + stoppies.next() + " ";
      }
      
      while(sentences.hasNextLine()) {
        wordString = sentences.nextLine();
        stopWords = stopString.toLowerCase().split("\\s+");
        words = wordString.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
        ArrayList<String> stopList = new ArrayList<String>(Arrays.asList(stopWords));
        ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
        for(int i = 0; i < stopList.size(); i++) {
          if(wordList.remove(stopList.get(i).trim())) i--;
        }
        List<String> line = wordList;
        for(int i = 0; i < line.size(); i++) System.out.print(line.get(i) + " ");
        System.out.println();
        Path file = Paths.get("sentence.txt");
        Files.write(file, line, Charset.forName("UTF-8"));
        String[] arg = new String[]{ "sentence.txt" };
        Stemmer.main(args);
      }
      
      /*
      Scanner senScanner = new Scanner(wordString);
      ArrayList<ArrayList<String>> senList = new ArrayList<ArrayList<String>>();
      while(senScanner.hasNextLine()) {senList.add(Arrays.asList(senScanner.nextLine().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+")));}
      for(int i = 0; i < senList.size(); i++) {
        ArrayList<String> str = senList.get(i);
        for(int j = 0; j < stopList.size(); j++) {
          if(str.remove(stopList.get(j).trim())) j--;
        }
      }
      
      stopWords = stopString.toLowerCase().split("\\s+");
      words = wordString.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
      ArrayList<String> stopList = new ArrayList<String>(Arrays.asList(stopWords));
      ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(words));
      
      for(int i = 0; i < stopList.size(); i++) {
        if(wordList.remove(stopList.get(i).trim())) i--;
      }
      
      List<String> lines = wordList;
      Path file = Paths.get("output_1.txt");
      Files.write(file, lines, Charset.forName("UTF-8"));
      
      sentences.close();
      stoppies.close();
      
      File stemFile = new File("C:/Users/Barack/Documents/GitHub/409Assignment1/Assignment_4/output_2.txt");
      stemDoc = new Scanner(stemFile);
      HashMap<String, Integer> freq = new HashMap<String, Integer>();
      while(stemDoc.hasNext()) {
        String word = stemDoc.next();
        if(!freq.containsKey(word)) freq.put(word, 1);
        else {
          int val = freq.get(word);
          val++;
          freq.put(word, val);
        }
      }
      
      stems = freq.keySet().toArray();
      
      Object[][] tdm = new Object[27][freq.size()];
      
      Scanner sen = new Scanner(txt);
      ArrayList<String> sentenceArr = new ArrayList<String>();
      while(sen.hasNextLine()) {
        String sentence = sen.nextLine();
        for(int i = 0; i < freq.size(); i++) {
          if(sentence.contains(
        }
      } */
      
      
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    
  }
  
}