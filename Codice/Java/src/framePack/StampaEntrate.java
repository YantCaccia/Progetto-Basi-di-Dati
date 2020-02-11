package framePack;

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

public class StampaEntrate extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1477804982367800803L;

	public StampaEntrate(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(2,1));
		
		JPanel sceltaPanel = new JPanel(new GridLayout(2,1));
		
		JLabel sceltaLabel = new JLabel("Scegli scuola calcio:");
		sceltaPanel.add(sceltaLabel);
		
		JComboBox<String> tendina = new JComboBox<String>();
		String sql2 = " SELECT nome FROM scuolacalcio";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			while(rs.next()) {
				tendina.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sceltaPanel.add(tendina);
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(e->{
			String creaDip = "SELECT SUM(importo) " + 
					"FROM scuolacalcio, bambino, retta " + 
					"WHERE scuolacalcio.partitaIva=bambino.scuolacalcio && retta.bambino=bambino.codFis && scuolacalcio.nome=?";
			try {
				PreparedStatement st1 = con.prepareStatement(creaDip);
				st1.setString(1, (String)tendina.getSelectedItem());
				ResultSet rs = st1.executeQuery();
				rs.next();
				JOptionPane.showMessageDialog(null, "Entrate: " + Integer.toString(rs.getInt(1)) + "$");
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		});
		
		mainPanel.add(sceltaPanel);
		mainPanel.add(ok);
		
		add(mainPanel);
		setSize(400,400);
		setVisible(true);
		setTitle("Stampa Entrate");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	
}
