
public class Main {

    public static void main(String[] args) {
        for (String s : args) {
            String[] SourceFileArg = s.split("=");
            if (SourceFileArg.length == 2 && SourceFileArg[0].equals("--current-folder") && !SourceFileArg[1].isEmpty()) {
                new Shell(SourceFileArg[1]);

            }
        }

        System.err.println("Usage: java Main --current-folder=PATH");
        System.exit(1);

    }
}
