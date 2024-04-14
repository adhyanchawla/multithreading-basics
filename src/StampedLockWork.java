import java.util.concurrent.locks.StampedLock;

public class StampedLockWork {
    StampedLock lock = new StampedLock();
    int a = 10;
    public void produce() {
        long stampedValue = lock.tryOptimisticRead();
        try {
            System.out.println("optimistic locking");
            a = 11;
            Thread.sleep(6000);
            if(lock.validate(stampedValue)) {
                System.out.println("updated the value successfully");
            } else {
                System.out.println("rollback of work");
                a = 10;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void consume() {
        long stampedValue = lock.writeLock();
        System.out.println("write lock is acquired by " + Thread.currentThread().getName());
        try {
            System.out.println("system is performing work");
            a = 9;
        } finally {
            lock.unlockWrite(stampedValue);
            System.out.println("lock is released by " + Thread.currentThread().getName());
        }
    }
}
