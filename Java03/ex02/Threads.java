
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// NOTE: Brain turned off while coding 
public class Threads {

    private static BlockingQueue<Integer> queue = null;

    public static void main(String[] args) {
        int arraySize = -1;
        int threadsCount = -1;

        for (String arg : args) {
            if (arg.startsWith("--arraySize=")) {
                arraySize = Integer.parseInt(arg.substring("--arraySize=".length()));
            } else if (arg.startsWith("--threadsCount=")) {
                threadsCount = Integer.parseInt(arg.substring("--threadsCount=".length()));
            }
        }

        queue = new ArrayBlockingQueue<>(threadsCount);

        if (arraySize == -1 || threadsCount == -1) {
            System.err.println("Usage: java Program --arraySize=<num> --threadsCount=<num>");
            System.exit(1);
        }
        if (arraySize <= threadsCount) {
            System.err.println("Error: arraySize must be greater than threadsCount");
            System.exit(1);
        }

        int segmentSize = arraySize / threadsCount;

        System.out.println("[array size: " + arraySize + ", threads count: " + threadsCount + ", segment size: " + segmentSize + "]");

        int[] array = new int[arraySize];
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < arraySize; i++) {
            array[i] = (int) (Math.random() * 1000);
        }

        int sumBefore = 0;
        for (int i = 0; i < arraySize; i++) {
            sumBefore += array[i];
        }
        System.out.println("Sum with the main Thread: " + sumBefore);

        for (int loop = 0; loop < threadsCount; loop++) {
            final int threadIndex = loop;
            final int start = segmentSize * threadIndex;
            final int end = (threadIndex == threadsCount - 1) ? (arraySize) : (start + segmentSize);

            threads.add(new Thread(() -> {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                try {
                    queue.put(sum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread " + threadIndex + " processing range: " + start + " to " + (end - 1) + " result: " + sum);
            }
            ));
        };

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int totalSum = 0;
        for (int i = 0; i < threadsCount; i++) {
            try {
                totalSum += queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sum by parallel Threads: " + totalSum);

    }
}
