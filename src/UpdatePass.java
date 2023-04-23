// package StudentManagement;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDesktopPane;

public class UpdatePass extends JFrame {

    private JPanel contentPane;
    private JTextField pass;
    private JTextField pass_new;

    Connection con = null;
    PreparedStatement pst = null;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdatePass frame = new UpdatePass(args[0], args[1]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public UpdatePass(String a, String b) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 620);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel studentDetails = new JLabel("Student Update");
		studentDetails.setForeground(Color.BLACK);
		studentDetails.setFont(new Font("Perpetua Titling MT", Font.BOLD, 22));

		JLabel PASS = new JLabel("Password new");
		PASS.setForeground(Color.BLACK);
		PASS.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));

		pass = new JTextField();
		pass.setColumns(10);

        JLabel PASS_NEW = new JLabel("Password");
		PASS_NEW.setForeground(Color.BLACK);
		PASS_NEW.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));

		pass_new = new JTextField();
		pass_new.setColumns(10);

		JButton submit = new JButton("Submit");
		submit.setForeground(Color.BLACK);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if(pass_new.getText().equals(b)){

				try {
					String query = "UPDATE `students` SET pass=IFNULL(?,pass) WHERE id=?";
					con = DriverManager.getConnection("jdbc:mysql://localhost/quanlydiem", "root", "060103");
					pst=con.prepareStatement(query);
					pst.setString(1, pass.getText().equals("") ? null : pass.getText());
					pst.setString(2, a.equals("") ? null : a);
					if(a.equals("")) {
						JOptionPane.showMessageDialog(null, "Need ID to update :(");
					}
					else {
						int k = pst.executeUpdate();
						if(k==1) {
							JOptionPane.showMessageDialog(null, "Pass update Successfully :)");
							dispose();
							MenuStudent menu = new MenuStudent(a,b);
							menu.show();
						}
						else{
							JOptionPane.showMessageDialog(null, "This ID does not exist :(");
						}
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}}
                else{
                    JOptionPane.showMessageDialog(null, "This password incorrect");
                }

			}
		});
		submit.setFont(new Font("Tahoma", Font.BOLD, 14));

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.GRAY);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuStudent menu = new MenuStudent(a, b);
				menu.show();
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(43)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(PASS, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                                        .addComponent(PASS_NEW))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(pass,  242, 242, 242)
                                                        .addComponent(pass_new,  242, 242, 242))
												.addGap(34))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
												.addComponent(studentDetails)
												.addGap(170))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(150)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(submit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)))))
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addGap(11)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(studentDetails)
												.addGap(20)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(PASS, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
														.addComponent(pass, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(PASS_NEW, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(pass_new, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
												
												.addGap(40)
												.addComponent(submit, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
												.addGap(20)
												.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
