package customerManagement;

import customerManagement.Constants;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParsePosition;

public class Display extends Application {
	Connection con = null;
	TableView<Kunde> customers;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle(Constants.TITLE);
		stage.setWidth(Constants.WIDTH);
		stage.setHeight(Constants.HEIGHT);
		stage.setResizable(false);
		
		Pane mainPane = new GridPane();
		Pane buttonsPane = new GridPane();
		Pane listCustomersPane = new GridPane();
		
		//	Insets for general use
		Insets bigInset = new Insets(Constants.INSET_BIG);
		Insets smallInset = new Insets(Constants.INSET_SMALL);
		
		CheckBox customersThatPaidCheckbox = new CheckBox();
		
		//	Table start
		customers = new TableView<Kunde>();
		TableColumn<Kunde, Integer> kdnrColumn = new TableColumn<>(Constants.COLUMN_KDNR);
		TableColumn<Kunde, String> nameColumn = new TableColumn<>(Constants.COLUMN_NAME);
		TableColumn<Kunde, String> streetColumn = new TableColumn<>(Constants.COLUMN_STREET);
		TableColumn<Kunde, Integer> housenrColumn = new TableColumn<>(Constants.COLUMN_HOUSENR);
		TableColumn<Kunde, String> townColumn = new TableColumn<>(Constants.COLUMN_TOWN);

		kdnrColumn.setMinWidth(Constants.COLUMNS_MIN_WIDTH);
		nameColumn.setMinWidth(Constants.COLUMNS_MIN_WIDTH);
		streetColumn.setMinWidth(Constants.COLUMNS_MIN_WIDTH);
		housenrColumn.setMinWidth(Constants.COLUMNS_MIN_WIDTH);
		townColumn.setMinWidth(Constants.COLUMNS_MIN_WIDTH);
		
		kdnrColumn.setMaxWidth(Constants.COLUMNS_MAX_WIDTH);
		nameColumn.setMaxWidth(Constants.COLUMNS_MAX_WIDTH);
		streetColumn.setMaxWidth(Constants.COLUMNS_MAX_WIDTH);
		housenrColumn.setMaxWidth(Constants.COLUMNS_MAX_WIDTH);
		townColumn.setMaxWidth(Constants.COLUMNS_MAX_WIDTH);

		kdnrColumn.setCellValueFactory(new PropertyValueFactory<>("KdNr"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		streetColumn.setCellValueFactory(new PropertyValueFactory<>("Street"));
		housenrColumn.setCellValueFactory(new PropertyValueFactory<>("HouseNr"));
		townColumn.setCellValueFactory(new PropertyValueFactory<>("Town"));
		
		customers.setMinWidth(500);
		ObservableList<Kunde> asd = getCustomers();
		customers.setItems(asd);
		customers.getColumns().addAll(kdnrColumn, nameColumn, streetColumn, housenrColumn, townColumn);
		//	Table end
		
		//	Buttons start
		Button connectButton = new Button(Constants.BUTTON_CONNECT);
		Button addCustomerButton = new Button(Constants.BUTTON_ADD);
		Button listCustomersButton = new Button(Constants.BUTTON_LIST);
		Button disconnectButton = new Button(Constants.BUTTON_DISCONNECT);

		connectButton.setMinWidth(Constants.BUTTONS_MIN_WIDTH);
		addCustomerButton.setMinWidth(Constants.BUTTONS_MIN_WIDTH);
		listCustomersButton.setMinWidth(Constants.BUTTONS_MIN_WIDTH);
		disconnectButton.setMinWidth(Constants.BUTTONS_MIN_WIDTH);
		//	Buttons end

		//	Textfields start
		TextField kdnrField = new TextField();
		TextField nameField = new TextField();
		TextField streetField = new TextField();
		TextField housenrField = new TextField();
		TextField townField = new TextField();

		kdnrField.setPrefColumnCount(Constants.MAX_CHARS);
		nameField.setPrefColumnCount(Constants.MAX_CHARS);
		streetField.setPrefColumnCount(Constants.MAX_CHARS);
		housenrField.setPrefColumnCount(Constants.MAX_CHARS);
		townField.setPrefColumnCount(Constants.MAX_CHARS);
		
		kdnrField.setPromptText(Constants.FIELD_KDNR);
		nameField.setPromptText(Constants.FIELD_NAME);
		streetField.setPromptText(Constants.FIELD_STREET);
		housenrField.setPromptText(Constants.FIELD_HOUSENR);
		townField.setPromptText(Constants.FIELD_TOWN);

		kdnrField.setTextFormatter(forceNumbers());
		housenrField.setTextFormatter(forceNumbers());
		//	Textfields end
		
		//	Miscellaneous settings start
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
		
		GridPane.setConstraints(buttonsPane, 0, 0);
		GridPane.setConstraints(connectButton, 0, Constants.ROW_CONNECT);
		GridPane.setConstraints(addCustomerButton, 0, Constants.ROW_ADD);
		GridPane.setConstraints(disconnectButton, 0, Constants.ROW_DISCONNECT);
		GridPane.setConstraints(listCustomersPane, 0, Constants.ROW_LIST);
		GridPane.setConstraints(kdnrField, 0, Constants.ROW_KDNR);
		GridPane.setConstraints(nameField, 0, Constants.ROW_NAME);
		GridPane.setConstraints(streetField, 0, Constants.ROW_STREET);
		GridPane.setConstraints(housenrField, 0, Constants.ROW_HOUSENR);
		GridPane.setConstraints(townField, 0, Constants.ROW_TOWN);
		GridPane.setConstraints(listCustomersButton, 0, 0);
		GridPane.setConstraints(customersThatPaidCheckbox, 1, 0);
		GridPane.setConstraints(customers, 1, 0);
		
		buttonsPane.setMaxWidth(Constants.BUTTONS_MAX_WIDTH);
		//	Miscellaneous settings end
		
		listCustomersPane.getChildren().addAll(
				listCustomersButton, customersThatPaidCheckbox);
		buttonsPane.getChildren().addAll(
				connectButton, addCustomerButton, listCustomersPane, disconnectButton
				, kdnrField, nameField, streetField, housenrField, townField);
		mainPane.getChildren().addAll(buttonsPane, customers);
		Scene scene = new Scene(mainPane, Constants.WIDTH, Constants.HEIGHT);
		
		// Load Window
		stage.setScene(scene);
		stage.show();
	}
	
	private ObservableList<Kunde> getCustomers() {
		ObservableList<Kunde> list = FXCollections.observableArrayList();
		list.add(new Kunde(1, "Horst Seehofer", "Weimarer Str.", 88, "Berlin"));
		list.add(new Kunde(1, "Ernst Seehofer", "Weimarer Str.", 87, "Berlin"));
		return list;
	}
	
	private static TextFormatter<String> forceNumbers() {
		DecimalFormat format = new DecimalFormat( "#.0" );
		TextFormatter<String> formatter = new TextFormatter<>( c ->
		{
		    if ( c.getControlNewText().isEmpty() )
		    {
		        return c;
		    }
	
		    ParsePosition parsePosition = new ParsePosition( 0 );
		    Object object = format.parse( c.getControlNewText(), parsePosition );
	
		    if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
		    {
		        return null;
		    }
		    else
		    {
		        return c;
		    }
		});
		return formatter;
	}
}
