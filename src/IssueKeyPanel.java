import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;

public class IssueKeyPanel extends JPanel {
	
	private JComboBox<String> keyNumberDropdown;
	private JComboBox<String> keyNameDropdown;
	private JTextField issuedByField;
	private JTextField timeField;
	private JTextField dateField;
	private JButton issueButton;
	private JButton resetButton;
	private JLabel finalMessage;
	
    public IssueKeyPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        finalMessage = new JLabel(" ");
        finalMessage.setForeground(Color.WHITE);
        finalMessage.setVisible(false);
        finalMessage.setFont(new Font("Arial", Font.BOLD, 14));
        add(finalMessage, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(40, 40, 40)); 
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        String[] keyNumbers = fetchKeyNumbers();
        String[] keyNames = fetchKeyNames();

        keyNumberDropdown = new JComboBox<>(keyNumbers);
        keyNameDropdown = new JComboBox<>(keyNames);

        issuedByField = createStyledTextField();
        timeField = createStyledTextField();
        timeField.setText(getCurrentTime());
        timeField.setEditable(false);

        dateField = createStyledTextField();
        dateField.setText(getCurrentDate());
        dateField.setEditable(false);

        issueButton = new JButton("Issue Key");
        Button_Style.styleButton(issueButton);
        
        
        resetButton = new JButton("Reset");
        Button_Style.styleButton(resetButton);
        resetButton.setBackground(new Color(75,0,0));
        
        addComponent(inputPanel, createLabel("Key Number:"), keyNumberDropdown, 0);
        addComponent(inputPanel, createLabel("Key Name:"), keyNameDropdown, 1);
        addComponent(inputPanel, createLabel("Issued By:"), issuedByField, 2);
        addComponent(inputPanel, createLabel("Time of Issuing:"), timeField, 3);
        addComponent(inputPanel, createLabel("Date of Issuing:"), dateField, 4);

        gbc.gridy++;
        add(inputPanel, gbc);


        gbc.gridy++;
        add(issueButton, gbc);
        
        gbc.gridy++;
        add(resetButton, gbc);
        
        new IssueKeyController(this);
    }

    private void addComponent(JPanel panel, JLabel label, JComponent component, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField(20);
        textField.setBorder(BorderFactory.createLineBorder(new Color(139, 115, 85), 2));
        textField.setBackground(new Color(50, 50, 50)); 
        textField.setForeground(Color.WHITE); 
        textField.setCaretColor(Color.WHITE); 
        return textField;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE); 
        label.setFont(new Font("Arial", Font.BOLD, 14)); 
        return label;
    }

    public String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalDateTime.now().format(dtf);
    }

    public String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.now().format(dtf);
    }
    
    private String[] fetchKeyNumbers() {
        ArrayList<String> keyNumbers = new ArrayList<>();
        Connection con = DatabaseConnection.getConnection();
        if (con != null) {
            try {
                String query = "SELECT ID FROM details";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    keyNumbers.add(rs.getString("ID"));
                }
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return keyNumbers.toArray(new String[0]);
    }

    private String[] fetchKeyNames() {
        ArrayList<String> keyNames = new ArrayList<>();
        Connection con = DatabaseConnection.getConnection();
        if (con != null) {
            try {
                String query = "SELECT KeyName FROM details";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    keyNames.add(rs.getString("KeyName"));
                }
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return keyNames.toArray(new String[0]);
    }
    
    public void setFinalMessage(String text, Color color) {
        finalMessage.setText(text);
        finalMessage.setForeground(color);
        finalMessage.setVisible(true);
    }
    
    public String getKeyNumber() { 
    	String keyNumber = (String) keyNumberDropdown.getSelectedItem(); 
    	return keyNumber;
    }
    
    public String getKeyName() { 
    	String keyName = (String) keyNameDropdown.getSelectedItem(); 
    	return keyName;
    }
    
    public JTextField getIssuedByField() {
    	return issuedByField; 
    }
    
    public JTextField getTimeField() {
    	return timeField; 
    }
    
    public JTextField getDateField() { 
    	return dateField; 
    }
    public JButton getIssueButton() { 
    	return issueButton; 
    }
    public JButton getResetButton() { 
    	return resetButton; 
    }
    
    public JComboBox<String> getKeyNumberDropdown() {
        return keyNumberDropdown;
    }

    public JComboBox<String> getKeyNameDropdown() {
        return keyNameDropdown;
    }
}
