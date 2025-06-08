public class MS3Simulator {
    public static void main(String[] args) {
        SharedMemory1 memory1 = new SharedMemory1();
        SharedMemory2 memory2 = new SharedMemory2();

        // writers level1
        new Writer(memory1, "Writer1").start();
        new Writer(memory1, "Writer2").start();
        new Writer(memory1, "Writer3").start();

        // processorlevel2
        new Processor(memory1, memory2).start();

        // readerslevel3
        new Reader(memory2, "Reader1").start();
        new Reader(memory2, "Reader2").start();
        new Reader(memory2, "Reader3").start();
    }
}
