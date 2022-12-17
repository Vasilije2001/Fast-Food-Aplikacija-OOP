package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import common.UniversalHelper;

public class OtkazPorudzbine extends JDialog {
	
	static UniversalHelper Helper = new UniversalHelper();
	private int ID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OtkazPorudzbine dialog = new OtkazPorudzbine(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OtkazPorudzbine(int ID) {
		this.ID = ID;
		initComp();
	}
	
	private void initComp() {
		setTitle("Otkaz Porudzbine");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Porudzbina uspesno otkazana");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 72, 414, 38);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Povratak na meni");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GlavniMeni glavnimeni = new GlavniMeni(ID);
				glavnimeni.setVisible(true);
				GlavniMeni Center = (GlavniMeni) Helper.CenterWindow(glavnimeni);
			}
		});
		btnNewButton.setBounds(141, 168, 150, 23);
		getContentPane().add(btnNewButton);
	}
}
