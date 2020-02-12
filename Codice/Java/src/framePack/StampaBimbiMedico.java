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

public class StampaBimbiMedico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -230607914628058359L;
	
	public StampaBimbiMedico(Connection con) {
		
		
JPanel mainPanel = new JPanel(new GridLayout(2,1));
		
		JPanel sceltaPanel = new JPanel(new GridLayout(2,1));
		
		JLabel sceltaLabel = new JLabel("Scegli medico:");
		sceltaPanel.add(sceltaLabel);
		
		JComboBox<String> tendina = new JComboBox<String>();
		String sql2 = " SELECT nome FROM medicoSportivo";
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
			//trovo il codice fiscale del medico
			String cercaCodFis = "SELECT codFis FROM medicoSportivo WHERE nome=?";
			String codFis = null;
			try {
				PreparedStatement st1 = con.prepareStatement(cercaCodFis);
				st1.setString(1, (String)tendina.getSelectedItem());
				ResultSet rs = st1.executeQuery();
				rs.next();
				codFis = rs.getString(1);
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
			ResultSet rs = executeSQL(con, codFis);
			try {
				rs.next();
				JOptionPane.showMessageDialog(null, "Numero bambini: " + Integer.toString(rs.getInt(1)));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//trovo numero dei bambini
			/*String cercanumero = "select count(*) " + 
					"from medicoSportivo, visita " + 
					"where visita.medico=medicoSportivo.codFis && medicoSportivo.codFis=?";
			try {
				PreparedStatement st1 = con.prepareStatement(cercanumero);
				st1.setString(1, codFis);
				ResultSet rs = st1.executeQuery();
				rs.next();
				JOptionPane.showMessageDialog(null, "Numero bambini: " + Integer.toString(rs.getInt(1)));
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}*/
		});
		
		mainPanel.add(sceltaPanel);
		mainPanel.add(ok);
		
		add(mainPanel);
		setSize(400,400);
		setVisible(true);
		setTitle("Stampa numero bambini per un medico");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		
	}
	
	public static ResultSet executeSQL(Connection con, String codFis) {
		ResultSet rs = null;
		String cercanumero = "select count(*) " + 
				"from medicoSportivo, visita " + 
				"where visita.medico=medicoSportivo.codFis && medicoSportivo.codFis=?";
		try {
			PreparedStatement st1 = con.prepareStatement(cercanumero);
			st1.setString(1, codFis);
			rs = st1.executeQuery();
			
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		return rs;
	}

}
