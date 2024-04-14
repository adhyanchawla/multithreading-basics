import java.util.concurrent.locks.ReentrantLock;

public class RentrantLockWork {
    boolean isAvailable;
    ReentrantLock lock = new ReentrantLock();
    public RentrantLockWork() {
        isAvailable = false;
    }

    public void produce() {
        try {
            lock.lock();
            System.out.println("Lock is acquired by " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (Exception e) {

        } finally {
            lock.unlock();
            System.out.println("Lock is released by " + Thread.currentThread().getName());
//            isAvailable = false;
        }
    }
}
