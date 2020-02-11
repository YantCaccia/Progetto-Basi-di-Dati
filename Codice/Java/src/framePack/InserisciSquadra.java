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

public class InserisciSquadra extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2573508806732377744L;

	public InserisciSquadra(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(5,1));
		
		JPanel nomepanel = new JPanel(new GridLayout(2,1));
		JLabel nomelabel = new JLabel("Nome");
		JTextField nomefield = new JTextField();
		nomepanel.add(nomelabel);
		nomepanel.add(nomefield);
		mainPanel.add(nomepanel);
		
		JPanel etapanel = new JPanel(new GridLayout(2,1));
		JLabel etalabel = new JLabel("Eta' bambini");
		JTextField etafield = new JTextField();
		etapanel.add(etalabel);
		etapanel.add(etafield);
		mainPanel.add(etapanel);
		
		JPanel catpanel = new JPanel(new GridLayout(2,1));
		JLabel catlabel = new JLabel("Categoria");
		JTextField catfield = new JTextField();
		catpanel.add(catlabel);
		catpanel.add(catfield);
		mainPanel.add(catpanel);
		
		JComboBox<String> allenatore = new JComboBox<String>();
		String sql2 = " SELECT nome, cognome FROM allenatore,dipendenti WHERE allenatore.dipendente=dipendenti.codFis";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			while(rs.next()) {
				allenatore.addItem(rs.getString(1) + " " + rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mainPanel.add(allenatore);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(e->{
			
			try {
				String nomecognome = (String) allenatore.getSelectedItem();
				String[] arr = nomecognome.split(" ", 2);
				String nome = arr[0];
				String cognome = arr[1];
				//Cerco il codice fiscale dell'allenatore
				String cercaAll = "SELECT codFis FROM dipendenti WHERE nome=? && cognome=?";
				PreparedStatement st = con.prepareStatement(cercaAll);
				st.setString(1, nome);
				st.setString(2, cognome);
				ResultSet rs = st.executeQuery();
				rs.next();
				String codFisAll = rs.getString(1);				
				//Creo la nuova squadra
				String creaSq = "INSERT INTO squadra VALUES (?,?,?,?);";
				PreparedStatement st1 = con.prepareStatement(creaSq);
				st1.setString(1, nomefield.getText());
				st1.setInt(2, Integer.parseInt(etafield.getText()));
				st1.setString(3, catfield.getText());
				st1.setString(4, codFisAll);
				st1.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
						
			dispose();
		});
		mainPanel.add(okButton);
		
		add(mainPanel);
		setVisible(true);
		setSize(600,600);
		setTitle("Inserisci Squadra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		
	}
}
