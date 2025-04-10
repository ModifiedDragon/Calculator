package nscr;

import javax.swing.*;
import java.awt.*;

public class Calculator {
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel buttons;

    public Calculator() {
        // Initialize the buttons panel and set its layout
        buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
    }

    public void startup() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400); // Adjusted size for better visibility
        frame.setLayout(new BorderLayout());
        buttons.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < 12; i++) {
            // Create a button
            JButton button = new JButton("" + i);

            // Add action listener to the button
            button.addActionListener(e -> {

            });
            button.setPreferredSize(new Dimension(frame.getWidth() / 5, frame.getHeight() / 5));

            // Set grid position for the button
            gbc.gridx = i % 5; // Column
            gbc.gridy = i / 5; // Row

            // Add button to the buttons panel
            buttons.add(button, gbc);
        }

        // Add the buttons panel to the frame
        frame.add(buttons, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
