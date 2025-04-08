import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    String username;
    String password;
    private static final String FILE_PATH = "point_data.txt";

    public static void main(String[] args) {
        Main mainInstance = new Main(); // Create an instance of Main
        mainInstance.loadPointData(); // Load point data from file

        JFrame frame = new JFrame("Login page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("background.png");
        backgroundPanel.setLayout(new GridBagLayout()); // For centering

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Button Panel (Bottom)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        JButton button1 = new JButton("Login");
        button1.setPreferredSize(new Dimension(200, 50));
        button1.addActionListener(e -> mainInstance.login());

        JButton button2 = new JButton("Sign Up");
        button2.setPreferredSize(new Dimension(200, 50));
        button2.addActionListener(e -> mainInstance.signUp());
        

        JButton button3 = new JButton("Exit");
        button3.setPreferredSize(new Dimension(200, 50));
        button3.addActionListener(e -> {
            mainInstance.savePointData(); // Save point data before exiting
            System.exit(0);
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        frame.add(backgroundPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void signUp() {
    }

    private void login() {
    }

    // Background Panel
    static class BackgroundPanel extends JPanel {
        public BackgroundPanel(String imagePath) {
            setBackground(Color.WHITE);
            setLayout(new GridBagLayout());
        }
    }

    private void savePointData() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while saving point data.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void loadPointData() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                if (fileScanner.hasNextInt()) {
                    username = fileScanner.next();
                }
                if (fileScanner.hasNextInt()) {
                    password = fileScanner.next();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while loading point data.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}