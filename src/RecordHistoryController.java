import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RecordHistoryController {
    private RecordHistoryPanel recordHistoryPanel;

    public RecordHistoryController(RecordHistoryPanel panel) {
        this.recordHistoryPanel = panel;
        this.recordHistoryPanel.getRefreshButton().addActionListener(new RefreshHistoryAction());
    }

    private class RefreshHistoryAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Connection con = DatabaseConnection.getConnection();
            if (con != null) {
                try {
                    String query = "SELECT ID, KeyName, IssuedBy, IssueDate, IssueTime, SubmissionDate, SubmissionTime FROM history";
                    try (PreparedStatement ps = con.prepareStatement(query);
                         ResultSet rs = ps.executeQuery()) {

                        DefaultTableModel tableModel = recordHistoryPanel.getTableModel();
                        tableModel.setRowCount(0); 

                        int serialNo = 1;
                        while (rs.next()) {
                            Object[] rowData = {
                                serialNo++,
                                rs.getString("ID"),
                                rs.getString("KeyName"),
                                rs.getString("IssuedBy"),
                                rs.getDate("IssueDate"),
                                rs.getTime("IssueTime"),
                                rs.getDate("SubmissionDate"),
                                rs.getTime("SubmissionTime")
                            };
                            tableModel.addRow(rowData);
                        }
                    }
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(recordHistoryPanel, "*Database Error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(recordHistoryPanel, "*Database Connection Failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
