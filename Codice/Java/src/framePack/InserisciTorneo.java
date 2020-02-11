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
import javax.swing.JTextField;

public class InserisciTorneo extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6264885284090324052L;

	
	public InserisciTorneo(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(5,1));
		
		JPanel nomepanel = new JPanel(new GridLayout(2,1));
		JLabel nomelabel = new JLabel("Nome");
		JTextField nomefield = new JTextField();
		nomepanel.add(nomelabel);
		nomepanel.add(nomefield);
		mainPanel.add(nomepanel);

		JPanel edpanel = new JPanel(new GridLayout(2,1));
		JLabel edlabel = new JLabel("Edizione");
		JTextField edfield = new JTextField();
		edpanel.add(edlabel);
		edpanel.add(edfield);
		mainPanel.add(edpanel);
		
		
		
		JPanel premiopanel = new JPanel(new GridLayout(2,1));
		JLabel premiolabel = new JLabel("Premio");
		JTextField premiofield = new JTextField();
		premiopanel.add(premiolabel);
		premiopanel.add(premiofield);
		mainPanel.add(premiopanel);
		
		JComboBox<String> squadra = new JComboBox<String>();
		String sql2 = " SELECT nome FROM squadra";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			while(rs.next()) {
				squadra.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mainPanel.add(squadra);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(e->{
			
			try {				
				//Creo il torneo
				String creaAll = "INSERT INTO torneo VALUES(?,?,?,?)";
				PreparedStatement st2 = con.prepareStatement(creaAll);
				st2.setString(1, nomefield.getText());
				st2.setInt(2, Integer.parseInt(edfield.getText()));
				st2.setString(3, premiofield.getText());
				st2.setString(4, (String)squadra.getSelectedItem());
				st2.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
						
			dispose();
		});
		mainPanel.add(okButton);
		
		add(mainPanel);
		setVisible(true);
		setSize(600,600);
		setTitle("Inserisci Torneo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	
}
