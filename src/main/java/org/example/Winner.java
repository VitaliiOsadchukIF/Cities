package org.example;

import javax.swing.*;

public class Winner {
    public void userHasGivenUp(String textFromTextField) {
        if (textFromTextField.equals("I give up")) {
            JOptionPane.showMessageDialog(null, "Every defeat is another step towards victory. \n This time the computer won", "Notification",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void cityNotFound() {
        JOptionPane.showMessageDialog(null,
                "The game is over because the city is not found.",
                "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
}
