package nscr;

import java.util.Scanner;

public class Caesar_Cypher {

    public static void caesar_Cypher() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gib dein zu verschlüsselndes Wort an (Nur Buchstaben: )");
        String text = scanner.nextLine().toLowerCase();
        System.out.println("Gib jetzt in einer Nummer die Anzahl an verschiebungen an:");
        int key = scanner.nextInt();
        String geheim = encrypt(text, key);
        ausgabe(text, geheim, key);
        scanner.close();
    }

    private static void ausgabe(String text, String geheim, int key) {
        System.out.println("Dein altes Wort war: " + text);
        System.out.println("Dein Schlüssel war: " + key);
        System.out.println("Dein neues Wort ist nun: " + geheim);
    }

    private static String encrypt(String text, int key) {
        int ASCIItemp;
        String tempstr = "";
        char temp;
        for (int i = 0; i < text.length(); i++) {
            ASCIItemp = (int) text.charAt(i);
            ASCIItemp += key;
            while (ASCIItemp < 97 || ASCIItemp > 122) {
                if (ASCIItemp > 122) {
                    ASCIItemp -= 27;
                } else if (ASCIItemp < 97) {
                    ASCIItemp += 27;
                }
            }
            temp = (char) ASCIItemp;
            tempstr += temp;
        }
        return tempstr;
    }
}
