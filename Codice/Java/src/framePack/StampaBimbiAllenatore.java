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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StampaBimbiAllenatore extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8963161151405488733L;

	public StampaBimbiAllenatore(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(2,1));
		
		JComboBox<String> tendina = new JComboBox<String>();
		
		//Per aggiungere i nomi degli allenatori alla tendina
		String sql = " SELECT nome,cognome FROM allenatore,dipendenti WHERE allenatore.dipendente=dipendenti.codFis";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String toDis = null;
				toDis = rs.getString("nome");
				toDis += " " + rs.getString("cognome");
				tendina.addItem(toDis);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JButton ok = new JButton("Mostra");
		ok.addActionListener(e->{
			String nomecognome = (String) tendina.getSelectedItem();
			String[] arr = nomecognome.split(" ", 2);
			String nome = arr[0];
			String cognome = arr[1];
			
			String query = "SELECT COUNT(*) FROM allenatore,dipendenti,squadra,bambino WHERE allenatore.dipendente=dipendenti.codFis &&"
					+ "squadra.allenatore=allenatore.dipendente &&"
					+ " bambino.squadra=squadra.nome && dipendenti.nome=? && dipendenti.cognome=?";
			
			try {
				PreparedStatement st = con.prepareStatement(query);
				st.setString(1, nome);
				st.setString(2, cognome);
				ResultSet rs = st.executeQuery();
				rs.next();
				JOptionPane.showMessageDialog(null, (Integer.toString(rs.getInt(1))));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		
		mainPanel.add(tendina);
		mainPanel.add(ok);
		add(mainPanel);
		setTitle("Stampa numero bambini");
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
}
