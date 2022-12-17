package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;

import common.UniversalHelper;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KorpaMeni extends JFrame {
	
	static UniversalHelper Helper = new UniversalHelper();
	
	private JPanel contentPane;
	private JTable table;
	private JTextField txtCena;
	private JTextField txtBrArt;
	public float UkCena;
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

	public void Cena() {
		Connection connect=Helper.DBSetup();
		String query = "SELECT SUM(k.kolicina) as BrArt, SUM(m.cena * k.kolicina) as Cena FROM korpa k, meni m WHERE m.ID = k.id_h ";
		try {
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				UkCena = rs.getFloat("Cena");
				txtCena.setText(rs.getString("Cena"));
				txtBrArt.setText(rs.getString("BrArt"));
			}
			
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
	}
	
	
	public void PrikazKorpe() {
		Connection connect=Helper.DBSetup();
		
		String query = "SELECT k.ID ,m.naziv,sum(m.cena * k.kolicina) as Cena, k.kolicina FROM meni m, korpa k WHERE m.ID = k.id_h group by k.ID, m.naziv, k.kolicina; ";
		try {	
			table.setModel(new DefaultTableModel());
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			int columns = rsmd.getColumnCount();
			String[] colName = new String[columns];
			for(int i = 0; i<columns; i++) {
			colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			while(rs.next()) {
			model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
			}			
		
			connect.close();
	
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
	}
	
	
	public void initComponents() {

		setBounds(100, 100, 421, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("KORPA");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 11, 385, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total artikala:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 549, 110, 34);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Total cena:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setBounds(200, 548, 110, 34);
		contentPane.add(lblNewLabel_3_1);
		
		JButton btnNewButton_1 = new JButton("KUPI");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PlacanjeMeni placanjemeni = new PlacanjeMeni(UkCena, ID);
				placanjemeni.setVisible(true);
				PlacanjeMeni Center = (PlacanjeMeni) Helper.CenterWindow(placanjemeni);
			}
		});
		btnNewButton_1.setBounds(279, 592, 89, 23);
		contentPane.add(btnNewButton_1);
		
		txtBrArt = new JTextField("");
		txtBrArt.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtBrArt.setForeground(Color.BLACK);
		txtBrArt.setBorder(null);
		txtBrArt.setHorizontalAlignment(SwingConstants.CENTER);
		txtBrArt.setEditable(false);
		txtBrArt.setBounds(102, 550, 68, 34);
		contentPane.add(txtBrArt);
		
		txtCena = new JTextField("");
		txtCena.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCena.setForeground(Color.BLACK);
		txtCena.setBorder(null);
		txtCena.setEditable(false);
		txtCena.setHorizontalAlignment(SwingConstants.CENTER);
		txtCena.setBounds(296, 549, 56, 34);
		contentPane.add(txtCena);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 385, 493);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1_1 = new JButton("Otkazi porudzbinu");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connect=Helper.DBSetup();
				String SQL = "DELETE FROM korpa";
				try {
				Statement st = connect.createStatement(); 
				st.execute(SQL);
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				dispose();
				OtkazPorudzbine otkazpor =  new OtkazPorudzbine(ID);
				otkazpor.setVisible(true);
				OtkazPorudzbine Center = (OtkazPorudzbine) Helper.CenterWindow(otkazpor);
				
			}	
		});
		btnNewButton_1_1.setBounds(20, 592, 136, 23);
		contentPane.add(btnNewButton_1_1);
	
	}
	
	
	public KorpaMeni(int ID) {
		this.ID = ID;
		setTitle("Korpa");
		initComponents();
		PrikazKorpe();
		Cena();
	}
}
