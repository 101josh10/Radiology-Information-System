package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalTimeStringConverter;
import model.Appointment;
import model.Patient;

public class ReceptionistController {
	//Declare Static Objects
	private final LocalTime OPENING_TIME = LocalTime.of(8, 0);
	private final LocalTime CLOSING_TIME = LocalTime.of(17, 0);
	
	//Declare variables
	@FXML private DatePicker displayDatePicker;
	@FXML private CheckBox xRayFilterCheckBox;
	@FXML private CheckBox ctFilterCheckBox;
	@FXML private CheckBox ultrasoundFilterCheckBox;
	@FXML private CheckBox mriFilterCheckBox;
	@FXML private DatePicker newApptDatePicker;
	@FXML private ComboBox<LocalTime> newApptTimeComboBox;
	@FXML private ComboBox<Patient> newApptPatientComboBox;
	@FXML private ChoiceBox<String> modalityChoiceBox;
	@FXML private TextField bodyPartTextField;
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
	@FXML private ChoiceBox<String> apptInfoTimeChoiceBox;
	@FXML private ChoiceBox<String> apptInfoPatientChoiceBox;
	@FXML private ChoiceBox<String> apptInfoModalityChoiceBox;
	@FXML private TextField apptInfoBodyPartsTextField;
	@FXML private TextArea apptInfoDescTextArea;

	//Other Variables
	ObservableList<LocalTime> timeSlotChoices = FXCollections.observableArrayList();
	ObservableList<String> patientChoices = FXCollections.observableArrayList();
	ObservableList<String> modalityChoices = FXCollections.observableArrayList();
	ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
	ObservableList<Patient> patientList = FXCollections.observableArrayList();
	LocalDate selectedDate = LocalDate.now();
	
	public void initialize(){

		initTimeSlots(); //populates time slot choices to be used when creating a new appointment or editing one
		initPatientChoices();//populates patient choices
		initModalityChoices();//populates modality choices
		
		displayDatePicker.setValue(selectedDate);
		
		getAppointmentsForDate(selectedDate);
		
		timeTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("displayTime"));
		
		appointmentTableView.setItems(appointmentList);
	}
	
	public void initTimeSlots() {
		for(int hours = OPENING_TIME.getHour(); hours < CLOSING_TIME.getHour(); hours++) {
			for(int mins = 0; mins < 60; mins += 15) {
				LocalTime time = LocalTime.of(hours, mins);
				
				//System.out.println(time.toString()); //uncomment for testing
				timeSlotChoices.add(time);
			}
		}
		
		newApptTimeComboBox.setItems(timeSlotChoices);
	}
	
	public void initPatientChoices() {
		Patient p1, p2, p3;

		p1 = new Patient();
		p1.setFirstName("Joshua");
		p1.setLastName("Knight");
		LocalDate dob = LocalDate.of(1995, 7, 24);
		p1.setDob(dob);
		p1.setSsn("123-45-6789");

		p2 = new Patient();
		p2.setFirstName("Jed");
		p2.setLastName("Dockery");
		dob = LocalDate.of(1994, 4, 13);
		p2.setDob(dob);
		p2.setSsn("987-65-4321");

		p3 = new Patient();
		p3.setFirstName("John");
		p3.setLastName("Doe");
		dob = LocalDate.of(1985, 1, 8);
		p3.setDob(dob);
		p3.setSsn("589-24-1154");

		patientList.add(p1);
		patientList.add(p2);
		patientList.add(p3);
		
		newApptPatientComboBox.setItems(patientList);
		
		newApptPatientComboBox.setButtonCell();
	}
	
	public void initModalityChoices() {
		modalityChoices.add("X-Ray");
		modalityChoices.add("CT Scan");
		modalityChoices.add("Ultrasound");
		modalityChoices.add("MRI");
	}
	
	public void getAppointmentsForDate(LocalDate date) {
		//input SQL Select statement
		
		//using static data for now
		Appointment a1 = new Appointment();
		a1.setPatient(patientList.get(0));
		LocalDateTime a1Time = LocalDateTime.of(2018, 4, 3, 9, 30);
		a1.setDateTime(a1Time);
		a1.setBodyPart("Arm");
		a1.setDesc("Test Description");
		a1.setModality("X-Ray");
		
		Appointment a2 = new Appointment();
		a2.setPatient(patientList.get(1));
		LocalDateTime a2Time = LocalDateTime.of(2018, 4, 3, 13, 30);
		a2.setDateTime(a2Time);
		a2.setBodyPart("Leg");
		a2.setDesc("Another Test");
		a2.setModality("MRI");
		
		Appointment a3 = new Appointment();
		a3.setPatient(patientList.get(2));
		LocalDateTime a3Time = LocalDateTime.of(2018, 5, 9, 16, 15);
		a3.setDateTime(a3Time);
		a3.setBodyPart("Brain");
		a3.setDesc("I dunno anymore");
		a3.setModality("Ultrasound");
		
		appointmentList.add(a1);
		appointmentList.add(a2);
		appointmentList.add(a3);
	}
	
	public void updateTimeSlots() { 
		selectedDate = displayDatePicker.getValue();
		getAppointmentsForDate(selectedDate);
	}
	
	public void clearFiltersPressed() { //When the clear filters button is pressed, this sets all filter checkboxes to be unchecked
		xRayFilterCheckBox.setSelected(false);
		ctFilterCheckBox.setSelected(false);
		ultrasoundFilterCheckBox.setSelected(false);
		mriFilterCheckBox.setSelected(false);
	}
	
	public void createAppointmentPushed() {
		Appointment appt = new Appointment();
		LocalDate newApptDate = newApptDatePicker.getValue();
		LocalTime newApptTime = newApptTimeComboBox.getValue();
		LocalDateTime newApptDateTime = LocalDateTime.of(newApptDate, newApptTime);
		appt.setDateTime(newApptDateTime);
		appt.setPatient((Patient) newApptPatientComboBox.getValue());
		appt.setModality((String) modalityChoiceBox.getValue());
		appt.setBodyPart(bodyPartTextField.getText());
		appt.setDesc(additionalTextArea.getText());
		
		appointmentList.add(appt);
	}
	
	public void editAppointmentPushed() {
		
	}
	
	public void deleteAppointmentPushed() {
		
	}
}
