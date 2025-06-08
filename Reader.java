import java.util.List;
import java.util.ArrayList;

public class Reader extends Thread {
    private final SharedMemory2 memory;
    private final String name;
    private final List<String> seen = new ArrayList<>();

    public Reader(SharedMemory2 memory, String name) {
        this.memory = memory;
        this.name = name;
    }

    public void run() {
        try {
            while (true) {
                System.out.println(name + " is trying to read...");
                List<String> data = memory.readAll();

                for (String item : data) {
                    if (!seen.contains(item)) {
                        System.out.println(name + " read: " + item);
                        seen.add(item);
                    }
                }

                System.out.println(name + " finished reading.");
                Thread.sleep(2000); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
