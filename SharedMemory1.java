import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SharedMemory1 {
    private final List<String> memory = new ArrayList<>();
    private final Semaphore writeLock = new Semaphore(1);

    public void write(String data, String writerName) throws InterruptedException {
        writeLock.acquire();
        System.out.println(writerName + " is writing: " + data);
        memory.add(data);
        Thread.sleep(300);  
        System.out.println(writerName + " finished writing: " + data);
        writeLock.release();
    }

    public synchronized List<String> readAndClear() {
        List<String> copy = new ArrayList<>(memory);
        memory.clear();
        return copy;
    }
}
