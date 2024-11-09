package numberGuesser;

import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuesser extends JFrame {

    Scanner sc = new Scanner(System.in);
    Random rand;
    int number;
    JTextField guessField;
    JButton guessButton;
    JLabel feedBackLabel;

    public NumberGuesser() {

        rand = new Random();
        number = rand.nextInt(100) + 1;

        setTitle("Number Guesser");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel guessLabel = new JLabel("Guess a number between 1 and 100: ");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        feedBackLabel = new JLabel("");

        add(guessLabel);
        add(guessField);
        add(guessButton);
        add(feedBackLabel);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        setVisible(true);
    }

    private void handleGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());

            if(guess == number) {
                feedBackLabel.setText("You guessed it!");
                guessButton.setEnabled(false);
                dispose();
            } else {
                feedBackLabel.setText("Try again!");
                new NumberGuesser();
            }
        } catch (NumberFormatException ex) {
            feedBackLabel.setText("You entered an invalid number!");
        }
    }

    public static void main(String[] args) {
        new NumberGuesser();
    }
}
