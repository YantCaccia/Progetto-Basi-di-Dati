package mainPack;
import java.sql.*;


public class Main {

	public static void main(String[] args) throws Exception {

		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/scuolecalcioDB?serverTimezone=UTC";

			Connection con = DriverManager.getConnection(url, "userscuolecalcio", "psw");
			
			MyFrame mf = new MyFrame(con);
			mf.setVisible(true);
		}
		catch (Exception e) {
			System.out.println("Errore:");
			System.out.println(e);
		}
	}

}
