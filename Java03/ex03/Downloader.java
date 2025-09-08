
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {

    private URL url = null;
    private String fileName;

    public Downloader(String url, String targetDirectory) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            this.url = null;
            print("ERROR", "Invalid URL: `" + url + "`");
            return;
        }

        File dir = new File(targetDirectory);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                print("INFO", "Created directory: " + targetDirectory);
            } else {
                print("ERROR", "Failed to create directory: " + targetDirectory);
                return;
            }
        }

        this.fileName = url.substring(url.lastIndexOf("/") + 1);
        File outputFile = new File(targetDirectory, this.fileName);
        try (InputStream inputStream = this.url.openStream(); FileOutputStream fos = new FileOutputStream(outputFile)) {

            inputStream.transferTo(fos);
            print("INFO", "Downloaded " + this.fileName + " to " + targetDirectory);

        } catch (IOException e) {
            print("ERROR", "Failed to download `" + this.url);
        }

    }

    private synchronized void print(String level, String message) {
        System.out.printf("%-5s %s%n", level, message);
    }
}
