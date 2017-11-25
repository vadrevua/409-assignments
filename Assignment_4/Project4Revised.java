import java.io.*;
import java.util.*;

public class Project4Revised {
  
  public static void main(String[] args) {
    
    try{
      
      Scanner stoppies = new Scanner(new File("stop_words.txt"));
      String stopString = "";
      while(stoppies.hasNext()) {
        stopString = stopString + stoppies.next() + " ";
      }
      ArrayList<String> stopList = new ArrayList<String>(Arrays.asList(stopString.toLowerCase().split("\\s+")));
      
      Scanner senFile = new Scanner(new File("sentences.txt"));
      while(senFile.hasNextLine()) {
        String sentence = senFile.nextLine();
        System.out.println(sentence);
        sentence = sentence.replaceAll("[^a-zA-Z ]", " ").toLowerCase().trim();
        Scanner words = new Scanner(sentence);
        ArrayList<String> wordList = new ArrayList<String>();
        while(words.hasNext()) {
          String word = words.next();
          if(!word.equals("")) wordList.add(word);
        }
        for(int i = 0; i < stopList.size(); i++) {
          if(wordList.remove(stopList.get(i).trim())) i--;
        }
        String newSentence = "";
        for(int i = 0; i < wordList.size(); i++) {
          newSentence += wordList.get(i) + " ";
        }
        File f1 = new File("output_3.txt");
        if(!f1.exists()) {
          f1.createNewFile();
        } 
        FileWriter fileWritter = new FileWriter(f1.getName(),true);
        BufferedWriter bw = new BufferedWriter(fileWritter);
        bw.write(newSentence + "\n");
        bw.close();
        words.close();
      }
      
    }
    catch(Exception e) {
      
    }
    
  }
  
}