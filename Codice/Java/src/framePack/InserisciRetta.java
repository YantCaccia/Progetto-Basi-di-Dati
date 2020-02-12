package framePack;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class InserisciRetta extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7811712427411216L;

	public InserisciRetta(Connection con) {
		
		JPanel mainpanel = new JPanel(new GridLayout(5,1));
		
		JComboBox<String> bambino = new JComboBox<String>();
		String sql2 = " SELECT codFis, nome FROM bambino";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			while(rs.next()) {
				bambino.addItem(rs.getString(1) + " - " + rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mainpanel.add(bambino);
		
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
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(e->{
			//Ottengo il codiceFisclae del bambino
			String str = (String) bambino.getSelectedItem();
			String codFis = str.split(" ", 2)[0];
			int importo = Integer.parseInt(rettafield.getText());
			int sconto = Integer.parseInt(scontofield.getText());
			boolean annuale = true;
			if(b2.isSelected()) annuale = false;
			executeSQL(con, codFis, importo, annuale, sconto);
			//Crea una retta e incrementa numero fatture emesse
			/*try {
				String creaRetta = "INSERT INTO retta(importo, bambino) VALUES(?, ?)";
				PreparedStatement st = con.prepareStatement(creaRetta);
				st.setInt(1, Integer.parseInt(rettafield.getText()));
				st.setString(2, codFis);
				st.executeUpdate();
			} catch (SQLException e1) {
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
			}*/
			dispose();
		});
		
		mainpanel.add(sceltapanel);
		mainpanel.add(rettafield);
		mainpanel.add(scontofield);
		mainpanel.add(okButton);
		
		add(mainpanel);
		setSize(400,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Inserisci Retta");
		
	}
	
	public static void executeSQL(Connection con, String codFis, int importo, boolean annuale, int sconto) {
		
		try {
			String creaRetta = "INSERT INTO retta(importo, bambino) VALUES(?, ?)";
			PreparedStatement st = con.prepareStatement(creaRetta);
			st.setInt(1, importo);
			st.setString(2, codFis);
			st.executeUpdate();
		} catch (SQLException e1) {
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
			
			if(annuale) {
				//annuale
				String creaRetta = "INSERT INTO annuale VALUES(?, ?)";
				PreparedStatement st1 = con.prepareStatement(creaRetta);
				st1.setInt(1, progressivo);
				st1.setInt(2, sconto);
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
		
	}

}
