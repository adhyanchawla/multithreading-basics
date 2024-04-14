import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    public static void main(String[] args) {

        System.out.println("main is starting");
//        Thread1 thread1 = new Thread1("thread1");
//        thread1.setDaemon(true);
//        thread1.start();


//        Thread thread2 = new Thread(()-> {
//            for(int i = 0; i < 5; i++)
//                System.out.println(Thread.currentThread().getName() + " " + i);
//        }, "thread2");
//        thread2.start();
//        System.out.println("main is exiting");

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10,
//                TimeUnit.MINUTES, new ArrayBlockingQueue<>(2), new CustomThreadFactory(), new CustomRejectHandler());
//        executor.allowCoreThreadTimeOut(true); // keep alive enabled
//        for(int i = 1; i <= 7; i++) {
//            executor.submit(()->{
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("Task processed by: " + Thread.currentThread().getName());
//            });
//        }
//
//        executor.shutdown();
//        ProducerConsumer producerConsumer = new ProducerConsumer(3);
//        Thread thread1 = new Thread(()->{
//            for(int i = 0; i < 6; i++) {
//                try {
//                    producerConsumer.produce(i);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//
//        Thread thread2 = new Thread(()->{
//            for(int i = 0; i < 6; i++) {
//                producerConsumer.consume();
//            }
//        });
//        thread1.setDaemon(true);
//        thread1.start();
//        thread2.start();

//        RentrantLockWork work = new RentrantLockWork();
//        Thread th1 = new Thread(()-> work.produce());
//        Thread th2 = new Thread(()-> work.produce());
//        th1.start();
//        th2.start();

//        ReadWriteLock lock = new ReentrantReadWriteLock();
//        ReadWriteLockWork work = new ReadWriteLockWork();
//
//        Thread th1 = new Thread(()-> work.produce(lock));
//        Thread th2 = new Thread(()-> work.produce(lock));
//
//        ReadWriteLockWork work1 = new ReadWriteLockWork();
//        Thread th3 = new Thread(()-> work.consume(lock));
//
//        th1.start();
//        th2.start();
//        th3.start();

//        StampedLockWork lock = new StampedLockWork();
//        Thread th1 = new Thread(()-> lock.produce());
////        Thread th2 = new Thread(() -> lock.produce());
//        Thread th3 = new Thread(() -> lock.consume());
//        th1.start();
////        th2.start();
//        th3.start();
        SemaphoreLockWork work = new SemaphoreLockWork();
        Thread th1 = new Thread(()-> work.producer());
        Thread th2 = new Thread(()-> work.producer());
        Thread th3 = new Thread(()-> work.producer());
        Thread th4 = new Thread(()-> work.producer());
        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }

}

class CustomThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread th = new Thread(r);
        th.setPriority(Thread.NORM_PRIORITY);
        th.setDaemon(false);
        return th;
    }
}

class CustomRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task rejected " + r.toString());
    }
}
