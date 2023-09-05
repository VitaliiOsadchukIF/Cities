package org.example.gameapp;

import org.example.Move;
import org.example.Winner;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ShowGameWindow {
    private static JLabel playerL;
    private static JLabel computerL;
    private static final int MAXLENGTHSYMBOLS = 26;
    private static Move move = new Move();
    private static JTextField playerBoard;
    private static JTextField computerBoard;
    private static JButton skipButton;
    private static JButton moveButton;
    private static JButton surrenderButton;
    private static JLabel label;

    public static void show() {
        JFrame gameFrame = new JFrame("City Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(600, 250);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - gameFrame.getWidth()) / 2;
        int y = (screenSize.height - gameFrame.getHeight()) / 2;
        gameFrame.setLocation(x, y);

        playerBoard = new JTextField();
        computerBoard = new JTextField();

        moveButton = new JButton("Move");
        skipButton = new JButton("Skip");
        surrenderButton = new JButton("Surrender");

        computerBoard.setEditable(false);
        playerBoard.setEditable(true);

        Font inputFont = new Font("Arial", Font.PLAIN, 14);
        playerBoard.setFont(inputFont);
        computerBoard.setFont(inputFont);

        ((AbstractDocument) playerBoard.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int insertLength = text.length();
                if (currentLength + insertLength <= MAXLENGTHSYMBOLS) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        moveButton.addActionListener(e -> processPlayerMove());

        playerBoard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,
                0), "enter");
        playerBoard.getActionMap().put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPlayerMove();
            }
        });

        skipButton.addActionListener(e -> {
            if (!computerBoard.getText().isEmpty()) {
                String computerMove = move.skip(); // редагував код Віталія!!
                computerBoard.setText(computerMove); // редагував код Віталія
            } else {
                new Winner().cityNotFound();
            }
            label.setText("<html>Computer: "+ String.valueOf(move.getCountForComputer())
                    + "<br>Player: " + String.valueOf(move.getCountForPlayer()));
        });

        surrenderButton.addActionListener(e -> {
            playerBoard.setText("");
            computerBoard.setText("");

            JOptionPane.showMessageDialog(null, "Every defeat is another step towards victory. \n This time the computer won", "Notification", JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);

        });

        playerBoard.setBounds(50, 50, 200, 30);
        computerBoard.setBounds(270, 50, 200, 30);
        moveButton.setBounds(50, 100, 80, 30);
        skipButton.setBounds(140, 100, 80, 30);
        surrenderButton.setBounds(230, 100, 100, 30);
        playerBoard.setCaretColor(Color.blue);

        label = new JLabel();
        label.setBounds(51,140,100,50);

        playerL = new JLabel("player");
        computerL = new JLabel("computer");

        playerL.setBounds(50,15,200,30);
        computerL.setBounds(270,15,200,30);
        playerL.setFont(new Font("Arial",Font.PLAIN,14));
        computerL.setFont(new Font("Arial",Font.PLAIN,14));

        gameFrame.setLayout(null);

        gameFrame.add(playerBoard);
        gameFrame.add(computerBoard);
        gameFrame.add(moveButton);
        gameFrame.add(skipButton);
        gameFrame.add(surrenderButton);
        gameFrame.add(label);
        gameFrame.add(playerL);
        gameFrame.add(computerL);


        gameFrame.getContentPane().setBackground(Color.GRAY);

        gameFrame.setVisible(true);
    }

    private static void processPlayerMove() {
        String input = playerBoard.getText().trim();
        playerBoard.setText("");

        new Winner().userHasGivenUp(input);


        if (move.isUserMoveValid(input)) {
            move.playGame(input);
            label.setText("<html>Computer: "+ String.valueOf(move.getCountForComputer())
                    + "<br>Player: " + String.valueOf(move.getCountForPlayer()));
            playerBoard.setText(""); // Очистити поле вводу
            String computerMove = move.getComputerMove();
            computerBoard.setText(computerMove);
        } else {
            // Вивести повідомлення про помилку
            JOptionPane.showMessageDialog(null,
                    "The entered city is invalid or does not comply with the rules. Please enter another city.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            playerBoard.setText(""); // Очистити поле вводу
        }
    }
}
