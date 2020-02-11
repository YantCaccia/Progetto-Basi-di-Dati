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

public class InserisciAllenatore extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 655439895652399019L;
	
	public InserisciAllenatore(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(6,1));
		
		JPanel codfispanel = new JPanel(new GridLayout(2,1));
		JLabel codfislabel = new JLabel("Codice Fiscale");
		JTextField codfisfield = new JTextField();
		codfispanel.add(codfislabel);
		codfispanel.add(codfisfield);
		mainPanel.add(codfispanel);
		
		JPanel nomepanel = new JPanel(new GridLayout(2,1));
		JLabel nomelabel = new JLabel("Nome");
		JTextField nomefield = new JTextField();
		nomepanel.add(nomelabel);
		nomepanel.add(nomefield);
		mainPanel.add(nomepanel);
		
		JPanel cognpanel = new JPanel(new GridLayout(2,1));
		JLabel cognlabel = new JLabel("Cognome");
		JTextField cognfield = new JTextField();
		cognpanel.add(cognlabel);
		cognpanel.add(cognfield);
		mainPanel.add(cognpanel);
		
		JComboBox<String> scalcio = new JComboBox<String>();
		String sql2 = " SELECT nome FROM scuolacalcio";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			while(rs.next()) {
				scalcio.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mainPanel.add(scalcio);
		
		//Mostra le squadre della scuola calcio selezionata
		JComboBox<String> tendina = new JComboBox<String>();
		updateTendina(con, tendina, (String)scalcio.getSelectedItem());
		mainPanel.add(tendina);
		
		//quando seleziono una scuola calcio aggiorno la lista degli elementi
		scalcio.addActionListener(e->{
			updateTendina(con, tendina, (String)scalcio.getSelectedItem());
		});
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(e->{
			
			try {
				//Cerco il codice fiscale del presidente
				String cercaPres = "SELECT presidente FROM scuolacalcio WHERE nome=?";
				PreparedStatement st = con.prepareStatement(cercaPres);
				st.setString(1, (String)scalcio.getSelectedItem());
				ResultSet rs = st.executeQuery();
				rs.next();
				String codFisPres = rs.getString(1);				
				//Creo il nuovo dipendente
				String creaDip = "INSERT INTO dipendenti VALUES(?, ?, ?, ?)";
				PreparedStatement st1 = con.prepareStatement(creaDip);
				st1.setString(1, codfisfield.getText());
				st1.setString(2, nomefield.getText());
				st1.setString(3, cognfield.getText());
				st1.setString(4, codFisPres);
				st1.executeUpdate();
				//Creo l'allenatore
				String creaAll = "INSERT INTO allenatore VALUES(?)";
				PreparedStatement st2 = con.prepareStatement(creaAll);
				st2.setString(1, codfisfield.getText());
				st2.executeUpdate();
				//Lo assegno alla squadra
				String cambiaSquadra = "UPDATE squadra " + 
						"SET allenatore=? " + 
						"WHERE nome=?";
				PreparedStatement st3 = con.prepareStatement(cambiaSquadra);
				st3.setString(1, codfisfield.getText());
				st3.setString(2, (String)tendina.getSelectedItem());
				st3.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
						
			dispose();
		});
		mainPanel.add(okButton);
		
		add(mainPanel);
		setVisible(true);
		setSize(600,600);
		setTitle("Inserisci Allenatore");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void updateTendina(Connection con, JComboBox<String> tendina, String nomeSCalcio) {
		//Seleziono le squadre della scuola calcio selezionata e le inserisco nel menu a tendina
		String sql = "SELECT squadra.nome " + 
				"FROM squadra, dipendenti, presidente, scuolacalcio " + 
				"WHERE scuolacalcio.presidente=presidente.codFis && dipendenti.presidente=presidente.codFis " + 
				"&& squadra.allenatore=dipendenti.codFis";
		tendina.removeAllItems();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				tendina.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
