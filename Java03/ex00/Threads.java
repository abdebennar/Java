

public class Threads {

    public static void main(String[] args) {

        int Count = Integer.parseInt(args[0]);
        System.out.println(
                "Count: " + Count);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < Count; i++) {
                System.out.println("Egg");
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < Count; i++) {
                System.out.println("Hen");
            }
        });
        t2.start();
        t1.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < Count; i++) {
            System.out.println("Human");
        }
        // ExecutorService pool = Executors.newFixedThreadPool(2);
        // pool.execute(() -> System.out.println("Task in pool"));
        // pool.shutdown();
    }
}
