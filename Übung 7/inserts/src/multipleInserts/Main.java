package multipleInserts;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main {
	private static String password;
	private static String username;
	private static DBMSManager dbmsm;
	
	public static void main(String[] args) {
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
    	int option = JOptionPane.showOptionDialog(null, panel, "Login-Daten",
    	                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
    	                         null, options, options[1]);
    	
    	if(option == 0) // pressing OK button
    	{
    	    password = new String(pass.getPassword());
    	    username = new String(name.getText());
    	    System.out.println("Username lautet: " + username);
    	}
    	
    	//	Verbindung herstellen
    	System.out.println("Es wird versucht eine Verbindung zur Datenbank herzustellen...");
    	if (dbmsm.connectToDatabase(username, password)) {
    		System.out.println("Verbindung erfolgreich hergestellt!");
    	} else {
    		System.out.println("Es konnte keine Verbindung hergestellt werden, terminiere...");
    		return;
    	}
    	
    	//	Daten hinzufügen
    	System.out.println("Es wird versucht, die Datensätze einzufügen...");
    	if (dbmsm.addDataset()) {
    		System.out.println("Datensätze erfolgreich eingefügt");
    	} else {
    		System.out.println("Konnte Datensätze nicht einfügen");
    	}
    	
    	//	Verbindung schließen
    	System.out.println("Schließe Verbindung zum Server...");
    	dbmsm.disconnectFromDatabase();
	}
}
