import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class CheckAvailabilityController {
    private CheckAvailabilityPanel checkAvailabilityPanel;

    public CheckAvailabilityController(CheckAvailabilityPanel panel) {
        this.checkAvailabilityPanel = panel;
        panel.getCheckButton().addActionListener(new CheckAvailabilityListener());
    }

    private class CheckAvailabilityListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            String keyName = checkAvailabilityPanel.getKeyName();
            JButton checkButton = checkAvailabilityPanel.getCheckButton();
            
            if (e.getSource() == checkButton) {
            	
            	Connection con = DatabaseConnection.getConnection();
            	if (con != null) {
            		PreparedStatement ps = null;
            		ResultSet rs = null;
            		try {
            			
            			String query = "SELECT s.availability FROM details d JOIN status s ON d.id = s.id WHERE d.keyname = ?;";
            			ps = con.prepareStatement(query);
            			ps.setString(1, keyName);
            			rs = ps.executeQuery();
            			
            			if (rs.next()) {
            				int availability = rs.getInt("availability"); 
            				
            				if (availability == 1) {
            	                checkAvailabilityPanel.setAvailabilityText("Key is Available", Color.GREEN);
            				} else {
            					checkAvailabilityPanel.setAvailabilityText("Key is NOT Available", Color.RED);
            				}
            			} else {
            			    checkAvailabilityPanel.setAvailabilityText("Key not found", Color.ORANGE);
            			}
            			
            		} catch (SQLException ex) {
            			ex.printStackTrace(); 
            			checkAvailabilityPanel.setAvailabilityText("Error occurred", Color.RED);
            		} finally {
            			try {
            				if (rs != null) 
            					rs.close();
            				if (ps != null) 
            					ps.close();
            				con.close();
            			} catch (SQLException ex) {
            				ex.printStackTrace();
            			}
            		}
            	} else {
            	    checkAvailabilityPanel.setAvailabilityText("Database Connection Failed", Color.RED);
            	}
            }
        }
    }
}
