import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends JFrame {
    public RegisterPage() {
        setTitle("Register Page");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel(""));
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (DatabaseHelper.register(name, email, password)) {
                    JOptionPane.showMessageDialog(null, "Registration Successful!");

                    Player player1 = new Player(name, "X");
                    Player player2 = new Player("Computer", "O");
                    new GamePage(player1, player2);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Failed!");
                }
            }
        });

        setVisible(true);
    }
}

