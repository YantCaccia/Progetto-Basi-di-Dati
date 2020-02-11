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

public class InserisciCentroSportivo extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3626901195935356267L;

	public InserisciCentroSportivo(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(6,1));
		
		JPanel nomePanel = new JPanel(new GridLayout(2,1));
		JLabel nomelabel = new JLabel("Nome:");
		JTextField nomefield = new JTextField();
		nomePanel.add(nomelabel);
		nomePanel.add(nomefield);
		mainPanel.add(nomePanel);
		
		JPanel indPanel = new JPanel(new GridLayout(2, 1));
		
		JLabel indlabel = new JLabel("Indirizzo:");
		
		JPanel inputindPanel = new JPanel(new GridLayout(1,3));
		JTextField viafield = new JTextField("Via");
		JTextField cittafield = new JTextField("Citta'");
		JTextField civicofield = new JTextField("Civico");
		inputindPanel.add(viafield);
		inputindPanel.add(cittafield);
		inputindPanel.add(civicofield);
		
		indPanel.add(indlabel);
		indPanel.add(inputindPanel);
		
		mainPanel.add(indPanel);
		
		JPanel campiPanel = new JPanel(new GridLayout(4,1));
		JLabel campilabel = new JLabel("Numero campi:");
		JTextField campifield = new JTextField();
		JLabel tipocampilabel = new JLabel("Tipologia campi:");
		JTextField tipocampifield = new JTextField();
		campiPanel.add(campilabel);
		campiPanel.add(campifield);
		campiPanel.add(tipocampilabel);
		campiPanel.add(tipocampifield);
		mainPanel.add(campiPanel);
		
		JPanel spoPanel = new JPanel(new GridLayout(4,1));
		JLabel spolabel = new JLabel("Numero spogliatoi:");
		JTextField spofield = new JTextField();
		JLabel doccelabel = new JLabel("Numero docce per ciascun spogliatoio:");
		JTextField doccefield = new JTextField();
		spoPanel.add(spolabel);
		spoPanel.add(spofield);
		spoPanel.add(doccelabel);
		spoPanel.add(doccefield);
		mainPanel.add(spoPanel);
		
		JComboBox<String> scalcio = new JComboBox<String>();
		String sql2 = " SELECT partitaIva, nome FROM scuolacalcio";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);
			while(rs.next()) {
				scalcio.addItem(rs.getString(1) + " - " + rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mainPanel.add(scalcio);
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(e->{
			String pIva = ((String) scalcio.getSelectedItem()).split(" ", 2)[0];
			String nome = nomefield.getText();
			String via = viafield.getText();
			String citta = cittafield.getText();
			int civico = Integer.parseInt(civicofield.getText());
			int nCampi = Integer.parseInt(campifield.getText());
			String tipoTerreno = tipocampifield.getText();
			int nSpogliatoi = Integer.parseInt(spofield.getText());
			int nDocce = Integer.parseInt(doccefield.getText());
			executeSQL(con, pIva, nome, via, citta, civico, nCampi, tipoTerreno, nSpogliatoi, nDocce);
			dispose();
		});
		mainPanel.add(ok);
		
		add(mainPanel);
		setSize(500,500);
		setVisible(true);
		setTitle("Inserisci Centro Sportivo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public static void executeSQL(Connection con, String pIva, String nome, String via, String citta, int civico, int nCampi, String tipoTerreno, int nSpogliatoi, int nDocce) {
		
		try {
			//Creo il nuovo centro (nome, via, citta, civico)
			String creaCentro = "INSERT INTO centrosportivo VALUES (?,?,?,?)";
			PreparedStatement st1 = con.prepareStatement(creaCentro);
			st1.setString(1, nome);
			st1.setString(2, via);
			st1.setString(3, citta);
			st1.setInt(4, civico);
			st1.executeUpdate();
			//Creo i campi
			for(int i = 0; i < nCampi; i++) {
				String creaCampo = "INSERT INTO campo(tipoTerreno, centrosportivo) VALUES (?, ?)";
				PreparedStatement st2 = con.prepareStatement(creaCampo);
				st2.setString(1, tipoTerreno);
				st2.setString(2, nome);
				st2.executeUpdate();
			}
			//Creo gli spogliatoi
			for(int i = 0; i < nSpogliatoi; i++) {
				String creaSpogliatoi = "INSERT INTO spogliatoio(nDocce, centrosportivo) VALUES (?,?)";
				PreparedStatement st3 = con.prepareStatement(creaSpogliatoi);
				st3.setInt(1, nDocce);
				st3.setString(2, nome);
				st3.executeUpdate();
			}
			//Setto questo centro sportivo come il centro della scuola calcio indicata dal parametro pIva
			String cambiaCentro = "UPDATE scuolacalcio " + 
					"SET centrosportivo = ? " + 
					"WHERE scuolacalcio.partitaIva=? ";
			PreparedStatement st4 = con.prepareStatement(cambiaCentro);
			st4.setString(1, nome);
			st4.setString(2, pIva);
			st4.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

}
