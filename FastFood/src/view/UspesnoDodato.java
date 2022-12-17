package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import common.UniversalHelper;

public class UspesnoDodato extends JDialog {

	static UniversalHelper Helper = new UniversalHelper();
	private int ID;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UspesnoDodato dialog = new UspesnoDodato(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UspesnoDodato(int ID) {
		this.ID = ID;
		initComp();
	}
	
	private void initComp() {
		setTitle("Uspesno Placeno");
		setBounds(100, 100, 458, 269);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Vrati se na glavni meni");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GlavniMeni glavnimeni = new GlavniMeni(ID);
				glavnimeni.setVisible(true);
				GlavniMeni Center = (GlavniMeni) Helper.CenterWindow(glavnimeni);
			}
		});
		btnNewButton.setBounds(96, 196, 259, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Porudzbzna je uspresno porucena!!!\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 31, 422, 57);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hvala na Vasoj kupovini. Posetite nas opet.\r\n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(10, 87, 422, 57);
		getContentPane().add(lblNewLabel_1);
	}

}
