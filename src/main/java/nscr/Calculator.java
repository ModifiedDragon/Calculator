package nscr;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Calculator {
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel buttons;
    private JButton[] button;
    private JTextArea textArea;

    public Calculator() {
        buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        button = new JButton[16];
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(300, 30));
        textArea.setFocusable(false);
    }

    public void startup() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800); // Adjusted size for better visibility
        frame.setLayout(new BorderLayout());
        buttons.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        gbc.fill = GridBagConstraints.HORIZONTAL;

        final String[] text = {textArea.getText()};

        for (int i = 0; i < 16; i++) {
                button[i] = new JButton(i + "");
                button[i].setFocusable(false);

                int finalI = i;
                if(i < 10) {
                    button[i].addActionListener(e -> {
                        int a = finalI;
                        System.out.println(a);
                        update(add(text[0], a + ""), false);
                    });
                }
                switch (i){
                    case 10:
                        button[10] = new JButton("+");
                        button[10].addActionListener(e -> {
                            System.out.println("+");
                            update(add(text[0], "+"), false);
                        });
                    case 11:
                        button[11] = new JButton("-");
                        button[11].addActionListener(e -> {
                            System.out.println("-");
                            update(add(text[0], "-"), false);
                        });
                    case 12:
                        button[12] = new JButton("*");
                        button[12].addActionListener(e -> {
                            System.out.println("*");
                            update(add(text[0], "*"), false);
                        });
                    case 13:
                        button[13] = new JButton("/");
                        button[13].addActionListener(e -> {
                            System.out.println("/");
                            update(add(text[0], "/"), false);
                        });
                    case 14:
                        button[14] = new JButton("=");
                        button[14].addActionListener(e -> {
                            System.out.println("=");
                            update(add(text[0], "="), false);
                        });
                    case 15:
                        button[15] = new JButton("delete");
                        button[15].addActionListener(e -> {
                            System.out.println("delete");
                            update(text[0], true);
                        });
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

    public void update(String text, boolean delete){
        if (delete){
            System.out.println("delete");
            if (!text.isEmpty()) {
                textArea.setText(text.substring(0, text.length() - 1));
            }
        } else {
            System.out.println("add");
            textArea.setText(text);
        }
    }

    public String add(String text, String adding){
        String temptext = text;
        if (!text.isEmpty()) {
            char lastChar = temptext.charAt(text.length() - 1);
            if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/' || lastChar == '=') {
                if (adding.equals('+') || adding.equals('-') || adding.equals('*') || adding.equals('/') || adding.equals('=')) {
                    System.out.println(text + "text1");
                    textArea.setText(text.substring(0, text.length() - 1));
                    return temptext = temptext + adding;
                }
            }
        }
        System.out.println(temptext + "text2 " + adding);
        temptext = temptext + adding;
        return temptext;
    }
}