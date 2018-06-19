package customerManagement;

import customerManagement.Constants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import java.sql.*;

public class Display extends Application {
	Connection con = null;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle(Constants.TITLE);
		stage.setWidth(Constants.WIDTH);
		stage.setHeight(Constants.HEIGHT);
		
		Pane mainPane = new GridPane();
		Pane buttonsPane = new GridPane();
		Pane listCustomersPane = new GridPane();
		
		Insets bigInset = new Insets(Constants.INSET_BIG);
		Insets smallInset = new Insets(Constants.INSET_SMALL);
		
		Button connectButton = new Button("Verbinden");
		Button addCustomerButton = new Button("Hinzufügen");
		Button listCustomersButton = new Button("Kunden anzeigen");
		Button disconnectButton = new Button("Trennen");
		CheckBox customersThatPaidCheckbox = new CheckBox();

		GridPane.setHalignment(connectButton, HPos.CENTER);
		GridPane.setHalignment(addCustomerButton, HPos.CENTER);
		GridPane.setHalignment(listCustomersButton, HPos.CENTER);
		GridPane.setHalignment(disconnectButton, HPos.CENTER);
		GridPane.setHalignment(customersThatPaidCheckbox, HPos.CENTER);

		GridPane.setMargin(connectButton, bigInset);
		GridPane.setMargin(addCustomerButton, bigInset);
		GridPane.setMargin(listCustomersButton, bigInset);
		GridPane.setMargin(disconnectButton, bigInset);
		GridPane.setMargin(customersThatPaidCheckbox, bigInset);
		
		GridPane.setConstraints(buttonsPane, 0, 0);
		GridPane.setConstraints(connectButton, 0, Constants.ROW_CONNECT);
		GridPane.setConstraints(addCustomerButton, 0, Constants.ROW_ADD);
		GridPane.setConstraints(disconnectButton, 0, Constants.ROW_DISCONNECT);
		GridPane.setConstraints(listCustomersPane, 0, Constants.ROW_LIST);
		GridPane.setConstraints(listCustomersButton, 0, 0);
		GridPane.setConstraints(customersThatPaidCheckbox, 1, 0);
		
		listCustomersPane.getChildren().addAll(
				listCustomersButton, customersThatPaidCheckbox);
		buttonsPane.getChildren().addAll(
				connectButton, addCustomerButton, listCustomersPane, disconnectButton);
		mainPane.getChildren().addAll(buttonsPane);
		Scene scene = new Scene(mainPane, Constants.WIDTH, Constants.HEIGHT);
		
		
	}
	
	private static void connect() {
		
	}
}
