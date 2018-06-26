package multipleInserts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
			// TODO Auto-generated catch block
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
	
	public boolean addDataset() throws SQLException {
		final int NO_INSERTS = 20000;	//	Wie viele Zeilen eingefügt werden sollen
		String query = "INSERT INTO Tabelle (Col1, Col2) VALUES (?, ?)";
		
		PreparedStatement preparedStatement;
		preparedStatement = con.prepareStatement(query);
		
		for (int i=0; i < NO_INSERTS; i++) {
			preparedStatement.setString(1, String.valueOf(Math.random() * i));	//	BSP für zufallszahlen
			preparedStatement.setString(2, "Produkt " + i);						//	BSP Namensgenerierung
			preparedStatement.executeUpdate();
		}
		return true;
	}
}
