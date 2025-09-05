
import java.util.Scanner;

public class Program {

    private static void error() {
        System.err.print("IllegalArgument");
        System.exit(-1);
    }

    private static String repeatString(char c, int RepeatTime) {
        String Holder = "";
        for (int i = 0; i < RepeatTime; i++) {
            Holder += c;
        }
        return Holder;
    }

    private static int convertAndGetSmallestValue(String thing) {
        int Result = 0;
        for (char c : thing.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            int Converted = c - '0';
            if (Converted < 0 || Converted > 9) {
                error();
            }
            if (Converted > Result) {
                Result = Converted;
            }
        }
        return Result;
    }

    private static void putStr(String p) {
        System.out.println(p);
    }

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        String Result = "";

        for (int l = 0; l < 18; l++) {
            System.out.print("-> ");
            String WeekInput = reader.nextLine();
            if (WeekInput.equals("42")) {
                break;
            }
            if (!(WeekInput.equals("Week " + (l + 1)))) {
                error();
            } else {
                System.out.print("--> ");
                String InputResults = reader.nextLine();
                int Bigest = convertAndGetSmallestValue(InputResults);
                Result += "\tweek " + (l + 1) + " ";
                Result += repeatString('=', Bigest);
                Result += ">\n"; // Better to use System.lineSeparator() for cross platform 
            }
        }
        System.out.println(Result);
    }
}
