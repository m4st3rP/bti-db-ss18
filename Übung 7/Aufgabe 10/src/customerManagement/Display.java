package customerManagement;

import customerManagement.Constants;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParsePosition;

/**
 * @author Finn-Frederik Jannsen, Philipp Schwarz
 * @version 1.0
 *
 *	GUI-Application for basic DBP Database management
 */

public class Display extends Application {
	private TableView<Kunde> customers;
	private DBMSManager dbmsm;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		dbmsm = new DBMSManager();

		//	Prepare Window
		stage.setTitle(Constants.TITLE);
		stage.setWidth(Constants.WIDTH);
		stage.setHeight(Constants.HEIGHT);
		stage.setResizable(false);

		//	3 Grids needed for organizing layout
		Pane mainPane = new GridPane();
		Pane buttonsPane = new GridPane();
		Pane listCustomersPane = new GridPane();

		// Insets for general use
		Insets bigInset = new Insets(Constants.INSET_BIG);
		Insets smallInset = new Insets(Constants.INSET_SMALL);

		//	Checkbox to only show Customers who paid
		CheckBox customersThatPaidCheckbox = new CheckBox();
		
		//	Effects for connection status
		Effect effectConnected = new DropShadow(10, Color.GREEN);
		Effect effectDisconnected = new DropShadow(10, Color.RED);

		// Table start
		customers = new TableView<Kunde>();
		TableColumn<Kunde, Integer> kdnrColumn = new TableColumn<>(Constants.COLUMN_KDNR);
		TableColumn<Kunde, String> nameColumn = new TableColumn<>(Constants.COLUMN_NAME);
		TableColumn<Kunde, String> streetColumn = new TableColumn<>(Constants.COLUMN_STREET);
		TableColumn<Kunde, Integer> housenrColumn = new TableColumn<>(Constants.COLUMN_HOUSENR);
		TableColumn<Kunde, String> townColumn = new TableColumn<>(Constants.COLUMN_TOWN);
		
		//	Bind minimum width to table width divided by the number of columns
		kdnrColumn.minWidthProperty().bind(customers.widthProperty().divide(5));
		nameColumn.minWidthProperty().bind(customers.widthProperty().divide(5));
		streetColumn.minWidthProperty().bind(customers.widthProperty().divide(5));
		housenrColumn.minWidthProperty().bind(customers.widthProperty().divide(5));
		townColumn.minWidthProperty().bind(customers.widthProperty().divide(5));

		kdnrColumn.setMaxWidth(Constants.COLUMNS_MAX_WIDTH);
		nameColumn.setMaxWidth(Constants.COLUMNS_MAX_WIDTH);
		streetColumn.setMaxWidth(Constants.COLUMNS_MAX_WIDTH);
		housenrColumn.setMaxWidth(Constants.COLUMNS_MAX_WIDTH);
		townColumn.setMaxWidth(Constants.COLUMNS_MAX_WIDTH);

		//	create Value Factories to work with Customer-Class variables
		kdnrColumn.setCellValueFactory(new PropertyValueFactory<>("KdNr"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		streetColumn.setCellValueFactory(new PropertyValueFactory<>("Street"));
		housenrColumn.setCellValueFactory(new PropertyValueFactory<>("HouseNr"));
		townColumn.setCellValueFactory(new PropertyValueFactory<>("Town"));

		customers.setMinWidth(500);
		customers.getColumns().addAll(kdnrColumn, nameColumn, streetColumn, housenrColumn, townColumn);
		// Table end

		// Buttons start
		Button connectButton = new Button(Constants.BUTTON_CONNECT);
		Button addCustomerButton = new Button(Constants.BUTTON_ADD);
		Button listCustomersButton = new Button(Constants.BUTTON_LIST);
		Button disconnectButton = new Button(Constants.BUTTON_DISCONNECT);

		connectButton.setMinWidth(Constants.BUTTONS_MIN_WIDTH);
		addCustomerButton.setMinWidth(Constants.BUTTONS_MIN_WIDTH);
		listCustomersButton.setMinWidth(Constants.BUTTONS_MIN_WIDTH);
		disconnectButton.setMinWidth(Constants.BUTTONS_MIN_WIDTH);
		
		//	Starting disconnected, thus show it
		connectButton.setEffect(effectDisconnected);
		// Buttons end

		// Textfields start
		TextField kdnrField = new TextField();
		TextField nameField = new TextField();
		TextField streetField = new TextField();
		TextField housenrField = new TextField();
		TextField townField = new TextField();
		TextField accountNameField = new TextField();
		PasswordField accountPasswordField = new PasswordField();

		kdnrField.setPrefColumnCount(Constants.MAX_CHARS);
		nameField.setPrefColumnCount(Constants.MAX_CHARS);
		streetField.setPrefColumnCount(Constants.MAX_CHARS);
		housenrField.setPrefColumnCount(Constants.MAX_CHARS);
		townField.setPrefColumnCount(Constants.MAX_CHARS);
		accountNameField.setPrefColumnCount(Constants.MAX_CHARS);
		accountPasswordField.setPrefColumnCount(Constants.MAX_CHARS);

		kdnrField.setPromptText(Constants.FIELD_KDNR);
		nameField.setPromptText(Constants.FIELD_NAME);
		streetField.setPromptText(Constants.FIELD_STREET);
		housenrField.setPromptText(Constants.FIELD_HOUSENR);
		townField.setPromptText(Constants.FIELD_TOWN);
		accountNameField.setPromptText(Constants.FIELD_ACCNAME);
		accountPasswordField.setPromptText(Constants.FIELD_ACCPWD);

		//	set text formatter to one that forces numbers-only input on number fields
		kdnrField.setTextFormatter(forceNumbers());
		housenrField.setTextFormatter(forceNumbers());
		// Textfields end

		// Miscellaneous settings start
		GridPane.setHalignment(connectButton, HPos.CENTER);
		GridPane.setHalignment(addCustomerButton, HPos.CENTER);
		GridPane.setHalignment(listCustomersButton, HPos.CENTER);
		GridPane.setHalignment(disconnectButton, HPos.CENTER);
		GridPane.setHalignment(customersThatPaidCheckbox, HPos.CENTER);
		GridPane.setHalignment(kdnrField, HPos.CENTER);
		GridPane.setHalignment(nameField, HPos.CENTER);
		GridPane.setHalignment(streetField, HPos.CENTER);
		GridPane.setHalignment(housenrField, HPos.CENTER);
		GridPane.setHalignment(townField, HPos.CENTER);
		GridPane.setHalignment(accountNameField, HPos.CENTER);
		GridPane.setHalignment(accountPasswordField, HPos.CENTER);

		GridPane.setMargin(connectButton, bigInset);
		GridPane.setMargin(addCustomerButton, bigInset);
		GridPane.setMargin(listCustomersButton, bigInset);
		GridPane.setMargin(disconnectButton, bigInset);
		GridPane.setMargin(customersThatPaidCheckbox, bigInset);
		GridPane.setMargin(customers, bigInset);
		GridPane.setMargin(kdnrField, smallInset);
		GridPane.setMargin(nameField, smallInset);
		GridPane.setMargin(streetField, smallInset);
		GridPane.setMargin(housenrField, smallInset);
		GridPane.setMargin(townField, smallInset);
		GridPane.setMargin(accountNameField, smallInset);
		GridPane.setMargin(accountPasswordField, smallInset);

		GridPane.setConstraints(buttonsPane, 0, 0);
		GridPane.setConstraints(connectButton, 0, Constants.ROW_CONNECT);
		GridPane.setConstraints(disconnectButton, 0, Constants.ROW_DISCONNECT);
		GridPane.setConstraints(accountNameField, 0, Constants.ROW_ACCNAME);
		GridPane.setConstraints(accountPasswordField, 0, Constants.ROW_ACCPWD);
		GridPane.setConstraints(listCustomersPane, 0, Constants.ROW_LIST);
		GridPane.setConstraints(addCustomerButton, 0, Constants.ROW_ADD);
		GridPane.setConstraints(kdnrField, 0, Constants.ROW_KDNR);
		GridPane.setConstraints(nameField, 0, Constants.ROW_NAME);
		GridPane.setConstraints(streetField, 0, Constants.ROW_STREET);
		GridPane.setConstraints(housenrField, 0, Constants.ROW_HOUSENR);
		GridPane.setConstraints(townField, 0, Constants.ROW_TOWN);
		GridPane.setConstraints(listCustomersButton, 0, 0);
		GridPane.setConstraints(customersThatPaidCheckbox, 1, 0);
		GridPane.setConstraints(customers, 1, 0);

		buttonsPane.setMaxWidth(Constants.BUTTONS_MAX_WIDTH);
		// Miscellaneous settings end

		//	Create GUI hierarchy
		listCustomersPane.getChildren().addAll(listCustomersButton, customersThatPaidCheckbox);
		buttonsPane.getChildren().addAll(connectButton, addCustomerButton, listCustomersPane, disconnectButton,
				kdnrField, nameField, streetField, housenrField, townField, accountNameField, accountPasswordField);
		mainPane.getChildren().addAll(buttonsPane, customers);
		Scene scene = new Scene(mainPane, Constants.WIDTH, Constants.HEIGHT);

		// Load Window
		stage.setScene(scene);
		stage.show();
		
		//	Set Action for connectButton clicking event
		connectButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (dbmsm.connectToDatabase(accountNameField.getText(), accountPasswordField.getText())) {
					//	On succesful connect show connection status using effect
					connectButton.setEffect(effectConnected);
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle(Constants.CONISSUE_TITLE);
					alert.setHeaderText(Constants.CONISSUE_HEADER);
					alert.setContentText(Constants.CONISSUE_CONTENT);
					alert.showAndWait();
				}
			}
		});

		//	Set Action for addCustomerButton clicking event
		addCustomerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					dbmsm.addCustomer(Integer.parseInt(kdnrField.getText()), nameField.getText(), streetField.getText(),
										Integer.parseInt(housenrField.getText()), townField.getText());
				}
				catch (Exception e) {
					e.printStackTrace();
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle(Constants.ADDISSUE_TITLE);
					alert.setHeaderText(Constants.ADDISSUE_HEADER);
					alert.setContentText(Constants.ADDISSUE_CONTENT);
					alert.showAndWait();
				}
			}
		});

		//	Set Action for listCustomersButton clicking event
		listCustomersButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				//	Request Data and pass it to the tableview
				customers.setItems(dbmsm.getAllCustomers(customersThatPaidCheckbox.isSelected()));
			}
		});

		//	Set Action for disconnectButton clicking event
		disconnectButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (dbmsm.disconnectFromDatabase()) {
					//	on succesful disconnect show connection status using effect
					connectButton.setEffect(effectDisconnected);
				}
			}
		});
	}
	
	
	private static TextFormatter<String> forceNumbers() {
		//	Only allow numbers
		DecimalFormat format = new DecimalFormat("#");
		TextFormatter<String> formatter = new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			//	Parse given entered text for numbers and remove ',' and '.'
			Object object = format.parse(c.getControlNewText().replaceAll("[,.]", ""), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		});
		return formatter;
	}
}
