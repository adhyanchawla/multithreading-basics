import java.util.ArrayDeque;
import java.util.Queue;

public class ProducerConsumer {
    Queue<Integer> sharedResource;
    boolean isAvailable;
    int bufferSize;
    int currSize;
    public ProducerConsumer(int bufferSize) {
        sharedResource = new ArrayDeque<>();
        isAvailable = false;
        this.bufferSize = bufferSize;
        this.currSize = 0;
    }

    public synchronized void produce(int item) throws Exception {
        while(sharedResource.size() == this.bufferSize) {
            System.out.println("Buffer size is full, thread needs to wait");
            wait();
        }
        System.out.println("Item added " + item);
        sharedResource.add(item);
        notify();
    }

    public synchronized void consume() {
        while(sharedResource.size() == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Item consumed " + sharedResource.peek());
        sharedResource.remove();
        notify();
    }
}
