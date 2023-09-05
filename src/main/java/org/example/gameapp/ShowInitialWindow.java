package org.example.gameapp;

import javax.swing.*;
import java.awt.*;

public class ShowInitialWindow {
    public static void show() {
        JFrame initialFrame = new JFrame("City Game");
        initialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialFrame.setSize(400, 137);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - initialFrame.getWidth()) / 2;
        int y = (screenSize.height - initialFrame.getHeight()) / 2;
        initialFrame.setLocation(x, y);

        Font buttonFont = new Font("Arial", Font.BOLD, 13);
        Font labelFont = new Font("Arial", Font.BOLD, 12);

        JButton startButton = new JButton("OK");
        startButton.setBounds(50, 60, 60, 30);
        startButton.setFont(buttonFont);

        JButton instructionsButton = new JButton("Instructions");
        instructionsButton.setBounds(120, 60, 120, 30);
        instructionsButton.setFont(buttonFont);

        JLabel startLabel = new JLabel("<html>Welcome to the game of childhood and <br>all smart people");
        startLabel.setBounds(10, 10, 250, 40);
        startLabel.setFont(labelFont);

        startButton.addActionListener(e -> {
            initialFrame.dispose();
            ShowGameWindow.show();
        });

        instructionsButton.addActionListener(e -> {
            ShowInstructionsWindow.show();
        });

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setLayout(null);

        panel.add(startLabel);
        panel.add(startButton);
        panel.add(instructionsButton);

        initialFrame.add(panel);
        initialFrame.setVisible(true);
    }
}

