import java.awt.*;
import javax.swing.*;

public class NewKey extends JPanel {
    
    private JTextField keyNumberField;
    private JTextField keyNameField;
    private JButton addButton;
    private JLabel finalMessage;
    private JButton resetButton;
    
    public NewKey() {
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
        finalMessage.setFont(new Font("Arial", Font.BOLD, 14));
        add(finalMessage, gbc);
        
        gbc.gridy++;
        gbc.gridwidth = 1;
        
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(40, 40, 40)); 
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        
        keyNumberField = createStyledTextField();
        keyNameField = createStyledTextField();
        
        addButton = new JButton("Add Key");
        Button_Style.styleButton(addButton);
        
        resetButton = new JButton("Reset");
        Button_Style.styleButton(resetButton);
        resetButton.setBackground(new Color(75,0,0));
        
        addComponent(inputPanel, createLabel("Key Number:"), keyNumberField, 0);
        addComponent(inputPanel, createLabel("Key Name:"), keyNameField, 1);
        
        gbc.gridy++;
        add(inputPanel, gbc);
        
        gbc.gridy++;
        add(addButton, gbc);
        
        gbc.gridy++;
        add(resetButton, gbc);
        
        new NewKeyController(this);
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
    
    public void setFinalMessage(String text, Color color) {
        finalMessage.setText(text);
        finalMessage.setForeground(color);
        finalMessage.setVisible(true);
    }
    
    public JTextField getKeyNumberField() { 
        return keyNumberField; 
    }
    
    public JTextField getKeyNameField() { 
        return keyNameField; 
    }
    
    public JButton getAddButton() { 
        return addButton; 
    }
    
    public JButton getResetButton() { 
    	return resetButton; 
    }
}