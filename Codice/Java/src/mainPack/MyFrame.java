package mainPack;
import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import framePack.InserisciAllenatore;
import framePack.InserisciMedico;
import framePack.IscriviBambinoFrame;
import framePack.StampaBimbiAllenatore;
import framePack.StampaBimbiSquadra;
import framePack.StampaEntrate;

public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2122161377842820073L;
	
	public MyFrame(Connection con) {
		
		JPanel mainPanel = new JPanel(new GridLayout(3, 5));
		
		
		JButton iscriviBambinoButton = new JButton("Iscrivi Bambino");
		iscriviBambinoButton.addActionListener(e->{
			new IscriviBambinoFrame(con);
		});
		mainPanel.add(iscriviBambinoButton);
		
		JButton stampaBimbiAllenatore = new JButton("Numero di bambini per allenatore");
		stampaBimbiAllenatore.addActionListener(e->{
			new StampaBimbiAllenatore(con);
		});
		mainPanel.add(stampaBimbiAllenatore);
		
		JButton inserisciAllenatore = new JButton("Inserisci un nuovo allenatore");
		inserisciAllenatore.addActionListener(e->{
			new InserisciAllenatore(con);
		});
		mainPanel.add(inserisciAllenatore);
		
		JButton stampaEntrate = new JButton("Stampa entrate fatture");
		stampaEntrate.addActionListener(e->{
			new StampaEntrate(con);
		});
		mainPanel.add(stampaEntrate);
		
		JButton stampaBimbiSquadra = new JButton("Stampa nome ed eta' dei bambini di una squadra");
		stampaBimbiSquadra.addActionListener(e->{
			new StampaBimbiSquadra(con);
		});
		mainPanel.add(stampaBimbiSquadra);
		
		JButton inserisciMedico = new JButton("Inserisci Medico");
		inserisciMedico.addActionListener(e->{
			new InserisciMedico(con);
		});
		mainPanel.add(inserisciMedico);
		
		
		
		
		
		
		add(mainPanel);
		setSize(800, 800);
		setTitle("GUI Scuola Calcio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		
	}

}
