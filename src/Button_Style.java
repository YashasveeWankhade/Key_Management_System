import java.awt.*;
import javax.swing.*;

public class Button_Style {
    public static JLabel createLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setForeground(new Color(70, 50, 50));
        return label;
    }

    public static void styleButton(JButton button) {
        button.setBackground(new Color(0, 150, 136)); 
        button.setForeground(Color.WHITE); 
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder()); 
        button.setFont(new Font("SansSerif", Font.BOLD, 16)); 
        button.setPreferredSize(new Dimension(160, 45)); 
    }
}