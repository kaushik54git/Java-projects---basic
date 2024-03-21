import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame implements ActionListener {
    private JTextField inputField;
    private JLabel outputLabel;
    private JButton convertButton;
    private JPanel panel;
    private ImageIcon currentBackgroundImage; // Store the current background image
    private JComboBox<String> unitSelection; // Added JComboBox for selecting units

    public TemperatureConverter() {
        setTitle("Temperature Converter");
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

        JLabel inputLabel = new JLabel("Enter temperature:");
        inputLabel.setBounds(130, 150, 120, 25);
        panel.add(inputLabel);

        inputField = new JTextField(10);
        inputField.setBounds(250, 150, 120, 25);
        panel.add(inputField);
        
        unitSelection = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        unitSelection.setBounds(380, 150, 80, 25);
        panel.add(unitSelection);

        
        outputLabel = new JLabel();
        outputLabel.setBounds(100, 250, 500, 25);
        panel.add(outputLabel);

        convertButton = new JButton("Convert");
        convertButton.setBounds(200, 200, 100, 30);
        convertButton.addActionListener(this);
        panel.add(convertButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            try {
                double temperature = Double.parseDouble(inputField.getText());
                double celsius = 0;
                double fahrenheit = 0;
                double kelvin = 0;
                String selectedUnit = (String) unitSelection.getSelectedItem();

                switch (selectedUnit) {
                    case "Celsius":
                        celsius = temperature;
                        fahrenheit = celsiusToFahrenheit(celsius);
                        kelvin = celsiusToKelvin(celsius);
                        break;
                    case "Fahrenheit":
                        fahrenheit = temperature;
                        celsius = fahrenheitToCelsius(fahrenheit);
                        kelvin = celsiusToKelvin(celsius);
                        break;
                    case "Kelvin":
                        kelvin = temperature;
                        celsius = kelvinToCelsius(kelvin);
                        fahrenheit = celsiusToFahrenheit(celsius);
                        break;
                }

                currentBackgroundImage = getBackgroundImage(celsius);

                if (celsius >= 21 && celsius<= 40){
                    outputLabel.setForeground(Color.CYAN);
                }else if (celsius>40){
                    outputLabel.setForeground(Color.RED);
                }else if (celsius<=20){
                    outputLabel.setForeground(Color.BLUE);
                }else{
                    outputLabel.setForeground(Color.BLACK);
                }

                outputLabel.setText(String.format("celsius: %.2f\u00B0C | fahrenheit: %.2f\u00B0F | kelvin: %.2f\u00B0K", celsius, fahrenheit, kelvin));
                
                // Repaint the panel to update the background image
                panel.repaint();
            } catch (NumberFormatException ex) {
                outputLabel.setText("Invalid input!");
            }
        }
    }

    private ImageIcon getBackgroundImage(double celsius) {
        // Example logic to determine the background image based on user input
        if (celsius >= 21 && celsius<= 40) {
            return new ImageIcon("relax.jpg");
        } else if (celsius > 40) {
            return new ImageIcon("hell.jpg");
        } else if (celsius <=20){
            return  new ImageIcon("cold.jpeg");
        } else{
            return  new ImageIcon("hell.webp");
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }
}
