
public class Main {

    public static void main(String[] args) {
        int threadsCount = 1; // default value
        String targetDirectory = "./downloads"; // default value

        for (String arg : args) {
            if (arg.startsWith("--threadsCount=")) {
                String value = arg.substring("--threadsCount=".length());
                try {
                    threadsCount = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number: " + value);
                    System.exit(1);
                }
            } else if (arg.startsWith("--targetDirectory=")) {
                targetDirectory = arg.substring("--targetDirectory=".length());
            } else {
                System.err.println("Unknown argument: " + arg);
                System.exit(1);
            }
        }

        Threader threader = new Threader(threadsCount, targetDirectory);

    }
}
