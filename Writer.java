public class Writer extends Thread {
    private final SharedMemory1 memory;
    private final String name;

    public Writer(SharedMemory1 memory, String name) {
        this.memory = memory;
        this.name = name;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println(name + " is trying to write...");
                memory.write("Data from " + name, name);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
