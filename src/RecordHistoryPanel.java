import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RecordHistoryPanel extends JPanel {
    private JButton refreshButton;
    private JTable historyTable;
    private DefaultTableModel tableModel;
    private RecordHistoryController controller;

    public RecordHistoryPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30));

        // Table setup
        String[] columnNames = {"S.No.", "Key ID", "Key Name", "Issued By", "Issue Date", "Issue Time", "Sub Date", "Sub Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        historyTable = new JTable(tableModel);
        historyTable.setBackground(new Color(50, 50, 50));
        historyTable.setForeground(Color.WHITE);
        historyTable.setFont(new Font("Arial", Font.PLAIN, 14));
        historyTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        historyTable.getTableHeader().setBackground(new Color(70, 70, 70));
        historyTable.getTableHeader().setForeground(Color.WHITE);

        // Scroll Pane for Table
        JScrollPane scrollPane = new JScrollPane(historyTable);
        add(scrollPane, BorderLayout.CENTER);

        // Refresh Button
        refreshButton = new JButton("Refresh History");
        Button_Style.styleButton(refreshButton);
        add(refreshButton, BorderLayout.SOUTH);

        new RecordHistoryController(this);
    }

    public JButton getRefreshButton() { return refreshButton; }
    public JTable getHistoryTable() { return historyTable; }
    public DefaultTableModel getTableModel() { return tableModel; }
}
