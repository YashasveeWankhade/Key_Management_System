import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;

public class IssueKeyController {
    private IssueKeyPanel issueKeyPanel;

    public IssueKeyController(IssueKeyPanel panel) {
        this.issueKeyPanel = panel;
        this.issueKeyPanel.getIssueButton().addActionListener(new IssueKeyAction());
        this.issueKeyPanel.getResetButton().addActionListener(new ResetKeyAction());
    }

    private class IssueKeyAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String keyID = issueKeyPanel.getKeyNumber().trim();
            String keyName = issueKeyPanel.getKeyName().trim();
            String issuedBy = issueKeyPanel.getIssuedByField().getText().trim();
            String issueTime = issueKeyPanel.getTimeField().getText().trim();
            String issueDate = issueKeyPanel.getDateField().getText().trim();
            JButton issueButton = issueKeyPanel.getIssueButton();
            JButton resetButton = issueKeyPanel.getResetButton();
            // Validate empty fields
            if (issuedBy.isEmpty()) {
                issueKeyPanel.setFinalMessage("*Issued By field cannot be empty", Color.RED);
                return;
            }

            try {
                Time time = Time.valueOf(issueTime); 
                Date date = Date.valueOf(issueDate); 

                if (e.getSource() == issueButton) {
                    try (Connection con = DatabaseConnection.getConnection()) {
                        if (con == null) {
                            issueKeyPanel.setFinalMessage("*Database Connection Failed", Color.RED);
                            return;
                        }

                        
                        String checkQuery = "SELECT s.Availability FROM status s JOIN details d ON s.ID = d.ID WHERE s.ID = ? AND d.KeyName = ?";
                        try (PreparedStatement p = con.prepareStatement(checkQuery)) {
                            p.setString(1, keyID);
                            p.setString(2, keyName);
                            try (ResultSet rs = p.executeQuery()) {
                                if (!rs.next()) {
                                    issueKeyPanel.setFinalMessage("Invalid Key ID and Key Name combination", Color.RED);
                                    return;
                                }
                            }
                        }

                        
                        String query = "SELECT Availability FROM status WHERE ID=?";
                        try (PreparedStatement p1 = con.prepareStatement(query)) {
                            p1.setString(1, keyID);
                            try (ResultSet rs = p1.executeQuery()) {
                                if (rs.next() && rs.getInt("Availability") == 1) {
                                    // Insert into history
                                    String insertQuery = "INSERT INTO history (ID, KeyName, IssuedBy, IssueDate, IssueTime) VALUES (?, ?, ?, ?, ?)";
                                    try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
                                        ps.setString(1, keyID);
                                        ps.setString(2, keyName);
                                        ps.setString(3, issuedBy);
                                        ps.setDate(4, date);
                                        ps.setTime(5, time);
                                        int rowsAffected = ps.executeUpdate();

                                        if (rowsAffected > 0) {
                                            
                                            String updateQuery = "UPDATE status SET Availability = 0 WHERE ID = ?";
                                            try (PreparedStatement updatePs = con.prepareStatement(updateQuery)) {
                                                updatePs.setString(1, keyID);
                                                updatePs.executeUpdate();
                                            }
                                            issueKeyPanel.setFinalMessage("Record inserted successfully", Color.GREEN);
                                        }
                                    }
                                } else {
                                    issueKeyPanel.setFinalMessage("Key is not available", Color.RED);
                                }
                            }
                        }
                    }
                }
            } catch (IllegalArgumentException ex) {
                issueKeyPanel.setFinalMessage("*Invalid date or time format", Color.RED);
            } catch (SQLException ex) {
                ex.printStackTrace();
                issueKeyPanel.setFinalMessage("*Database Error", Color.RED);
            }
        }
    }
    private class ResetKeyAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            issueKeyPanel.setFinalMessage("", Color.WHITE);
            issueKeyPanel.getIssuedByField().setText("");  
           
            issueKeyPanel.getTimeField().setText(issueKeyPanel.getCurrentTime());
            issueKeyPanel.getDateField().setText(issueKeyPanel.getCurrentDate());

           
            if (issueKeyPanel.getKeyNumberDropdown().getItemCount() > 0) {
                issueKeyPanel.getKeyNumberDropdown().setSelectedIndex(0);
            }
            if (issueKeyPanel.getKeyNameDropdown().getItemCount() > 0) {
                issueKeyPanel.getKeyNameDropdown().setSelectedIndex(0);
            }

         
            issueKeyPanel.revalidate();
            issueKeyPanel.repaint();
        }
    }
}
