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
    private static Move move = new Move();
    private static JTextField playerBoard;
    private static JTextField computerBoard;
    private static JButton skipButton;
    private static JButton moveButton;
    private static JButton surrenderButton;

    public static void show() {
        JFrame gameFrame = new JFrame("City Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(600, 400);

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
                if (currentLength + insertLength <= 26) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        moveButton.addActionListener(e -> processPlayerMove());

        playerBoard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        playerBoard.getActionMap().put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPlayerMove();
            }
        });

        skipButton.addActionListener(e -> {
            computerBoard.setText("");
            String computerMove = move.skip();
            computerBoard.setText(computerBoard.getText() + computerMove + "\n");
        });

        surrenderButton.addActionListener(e -> {
            playerBoard.setText("");
            computerBoard.setText("");

            gameFrame.dispose();
            ShowInitialWindow.show();
        });


        playerBoard.setBounds(50, 50, 200, 30);
        computerBoard.setBounds(270, 50, 200, 30);
        moveButton.setBounds(50, 100, 80, 30);
        skipButton.setBounds(140, 100, 80, 30);
        surrenderButton.setBounds(230, 100, 100, 30);
        playerBoard.setCaretColor(Color.blue);

        gameFrame.setLayout(null);

        gameFrame.add(playerBoard);
        gameFrame.add(computerBoard);
        gameFrame.add(moveButton);
        gameFrame.add(skipButton);
        gameFrame.add(surrenderButton);

        gameFrame.getContentPane().setBackground(Color.GRAY);

        gameFrame.setVisible(true);
    }

    private static void processPlayerMove() {
        String input = playerBoard.getText().trim();
        playerBoard.setText("");

        String str = new Winner().userHasGivenUp(input);
        computerBoard.setText(str);

        move.playGame(input);
        String responseFromComputer = move.getComputerMove();
        computerBoard.setText(responseFromComputer);
    }
}
