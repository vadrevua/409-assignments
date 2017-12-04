import java.util.*;
import java.io.*;

public class Clustering {
  
  private ArrayList<ArrayList<Double>> clusters = new ArrayList<ArrayList<Double>>();
  private ArrayList<ArrayList<Double>> vectors = new ArrayList<ArrayList<Double>>();
  
  public static void main(String[] args) {
    
    Clustering c = new Clustering();
    c.importData();
    c.cluster(1.0);
    
  }
  
  public void importData() {
    
    File data = new File("out.csv");
    try{
      Scanner scan = new Scanner(data);
      scan.nextLine();
      while(scan.hasNextLine()) {
        Scanner senScan = new Scanner(scan.nextLine());
        senScan.useDelimiter(",");
        ArrayList<Double> vector = new ArrayList<Double>();
        senScan.next();
        while(senScan.hasNext()) {
          vector.add(Double.parseDouble(senScan.next()));
        }
        senScan.close();
        vectors.add(vector);
      }
      scan.close();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    
  }
  
  public void cluster(double alpha) {
    
    final double HD = 5.0;
    for(ArrayList<Double> v : vectors) {
      int clusterIndex = -1;
      for(ArrayList<Double> c : clusters) {
        double distance = 0.0;
        double minDistance = HD;
        for(int j = 0; j < v.size(); j++) {
          distance += Math.pow(v.get(j) - c.get(j), 2.0);
        }
        distance = Math.sqrt(distance);
        if(distance < minDistance) {
          clusterIndex = clusters.indexOf(c);
          minDistance = distance;
        }
      }
      if(clusterIndex == -1) {
        clusters.add(v);
        clusters.get(clusters.size() - 1).add(1.0);
      }
      else {
        ArrayList<Double> cluster = clusters.get(clusterIndex);
        double m = cluster.get(cluster.size() - 1);
        System.out.println(clusterIndex + ": " + (m+1));
        for(int i = 0; i < cluster.size() - 1; i++) {
          double newVal = (m * cluster.get(i) + alpha * v.get(i)) / (m + 1.0);
          cluster.set(i, newVal);
          //System.out.println(newVal);
        }
        cluster.set(cluster.size() - 1, m + 1);
      }
    }
    
    for(ArrayList<Double> c : clusters) {
      for(int i = 0; i < c.size() - 1; i++) {
        System.out.print(c.get(i) + " ");
      }
      System.out.println();
    }
    System.out.println(clusters.size());
  }
  
}