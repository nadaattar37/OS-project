import java.util.concurrent.Semaphore;

public class ReaderWriter {

    static int sharedData = 0;
    static Semaphore readmutex = new Semaphore(1); 
    static Semaphore writemutex = new Semaphore(1); 
    static int readercount = 0; 
    static int waitingWriters    = 0;

    

    static class Reader extends Thread {
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " wants to read");
    
                while (true) {
                    
                    if (waitingWriters == 0) {
                        readmutex.acquire();
                        readercount++;
                        readmutex.release();

                        
                        System.out.println(Thread.currentThread().getName() + " is reading, , data = " + sharedData + ". Reader count =  "+readercount );
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " finished reading");
                        readmutex.acquire();
                        readercount--;  
                        readmutex.release();
                        break;
                    } else {
                        readmutex.release();
                        Thread.sleep(1000);
                    }
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }


static class Writer extends Thread {
        public void run() {
            try {
                readmutex.acquire();
                System.out.println(Thread.currentThread().getName() + " wants to write.");
                writemutex.acquire();
                waitingWriters++;
                System.out.println(Thread.currentThread().getName() + " is writing.");
                sharedData++;
                System.out.println(Thread.currentThread().getName() + " is done writing. Data: " + sharedData);
                waitingWriters--;
                writemutex.release();
                readmutex.release();
    
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
}
public static void main(String[] args) {
    Thread[] readers = new Thread[2];  
        for (int i = 0; i < readers.length; i++) {
            readers[i] = new Thread(new Reader());
            readers[i].setName("reader" + (i + 1)); // Give them names
            readers[i].start();
        }
        Thread[] writers = new Thread[2];  
        for (int i = 0; i < writers.length; i++) {
            writers[i] = new Thread(new Writer());
            writers[i].setName("writer" + (i + 1));
            writers[i].start();
        }

}
}
