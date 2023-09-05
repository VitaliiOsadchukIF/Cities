package org.example.gameapp;

import javax.swing.*;
import java.awt.*;

public class ShowInstructionsWindow {
    public static void show() {
        JFrame instructionsFrame = new JFrame("Instructions");
        instructionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        instructionsFrame.setSize(400, 200);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - instructionsFrame.getWidth()) / 2;
        int y = (screenSize.height - instructionsFrame.getHeight()) / 2;
        instructionsFrame.setLocation(x, y);

        instructionsFrame.getContentPane().setBackground(Color.GRAY);

        JTextArea instructionsArea = new JTextArea("1. You have to smile when playing this game )))\n" +
                "2. Then write your favorite city\n" +
                "3. The computer will write its city on the last letter\n" +
                "4. Then you also enter the city that begins with \n the last letter of the entered city\n" +
                "5. Cities should not be repeated");
        instructionsArea.setEditable(false);

        Font boldFont = new Font("Arial", Font.BOLD, 14);
        instructionsArea.setFont(boldFont);

        instructionsArea.setBackground(Color.GRAY);

        JScrollPane scrollPane = new JScrollPane(instructionsArea);

        scrollPane.getViewport().setBackground(Color.GRAY);

        instructionsFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> instructionsFrame.dispose());
        instructionsFrame.getContentPane().add(backButton, BorderLayout.SOUTH);

        instructionsFrame.setVisible(true);
    }
}
