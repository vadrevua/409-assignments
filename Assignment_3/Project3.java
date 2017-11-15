/*
 * Program that trains and test two types of activation functions on a dataset
 */

import java.io.*;
import java.util.*;

public class Project3 {
  
  private double[][] dataArr = new double[9][5];
  private ArrayList<Double[]> neurons;
  
  public static void main(String[] args) {
    
    Project3 p = new Project3();
    p.importData();
    p.normalizeData();
    p.activationFunction(1.0);
    
    // For checking weight values
    for(int i = 0; i < p.neurons.size(); i++) {
      System.out.println(p.neurons.get(i)[0] + " " + p.neurons.get(i)[1] + " " + p.neurons.get(i)[2]);
    }
    
  }
  
  // Read data and add it to array
  public void importData() {
    
    try {
      
      File data1 = new File("C:/Users/Barack/Documents/CMSC_409/Project3_data/train_data_1.txt");
      Scanner scan1 = new Scanner(data1);
      scan1.useDelimiter(",|\n");
      
      File data2 = new File("C:/Users/Barack/Documents/CMSC_409/Project3_data/train_data_2.txt");
      Scanner scan2 = new Scanner(data2);
      scan2.useDelimiter(",|\n");
      
      File data3 = new File("C:/Users/Barack/Documents/CMSC_409/Project3_data/train_data_3.txt");
      Scanner scan3 = new Scanner(data3);
      scan3.useDelimiter(",|\n");      
      
      File data4 = new File("C:/Users/Barack/Documents/CMSC_409/Project3_data/test_data_4.txt");
      Scanner scan4 = new Scanner(data4);
      scan4.useDelimiter(",|\n");   
      
      int i = 0;
      while(scan1.hasNextLine() && scan2.hasNextLine() && scan3.hasNextLine() && scan4.hasNextLine()) {
        dataArr[i][0] = scan1.nextDouble();
        dataArr[i][1] = Double.parseDouble(scan1.next());
        scan2.nextDouble();
        dataArr[i][2] = Double.parseDouble(scan2.next());
        scan3.nextDouble();
        dataArr[i][3] = Double.parseDouble(scan3.next());
        scan4.nextDouble();
        dataArr[i][4] = Double.parseDouble(scan4.next());
        i++;
      }
      
      scan1.close();
      scan2.close();
      scan3.close();
      scan4.close();
      
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    
  }
  
  public void normalizeData() {
    
    double max = Double.MIN_VALUE;
    double min = Double.MAX_VALUE;
    
    for(int i = 0; i < 9; i++) {
      dataArr[i][0] = (dataArr[i][0] - 5.0) / 8.0;
    }
    
    for(int i = 1; i < 4; i++) {
      for(int j = 0; j < 9; j++) {
        if(dataArr[j][i] > max) max = dataArr[j][i];
        else if(dataArr[j][i] < min) min = dataArr[j][i];
      }
    }
    
    for(int i = 1; i < 4; i++) {
      for(int j = 0; j < 9; j++) {
        dataArr[j][i] = (dataArr[j][i] - min) / (max - min);
      }
    }
    
  }
  
  public void activationFunction(double alpha) {
    
    neurons = new ArrayList<Double[]>();
    neurons.add(new Double[]{ Math.random(), Math.random(), 0.0 });
    neurons.add(new Double[]{ Math.random(), Math.random(), 0.0 });
    neurons.add(new Double[]{ Math.random(), Math.random(), 0.0 });
    neurons.add(new Double[]{ Math.random(), Math.random(), 0.0 });
    neurons.add(new Double[]{ Math.random(), Math.random(), 0.0 });
    neurons.add(new Double[]{ Math.random(), Math.random(), 0.0 });
    neurons.add(new Double[]{ Math.random(), Math.random(), 0.0 });
    neurons.add(new Double[]{ Math.random(), Math.random(), 0.0 });
    neurons.add(new Double[]{ Math.random(), Math.random(), 0.0 });
    
    for(int i = 1; i < 4; i++) {
      for(int j = 0; j < 9; j++) {
        double net = Double.MIN_VALUE;
        int winner = 0;
        for(int k = 0; k < neurons.size(); k++) {
          double out = (neurons.get(k)[0] * dataArr[j][0]) + (neurons.get(k)[1] * dataArr[j][i]);
          if(out > net) {
            net = out;
            winner = k;
          }
        }
        double a = neurons.get(winner)[0];
        double b = neurons.get(winner)[1];
        double normalizedA = (alpha * dataArr[j][0] + a) / Math.sqrt(Math.pow(alpha * dataArr[j][0] + a, 2) + Math.pow(alpha * dataArr[j][i] + b, 2));
        double normalizedB = (alpha * dataArr[j][i] + b) / Math.sqrt(Math.pow(alpha * dataArr[j][i] + b, 2) + Math.pow(alpha * dataArr[j][0] + a, 2));
        neurons.set(winner, new Double[]{ normalizedA, normalizedB, 1.0 });
      }
    }
    
  }
  
}