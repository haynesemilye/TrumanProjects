public class TimeThread implements Runnable {

  /* Private variables to hold the number
   * the thread is searching for, as well
   * as the two other arrays
   */
  private int threadId;
  private int[] list2;
  private int[] list3;
  
  /** Constructor 
    * @param theId the number the thread is searching for
    * @param theList2 the second line of numbers
    * @param theList3 the third list of numbers
    */
  public TimeThread (int theId, 
                    int[] theList2, 
                    int[] theList3) {
    
    //Initialize class variables
    threadId = theId;
    list2 = theList2;
    list3 = theList3;
    
  }
  /** Begin execution of thread. 
    * Iterates through second and third arrays. 
    * If it finds the number it is searching for
    * in both arrays, it prints that number as a 
    * common meeting time
    */
  public void run(){

    for (int i = 0; i < list2.length; i++){
    	if(list2[i] == threadId){
    		for (int j = 0; j < list3.length; j++){
    			if(list3[j] == threadId){
    			
    				System.out.println(threadId 
    				    + " is a common meeting time.");
  				
  			  }
  		  }
  	  }
    }
  }
}
