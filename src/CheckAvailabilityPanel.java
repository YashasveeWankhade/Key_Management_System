import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class CheckAvailabilityPanel extends JPanel {
    private JComboBox<String> keyNameDropdown;
    private JLabel availabilityLabel;
    private JButton checkButton;

    public CheckAvailabilityPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30));

        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        inputPanel.setBackground(new Color(40, 40, 40));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        keyNameDropdown = new JComboBox<>(fetchKeyNames());

        inputPanel.add(createLabel("Key Name:"));
        inputPanel.add(keyNameDropdown);

        checkButton = new JButton("Check Availability");
        Button_Style.styleButton(checkButton);

        availabilityLabel = new JLabel("", SwingConstants.CENTER);
        availabilityLabel.setFont(new Font("Arial", Font.BOLD, 14));
        availabilityLabel.setForeground(Color.WHITE);
        availabilityLabel.setVisible(false);

        JPanel wrapperPanel = new JPanel(new BorderLayout(0, 10));
        wrapperPanel.setBackground(new Color(30, 30, 30));
        wrapperPanel.add(inputPanel, BorderLayout.CENTER);
        wrapperPanel.add(checkButton, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(30, 30, 30));
        bottomPanel.add(availabilityLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(wrapperPanel, gbc);

        gbc.gridy = 1;
        add(bottomPanel, gbc);

        new CheckAvailabilityController(this);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    public String getKeyName() {
        return (String) keyNameDropdown.getSelectedItem();
    }

    public JButton getCheckButton() {
        return checkButton;
    }

    public void setAvailabilityText(String text, Color color) {
        availabilityLabel.setText(text);
        availabilityLabel.setForeground(color);
        availabilityLabel.setVisible(true);
    }

    private String[] fetchKeyNames() {
        ArrayList<String> keyNames = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT KeyName FROM details");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                keyNames.add(rs.getString("KeyName"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return keyNames.toArray(new String[0]);
    }
}
