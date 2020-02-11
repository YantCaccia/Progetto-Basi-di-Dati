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
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StampaTorneiSquadra extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6337498573961009375L;
	
	public StampaTorneiSquadra(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(3,1));
		
		JPanel sceltaPanel = new JPanel(new GridLayout(2,1));
		
		JLabel sceltaLabel = new JLabel("Scegli squadra:");
		sceltaPanel.add(sceltaLabel);
		
		JComboBox<String> tendina = new JComboBox<String>();
		String sql2 = " SELECT nome FROM squadra";
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
		
		JTextArea ta = new JTextArea();
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(e->{
			ta.setText("");
			String cercaTornei = "SELECT torneo.nome, edizione, premio " + 
					"FROM squadra, torneo " + 
					"WHERE torneo.squadra=squadra.nome && squadra.nome=?";
			try {
				PreparedStatement st1 = con.prepareStatement(cercaTornei);
				st1.setString(1, (String)tendina.getSelectedItem());
				ResultSet rs = st1.executeQuery();
				while(rs.next()) {
					ta.append(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getString(3) + "\n");
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
		setTitle("Stampa tornei di una squadra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

}
