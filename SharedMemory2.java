import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SharedMemory2 {
    private final List<String> memory = new ArrayList<>();
    private final Semaphore rwLock = new Semaphore(1);  

    public void write(String data) throws InterruptedException {
        rwLock.acquire();
        System.out.println("Processor is writing: " + data);
        memory.add(data);
        Thread.sleep(300);  
        System.out.println("Processor finished writing to output");
        rwLock.release();
    }

    public synchronized List<String> readAll() {
        return new ArrayList<>(memory);
    }
}
