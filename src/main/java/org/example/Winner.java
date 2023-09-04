package org.example;

import javax.swing.*;

public class Winner {
    public void userHasGivenUp(String textFromTextField) {
        if (textFromTextField.equals("I give up")) {
            JOptionPane.showMessageDialog(null, "Computer won!", "Notification",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void wrongCity() {
        JOptionPane.showMessageDialog(null,
                "The entered city is invalid or does not comply with the rules. Please enter another city.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void cityNotFound() {
        JOptionPane.showMessageDialog(null,
                "The game is over because the city is not found.",
                "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
}
