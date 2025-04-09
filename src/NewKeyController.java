import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.Color;

public class NewKeyController {
    private NewKey newKey;

    public NewKeyController(NewKey panel) {
        this.newKey = panel;
        this.newKey.getAddButton().addActionListener(new NewKeyAction());
        this.newKey.getResetButton().addActionListener(new ResetKeyAction());
    }

    private class NewKeyAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String keyID = newKey.getKeyNumberField().getText().trim();
            String keyName = newKey.getKeyNameField().getText().trim();
            JButton addButton = newKey.getAddButton();

            if (e.getSource() == addButton) {
                if (keyName.isEmpty() || keyID.isEmpty()) {
                    newKey.setFinalMessage("*Key ID and Key Name field cannot be empty ", Color.RED);
                    return;
                }
                
                Connection con = DatabaseConnection.getConnection(); 

                if (con != null) {
                    try {
                        String checkQuery = "SELECT 1 FROM details WHERE ID = ? AND KeyName = ? LIMIT 1";
                        PreparedStatement p = con.prepareStatement(checkQuery);
                        p.setString(1, keyID);
                        p.setString(2, keyName);

                        ResultSet rs = p.executeQuery();
                        if (rs.next()) {
                            newKey.setFinalMessage("Key is already exists", Color.RED);
                        } else {
                            rs.close();
                            p.close(); 

                            String query = "INSERT INTO details (ID, KeyName) VALUES (?, ?)";
                            p = con.prepareStatement(query);
                            p.setString(1, keyID);
                            p.setString(2, keyName);
                            
                            int rowsAffected = p.executeUpdate();
                            if (rowsAffected > 0) {
                                newKey.setFinalMessage("New Key Inserted", Color.GREEN);
                            } else {
                                newKey.setFinalMessage("New Key NOT Inserted", Color.RED);
                            }
                        }

                        rs.close();
                        p.close();
                        con.close();

                    } catch (Exception exp) {
                        exp.printStackTrace();
                        newKey.setFinalMessage("*Database Error", Color.RED);
                    }
                } else {
                    newKey.setFinalMessage("*Database Connection Failed", Color.RED);
                }
            }
        }
    }
    private class ResetKeyAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	newKey.setFinalMessage("", Color.WHITE);
        	newKey.getKeyNumberField().setText(""); 
        	newKey.getKeyNameField().setText(""); 
            
            
        	newKey.revalidate();
        	newKey.repaint();
        }
    }
}
