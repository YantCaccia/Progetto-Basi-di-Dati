package mainPack;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import framePack.*;
public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2122161377842820073L;
	
	public MyFrame(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(3, 5));
		
		
		JButton iscriviBambinoButton = new JButton("Iscrivi Bambino");
		iscriviBambinoButton.addActionListener(e->{
			new IscriviBambino(con);
		});
		mainPanel.add(iscriviBambinoButton);
		
		JButton stampaBimbiAllenatore = new JButton("<html>Numero di bambini<br />per allenatore</html>");
		stampaBimbiAllenatore.addActionListener(e->{
			new StampaBimbiAllenatore(con);
		});
		mainPanel.add(stampaBimbiAllenatore);
		
		JButton inserisciAllenatore = new JButton("<html>Inserisci nuovo<br />allenatore</html>");
		inserisciAllenatore.addActionListener(e->{
			new InserisciAllenatore(con);
		});
		mainPanel.add(inserisciAllenatore);
		
		JButton stampaEntrate = new JButton("Stampa entrate fatture");
		stampaEntrate.addActionListener(e->{
			new StampaEntrate(con);
		});
		mainPanel.add(stampaEntrate);
		
		JButton stampaBimbiSquadra = new JButton("<html>Nome ed eta' dei<br />bimbi di una squadra</html>");
		stampaBimbiSquadra.addActionListener(e->{
			new StampaBimbiSquadra(con);
		});
		mainPanel.add(stampaBimbiSquadra);
		
		JButton inserisciMedico = new JButton("Inserisci Medico");
		inserisciMedico.addActionListener(e->{
			new InserisciMedico(con);
		});
		mainPanel.add(inserisciMedico);
		
		JButton inserisciSquadra = new JButton("Inserisci Squadra");
		inserisciSquadra.addActionListener(e->{
			new InserisciSquadra(con);
		});
		mainPanel.add(inserisciSquadra);
		
		JButton stampaTorneiSquadra = new JButton("<html>Stampa tornei di<br />una squadra</html>");
		stampaTorneiSquadra.addActionListener(e->{
			new StampaTorneiSquadra(con);
		});
		mainPanel.add(stampaTorneiSquadra);
		
		JButton inserisciTorneo = new JButton("Inserisci Torneo");
		inserisciTorneo.addActionListener(e->{
			new InserisciTorneo(con);
		});
		mainPanel.add(inserisciTorneo);
		
		JButton stampaBimbiMedico = new JButton("Stampa bimbi di un medico");
		stampaBimbiMedico.addActionListener(e->{
			new StampaBimbiMedico(con);
		});
		mainPanel.add(stampaBimbiMedico);
		
		JButton stampaPreparatori = new JButton("<html>Stampa preparatori di<br />una squadra</html>");
		stampaPreparatori.addActionListener(e->{
			new StampaPreparatori(con);
		});
		mainPanel.add(stampaPreparatori);
		
		JButton stampaEdizioni = new JButton("Stampa edizioni torneo");
		stampaEdizioni.addActionListener(e->{
			new StampaEdizioniTorneo(con);
		});
		mainPanel.add(stampaEdizioni);
		
		JButton stampaFatture = new JButton("<html>Stampa numero fatture<br />emesse per scuola calcio</html>");
		stampaFatture.addActionListener(e->{
			new StampaNumeroFatture(con);
		});
		mainPanel.add(stampaFatture);
		
		JButton inserisciRetta = new JButton("Inserisci Retta");
		inserisciRetta.addActionListener(e->{
			new InserisciRetta(con);
		});
		mainPanel.add(inserisciRetta);
		
		JButton inserisciCentro = new JButton("Inserisci Centro Sportivo");
		inserisciCentro.addActionListener(e->{
			new InserisciCentroSportivo(con);
		});
		mainPanel.add(inserisciCentro);
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		add(mainPanel);
		setSize(1000, 1000);
		setTitle("GUI Scuola Calcio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		
	}

}
