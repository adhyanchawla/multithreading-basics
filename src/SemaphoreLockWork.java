import java.util.concurrent.Semaphore;

public class SemaphoreLockWork {
    Semaphore semaphore = new Semaphore(2);
    boolean isAvailable = false;
    public void producer() {
        try {
            System.out.println("Lock is acquired by " + Thread.currentThread().getName());
            isAvailable = true;
            semaphore.acquire();
            Thread.sleep(4000);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Lock is released by " + Thread.currentThread().getName());
            semaphore.release();
        }
    }
}
