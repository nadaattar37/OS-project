import java.util.List;

public class Processor extends Thread {
    private final SharedMemory1 inputMemory;
    private final SharedMemory2 outputMemory;

    public Processor(SharedMemory1 inputMemory, SharedMemory2 outputMemory) {
        this.inputMemory = inputMemory;
        this.outputMemory = outputMemory;
    }

    public void run() {
        try {
            while (true) {
                System.out.println("processor is trying to read from input...");
                List<String> data = inputMemory.readAndClear();

                if (!data.isEmpty()) {
                    for (String d : data) {
                        System.out.println("processor read: " + d);
                    }
                    String result = "Processed: " + String.join(", ", data);
                    System.out.println("processor is trying to write to output...");
                    outputMemory.write(result);
                }

                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
