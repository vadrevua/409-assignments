/*
 * Program that trains and tests a function to predict temperature given time of day as input
 */

import java.io.*;
import java.util.*;

public class Project3 {
  
  private double[][] dataArr = new double[36][2];
  private ArrayList<Double[]> neurons;
  
  public static void main(String[] args) {
    
    Project3 p = new Project3();
    p.importData();
    //p.normalizeData();
    p.activationFunction(0.00062);
    
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
      while(scan1.hasNextLine()) {
        dataArr[i][0] = scan1.nextDouble();
        dataArr[i][1] = Double.parseDouble(scan1.next());
        i++;
      }
      
      while(scan2.hasNextLine()) {
        dataArr[i][0] = scan2.nextDouble();
        dataArr[i][1] = Double.parseDouble(scan2.next());
        i++;
      }
      
      while(scan3.hasNextLine()) {
        dataArr[i][0] = scan3.nextDouble();
        dataArr[i][1] = Double.parseDouble(scan3.next());
        i++;
      }
      
      while(scan4.hasNextLine()) {
        dataArr[i][0] = scan4.nextDouble();
        dataArr[i][1] = Double.parseDouble(scan4.next());
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
    
    double[] weights = new double[]{ Math.random() * 20, Math.random() * 20 };
    
    for(int i = 0; i < 1000; i++) {
      
      double te_a = 0.0;
      double te_b = 0.0;
      
      for(int j = 0; j < 27; j++) {
        
        double out = weights[0] * dataArr[j][0] + weights[1];
        double dout = dataArr[j][1];
        te_a += ((dout - out) * dataArr[j][0]);
        te_b += (dout - out);
        
      }
      
      weights[0] += alpha * te_a;
      weights[1] += alpha * te_b;
      
    }
    
    System.out.println("Weights: x1 = " + weights[0] + ", x0 = " + weights[1] + "\n");
    System.out.println("TRAINING ERRORS");
    System.out.println("Output (desired)\tOutput (actual)\tAbsolute Error");
    
    for(int i = 0; i < 27; i++) {
      
      double out = weights[0] * dataArr[i][0] + weights[1];
      double dout = dataArr[i][1];
      double err = Math.abs(out - dout);
      System.out.println(dout + "\t" + out + "\t" + err);
      
    }
    
    System.out.println("\nTESTING ERRORS");
    System.out.println("Output (desired)\tOutput (actual)\tAbsolute Error");
    
    for(int i = 27; i < 36; i++) {
      
      double out = weights[0] * dataArr[i][0] + weights[1];
      double dout = dataArr[i][1];
      double err = Math.abs(out - dout);
      System.out.println(dout + "\t" + out + "\t" + err);
      
    }
    
  }
  
}