package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JProgressBar;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import common.UniversalHelper;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class PlacanjeMeni extends JFrame {

	static UniversalHelper Helper = new UniversalHelper();
	
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_Kartica;
	private JTextField textField_Exp;
	private JTextField textField_Ime;
	private JTextField textField_Prez;
	private JTextField textField_Adresa;
	private JTextField textField_Adresa2;
	private JTextField textField_BrTel;
	private JPasswordField passwordField;
	private JTextField txtCena;
	private float UkupnaC;
	private String Cena;
	private int ID;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KorpaMeni frame = new KorpaMeni(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	public void initComponents() {

		setBounds(100, 100, 330, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Broj kartice:");
		lblNewLabel_1.setBounds(10, 78, 102, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_Kartica = new JTextField();
	
		textField_Kartica.setBounds(10, 103, 292, 20);
		contentPane.add(textField_Kartica);
		textField_Kartica.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Exp. date:");
		lblNewLabel_1_1.setBounds(10, 141, 64, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textField_Exp = new JTextField();
		textField_Exp.setColumns(10);
		textField_Exp.setBounds(10, 166, 125, 20);
		contentPane.add(textField_Exp);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Sigurnosni kod:");
		lblNewLabel_1_1_1.setBounds(177, 141, 89, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ime:");
		lblNewLabel_1_2.setBounds(10, 208, 64, 14);
		contentPane.add(lblNewLabel_1_2);
		
		textField_Ime = new JTextField();
		textField_Ime.setColumns(10);
		textField_Ime.setBounds(10, 233, 125, 20);
		contentPane.add(textField_Ime);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Prezime:");
		lblNewLabel_1_2_1.setBounds(177, 208, 64, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		textField_Prez = new JTextField();
		textField_Prez.setColumns(10);
		textField_Prez.setBounds(177, 233, 125, 20);
		contentPane.add(textField_Prez);
		
		textField_Adresa = new JTextField();
		textField_Adresa.setColumns(10);
		textField_Adresa.setBounds(10, 309, 292, 20);
		contentPane.add(textField_Adresa);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Adresa:");
		lblNewLabel_1_2_2.setBounds(10, 284, 64, 14);
		contentPane.add(lblNewLabel_1_2_2);
		
		textField_Adresa2 = new JTextField();
		textField_Adresa2.setColumns(10);
		textField_Adresa2.setBounds(10, 376, 292, 20);
		contentPane.add(textField_Adresa2);
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("Adresa dostave:");
		lblNewLabel_1_2_2_1.setBounds(10, 351, 102, 14);
		contentPane.add(lblNewLabel_1_2_2_1);
		
		JLabel lblNewLabel_1_2_2_1_1 = new JLabel("Broj telefona:");
		lblNewLabel_1_2_2_1_1.setBounds(10, 426, 102, 14);
		contentPane.add(lblNewLabel_1_2_2_1_1);
		
		textField_BrTel = new JTextField();
		textField_BrTel.setColumns(10);
		textField_BrTel.setBounds(10, 451, 292, 20);
		contentPane.add(textField_BrTel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 166, 125, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("PLACANJE");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 292, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("Ukupno za naplatu:");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setBounds(10, 497, 125, 14);
		contentPane.add(lblNewLabel_3_1);
		
		JButton btnNewButton_1 = new JButton("KUPI");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connect=Helper.DBSetup();
				String SQL = "DELETE FROM korpa";
				if(textField_Kartica.getText().isEmpty() || 
				   textField_Exp.getText().isEmpty() 	 ||
				   passwordField.getText().isEmpty()	 ||
				   textField_Ime.getText().isEmpty()	 ||
				   textField_Prez.getText().isEmpty()	 ||
				   textField_Adresa.getText().isEmpty()  ||
				   textField_Adresa2.getText().isEmpty() ||
				   textField_BrTel.getText().isEmpty()     ) {
					JOptionPane.showMessageDialog(null, "Niste popunili sva polja");
				}
				
				
				else {
				try {
					Statement st = connect.createStatement(); 
					st.execute(SQL);
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				
				dispose();
				UspesnoDodato uspdod = new UspesnoDodato(ID);
				uspdod.setVisible(true);
				UspesnoDodato Center = (UspesnoDodato) Helper.CenterWindow(uspdod);
			}
			}
		});
		btnNewButton_1.setBounds(99, 542, 89, 23);
		contentPane.add(btnNewButton_1);
		
		txtCena = new JTextField();
		txtCena.setHorizontalAlignment(SwingConstants.CENTER);
		txtCena.setBorder(null);
		txtCena.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCena.setEditable(false);
		txtCena.setBounds(129, 494, 72, 20);
		contentPane.add(txtCena);
		txtCena.setColumns(10);
		
		Cena = Float.toString(UkupnaC);
		//JOptionPane.showMessageDialog(null,String.valueOf(UkupnaC));
		
		txtCena.setText(Cena);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(PlacanjeMeni.class.getResource("/resources/Kartice150.png")));
		lblNewLabel.setBounds(152, 57, 150, 35);
		contentPane.add(lblNewLabel);
	
	}
	
	public PlacanjeMeni(float UkCena, int ID) {
		this.ID = ID;
		setTitle("Placanje");
		this.UkupnaC = UkCena;
		initComponents();	
	}
}
