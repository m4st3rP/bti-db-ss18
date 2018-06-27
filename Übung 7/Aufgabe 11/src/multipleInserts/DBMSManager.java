package multipleInserts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.sql.*;

/**
 * @author Finn-Frederik Jannsen, Philipp Schwarz
 * @version 1.0
 *
 *	SQL-Class for basic DBP Database management
 */
public class DBMSManager {
	private Connection con;

	//	on creation connection stays unchanged
	public DBMSManager() {
	}

	//	connect to HAW database and try to log in
	public boolean connectToDatabase(String user, String password) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@ora14.informatik.haw-hamburg.de:1521:inf14";
		try {
			//	set connection on success
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean disconnectFromDatabase() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Adds random data to the database
	 * @return A boolean if the process finished properly
	 * @throws SQLException
	 */
	public boolean addDataset() throws SQLException {
		final int NO_INSERTS = 20000;	//	Wie viele Zeilen eingefügt werden sollen
		String query = "INSERT INTO PRODUKT (ARTIKELNUMMER, NAME, LAGERORT, PREIS, BESTAND) VALUES (?,?,?,?,?)";
		
		PreparedStatement preparedStatement;
		preparedStatement = con.prepareStatement(query);
		
		for (int i=0; i < NO_INSERTS; i++) {
			preparedStatement.setInt(1, 6 + i);
			preparedStatement.setString(2, "Produkt " + i);						//	BSP Namensgenerierung
			preparedStatement.setString(3, "Lagerhalle " + String.valueOf((int) (Math.random() * i)));						//	BSP Namensgenerierung
			preparedStatement.setInt(4, (int) (Math.random() * 1000));	//	BSP für zufallszahlen
			preparedStatement.setInt(5, (int) (Math.random() * 50));	//	BSP für zufallszahlen
			preparedStatement.executeUpdate();
			if (i % 1000 == 0) {
				System.out.println("Es wurden " + i + " Datensätze eingefügt.");
			}
		}
		return true;
	}
}
