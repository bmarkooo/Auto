package auto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Metode {
	
	
	private Connection konektujSe (String baza) throws SQLException {
		
		final String url = "jdbc:mysql://localhost:3306/"+baza+"?useSSL=false";
		final String user = "root";
		final String password = "marko";
		
		return DriverManager.getConnection(url, user, password);
	}

	
	public void ubaciMarku (String imeMarke, String zemlja) {
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		
		try {
			konekcija = konektujSe("automobili");
			System.out.println("Uspesna konekcija!");
			
			String query = "INSERT INTO automobili VALUES (NULL,?,?)";
			pst = konekcija.prepareStatement(query);
			  pst.setString(1, imeMarke);
			  pst.setString(2, zemlja);
			pst.execute();
			System.out.println("Uspesan unos!");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Neuspesna konekcija!");
		}finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				konekcija.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public Marke vratiMarkuAutomobila (int id) {
		
		Connection konekcija = null;
		PreparedStatement pst = null;
		ResultSet res = null;
		Marke marke = new Marke();
		
		try {
			konekcija = konektujSe("automobili");
			System.out.println("Uspesna konekcija!");
			
			String query = "SELECT * FROM automobili WHERE id_marke = ?";
			pst = konekcija.prepareStatement(query);
			    pst.setInt(1, id);
			res = pst.executeQuery();
			
			while (res.next()) {
			marke.setIdMarke(res.getInt("id_marke"));
			marke.setImeMarke(res.getString("ime_marke"));
			marke.setZemlja(res.getString("zemlja"));
			}
			return marke;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Neuspesna konekcija!");
			return null;
		}finally {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				konekcija.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
