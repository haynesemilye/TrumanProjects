import java.util.concurrent.locks.ReentrantLock;

/** An atomic integer that achieves atomicity through explicit 
 * locking and unlocking. 
 * 
 */
public class ExplicitlyLockingInt implements AtomicInt {

    private int i;
    private ReentrantLock theLock;
	
    public ExplicitlyLockingInt()
    {
	i = 0;
	theLock = new ReentrantLock();
    }
	

    public ExplicitlyLockingInt (int initialVal)
    {
	i = initialVal;
	theLock = new ReentrantLock();
    }
	

    public void add(int value) {
	theLock.lock();
	i += value;
	theLock.unlock();
    }

 
    public int get() {
	// Technically, we don't need to take the lock here.
	theLock.lock();
	int val = i;
	theLock.unlock();
	return val;
    }


    public void set(int value) {
	theLock.lock();
	i = value;
	theLock.unlock();
    }

}
