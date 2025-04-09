import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login();  
        });
    }
}

class Login implements ActionListener {
    JFrame frame;
    JButton button1, button2;
    JTextField tf1;
    JPasswordField tf2;
    JLabel l, l1, l2, l4;
    JCheckBox showPassword;

    Login() {
        frame = new JFrame("Login Window");
        frame.setSize(900, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(20, 20, 20));

       
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(20, 20, 20));
        
       
        JPanel rightPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("Resources/NMIMS-LOGO.jpg"));

                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        rightPanel.setPreferredSize(new Dimension(400, 550));

       
        l = new JLabel("LOGIN PAGE", SwingConstants.CENTER);
        l.setFont(new Font("Broadway", Font.BOLD, 36)); 
        l.setForeground(Color.WHITE);
        l.setHorizontalAlignment(SwingConstants.CENTER); 
        l.setBounds(0, 30, 400, 50);

        // Username label and field
        l1 = new JLabel("Username:");
        l1.setBounds(50, 100, 100, 25);
        l1.setForeground(Color.WHITE);

        tf1 = new JTextField();
        tf1.setBounds(50, 130, 350, 40);
        tf1.setBackground(new Color(40, 40, 40));
        tf1.setForeground(Color.WHITE);
        tf1.setCaretColor(Color.WHITE);
        tf1.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));

        // Password label and field
        l2 = new JLabel("Password:");
        l2.setBounds(50, 190, 100, 25);
        l2.setForeground(Color.WHITE);

        tf2 = new JPasswordField();
        tf2.setBounds(50, 220, 350, 40);
        tf2.setBackground(new Color(40, 40, 40));
        tf2.setForeground(Color.WHITE);
        tf2.setCaretColor(Color.WHITE);
        tf2.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));

        // Show password checkbox
        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(50, 270, 150, 20);
        showPassword.setBackground(new Color(20, 20, 20));
        showPassword.setForeground(Color.WHITE);
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                tf2.setEchoChar((char) 0);
            } else {
                tf2.setEchoChar('*');
            }
        });

        // Login button
        button1 = new JButton("Login");
        button1.setBounds(50, 310, 160, 45);
        button1.addActionListener(this);
        button1.setFocusable(false);
        button1.setBackground(new Color(0, 150, 136));
        button1.setForeground(Color.WHITE);
        button1.setFont(new Font("SansSerif", Font.BOLD, 16));
        button1.setBorder(BorderFactory.createEmptyBorder());

        // Reset button
        button2 = new JButton("Reset");
        button2.setBounds(240, 310, 160, 45);
        button2.addActionListener(this);
        button2.setFocusable(false);
        button2.setBackground(new Color(211, 47, 47));
        button2.setForeground(Color.WHITE);
        button2.setFont(new Font("SansSerif", Font.BOLD, 16));
        button2.setBorder(BorderFactory.createEmptyBorder());
        
        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(50, 370, 350, 30);
        messagePanel.setBackground(new Color(20, 20, 20));

        // Error message label
        l4 = new JLabel("");
        l4.setForeground(Color.RED);
        l4.setFont(new Font("Arial Black", Font.BOLD, 12));
        messagePanel.add(l4);

        leftPanel.add(l);
        leftPanel.add(l1);
        leftPanel.add(tf1);
        leftPanel.add(l2);
        leftPanel.add(tf2);
        leftPanel.add(showPassword);
        leftPanel.add(button1);
        leftPanel.add(button2);
        leftPanel.add(messagePanel);

        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(leftPanel, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            if (tf1.getText().isEmpty() || tf2.getPassword().length == 0) {
                l4.setText("*Username and Password required");
            } else {
                String user = tf1.getText();
                String pass = new String(tf2.getPassword());

                Connection con = DatabaseConnection.getConnection();
                if (con != null) {
                    try {
                        String query = "SELECT * FROM login WHERE username=? AND password=?";
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, user);
                        ps.setString(2, pass);

                        ResultSet r = ps.executeQuery();

                        if (r.next()) {
                            frame.setVisible(false);
                            new Key();
                        } else {
                            l4.setText("*Enter Valid Username and Password");
                        }
                        r.close();
                        ps.close();
                        con.close();
                        
                        

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        l4.setText("*Database Error");
                    }
                } else {
                    l4.setText("*Database Connection Failed");
                }
            }

        }
        if (e.getSource() == button2) {
            tf1.setText("");
            tf2.setText("");
            l4.setText("");
        }
    }
}