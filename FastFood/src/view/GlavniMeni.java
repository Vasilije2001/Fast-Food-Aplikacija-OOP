package view;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import common.UniversalHelper;

public class GlavniMeni extends JFrame {
	
	static UniversalHelper Helper = new UniversalHelper();
	private JPanel contentPane;
	private int ID;
	private JTextField txtImeiP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniMeni frame = new GlavniMeni(0);
					frame.setVisible(true);
					GlavniMeni Center = (GlavniMeni) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private void initComponents() {
		setTitle("Vasin Fast Food");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnHrana = new JButton("Udjite u meni");
		btnHrana.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnHrana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HranaMeni hranameni = new HranaMeni(ID);
				hranameni.setVisible(true);
				HranaMeni Center = (HranaMeni) Helper.CenterWindow(hranameni);
			}
		});
		btnHrana.setBounds(233, 408, 172, 34);
		contentPane.add(btnHrana);
		
		JLabel lblNewLabel = new JLabel("Dobrodosli u VASIN FAST FOOD!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(0, 45, 637, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(GlavniMeni.class.getResource("/resources/Logo260.png")));
		lblNewLabel_1.setBounds(158, 115, 324, 260);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("KORISNIK:  ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(0, 446, 98, 24);
		contentPane.add(lblNewLabel_2);
		
		txtImeiP = new JTextField();
		txtImeiP.setEditable(false);
		txtImeiP.setBorder(null);
		txtImeiP.setBounds(70, 445, 142, 24);
		contentPane.add(txtImeiP);
		txtImeiP.setColumns(10);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login login = new Login();
				login.setVisible(true);
				Login Center = (Login) Helper.CenterWindow(login);
			}
		});
		btnNewButton.setBounds(538, 448, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel jLabelObject = new JLabel();
	}
	
	public void PrikazKor(int ID) {
		Connection connect = Helper.DBSetup();
		String query = "SELECT CONCAT_WS(' ', Ime, prezime) as IiP FROM korisnik WHERE ID = '"+ID+"'";
		Statement stm;
		try {
			stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(query);
			if(rs.next()) {
				String IiP = rs.getString("IiP");
				txtImeiP.setText(IiP);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public GlavniMeni(int ID) {
		this.ID=ID;
		initComponents();
		PrikazKor(ID);
	}
}
