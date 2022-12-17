package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import common.UniversalHelper;
import javax.swing.ImageIcon;


public class HranaMeni extends JFrame {
	
	static UniversalHelper Helper = new UniversalHelper();
	private JPanel contentPane;
	private int idForInsert;
	private JTable tableHrana;
	private JSpinner spinner;
	private JComboBox comboBox;
	public int brKom;
	public int idH;
	private int ID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HranaMeni frame = new HranaMeni(0);
					frame.setVisible(true);
					HranaMeni Center = (HranaMeni) Helper.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	public void TabelaHrana() {
		
		Connection connect=Helper.DBSetup();
		try {
		String vrsta=comboBox.getSelectedItem().toString();
		if(vrsta=="HRANA") {
			String query = "SELECT ID,naziv,cena FROM meni WHERE vrsta='H'";
			tableHrana.setModel(new DefaultTableModel());
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tableHrana.getModel();
			int columns = rsmd.getColumnCount();
			String[] colName = new String[columns];
			for(int i = 0; i<columns; i++) {
			colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			while(rs.next()) {
			model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3)});
			}
			
			
		}
		
		else if(vrsta=="PICA") {
			String query = "SELECT ID,naziv,cena FROM meni WHERE vrsta='P'";
			tableHrana.setModel(new DefaultTableModel());
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tableHrana.getModel();
			int columns = rsmd.getColumnCount();
			String[] colName = new String[columns];
			for(int i = 0; i<columns; i++) {
			colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			while(rs.next()) {
			model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3)});
			}
			
			
		}
		
		else if(vrsta=="DEZERTI") {
			String query = "SELECT ID,naziv,cena FROM meni WHERE vrsta='D'";
			tableHrana.setModel(new DefaultTableModel());
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tableHrana.getModel();
			int columns = rsmd.getColumnCount();
			String[] colName = new String[columns];
			for(int i = 0; i<columns; i++) {
			colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			while(rs.next()) {
			model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3)});
			}
		}	
		connect.close();
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		}
	
	public void initComponents() {

		setBounds(100, 100, 848, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MENI");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 24, 812, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Dodaj u korpu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				brKom=(Integer)spinner.getValue();
				
				String SQLInsert = "INSERT INTO korpa (id_h, kolicina) VALUES ("+idForInsert+", "+brKom+")";
				Connection connect=Helper.DBSetup();
				try {
					Statement STM=connect.createStatement();
					STM.execute(SQLInsert);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(643, 121, 157, 40);
		contentPane.add(btnNewButton);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 999, 1));
		spinner.setBounds(438, 123, 195, 40);
		contentPane.add(spinner);
		
		JButton btnKorpa = new JButton("");
		btnKorpa.setIcon(new ImageIcon(HranaMeni.class.getResource("/resources/Korpa110.png")));
		btnKorpa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				KorpaMeni korpameni = new KorpaMeni(ID);
				korpameni.setVisible(true);
				KorpaMeni Center = (KorpaMeni) Helper.CenterWindow(korpameni);
			}
		});
		btnKorpa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnKorpa.setBounds(550, 298, 110, 110);
		contentPane.add(btnKorpa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 351, 356);
		contentPane.add(scrollPane);
		
		tableHrana = new JTable();
		scrollPane.setViewportView(tableHrana);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"HRANA", "PICA", "DEZERTI"}));
		comboBox.setBounds(10, 66, 176, 29);
		contentPane.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("IZABERI");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaHrana();
			}
		});
		btnNewButton_1.setBounds(196, 66, 165, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Korpa");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(550, 264, 110, 23);
		contentPane.add(lblNewLabel_1);
		
		ListSelectionModel rowSelectionModel = tableHrana.getSelectionModel();

		 rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		if(lsm.isSelectionEmpty()) {
		//JOptionPane.showMessageDialog(null, "No Selection");
		}
		else {
		int selRow = tableHrana.getSelectedRow();
		idForInsert = Integer.parseInt(tableHrana.getModel().getValueAt(selRow, 0).toString());
		//JOptionPane.showMessageDialog(null,String.valueOf(idForInsert));
		}
		}

		 });
	
	}
	public HranaMeni(int ID) {
		this.ID = ID;
		setTitle("Jelovnik");
		initComponents();
		TabelaHrana();
	}
}
