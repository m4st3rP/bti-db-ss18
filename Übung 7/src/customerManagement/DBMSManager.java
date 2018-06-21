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
    
    public DBMSManager() {}
    
    public boolean connectToDatabase(String user, String password) {
        String url = "jdbc:oracle.thin:@ora14.informatik.haw-hamburg.de:1521:inf14";
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
            String query = "INSERT INTO Kunde VALUES ("+kdnr+","+name+","+street+","+housenr+","+town+");";
            amount = statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }
    
    public ObservableList<Kunde> getAllCustomers(boolean OnlyWhoOrderedSomething) {
        ObservableList<Kunde> list = FXCollections.observableArrayList();
        try {
            Statement statement;
            String query;
            
            statement = con.createStatement();
            if (OnlyWhoOrderedSomething) {
                query = "SELECT * FROM Kunden JOIN Bestellung USING (Kundennummer);";
            } else {
                query = "SELECT * FROM Kunden;";                
            }
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()) {
                list.add(new Kunde(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getString(5)));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
