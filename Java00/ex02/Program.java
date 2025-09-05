
import java.util.Scanner;

public class Program {

    public static int sumMumbersInStr(String str) {
        int Sum = 0;
        for (char c : str.toCharArray()) {
            Sum = Sum + c - '0';
        }
        return Sum;
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int l = 2; l < number; l++) {
            if (number % l == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner rc = new Scanner(System.in);
        int Counter = 0;
        while (true) {
            System.out.print("-> ");
            if (!rc.hasNext()) {
                break;
            }
            String Input = rc.next();
            int InputSum = sumMumbersInStr(Input);
            if (isPrime(InputSum)) {
                Counter++;
            } else {
                break;
            }
        }

        System.out.print(Counter);
    }
}
