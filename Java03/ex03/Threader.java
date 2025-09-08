
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Threader {

    int numberOfThreads;
    String targetDirectory;
    ArrayBlockingQueue<String> queue = null;
    AtomicInteger fileCounter = new AtomicInteger(0);

    public Threader(int numberOfThreads, String targetDirectory) {
        this.numberOfThreads = numberOfThreads;
        this.targetDirectory = targetDirectory;

        try {
            FileInputStream inputFile = new FileInputStream("files_urls.txt");
            byte[] fileContent = inputFile.readAllBytes();
            String[] urlsArray = new String(fileContent, StandardCharsets.UTF_8).trim().split("\n");
            inputFile.close();

            System.out.println("Found " + urlsArray.length + " URLs to download.");

            this.queue = new ArrayBlockingQueue<>(urlsArray.length, true, java.util.Arrays.asList(urlsArray));

        } catch (Exception e) {
            System.err.println("Error reading files_urls.txt: " + e.getMessage());
            System.exit(1);
        }

        for (int i = 1; i <= numberOfThreads; i++) {
            int threadId = i;
            Thread thread = new Thread(() -> worker(threadId));
            thread.start();
        }

        // join threads (optional)
    }

    private void worker(int threadId) {
        String url;
        while ((url = queue.poll()) != null) {
            int fileNumber = fileCounter.incrementAndGet();
            print(threadId, "start download file number " + fileNumber);
            new Downloader(url, this.targetDirectory);
            print(threadId, "finish download file number " + fileNumber);
        }
    }

    private synchronized void print(int threadId, String message) {
        System.out.printf("%-10s %s%n", "Thread-" + threadId, message);
    }
}
