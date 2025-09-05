
import java.util.Scanner;

public class Program {

    public static void PrintResult(boolean result, int iterations) {
        System.out.println((result ? "true" : "false") + " " + iterations);
        System.exit(result ? 0 : -1);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int iterations = 0;
        int l = 2;
        int number;

        System.out.print("-> ");
        number = sc.nextInt();

        if (number < 0) {
            System.err.print("IllegalArgument");
            System.exit(1);
        }

        if (number == 0 || number == 1) {
            PrintResult(false, iterations);
        }

        for (; l < number; l++) {
            iterations++;
            if (number % l == 0) {
                PrintResult(false, iterations);
            } else if (l == Math.round(Math.sqrt(number))) {
                PrintResult(true, iterations);
            }
        }

        PrintResult(true, iterations);
    }
}
