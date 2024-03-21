import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
//import java.io.*;

public class guess_number extends JFrame implements ActionListener {
    private JTextField inputField;
    private JLabel outputLabel;
    private JButton convertButton;
    private JPanel panel;
    private ImageIcon currentBackgroundImage;

    int count = 1;

    Random rand = new Random();
    public int number = rand.nextInt(1000);

    public guess_number() {
        setTitle("Number Guessing Game");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel with a null layout to add components freely
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the current background image
                if (currentBackgroundImage != null) {
                    g.drawImage(currentBackgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        panel.setLayout(null);

        JLabel inputLabel = new JLabel("Enter your number (integer):");
        inputLabel.setBounds(130, 150, 180, 25);
        panel.add(inputLabel);

        inputField = new JTextField(10);
        inputField.setBounds(310, 150, 120, 25);
        panel.add(inputField);

        outputLabel = new JLabel();
        outputLabel.setBounds(100, 250, 500, 25);
        panel.add(outputLabel);

        convertButton = new JButton("Guess");
        convertButton.setBounds(220, 200, 100, 30);
        convertButton.addActionListener(this);
        panel.add(convertButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        System.out.print(number);

        if (e.getSource() == convertButton) {
            try {
                int user_number = Integer.parseInt(inputField.getText());
                int difference = Math.abs(user_number - number);

                if (difference > 30) {
                    outputLabel.setText("Too Hot");
                    currentBackgroundImage = new ImageIcon("too hot.jpeg");
                } else if (difference > 20 && difference <=30) {
                    outputLabel.setText("Hot");
                    currentBackgroundImage = new ImageIcon("hot.jpeg");
                } else if (difference > 10 && difference <= 20) {
                    outputLabel.setText("Cold");
                    currentBackgroundImage = new ImageIcon("cold.jpeg");
                } else if (difference > 0 && difference <= 10){
                    outputLabel.setText("Too Cold");
                    currentBackgroundImage = new ImageIcon("too cold.jpeg");
                } else {
                    outputLabel.setText(String.format("Congratulations! You guessed the number and you guessed it in %d tries.", count));
                    currentBackgroundImage = new ImageIcon("congratulation.jpeg");
                }

                panel.repaint();
                count++;
            } catch (NumberFormatException ex) {
                outputLabel.setText("Invalid input!");
            }
        }
    }

    public static void main(String[] args) {
        new guess_number();
    }
}
