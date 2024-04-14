import java.util.concurrent.locks.ReadWriteLock;

public class ReadWriteLockWork {
    boolean isAvailable = false;
    public void produce(ReadWriteLock lock) {
        try {
            lock.readLock().lock();
            System.out.println("Currently the thread that accesses the read lock is " + Thread.currentThread().getName());
            Thread.sleep(4000);
            isAvailable = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.readLock().unlock();
            System.out.println("Currently the thread that releases the read lock is " + Thread.currentThread().getName());
        }
    }

    public void consume(ReadWriteLock lock) {
        try {
            lock.writeLock().lock();
            System.out.println("Currently the thread that accesses the write lock is " + Thread.currentThread().getName());
            Thread.sleep(4000);
            isAvailable = false;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.writeLock().unlock();
            System.out.println("Currently the thread that releases the write lock is " + Thread.currentThread().getName());
        }
    }
}
