package internship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI {
    private static int numberToGuess;
    private static int guessCount;
    private static JTextField guessField;
    private static JLabel messageLabel;
    private static JLabel guessCountLabel;
    private static JButton submitButton;
    private static JButton restartButton;

    public static void main(String[] args) {
        // Generate the number to guess
        resetGame();

        // Create the main frame
        JFrame frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setBackground(new Color(52, 73, 94));
        panel.setLayout(new GridBagLayout());
        frame.add(panel);
        placeComponents(panel);

        // Make the frame visible
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Number Guessing Game!", JLabel.CENTER);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

        // Create instruction label
        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:", JLabel.CENTER);
        instructionLabel.setForeground(Color.WHITE);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 1;
        panel.add(instructionLabel, gbc);

        // Create input field for guesses
        guessField = new JTextField(10);
        guessField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 2;
        panel.add(guessField, gbc);

        // Create submit button
        submitButton = new JButton("Submit Guess");
        submitButton.setBackground(new Color(46, 204, 113));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(submitButton, gbc);

        // Create restart button
        restartButton = new JButton("Restart Game");
        restartButton.setBackground(new Color(231, 76, 60));
        restartButton.setForeground(Color.WHITE);
        restartButton.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(restartButton, gbc);

        // Create message label
        messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(messageLabel, gbc);

        // Create guess count label
        guessCountLabel = new JLabel("Number of guesses: 0", JLabel.CENTER);
        guessCountLabel.setForeground(Color.WHITE);
        guessCountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 5;
        panel.add(guessCountLabel, gbc);

        // Add action listeners to buttons
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
    }

    private static void resetGame() {
        Random rand = new Random();
        numberToGuess = rand.nextInt(100) + 1;
        guessCount = 0;
        if (messageLabel != null) messageLabel.setText("");
        if (guessCountLabel != null) guessCountLabel.setText("Number of guesses: 0");
        if (guessField != null) guessField.setText("");
    }

    private static void checkGuess() {
        String guessText = guessField.getText();
        try {
            int guess = Integer.parseInt(guessText);
            guessCount++;
            guessCountLabel.setText("Number of guesses: " + guessCount);
            int difference = Math.abs(guess - numberToGuess);

            if (guess == numberToGuess) {
                messageLabel.setText("<html><font color='yellow'>Congratulations, you guessed the number!</font></html>");
            } else if (difference <= 5) {
                messageLabel.setText("<html><font color='orange'>You're very close! Try again:</font></html>");
            } else if (difference <= 10) {
                messageLabel.setText("<html><font color='red'>You're close! Try again:</font></html>");
            } else if (guess < numberToGuess) {
                messageLabel.setText("<html><font color='red'>Your guess is too low. Try again:</font></html>");
            } else {
                messageLabel.setText("<html><font color='red'>Your guess is too high. Try again:</font></html>");
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("<html><font color='red'>Please enter a valid number.</font></html>");
        }
    }
}
