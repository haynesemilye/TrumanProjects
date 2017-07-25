
/**
 * An interface for an atomically
 * implemented integer
 */
public interface AtomicInt {
	
	/**
	 * set the value
	 * @param value value to set
	 */
	void set(int value);
	
	/**
	 * Get the value
	 * @return value of integer
	 */
	int get();
	
	/**
	 * Add to the integer
	 * @param value value to add
	 */
	void add(int value);

}
