import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class LabelThread implements Runnable {

	private int row;
	AtomicInt[][] regionList;
	AtomicInt[][] labelList;
	AtomicInt doneChanging;
	AtomicInt loopControl;
	AtomicInt numRows;
	AtomicInt numCols;
	CyclicBarrier barrierFirst;
	CyclicBarrier barrierSecond;

	/**
	 * Initialization for the threads
	 * @param row the row number
	 * @param regionList the input from the user
	 * @param labelList the array of labels in the regions
	 * @param doneChanging checks if there have been any changes
	 * @param loopControl boolean loop executes on
	 * @param numRows number of rows in arrays
	 * @param numCols number of columns in arrays
	 * @param barrierFirst first barrier
	 * @param barrierSecond second barrier
	 */
	public LabelThread (int row, 
			AtomicInt[][] regionList, 
			AtomicInt[][] labelList, 
			AtomicInt doneChanging,
			AtomicInt loopControl,
			AtomicInt numRows,
			AtomicInt numCols,
			CyclicBarrier barrierFirst,
			CyclicBarrier barrierSecond) {
		this.row = row;
		this.regionList = regionList;
		this.labelList = labelList;
		this.doneChanging = doneChanging;
		this.loopControl = loopControl;
		this.numRows = numRows;
		this.numCols = numCols;
		this.barrierFirst = barrierFirst;
		this.barrierSecond = barrierSecond;
	}

	/**
	 * Goes through each column, checks
	 * if neighbors are the same, if so,
	 * set the current value to the max
	 * of the two ints.
	 */
	public void run() {

		while(loopControl.get() == 0){

			for (int i = 0; i < numCols.get(); i++){

				//Above
				if(row != 0 
						&& regionList[row][i].get() 
							== regionList[row - 1][i].get()
						&& labelList[row][i].get() 
							!= labelList[row - 1][i].get()){
					
					labelList[row][i].set(Math.max(labelList[row][i].get(), 
							labelList[row - 1][i].get()));
					
					//We've made a change, so we are not done changing
					doneChanging.set(0);

				//Below
				} else if (row != (numRows.get() - 1) 
						&& regionList[row][i].get() 
							== regionList[row + 1][i].get()
						&& labelList[row][i].get() 
							!= labelList[row + 1][i].get()){

					labelList[row][i].set(Math.max(labelList[row][i].get(), 
							labelList[row + 1][i].get()));
					
					doneChanging.set(0);

				//Left
				} else if (i != 0 
						&& regionList[row][i].get() 
							== regionList[row][i - 1].get()
						&& labelList[row][i].get() 
							!= labelList[row][i - 1].get()){

					labelList[row][i].set(Math.max(labelList[row][i].get(), 
							labelList[row][i - 1].get()));
					
					doneChanging.set(0);

				//Right
				} else if (i != (numCols.get() - 1) 
						&& regionList[row][i].get() 
							== regionList[row][i + 1].get()
						&& labelList[row][i].get() 
							!= labelList[row][i + 1].get()){

					labelList[row][i].set(Math.max(labelList[row][i].get(), 
							labelList[row][i + 1].get()));
					
					doneChanging.set(0);

				} else {
					//do nothing
				}
			}
			//First barrier to wait for all threads to catch up
			try {
				barrierFirst.await();
			} catch (BrokenBarrierException e) {
				System.out.println("barrierFirst broken!");
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println("barrierFirst interrupted!");
				e.printStackTrace();
			}
			
			/* Only the first row checks this
			 * 
			 * If there have been no changes, 
			 * the loops exits. If there have been 
			 * changes, keep looping and reset the 
			 * done variable.
			 */
			if (row == 0){
				if (doneChanging.get() == 1){
					loopControl.set(1);
				} else {
					loopControl.set(0);
					doneChanging.set(1);
				}
			}
			
			//Second barrier
			try {
				barrierSecond.await();
			} catch (BrokenBarrierException e) {
				System.out.println("barrierSecond broken!");
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println("barrierSecond interrupted!");
				e.printStackTrace();
			}
		}
	}
}
