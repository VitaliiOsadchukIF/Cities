package org.example;

import javax.swing.*;

public class Winner {
    private static String notification = "Notification";

    public void userHasGivenUp(String textFromTextField) {
        if (textFromTextField.equals("I give up")) {
            JOptionPane.showMessageDialog(null, "Every defeat is another step towards victory." +
                            "\n This time the computer won", notification,
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void cityNotFound(String getComputerMove) {
        if (getComputerMove.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "The game is over because the city is not found.",
                    notification, JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void isPlayerWon(String getComputerMove) {
        if (getComputerMove.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Player Won.",
                    "Notification", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}

