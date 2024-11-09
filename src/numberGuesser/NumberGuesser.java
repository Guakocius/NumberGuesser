package numberGuesser;

import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Just a normal number guesser, nothing unusual
 */
public class NumberGuesser extends JFrame {

    Scanner sc = new Scanner(System.in);
    Random rand;
    int number;
    JTextField guessField;
    JButton guessButton;
    JButton resultButton;
    JLabel feedBackLabel;

    public NumberGuesser() {

        rand = new Random();
        number = rand.nextInt(100) + 1;

        setTitle("Number Guesser");
        setSize(2560, 1440);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel guessLabel = new JLabel("Guess a number between 1 and 100: ");
        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultButton = new JButton("Show Result");
        resultButton.addActionListener(new ActionListener() {

            HttpServletRequest req;
            HttpServletResponse resp;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    forwardServlet(req, resp);
                } catch (ServletException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        feedBackLabel = new JLabel("");

        add(guessLabel);
        add(guessField);
        add(guessButton);
        add(resultButton);
        add(feedBackLabel);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
            public void actionReleased(ActionEvent e) {

            }
        });

        setVisible(true);
    }

    private void handleGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess == number) {
                feedBackLabel.setText("You guessed it!");
                guessButton.setEnabled(false);
                dispose();
            } else {

                for (int i = 1; i <= 1000000000; i++) {
                    new NumberGuesser();
                    TryAgainFrame tryAgainFrame = new TryAgainFrame();
                    tryAgainFrame.setVisible(true);
                }

                number = rand.nextInt(100) + 1;
            }
        } catch (NumberFormatException ex) {
            feedBackLabel.setText("Try again");
            new NumberGuesser();
        }
    }

    public static void main(String[] args) {
        new NumberGuesser();
    }

    public static void forwardServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher rd = req.getRequestDispatcher("index.html");
        rd.forward(req, resp);
    }
}

    class TryAgainFrame extends JFrame {
        public TryAgainFrame() {
            setTitle("Try Again!");
            setSize(2560, 1440);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            setLayout(new FlowLayout());
            JLabel tryAgainFrame;
            tryAgainFrame = new JLabel("Try Again");
            add(tryAgainFrame);
        }
    }



