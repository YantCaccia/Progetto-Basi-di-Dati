package mainPack;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StampaNumeroFatture extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3419574431452648144L;

	public StampaNumeroFatture(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(2,1));
		
		JPanel sceltaPanel = new JPanel(new GridLayout(2,1));
		
		JLabel sceltaLabel = new JLabel("Scegli scuola calcio:");
		sceltaPanel.add(sceltaLabel);
		
		JComboBox<String> tendina = new JComboBox<String>();
		String sql2 = " SELECT partitaIva, nome FROM scuolacalcio";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			while(rs.next()) {
				tendina.addItem(rs.getString(1) + " - " + rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sceltaPanel.add(tendina);
		
		JTextArea ta = new JTextArea();
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(e->{
			//Prendo la partitaIva della squadra
			String str = (String) tendina.getSelectedItem();
			String[] arr = str.split(" ", 2);
			String pIva = arr[0];
			//Cerco il numero della scuola calcio
			String cercaScuolaCalcio = "select nFattureEmesse from scuolacalcio where scuolacalcio.partitaIva=?";
			try {
				PreparedStatement st1 = con.prepareStatement(cercaScuolaCalcio);
				st1.setString(1, pIva);
				ResultSet rs = st1.executeQuery();
				while(rs.next()) {
					JOptionPane.showMessageDialog(null, (rs.getInt(1)));
				}
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		});
		
		mainPanel.add(sceltaPanel);
		mainPanel.add(ta);
		mainPanel.add(ok);
		
		add(mainPanel);
		setSize(400,400);
		setVisible(true);
		setTitle("Stampa numero fatture");
		
	}
	
	
	
	
}
