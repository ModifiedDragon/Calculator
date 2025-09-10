package nscr;

import java.util.Scanner;

public class Fibonacci {

    public static void fibonacci() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gebe mir die Anzahl der ersten Fibonacci Nummern an: ");
        int count = scanner.nextInt();
        counting(count);
    }

    private static void counting(int count) {
        int first = 1;
        int second = 1;
        int temp = 0;
        System.out.println(0);
        for (int i = 0; i < count - 1; i++) {
            System.out.println(second);
            temp = second;
            second += first;
            first = temp;
        }
    }
}
