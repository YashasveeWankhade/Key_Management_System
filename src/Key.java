import java.awt.*;
import javax.swing.*;

public class Key {
    public Key() {
        JFrame frame = new JFrame("Key Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(new Color(75, 0, 0));
        
        ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("Resources/NMIMS-LOGO.jpg"));
        frame.setIconImage(image.getImage());

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setBackground(new Color(75, 0, 0));
        tabbedPane.setForeground(new Color(255, 255, 255));
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        tabbedPane.add("Issue Key", new IssueKeyPanel());
        tabbedPane.add("Submit Key", new SubmitKeyPanel());
        tabbedPane.add("Check Availability", new CheckAvailabilityPanel());
        tabbedPane.add("Record History", new RecordHistoryPanel());
        tabbedPane.add("New Key", new NewKey());

        frame.add(tabbedPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void main(String args[]){
        new Key();
    }
}
