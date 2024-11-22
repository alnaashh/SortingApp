import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SortingAppFancy extends JFrame {

    private JTextField inputField;
    private JComboBox<String> methodSelector;
    private JTextArea resultArea;

    public SortingAppFancy() {
        setTitle("Sorting Application");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Color bgColor = new Color(240, 248, 255);
        getContentPane().setBackground(bgColor);

        JLabel headerLabel = new JLabel("Sorting Application", JLabel.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setForeground(new Color(70, 130, 180));
        add(headerLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(bgColor);
        inputPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel inputLabel = new JLabel("Enter numbers:", JLabel.CENTER);
        inputLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputField = new JTextField(20);
        inputField.setHorizontalAlignment(JTextField.CENTER);

        JPanel methodPanel = new JPanel(new FlowLayout());
        methodPanel.setBackground(bgColor);
        JLabel methodLabel = new JLabel("Choose Sorting Method:");
        methodLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        methodSelector = new JComboBox<>(new String[]{"Bubble Sort", "Selection Sort"});
        methodPanel.add(methodLabel);
        methodPanel.add(methodSelector);

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(methodPanel);
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bgColor);
        JButton sortButton = new JButton("Sort");
        sortButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        sortButton.setBackground(new Color(135, 206, 250));
        sortButton.setForeground(Color.WHITE);
        sortButton.setFocusPainted(false);
        sortButton.addActionListener(new SortActionListener());
        buttonPanel.add(sortButton);
        add(buttonPanel, BorderLayout.SOUTH);

        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setForeground(new Color(70, 130, 180));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Sorted Output"));
        scrollPane.setBackground(bgColor);

        add(scrollPane, BorderLayout.EAST);
    }

    private class SortActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(SortingAppFancy.this, "Please enter numbers!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String[] tokens = input.split(",");
                int[] numbers = Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();

                String selectedMethod = (String) methodSelector.getSelectedItem();
                if (selectedMethod.equals("Bubble Sort")) {
                    bubbleSort(numbers);
                } else if (selectedMethod.equals("Selection Sort")) {
                    selectionSort(numbers);
                }

                resultArea.setText("Sorted Array: " + Arrays.toString(numbers));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(SortingAppFancy.this, "Invalid input! Please enter numbers separated by commas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingAppFancy app = new SortingAppFancy();
            app.setVisible(true);
        });
    }
}