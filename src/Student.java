// package StudentManagement;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
import java.awt.Color;
import javax.swing.JDesktopPane;

public class Student extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JTextField email;
	private JTextField address;
	private JTextField phone;
	private JTextField gpa;
	private JTextField pass;

	Connection con = null;
	PreparedStatement pst = null;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Student() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 620);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel studentDetails = new JLabel("Student Details");
		studentDetails.setForeground(Color.BLACK);
		studentDetails.setFont(new Font("Perpetua Titling MT", Font.BOLD, 22));

		JLabel ID = new JLabel("Student ID");
		ID.setForeground(Color.BLACK);
		ID.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));

		JLabel Name = new JLabel("Student Name");
		Name.setForeground(Color.BLACK);
		Name.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));

		JLabel Email = new JLabel("Email");
		Email.setForeground(Color.BLACK);
		Email.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));

		JLabel Address = new JLabel("Address");
		Address.setForeground(Color.BLACK);
		Address.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));

		JLabel Phone = new JLabel("Phone Number");
		Phone.setForeground(Color.BLACK);
		Phone.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));

		JLabel GPA = new JLabel("GPA");
		GPA.setForeground(Color.BLACK);
		GPA.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));

		JLabel PASS = new JLabel("Pass");
		PASS.setForeground(Color.BLACK);
		PASS.setFont(new Font("Perpetua Titling MT", Font.BOLD, 14));

		id = new JTextField();
		id.setColumns(10);

		name = new JTextField();
		name.setColumns(10);

		email = new JTextField();
		email.setColumns(10);

		address = new JTextField();
		address.setColumns(10);

		phone = new JTextField();
		phone.setColumns(10);

		gpa = new JTextField();
		gpa.setColumns(10);

		pass = new JTextField();
		pass.setColumns(10);

		JButton submit = new JButton("Submit");
		submit.setForeground(Color.BLACK);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String query = "INSERT INTO `students`(`id`,`st_name`, `email`, `address`, `phone`, `GPA`,`pass`) VALUES (?, ?, ?, ?,?,?,?)";
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlydiem", "root", "060103");
					pst=con.prepareStatement(query);
					pst.setString(1, id.getText());
					pst.setString(2, name.getText());
					pst.setString(3, email.getText());
					pst.setString(4, address.getText());
					pst.setString(5, phone.getText());
					pst.setString(6, gpa.getText());
					pst.setString(7, pass.getText());
					if(id.getText().equals("") ||name.getText().equals("") || email.getText().equals("") || address.getText().equals("") || phone.getText().equals("")|| gpa.getText().equals("")|| pass.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Fill all the details :(");
					}
					else {
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Student added Successfully :)");
						dispose();
						Menu menu = new Menu();
						menu.show();
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
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
				Menu menu = new Menu();
				menu.show();
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(43)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(ID)
														.addComponent(Name, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
														.addComponent(Email)
														.addComponent(Address)
														.addComponent(Phone)
														.addComponent(GPA)
														.addComponent(PASS))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(address, 242, 242, 242)
														.addComponent(phone, 242, 242, 242)
														.addComponent(id, 242, 242, 242)
														.addComponent(gpa, 242, 242, 242)
														.addComponent(name, 242, 242, 242)
														.addComponent(email, 242, 242, 242)
														.addComponent(pass, 242, 242, 242)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
																.addComponent(email)
																.addComponent(name)
																.addComponent(id)
																.addComponent(gpa)
																.addComponent(pass)))

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
														.addComponent(ID, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
														.addComponent(id, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
												.addGap(20)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(Name, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
														.addComponent(name, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
												.addGap(20)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(Email, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
														.addComponent(email, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
												.addGap(20)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(Address)
														.addComponent(address, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
												.addGap(20)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(phone, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
														.addComponent(Phone))
												.addGap(20)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(GPA, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
														.addComponent(gpa, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
												.addGap(20)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(PASS, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
														.addComponent(pass, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
												.addGap(40)
												.addComponent(submit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
		);
		contentPane.setLayout(gl_contentPane);
	}

}