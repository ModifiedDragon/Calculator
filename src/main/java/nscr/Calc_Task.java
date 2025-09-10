package nscr;

import java.util.Scanner;

import static java.lang.System.exit;

public class Calc_Task {
    double zahl1 = 142.7;
    double zahl2 = 134.5;
    double ergebnis;
    String operator = "+";
    Scanner scanner = new Scanner(System.in);

    public void afg() {
        System.out.println("Gebe deine erste Zahl an: ");
        zahl1 = scanner.nextInt();
        System.out.println("Bitte gebe den Operator an: ");
        operator = scanner.next().toLowerCase();
        if (operator.equals("wurzel")) {
            ergebnis = root(zahl1);

        } else if (operator.equals("fibonacci")) {
            Fibonacci.fibonacci();
        } else if (operator.equals("caesar")) {
            Caesar_Cypher.caesar_Cypher();
        } else if (operator.equals("realcalc")) {
            Calculator calculator = new Calculator();
            calculator.startup();
        } else {
            System.out.println("Gebe deine zweite Zahl an: ");
            zahl2 = scanner.nextInt();
            double ergebisa[] = new double[5];
            ergebisa[0] = berechneErgebnis(zahl1, zahl2, "+");
            ergebisa[1] = berechneErgebnis(zahl1, zahl2, "-");
            ergebisa[2] = berechneErgebnis(zahl1, zahl2, "*");
            ergebisa[3] = berechneErgebnis(zahl1, zahl2, "/");
            ergebisa[4] = berechneErgebnis(zahl1, zahl2, "^");
            System.out.println(ergebisa[0]);
            System.out.println(ergebisa[1]);
            System.out.println(ergebisa[2]);
            System.out.println(ergebisa[3]);
            System.out.println(ergebisa[4]);
            ergebnis = berechneErgebnis(zahl1, zahl2, operator);
            if (ergebnis % (int) ergebnis == 0) {
                System.out.println((int) ergebnis);
            }
            System.out.println(ergebnis);
            weiter();
        }
    }

    private void weiter() {
        System.out.println("Willst du weitere Berechnungen machen? (y/n)");
        String a = scanner.next();
        if (a.equals("y")) {
            afg();
        } else if (a.equals("n")) {
            scanner.close();
            exit(1);
        } else {
            System.out.println("Bitte gebe ein 'y' oder 'n' an.");
            weiter();
        }
    }

    private double berechneErgebnis(double z1, double z2, String op) {
        double ergebnis = 0;
        switch (op){
            case "+" -> ergebnis = z1 + z2;
            case "-" -> ergebnis = z1 - z2;
            case "*" -> ergebnis = z1 * z2;
            case "/" -> ergebnis = z1 / z2;
            case "^" -> ergebnis = Math.pow(z1, z2);
        }
        return ergebnis;
    }

    public double root(double z) {
        return Math.sqrt(z);
    }
}
