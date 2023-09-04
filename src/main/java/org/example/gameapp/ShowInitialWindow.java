package org.example.gameapp;

import javax.swing.*;
import java.awt.*;

public class ShowInitialWindow {
    public static void show() {
        JFrame initialFrame = new JFrame("City Game");
        initialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialFrame.setSize(400, 200);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - initialFrame.getWidth()) / 2;
        int y = (screenSize.height - initialFrame.getHeight()) / 2;
        initialFrame.setLocation(x, y);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Font labelFont = new Font("Arial", Font.BOLD, 16);

        JButton startButton = new JButton("OK");
        startButton.setFont(buttonFont);

        JButton instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(buttonFont);

        JLabel startLabel = new JLabel("To start the game, press OK");
        startLabel.setFont(labelFont);

        startButton.addActionListener(e -> {
            initialFrame.dispose();
            ShowGameWindow.show(); // Викликаємо метод showGameWindow() після натискання кнопки
        });

        instructionsButton.addActionListener(e -> {
            ShowInstructionsWindow.show();
        });

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        panel.add(startLabel);
        panel.add(startButton);
        panel.add(instructionsButton);

        initialFrame.add(panel);
        initialFrame.setVisible(true);
    }
}
