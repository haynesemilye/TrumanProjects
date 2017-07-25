import java.io.FileNotFoundException;
import java.lang.InterruptedException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;


/** A program to find the regions of a
 * grid of ints
 * 
 * Developed by Emily Haynes on 10 December 2014
 */

public class HaynesRegionLabel {

	/** The main class
	 * Gets the file from the user, creates two separate
	 * 2d arrays, one with the file information, the other
	 * the same size as the first but with unique labels
	 * Creates threads to label the regions
	 * Prints final label array
	 * @param args gets the file name from the user
	 */

	public static void main (String [] args) 
	throws FileNotFoundException {

		String filename = args[0];

		//Tries to execute, fails if file is not found
		try {

			//Create new Scanner object from the file
			Scanner regionFile = new Scanner(new FileReader(filename));

			//Number of rows and columns in the matrix
			AtomicInt numRows = new ExplicitlyLockingInt(regionFile.nextInt());
			AtomicInt numCols = new ExplicitlyLockingInt(regionFile.nextInt());
			
			// Shared "booleans" for threads to use
			AtomicInt doneChanging = new ExplicitlyLockingInt(0);
			AtomicInt loopControl = new ExplicitlyLockingInt(0);
			
			//Barriers for threads
			CyclicBarrier barrierFirst = new CyclicBarrier(numRows.get());
			CyclicBarrier barrierSecond = new CyclicBarrier(numRows.get());
			
			//Array to hold the grid
			AtomicInt[][] regionList = new ExplicitlyLockingInt[numRows.get()][numCols.get()];
			
			for (int i = 0; i < numRows.get(); i++) {
				for (int j = 0; j < numCols.get(); j++){
					AtomicInt a = new ExplicitlyLockingInt(regionFile.nextInt());
					regionList[i][j] = a;
				}
			}

			//Array to hold labels
			AtomicInt[][] labelList = new ExplicitlyLockingInt[numRows.get()][numCols.get()];

			int count = 0;
			for (int i = 0; i < numRows.get(); i++){
				for (int j = 0; j < numCols.get(); j ++){
					AtomicInt a = new ExplicitlyLockingInt(count);
					labelList[i][j] = a;
					count++;
				}
			}

			//Close dat file. 
			regionFile.close();

			//Create arrays to hold frames and threads
			LabelThread [] frame = new LabelThread[numRows.get()];
			Thread [] t = new Thread[numRows.get()];

			/* Initialize the threads, passing in the row
			 * number, the two arrays, the control
			 * variables, number of rows and columns,
			 * and the barriers
			 */
			for (int i = 0; i < numRows.get(); i++){
				frame[i] = new LabelThread(i, 
							regionList, 
							labelList, 
							doneChanging,
							loopControl,
							numRows,
							numCols,
							barrierFirst,
							barrierSecond);
			}

			///Create the threads!
			for (int i = 0; i < numRows.get(); i++){
				t[i] = new Thread(frame[i]);

			}

			//Start the threads!
			for (int i = 0; i < numRows.get(); i++){
				t[i].start();

			}

			//Join the threads! Be the threads!
			for (int i = 0; i < numRows.get(); i++){

				//Throws InterruptedException
				try {
					t[i].join();
				}
				catch (InterruptedException e) {
					System.out.println("oh noes! Thread " 
							+ i
							+ " got interrupted. Join failed.");
				}
			}
			
			//Print out the final label array
			for (int i = 0; i < numRows.get(); i++) {
				for (int j = 0; j < numCols.get(); j++){
					if(j % numCols.get() == 0){
						System.out.println();
					}
					System.out.print(labelList[i][j].get() + "\t");
				}
			}
			System.out.println();

		}
		//oh noes! The file doesn't exist
		catch (FileNotFoundException e){
			System.out.println("oh noes! i cant haz file :(");
		}
	}
}

