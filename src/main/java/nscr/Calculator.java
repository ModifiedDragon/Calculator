package nscr;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Calculator {
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel buttons;
    private JButton[] button;
    private JTextArea textArea;
    private String text;
    private boolean equals = false;

    public Calculator() {
        buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        button = new JButton[16];
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(300, 30));
        textArea.setFocusable(false);
        text = "";
    }

    public void startup() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800); // Adjusted size for better visibility
        frame.setLayout(new BorderLayout());
        buttons.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < 16; i++) {
            button[i] = new JButton(i + "");
            button[i].setFocusable(false);

            int finalI = i;
            if (i < 10) {
                button[i].addActionListener(e -> {
                    int a = finalI;
                    if (equals) {
                        clear();
                    }
                    System.out.println(a);
                    add(text, (char) ('0' + a));
                    equals = false;
                });
            }
            switch (i) {
                case 10:
                    button[10] = new JButton("+");
                    button[10].addActionListener(e -> {
                        System.out.println("+");
                        add(text, '+');
                        equals = false;
                    });
                    break;
                case 11:
                    button[11] = new JButton("-");
                    button[11].addActionListener(e -> {
                        System.out.println("-");
                        add(text, '-');
                        equals = false;
                    });
                    break;
                case 12:
                    button[12] = new JButton("*");
                    button[12].addActionListener(e -> {
                        System.out.println("*");
                        add(text, '*');
                        equals = false;
                    });
                    break;
                case 13:
                    button[13] = new JButton("/");
                    button[13].addActionListener(e -> {
                        System.out.println("/");
                        add(text, '/');
                        equals = false;
                    });
                    break;
                case 14:
                    button[14] = new JButton("=");
                    button[14].addActionListener(e -> {
                        System.out.println("=");
                        add(text, '=');
                        String temp1 = text;
                        update(calculate(text), false);
                        textArea.setText(temp1 + text);
                        equals = true;
                    });
                    break;
                case 15:
                    button[15] = new JButton("delete");
                    button[15].addActionListener(e -> {
                        update(text, true);
                        equals = false;
                    });
                    break;
            }

            gbc.gridx = i % 5; // Column
            gbc.gridy = i / 5; // Row
            System.out.println(i);
            buttons.add(button[i], gbc);
        }
        frame.add(buttons, BorderLayout.CENTER);
        frame.add(textArea, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public void update(String newText, boolean delete){
        if (delete){
            if (!newText.isEmpty()) {
                text = newText.substring(0, newText.length() - 1);
                textArea.setText(text);
            }
        } else {
            text = newText;
            textArea.setText(text);
        }
    }

    public void add(String newtext, char adding) {
        System.out.println("Current text: " + newtext);
        System.out.println("Adding character: " + adding + " (ASCII: " + (int) adding + ")");
        if (!this.text.isEmpty()) {
            if ((adding == '+') || (adding == '-') || (adding == '*') || (adding == '/')) {
                char a = newtext.charAt(newtext.length() - 1);
                if (a == '+' || a == '-' || a == '*' || a == '/') {
                    update(this.text, true);
                    update(this.text + adding, false);
                } else {
                    update(this.text + adding, false);
                }
            } else {
                update(this.text + adding, false);
            }
        } else {
            update(this.text + adding, false);
        }
    }

    public ArrayList<String> transforminList(String textInput){
        ArrayList<String> equation = new ArrayList<>();
        int tempI = 0;
        System.out.println(textInput);
        for (int i = 0; i < textInput.length(); i++){
            if (textInput.charAt(i) == '+' || textInput.charAt(i) == '-' || textInput.charAt(i) == '*' || textInput.charAt(i) == '/') {
                equation.add(textInput.substring(tempI, i));
                System.out.println(textInput.charAt(i));
                System.out.println(equation);
                equation.add(String.valueOf(textInput.charAt(i)));
                tempI = i + 1;
            } else if (textInput.charAt(i) == '=') {
                equation.add(textInput.substring(tempI, i));
                System.out.println(textInput.charAt(i));
                System.out.println(equation);
            }
        }
        return equation;
    }

    public String calculate(String equation) {
        ArrayList<String> transformed = transforminList(equation);
        Stack<String> storage = new Stack<>();

        for (int i = 0; i < transformed.size(); i++) {
            String current = transformed.get(i);
            if (current.equals("*") || current.equals("/")) {
                String result = calc(storage.pop(), current, transformed.get(i + 1));
                storage.push(result);
                i++;
            } else {
                storage.push(current);
            }
        }

        ArrayList<String> lastList = new ArrayList<>();
        while (!storage.isEmpty()) {
            lastList.addFirst(storage.pop()); // Reverse the stack to maintain order
        }
        for (int i = 0; i < lastList.size(); i++) {
            String current = lastList.get(i);
            if (current.equals("+") || current.equals("-")) {
                String result = calc(lastList.get(i - 1), current, lastList.get(i + 1));
                lastList.set(i - 1, result);
                lastList.remove(i);
                lastList.remove(i);
                i--;
            }
        }
        System.out.println(lastList);
        return lastList.getFirst();
    }

    public String calc(String operand1, String operator, String operand2) {
        float equals = 0;
        System.out.println(operand1 + "+" + operator + "+" + operand2);
        float temp1 = Float.parseFloat(operand1);
        float temp2 = Float.parseFloat(operand2);
        switch (operator){
            case "+":
                equals += temp1 + temp2;
                break;
            case "-":
                equals += temp1 - temp2;
                break;
            case "*":
                equals += temp1 * temp2;
                break;
            case "/":
                equals += temp1 / temp2;
                break;
        }
        if (equals == (int) equals) {
            return String.valueOf((int) equals);
        }
        return String.valueOf(equals);
    }

    public void clear() {
        text = "";
        textArea.setText("");
    }

}