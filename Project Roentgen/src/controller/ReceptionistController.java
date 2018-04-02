package controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Appointment;

public class ReceptionistController {

	//Declare variables
	@FXML private DatePicker displayDatePicker;
	@FXML private CheckBox xRayFilterCheckBox;
	@FXML private CheckBox ctFilterCheckBox;
	@FXML private CheckBox ultrasoundFilterCheckBox;
	@FXML private CheckBox mriFilterCheckBox;
	@FXML private DatePicker newApptDatePicker;
	@FXML private ChoiceBox appointmentTimeChoiceBox;
	@FXML private ChoiceBox patientChoiceBox;
	@FXML private ChoiceBox modalityChoiceBox;
	@FXML private TextField bodyPartsTextField;
	@FXML private TextArea additionalTextArea;
	
	//Appointment Time Slot TableView
	@FXML private TableView<Appointment> appointmentTableView;
	
}
