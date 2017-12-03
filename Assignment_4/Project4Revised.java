import java.io.*;
import java.util.*;

public class assignment4 {
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] dataArr = new String[35];
		try {
		      
		      File data1 = new File("sentences.txt");
		      Scanner scan1 = new Scanner(data1);
		      
		      int i = 0;
		      while(scan1.hasNextLine()) {
		        dataArr[i] = scan1.nextLine();
		        
		        i++;
		      }
		      
		      scan1.close();
		      
		      
		    }
		    catch(IOException e) {
		      e.printStackTrace();
		    }
		
		for(String line : dataArr) {
			System.out.println(line);
		}
	}
	*/
	
	public static void main(String[] args) {
	    /*
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
	      
	      Stemmer.main(new String[]{"output_3.txt"});
	      
	      
	    }
	    catch(Exception e) {
	      
	    }
	    */
		
		try{
		      
		      Scanner stem = new Scanner(new File("stem_out.txt"));
		      ArrayList<String> stemList = new ArrayList<String>();
		      //String stemString = "";
		      while(stem.hasNext()) {
		    	  	String word = stem.next();
		        if(!stemList.contains(word)) {
		        		stemList.add(word);
		        }
		      }
		      
		      Scanner stem2 = new Scanner(new File("stem_out.txt"));
		      int sentCount = 0;
		      
		      while(stem2.hasNextLine()) {
		    	  	stem2.nextLine();
		    	  	sentCount++;
		      }
		      
		      Scanner stem3 = new Scanner(new File("stem_out.txt"));
		      int[][] tdm = new int[sentCount][stemList.size()];
		      
		      for(int i = 0; i<tdm.length; i++) {
		    	  	for(int j = 0; j<tdm[0].length; j++) {
		    	  		tdm[i][j] = 0;
		    	  	}
		      }
		      
		      int currentLine = 0;
		      while(stem3.hasNextLine()) {
		    	  	Scanner stemLine = new Scanner(stem3.nextLine());
		    	  	while(stemLine.hasNext()) {
		    	  		String word = stemLine.next();
		    	  		int position = stemList.indexOf(word);
		    	  		tdm[currentLine][position]++;
		    	  	}
		    	  	currentLine++;
		    	  	stemLine.close();
		      }
		      
		      
		      File f1 = new File("tdm.csv");
		      if(!f1.exists()) {
		          f1.createNewFile();
		      } 
		      FileWriter fileWritter = new FileWriter(f1.getName(),true);
		      BufferedWriter bw = new BufferedWriter(fileWritter);
		      String columnHeaders = "";
		      String row = "";
		      
		      for(int i = 0; i<stemList.size(); i++) {
		    	  	if(i == 0) {
		    	  		columnHeaders = "Keyword set," + stemList.get(i);
		    	  	} else {
		    	  		columnHeaders = columnHeaders +","+ stemList.get(i);
		    	  	}	
		      }
		      System.out.println(columnHeaders);
		      bw.write(columnHeaders + "\n");
		      for(int i = 0; i<tdm.length; i++) {
		    	  	for(int j = 0; j<tdm[0].length; j++) {
		    	  		if(j == 0) {
		    	  			row = "Sentence " + i +","+ tdm[i][j];
		    	  		} else {
		    	  			row = row +","+ tdm[i][j];
		    	  		}
		    	  	}
		    	  	bw.write(row + "\n");
		    	  	System.out.println(row);
		      }
		      bw.flush();
		      bw.close();
		      
		      stem.close();
		      stem2.close();
		      stem3.close();
		    }
		    catch(Exception e) {
		      
		    }
	    
	  }
	  
}
