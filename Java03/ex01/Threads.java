
public class Threads {

    private static final Object lock = new Object();
    private static boolean eggTurn = false;

    public static void main(String[] args) throws InterruptedException {
        int Count = Integer.parseInt(args[0]);

        Thread eggThread = new Thread(() -> {
            for (int i = 0; i < Count; i++) {
                synchronized (lock) {
                    while (eggTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("Hen"); // Egg prints Hen
                    eggTurn = true;
                    lock.notifyAll();
                }
            }
        });

        Thread henThread = new Thread(() -> {
            for (int i = 0; i < Count; i++) {
                synchronized (lock) {
                    while (!eggTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("Egg"); // Hen prints Egg
                    eggTurn = false;
                    lock.notifyAll();
                }
            }
        });

        eggThread.start();
        henThread.start();

        eggThread.join();
        henThread.join();

        for (int i = 0; i < Count; i++) {
            System.out.println("Human");
        }
    }
}
