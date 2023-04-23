// package StudentManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ViewOneStudent extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private Connection con = null;

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewOneStudent frame = new ViewOneStudent(args[0], args[1]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewOneStudent(String a, String b) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(80, 5, 30, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/quanlydiem", "root", "060103");
                    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = stmt.executeQuery(
                            "SELECT  * FROM students WHERE id = " + a);

                    if (!rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "Student ID not found!");
                        return;
                    }

                    ResultSetMetaData rsmd = rs.getMetaData();
                    int cols = rsmd.getColumnCount();
                    String[] column = new String[cols];
                    for (int i = 1; i <= cols; i++) {
                        column[i - 1] = rsmd.getColumnName(i);
                    }

                    rs.last();
                    int rows = rs.getRow();
                    rs.beforeFirst();

                    String[][] data = new String[rows][cols];
                    int count = 0;
                    while (rs.next()) {
                        for (int i = 1; i <= cols; i++) {
                            data[count][i - 1] = rs.getString(i);
                        }
                        count++;
                    }
                    con.close();

                    table = new JTable(data, column);
                    JScrollPane sp = new JScrollPane(table);
                    contentPane.add(sp, BorderLayout.CENTER);
                    contentPane.revalidate();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuStudent menu = new MenuStudent(a, b);
                menu.show();
                dispose();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCancel.setBounds(143, 164, 149, 33);
        buttonPanel.add(btnCancel);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
}