import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SortingApp extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;
    private JComboBox<String> sortMethodBox;
    private JButton sortButton;

    public SortingApp() {
        setTitle("Sorting Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1, 5, 5));

        JLabel inputLabel = new JLabel("Enter numbers (comma-separated):");
        inputField = new JTextField();
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        sortMethodBox = new JComboBox<>(new String[]{"Bubble Sort", "Selection Sort"});
        inputPanel.add(sortMethodBox);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        sortButton = new JButton("Sort");
        sortButton.setBackground(new Color(100, 149, 237));
        sortButton.setForeground(Color.WHITE);
        sortButton.setFont(new Font("Arial", Font.BOLD, 14));
        sortButton.addActionListener(new SortButtonListener());
        add(sortButton, BorderLayout.SOUTH);
    }

    private class SortButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String input = inputField.getText();
                String[] numberStrings = input.split(",");
                int[] numbers = Arrays.stream(numberStrings).mapToInt(Integer::parseInt).toArray();

                String selectedMethod = (String) sortMethodBox.getSelectedItem();
                if ("Bubble Sort".equals(selectedMethod)) {
                    bubbleSort(numbers);
                } else if ("Selection Sort".equals(selectedMethod)) {
                    selectionSort(numbers);
                }

                resultArea.setText("Sorted Numbers: " + Arrays.toString(numbers));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(SortingApp.this, "Invalid input! Please enter numbers separated by commas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
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
            SortingApp app = new SortingApp();
            app.setVisible(true);
        });
    }
}
