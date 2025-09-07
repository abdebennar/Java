
import java.io.FileInputStream;
import java.util.Scanner;

public class Prompter extends Signature {

    private final int MAX_BYTES = 32;

    public Prompter() {
        // lop over data and print it out
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }

        Scanner Scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a file name: ");
            String FileName = Scanner.nextLine();
            if (FileName.equals("exit")) {
                break;
            }

            try {
                FileInputStream File2Check = new FileInputStream(FileName);
                byte[] FileContentAsBytes = File2Check.readNBytes(MAX_BYTES);
                String FileContentAsString = new String(FileContentAsBytes, "hex");
                File2Check.close();

                System.out.println("Bytes read: " + FileContentAsString);

                for (int i = MAX_BYTES; i >= 2; i -= 2) {
                    String SubStr = FileContentAsString.substring(0, i);
                    if (map.containsKey(SubStr)) {
                        System.out.println("PROCESSED");
                        System.out.println("File type: " + map.get(SubStr));
                        break;
                    }
                    if (i == 2) {
                        System.out.println("UNDEFINED");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
