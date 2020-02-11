package mainPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import framePack.*;

public class Tester {

	public static void main(String[] args) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/scuolecalcioDB?serverTimezone=UTC";

			Connection con = DriverManager.getConnection(url, "userscuolecalcio", "psw");
			
			System.out.println("Applicazione tester\n");
			
			System.out.println("Testiamo l'inserimento di un centro sportivo "
					+ "con i seguenti parametri:\n"
					+ "Kennedy - Via della sapienza, 55 - 3 campi in cemento "
					+ "e 2 spogliatoi da 3 docce l'uno.\n"
					+ "Lo assegnamo alla scuola calcio Recca");
			InserisciCentroSportivo.executeSQL(con, "77253160640", "Kennnnedy", "Via della Sapienza", "Giugliano" , 55, 3, "Cemento", 2, 3);
			DBTablePrinter.printTable(con, "centrosportivo");
			
			System.out.println("Testiamo l'inserimento di un allenatore "
					+ "con i seguenti parametri:\n"
					+ "Maurizio Battista - BTTMRZ63A01F839A - assegnato alla squadra Juniores Recca B\n"
					+ "del presidente MTANDR99T31F839O");
			InserisciAllenatore.executeSQL(con, "BTTMRZ63A01F839A", "Maurizio", "Battista", "MTANDR99T31F839O", "Juniores Recca B");
			DBTablePrinter.printTable(con, "squadra");
			DBTablePrinter.printTable(con, "dipendenti");
			
			System.out.println("Testiamo l'inserimento di un medico con i seguenti parametri:\n"
					+ "Leonardo di Caprio - DCPLNR63A01F839A - nella scuola calcio con p.Iva 77253160640");
			InserisciMedico.executeSQL(con, "Leonardo Di Caprio", "DCPLNR63A01F839A", "77253160640");
			DBTablePrinter.printTable(con, "medicosportivo");
			DBTablePrinter.printTable(con, "collabora");
			
			System.out.println("Testiamo l'inserimento di una retta con i seguenti parametri:\n"
					+ "65 euro, mensile, del bambino con codice fiscale CCCNTN05A01F839T");
			InserisciRetta.executeSQL(con, "CCCNTN05A01F839T", 65, false, 0);
			DBTablePrinter.printTable(con, "retta");
			DBTablePrinter.printTable(con, "mensile");
			
			System.out.println("Inseriamo una squadra con i seguenti parametri:\n"
					+ "Esordienti Recca - categoria Esordienti - 17 anni - all'allenatore DMILGU63A01F839A");
			InserisciSquadra.executeSQL(con, "Esordienti Recca", 17, "Esordienti", "DMILGU63A01F839A");
			DBTablePrinter.printTable(con, "squadra");
			
			System.out.println("Inseriamo un torneo con i seguenti parametri:\n"
					+ "Torneo Estivo, edizione 2020, premio Medaglia in Lega, vinto da Esordienti Recca");
			InserisciTorneo.executeSQL(con, "Torneo Estivo", 2020, "Medaglia in lega", "Esordienti Recca");
			DBTablePrinter.printTable(con, "torneo");
			
			System.out.println("Inseriamo un bambino con i seguenti parametri:\n"
					+ "Alessandro de Rosa - 10 anni - nella squadra Juniores Recca della scuola calcio 77253160640");
			IscriviBambinoFrame.executeSQL(con, "DRSLSS05A01F839T", "Alessandro De Rosa", 10, "77253160640", "Juniores Recca", 50, 0, false);
			DBTablePrinter.printTable(con, "bambino");
			
			System.out.println("Testiamo la stampa del numero di bambini dell'allenatore Luigi Di Maio");
			ResultSet rs = StampaBimbiAllenatore.executeSQL(con, "Luigi", "Di Maio");
			DBTablePrinter.printResultSet(rs);
			
			System.out.println("Testiamo la stampa del numero di bambini visitati dal medico con codice fiscale GCMPCA63A01F839A");
			rs = StampaBimbiMedico.executeSQL(con, "GCMPCA63A01F839A");
			DBTablePrinter.printResultSet(rs);
			
			System.out.println("Testiamo la stampa delle info dei bambini della squadra Juniores Recca B");
			rs = StampaBimbiSquadra.executeSQL(con, "Juniores Recca B");
			DBTablePrinter.printResultSet(rs);
			
			System.out.println("Testiamo la stampa delle info sulle varie edizioni del Torneo Natalizio");
			rs = StampaEdizioniTorneo.executeSQL(con, "Torneo Natalizio");
			DBTablePrinter.printResultSet(rs);
			
			System.out.println("Testiamo la stampa delle entrate della scuola calcio Recca ");
			rs = StampaEntrate.executeSQL(con, "Recca");
			DBTablePrinter.printResultSet(rs);
			
			System.out.println("Testiamo la stampa del numero di fatture emesse dalla scuola calcio Recca ");
			rs = StampaNumeroFatture.executeSQL(con, "77253160640");
			DBTablePrinter.printResultSet(rs);
			
			System.out.println("Testiamo la stampa delle info dei preparatori della squadra Juniores Recca B");
			rs = StampaPreparatori.executeSQL(con, "Juniores Recca B");
			DBTablePrinter.printResultSet(rs);
			
			System.out.println("Testiamo la stampa dei tornei vinti dalla squadra Juniores Recca B");
			rs = StampaTorneiSquadra.executeSQL(con, "Juniores Recca B");
			DBTablePrinter.printResultSet(rs);
			
			
		}
		catch (Exception e) {
			System.out.println("Errore:");
			System.out.println(e);
		}

	}

}
