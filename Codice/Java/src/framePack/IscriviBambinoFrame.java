package framePack;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class IscriviBambinoFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7828239879578366643L;

	public IscriviBambinoFrame(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(7,1));
		
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
		
		JPanel etapanel = new JPanel(new GridLayout(2,1));
		JLabel etalabel = new JLabel("Eta'");
		JTextField etafield = new JTextField();
		etapanel.add(etalabel);
		etapanel.add(etafield);
		mainPanel.add(etapanel);
		
		JPanel scpanel = new JPanel(new GridLayout(2,1));
		JLabel sclabel = new JLabel("Scuola Calcio");
		JTextField scfield = new JTextField();
		scpanel.add(sclabel);
		scpanel.add(scfield);
		mainPanel.add(scpanel);
		
		JPanel sqpanel = new JPanel(new GridLayout(2,1));
		JLabel sqlabel = new JLabel("Squadra");
		JTextField sqfield = new JTextField();
		sqpanel.add(sqlabel);
		sqpanel.add(sqfield);
		mainPanel.add(sqpanel);
		
		JPanel rettapanel = new JPanel(new GridLayout(4,1));
		JLabel rettalabel = new JLabel("Retta");
		
		JPanel sceltapanel = new JPanel(new GridLayout(1,2));
		ButtonGroup bg = new ButtonGroup();
		JRadioButton b1 = new JRadioButton("Annuale");
		sceltapanel.add(b1);
		bg.add(b1);
		b1.setSelected(true);
		JRadioButton b2 = new JRadioButton("Mensile");
		sceltapanel.add(b2);
		bg.add(b2);
		
		JTextField rettafield = new JTextField();
		rettafield.setText("Importo");
		JTextField scontofield = new JTextField();
		scontofield.setText("Sconto (se retta mensile viene ignorato)");
		
		rettapanel.add(rettalabel);
		rettapanel.add(sceltapanel);
		rettapanel.add(rettafield);
		rettapanel.add(scontofield);
		mainPanel.add(rettapanel);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(e->{
			String nome = nomefield.getText();
			String codFis = codfisfield.getText();
			int eta = Integer.parseInt(etafield.getText());
			String squadra = sqfield.getText();
			String scuolacalcio = "Recca";
			String pIva = null;
			//Abbiamo il nome della scuola calcio, ma ci serve la partita Iva. Prendiamola:
			/**/
			try {
				String cercaSc = "SELECT partitaIva FROM scuolacalcio WHERE nome = ?";
				PreparedStatement st = con.prepareStatement(cercaSc);
				st.setString(1, scuolacalcio);
				ResultSet rs = st.executeQuery();
				rs.next();
				pIva = rs.getString("partitaIva");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/**/
			try {
				String creaBimbo = "INSERT INTO bambino VALUES(?, ?, ?, ?, ?)";
				PreparedStatement st = con.prepareStatement(creaBimbo);
				st.setString(1, codFis);
				st.setString(2, nome);
				st.setInt(3, eta);
				st.setString(4, pIva);
				st.setString(5, squadra);
				st.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Crea una retta e incrementa numero fatture emesse
			try {
				String creaRetta = "INSERT INTO retta(importo, bambino) VALUES(?, ?)";
				PreparedStatement st = con.prepareStatement(creaRetta);
				st.setInt(1, Integer.parseInt(rettafield.getText()));
				st.setString(2, codFis);
				st.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				String aggiornaFatture = "UPDATE scuolacalcio " + 
						"SET nFattureEmesse = nFattureEmesse+1 " + 
						"WHERE partitaIva IN (" + 
						"SELECT scuolacalcio " + 
						"FROM bambino " + 
						"WHERE bambino.codFis=?)";
				PreparedStatement st = con.prepareStatement(aggiornaFatture);
				st.setString(1, codFis);
				st.executeUpdate();
			}
			catch(Exception e4) {
				e4.printStackTrace();
			}
			
			//Prendi il progressivo dell'ultima retta e collegalo ad una nuova mensile o annuale
			int progressivo;
			try {
				Statement st = con.createStatement();
				String sql = "SELECT * FROM retta ORDER BY progressivo DESC";
				ResultSet rs = st.executeQuery(sql);
				rs.next();
				progressivo = rs.getInt(1);
				
				if(b1.isSelected()) {
					//annuale
					String creaRetta = "INSERT INTO annuale VALUES(?, ?)";
					PreparedStatement st1 = con.prepareStatement(creaRetta);
					st1.setInt(1, progressivo);
					st1.setInt(2, Integer.parseInt(scontofield.getText()));
					st1.executeUpdate();
				}
				else {
					//mensile
					String creaRetta = "INSERT INTO mensile VALUES(?)";
					PreparedStatement st1 = con.prepareStatement(creaRetta);
					st1.setInt(1, progressivo);
					st1.executeUpdate();
				}
			}
			catch (Exception e2) {
				e2.getStackTrace();
			}
			
			
			dispose();
		});
		mainPanel.add(okButton);
		
		add(mainPanel);
		setVisible(true);
		setSize(600,600);
		setTitle("Iscrivi Bambino");
		
	}
	
	
	
}
