import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterGUI extends JFrame {
    private JComboBox<String> sourceComboBox;
    private JComboBox<String> targetComboBox;
    private JTextField amountTextField;
    private JLabel resultLabel;
    private JButton convertButton;

    public CurrencyConverterGUI() {
        // Initialize components
        sourceComboBox = new JComboBox<>(new String[]{"INR", "USD", "EUR", "GBP", "JPY"});
        targetComboBox = new JComboBox<>(new String[]{"INR", "USD", "EUR", "GBP", "JPY"});
        amountTextField = new JTextField(10);
        resultLabel = new JLabel();
        convertButton = new JButton("Convert");

        // Set up layout
        setLayout(new BorderLayout());

        // Add components to the frame
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(230, 230, 250)); // Light purple
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        topPanel.add(new JLabel("Source Currency:"));
        topPanel.add(sourceComboBox);
        topPanel.add(new JLabel("Target Currency:"));
        topPanel.add(targetComboBox);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(240, 248, 255)); // Light blue
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        centerPanel.add(new JLabel("Amount:"));
        centerPanel.add(amountTextField);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(224, 238, 238)); // Light cyan
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        bottomPanel.add(convertButton);
        resultLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // Add padding to the left
        bottomPanel.add(resultLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        // Set background color and padding for the frame
        getContentPane().setBackground(new Color(245, 245, 245)); // Light gray
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        // Add action listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sourceCurrency = (String) sourceComboBox.getSelectedItem();
                String targetCurrency = (String) targetComboBox.getSelectedItem();
                double amount = Double.parseDouble(amountTextField.getText());

                double convertedAmount = CurrencyConverter.convert(sourceCurrency, targetCurrency, amount);
                resultLabel.setText(String.format("%.2f %s", convertedAmount, targetCurrency));
            }
        });

        // Set fonts and colors
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font resultFont = new Font("Arial", Font.PLAIN, 16);
        Color textColor = new Color(50, 50, 50); // Dark gray

        sourceComboBox.setFont(labelFont);
        targetComboBox.setFont(labelFont);
        amountTextField.setFont(labelFont);
        convertButton.setFont(labelFont);
        resultLabel.setFont(resultFont);

        convertButton.setForeground(Color.WHITE);
        convertButton.setBackground(new Color(100, 149, 237)); // Blue

        topPanel.setForeground(textColor);
        centerPanel.setForeground(textColor);
        bottomPanel.setForeground(textColor);
    }

    public static void main(String[] args) {
        CurrencyConverterGUI gui = new CurrencyConverterGUI();
        gui.setTitle("Currency Converter");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(400, 200);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }
}

class CurrencyConverter {
    private static final Map<String, Map<String, Double>> EXCHANGE_RATES = new HashMap<>();

    static {
        Map<String, Double> inrRates = new HashMap<>();
        inrRates.put("USD", 0.012);
        inrRates.put("EUR", 0.011);
        inrRates.put("GBP", 0.010);
        inrRates.put("JPY", 1.66);
        inrRates.put("INR", 1.0);
        EXCHANGE_RATES.put("INR", inrRates);

        Map<String, Double> usdRates = new HashMap<>();
        usdRates.put("INR", 81.63);
        usdRates.put("EUR", 0.92);
        usdRates.put("GBP", 0.80);
        usdRates.put("JPY", 134.09);
        usdRates.put("USD", 1.0);
        EXCHANGE_RATES.put("USD", usdRates);

        // Add exchange rates for other currencies
    }

    public static double convert(String sourceCurrency, String targetCurrency, double amount) {
        Map<String, Double> sourceRates = EXCHANGE_RATES.get(sourceCurrency);
        double targetRate = sourceRates.get(targetCurrency);
        return amount * targetRate;
    }
}