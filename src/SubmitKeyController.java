import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javax.swing.*;


public class SubmitKeyController {
    private SubmitKeyPanel submitKeyPanel;

    public SubmitKeyController(SubmitKeyPanel panel) {
        this.submitKeyPanel = panel;
        this.submitKeyPanel.getSubmitButton().addActionListener(new SubmitKeyListener());
        this.submitKeyPanel.getResetButton().addActionListener(new ResetKeyAction());
    }

    private class SubmitKeyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String keyNumber = submitKeyPanel.getKeyNumber();
            String keyName = submitKeyPanel.getKeyName();
            String submissionTime = submitKeyPanel.getTimeSubmitField().getText().trim();
            String submissionDate = submitKeyPanel.getDateSubmitField().getText().trim();
            JButton submitButton = submitKeyPanel.getSubmitButton();

            if (e.getSource() == submitButton) {
                try (Connection con = DatabaseConnection.getConnection()) {
                    if (con == null) {
                        submitKeyPanel.setFinalMessage("*Database Connection Failed", Color.RED);
                        return;
                    }

                   
                    String checkQuery = "SELECT s.Availability " +
                                        "FROM status s " +
                                        "JOIN details d ON s.ID = d.ID " +
                                        "WHERE s.ID = ? AND d.KeyName = ?";
                    
                    try (PreparedStatement p = con.prepareStatement(checkQuery)) {
                        p.setString(1, keyNumber);
                        p.setString(2, keyName);
                        try (ResultSet rs = p.executeQuery()) {
                            if (!rs.next()) {
                                submitKeyPanel.setFinalMessage("Invalid Key ID and Key Name combination", Color.RED);
                                return;
                            }
                            if (rs.getInt("Availability") == 1) {
                                submitKeyPanel.setFinalMessage("Key is already in collection", Color.RED);
                                return;
                            }
                        }
                    }

                    
                    String updateQuery = "UPDATE history " +
                                         "SET SubmissionDate = ?, SubmissionTime = ? " +
                                         "WHERE KeyName = ? AND ID = ? " +
                                         "AND SubmissionDate IS NULL AND SubmissionTime IS NULL " +
                                         "ORDER BY IssueDate DESC, IssueTime DESC LIMIT 1";
                    
                    try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
                        ps.setDate(1, Date.valueOf(submissionDate));
                        ps.setTime(2, Time.valueOf(submissionTime));
                        ps.setString(3, keyName);
                        ps.setString(4, keyNumber);

                        int rowsAffected = ps.executeUpdate();
                        if (rowsAffected > 0) {
                            
                            String updateStatusQuery = "UPDATE status SET Availability = 1 WHERE ID = ?";
                            try (PreparedStatement updateStatusPs = con.prepareStatement(updateStatusQuery)) {
                                updateStatusPs.setString(1, keyNumber);
                                updateStatusPs.executeUpdate();
                            }

                            submitKeyPanel.setFinalMessage("Key Submitted successfully", Color.GREEN);
                        } else {
                            submitKeyPanel.setFinalMessage("No pending submission found", Color.RED);
                        }
                    }
                } catch (SQLException exp) {
                    exp.printStackTrace();
                    submitKeyPanel.setFinalMessage("*Database Error", Color.RED);
                }
            }
        }
    }
    private class ResetKeyAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            submitKeyPanel.setFinalMessage("", Color.WHITE);
            
            
            submitKeyPanel.getTimeSubmitField().setText(submitKeyPanel.getCurrentTime());
            submitKeyPanel.getDateSubmitField().setText(submitKeyPanel.getCurrentDate());

            
            if (submitKeyPanel.getKeyNumberDropdown().getItemCount() > 0) {
                submitKeyPanel.getKeyNumberDropdown().setSelectedIndex(0);
            }
            if (submitKeyPanel.getKeyNameDropdown().getItemCount() > 0) {
                submitKeyPanel.getKeyNameDropdown().setSelectedIndex(0);
            }

            
            submitKeyPanel.revalidate();
            submitKeyPanel.repaint();
        }
    }
}
