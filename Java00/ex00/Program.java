
public class Program {

    public static void main(String[] args) {

        int[] var = {4, 7, 9, 5, 9, 8};
        int sum = 0;

        for (int i = 0; i < var.length; ++i) {
            sum += var[i];
        }

        System.out.print(sum);
    }
}
