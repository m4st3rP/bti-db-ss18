package customerManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBMSManager {
	private Connection con;

	public DBMSManager() {
	}

	public boolean connectToDatabase(String user, String password) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@ora14.informatik.haw-hamburg.de:1521:inf14";
		try {
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

	public int addCustomer(int kdnr, String name, String street, int housenr, String town) {
		Statement statement;
		int amount = 0;
		try {
			statement = con.createStatement();
			String query = "INSERT INTO Kunde VALUES (" + kdnr + ",'" + name + "','" + street + "'," + housenr + ",'" + town
					+ "')";
			amount = statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return amount;
	}

	public ObservableList<Kunde> getAllCustomers(boolean OnlyWhoOrderedSomething) {
		ObservableList<Kunde> list = FXCollections.observableArrayList();
		int kdnr, housenr;
		String name, street, town;

		try {
			Statement statement;
			String query;

			statement = con.createStatement();
			if (OnlyWhoOrderedSomething) {
				query = "SELECT * FROM KUNDE WHERE EXISTS (SELECT * FROM BESTELLUNG WHERE KUNDENNUMMER = KUNDE.KUNDENNUMMER)";
			} else {
				query = "SELECT * FROM KUNDE";
			}
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				kdnr = result.getInt(1);
				name = result.getString(2);
				street = result.getString(3);
				housenr = result.getInt(4);
				town = result.getString(5);
				list.add(new Kunde(kdnr, name, street, housenr, town));
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
