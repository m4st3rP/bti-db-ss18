package multipleInserts;

import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * @author Finn-Frederik Jannsen, Philipp Schwarz
 * @version 1.0
 *
 *	Basic Application to add random Data to our Database
 */
public class Main {
	
	public static void main(String[] args) {
		// Creating GUI
		String password = "";
		String username = "";
		DBMSManager dbmsm;
		dbmsm = new DBMSManager();
		
		JPanel panel = new JPanel();
    	JLabel passlabel = new JLabel("Gib ein Passwort ein:");
    	JPasswordField pass = new JPasswordField(10);
    	JLabel namelabel = new JLabel("Gib deinen Username ein:");
    	JTextField name = new JTextField(6);
    	
    	panel.add(namelabel);
    	panel.add(name);
    	panel.add(passlabel);
    	panel.add(pass);
    	String[] options = new String[]{"OK", "Abbrechen"};
    	int option = JOptionPane.showOptionDialog(null, panel, "Login-Daten:",
    	                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
    	                         null, options, options[1]);
    	
    	if(option == 0) // pressing OK button
    	{
    	    password = new String(pass.getPassword());
    	    username = new String(name.getText());
    	    System.out.println("Username lautet: " + username);
    	}
    	
    	// Create Connection
    	System.out.println("Es wird versucht eine Verbindung zur Datenbank herzustellen...");
    	if (dbmsm.connectToDatabase(username, password)) {
    		System.out.println("Verbindung erfolgreich hergestellt!");
    	} else {
    		System.out.println("Es konnte keine Verbindung hergestellt werden, terminiere...");
    		return;
    	}
    	
    	// Add Data
    	System.out.println("Es wird versucht, die Datensätze einzufügen...");
		try {
			dbmsm.addDataset();
		} catch (SQLException e) {
			System.out.println("Konnte Datensätze nicht einfügen");
			e.printStackTrace();
		}
    	
    	// Close Connection
    	System.out.println("Schließe Verbindung zum Server...");
    	dbmsm.disconnectFromDatabase(); 
	}
}
