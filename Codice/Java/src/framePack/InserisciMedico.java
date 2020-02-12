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

public class InserisciMedico extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6737066608265791165L;

	public InserisciMedico(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(4,1));
		
		JPanel nomePanel = new JPanel(new GridLayout(2,1));
		JLabel nomeLabel = new JLabel("Nome");
		nomePanel.add(nomeLabel);
		JTextField nomeField = new JTextField();
		nomePanel.add(nomeField);
		mainPanel.add(nomePanel);
		
		JPanel codfisPanel = new JPanel(new GridLayout(2,1));
		JLabel codfisLabel = new JLabel("Codice Fiscale");
		codfisPanel.add(codfisLabel);
		JTextField codfisField = new JTextField();
		codfisPanel.add(codfisField);
		mainPanel.add(codfisPanel);
		
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
		mainPanel.add(tendina);
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(e->{
			String nome = nomeField.getText();
			String codFis = codfisField.getText();
			//Troviamo la pIva della scuola calcio
			String pIva = null;
			try {
				String creaDip = "SELECT partitaIva FROM scuolacalcio WHERE nome=?";
				PreparedStatement st1 = con.prepareStatement(creaDip);
				st1.setString(1, (String)tendina.getSelectedItem());
				ResultSet rs = st1.executeQuery();
				rs.next();
				pIva = rs.getString(1);
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			executeSQL(con, nome, codFis, pIva);
			//Ora creiamo il medico
			/*try {
				String creaMed = "INSERT INTO medicoSportivo VALUES(?,?)";
				PreparedStatement st1 = con.prepareStatement(creaMed);
				st1.setString(1, nomeField.getText());
				st1.setString(2, codfisField.getText());
				st1.executeUpdate();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			//Ora colleghiamo il medico alla squadra
			try {
				String creaCollabora = "INSERT INTO collabora VALUES (?,?)";
				PreparedStatement st1 = con.prepareStatement(creaCollabora);
				st1.setString(1, pIva);
				st1.setString(2, codfisField.getText());
				st1.executeUpdate();
			} catch(Exception e1) {
				e1.printStackTrace();
			}*/
			dispose();
		});
		mainPanel.add(ok);
		
		add(mainPanel);
		setVisible(true);
		setSize(400,400);
		setTitle("Insersci Medico");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public static void executeSQL(Connection con, String nome, String codFis, String pIva) {
		//Creaimo il medico
		try {
			String creaMed = "INSERT INTO medicoSportivo VALUES(?,?)";
			PreparedStatement st1 = con.prepareStatement(creaMed);
			st1.setString(1, nome);
			st1.setString(2, codFis);
			st1.executeUpdate();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		//Ora colleghiamo il medico alla squadra
		try {
			String creaCollabora = "INSERT INTO collabora VALUES (?,?)";
			PreparedStatement st1 = con.prepareStatement(creaCollabora);
			st1.setString(1, pIva);
			st1.setString(2, codFis);
			st1.executeUpdate();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
	
	}
	
}
