package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
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
	@FXML private TableColumn<Appointment, String> timeTableColumn;
	@FXML private TableColumn<Appointment, String> xrayTableColumn;
	@FXML private TableColumn<Appointment, String> ctTableColumn;
	@FXML private TableColumn<Appointment, String> ultrasoundTableColumn;
	@FXML private TableColumn<Appointment, String> mriTableColumn;

	//Appointment Info
	@FXML private DatePicker apptInfoDatePicker;
	@FXML private ChoiceBox apptInfoTimeChoiceBox;
	@FXML private ChoiceBox apptInfoPatientChoiceBox;
	@FXML private ChoiceBox apptInfoModalityChoiceBox;
	@FXML private TextField apptInfoBodyPartsTextField;
	@FXML private TextField apptInfoDescTextArea;

	//Other Variables
	ObservableList<String> timeSlotChoices = FXCollections.observableArrayList();
	ObservableList<String> patientChoices = FXCollections.observableArrayList();
	ObservableList<String> modalityChoices = FXCollections.observableArrayList();

	public void initialize(){

	}
}
