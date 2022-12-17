package view;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import common.UniversalHelper;
import java.awt.SystemColor;

public class Login extends JFrame {

	static UniversalHelper Helper = new UniversalHelper();
	
	
	private JPanel contentPane;
	private JTextField txtKorisnickoime;
	private JPasswordField txtLozinka;
	public int ID;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					Login  CW =  (Login) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void initComponents() {

		setBackground(new Color(255, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DOBRODOSLI");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 111, 354, 32);
		contentPane.add(lblNewLabel);
		
		JLabel KorisnickoIme = new JLabel("KORISNICKO IME");
		KorisnickoIme.setBackground(new Color(128, 0, 0));
		KorisnickoIme.setFont(new Font("Tahoma", Font.BOLD, 14));
		KorisnickoIme.setHorizontalAlignment(SwingConstants.CENTER);
		KorisnickoIme.setBounds(10, 180, 135, 17);
		contentPane.add(KorisnickoIme);
		
		JLabel Lozinka = new JLabel("LOZINKA");
		Lozinka.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lozinka.setHorizontalAlignment(SwingConstants.CENTER);
		Lozinka.setBounds(10, 225, 135, 17);
		contentPane.add(Lozinka);
		
		txtLozinka = new JPasswordField();
		txtLozinka.setBounds(197, 224, 147, 18);
		contentPane.add(txtLozinka);
		
		txtKorisnickoime = new JTextField();
		txtKorisnickoime.setBounds(197, 179, 147, 18);
		contentPane.add(txtKorisnickoime);
		txtKorisnickoime.setColumns(10);
		
				
		
		JButton btnLogin = new JButton("ULOGUJ SE");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			      
				String username = txtKorisnickoime.getText();
			    char[] password = txtLozinka.getPassword();
			    String pw = "";
			    for(int i = 0; i < password.length; i++ ) {
			    	pw+=password[i];
			    }
				
				String sqlA = "select * from korisnik where username = '"+username+"' and password = '"+pw+"' AND Role = 'Admin'";
				String sql = "select * from korisnik where username = '"+username+"' and password = '"+pw+"'";
				Connection connect = Helper.DBSetup();
				
				try{			      				      				      

				      Statement stm = connect.createStatement();
				      ResultSet rsA = stm.executeQuery(sqlA);
				     
				      if(rsA.next()) {
				    	 
				      }
				      //User interface
				      else {
					     ResultSet rs = stm.executeQuery(sql);			     
					     
					     if(rs.next()) {	
					    	 
						     ID = rs.getInt("ID");
						     
					    	 dispose();
					    	 GlavniMeni KorisnikPage = new GlavniMeni(ID);
					    	 KorisnikPage.setVisible(true);
					    	 GlavniMeni CW = (GlavniMeni) Helper.CenterWindow(KorisnikPage);
					    	 
				    	  }
				    	  else {
				    		  JOptionPane.showMessageDialog(null,"Greska!");
							  
							  KorisnickoIme.setText("");
							  Lozinka.setText("");
				    	  }
				      }
				      	
				      connect.close();
				    }
					catch(Exception eis){ System.out.println(e);}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(10, 282, 135, 25);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTRUJ SE");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		    	Registracija RegPage = new Registracija();
		    	RegPage.setVisible(true);
		    	Registracija CW = (Registracija) Helper.CenterWindow(RegPage);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegister.setBounds(197, 284, 147, 23);
		contentPane.add(btnRegister);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/resources/hamburger140.png")));
		lblNewLabel_1.setBounds(107, 11, 140, 89);
		contentPane.add(lblNewLabel_1);
		
		
	
	}
	
	public Login() {
		setForeground(Color.BLACK);
		setTitle("Login");
		setResizable(false);
		initComponents();
		
	}
}