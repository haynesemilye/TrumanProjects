import java.io.FileNotFoundException;
import java.lang.InterruptedException;
import java.io.FileReader;
import java.util.Scanner;

/** A program to determine the common meeting time
 * between three people. 
 * 
 * Developed by Emily Haynes on 1 December 2014
 */
 
public class HaynesCMT {
 
  /** Read a file containing the availible meeting times 
    * of three different people.
    * The times for each person are on separate lines,
    * and the first number on each line indicates how many 
    * times are in the line. 
    * Create the same number of threads as there are times on
    * the first line of the file. Then, each thread compares 
    * its number against the other two lines in the file.
    * @param args gets the file name from the user
    */
 
  public static void main (String [] args) 
    throws FileNotFoundException {
  
    String filename = args[0];
    
    //Tries to execute, fails if file is not found
    try {
    
      //Create new Scanner object from the file
      Scanner timeFile = new Scanner(new FileReader(filename));
      
      /** The first number in the file is the 
        * number of threads to create
        */
      int numThreads = timeFile.nextInt();
      
      //Array to hold the first line of ints
      int[] times1 = new int[numThreads];
      for (int i = 0; i < numThreads; i++) {
        times1[i] = timeFile.nextInt();
      }
      
      //The first number on the second line
      int numTimes = timeFile.nextInt();
      
      //Array to hold the second line of ints
      int[] times2 = new int[numTimes];
      for (int i = 0; i < numTimes; i++) {
        times2[i] = timeFile.nextInt();
      }
      
      //The first number on the third line
      numTimes = timeFile.nextInt();
      
      //Array to hold the third line of ints
      int[] times3 = new int[numTimes];
      for (int i = 0; i < numTimes; i++) {
        times3[i] = timeFile.nextInt();
      }
      
      //Close dat file. 
      timeFile.close();
      
      //Create arrays to hold frames and threads
      TimeThread [] frame = new TimeThread[numThreads];
      Thread [] t = new Thread[numThreads];
      
      /* Create the threads, passing in the number
       * each thread is looking for, as well as the
       * other two arrays.
      */
      for (int i = 0; i < numThreads; i++){
        frame[i] = new TimeThread(times1[i], 
                                  times2, 
                                  times3);
      }
      
      //Create the threads!
      for (int i = 0; i < numThreads; i++){
        t[i] = new Thread(frame[i]);
      }
      
      //Start the threads!
      for (int i = 0; i < numThreads; i++){
        t[i].start();
      }
      
      //Join the threads! Be the threads!
      for (int i = 0; i < numThreads; i++){
        
        //Throws InterruptedException
        try {
          t[i].join();
        }
        catch (InterruptedException e) {
          System.out.println("oh noes! Thread " 
                            + times1[i] 
                            + " got interrupted. Join failed.");
        }
      }
    }
    //oh noes! The file doesn't exist
    catch (FileNotFoundException e){
      System.out.println("oh noes! i cant haz file :(");
    }
  }
}
