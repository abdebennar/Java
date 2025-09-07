
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Signature {

    HashMap<String, String> map = new HashMap<String, String>();

    public Signature() {
        try {
            final FileInputStream InputFile = new FileInputStream("signature.txt");
            byte[] FileContentAsBytes = InputFile.readAllBytes();
            String FileContentAsString = new String(FileContentAsBytes, "UTF-8");
            String[] lines = FileContentAsString.split("\n");
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String key = parts[1];
                    String value = parts[0];
                    map.put(key.trim(), value.trim());
                }
            }

            InputFile.close();
        } catch (IOException e) {
            // e.getMessage();
            e.printStackTrace();
        }
    }
}
