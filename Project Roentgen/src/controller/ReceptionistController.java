package controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Patient;

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
	@FXML private TextArea apptInfoDescTextArea;

	//Other Variables
	ObservableList<String> timeSlotChoices = FXCollections.observableArrayList();
	ObservableList<String> patientChoices = FXCollections.observableArrayList();
	ObservableList<String> modalityChoices = FXCollections.observableArrayList();
	ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
	ObservableList<Patient> patientList = FXCollections.observableArrayList();
	LocalDate currentDate = LocalDate.now();
	
	public void initialize(){

		initTimeSlots(); //populates time slot choices to be used when creating a new appointment or editing one
		initModalityChoices();//populates modality choices
		
		displayDatePicker.setValue(currentDate);
		
		getAppointmentsForDate(currentDate);
		
		timeTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("dateTime"));
		
		appointmentTableView.setItems(appointmentList);
	}
	
	public void initTimeSlots() {
		String time;
		for(int hours = 8; hours < 17; hours++) {
			time = "";
			for(int mins = 0; mins < 60; mins += 15) {
				if(hours < 12) {
					if(mins == 0){
						time = hours + ":" + mins + "0 AM";
					}
					else {
						time = hours + ":" + mins + " AM";
					}
				}
				else if(hours == 12) {
					if(mins == 0){
						time = hours + ":" + mins + "0 PM";
					}
					else {
						time = hours + ":" + mins + " PM";
					}
				}
				else if(hours > 12) {
					int formatted = hours % 12;
					if(mins == 0){
						time = formatted + ":" + mins + "0 PM";
					}
					else {
						time = formatted + ":" + mins + " PM";
					}
				}
				//System.out.println(time); //uncomment for testing
				timeSlotChoices.add(time);
			}
		}
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
		Patient p1, p2, p3;

		p1 = new Patient();
		p1.setFirstName("Joshua");
		p1.setLastName("Knight");
		Calendar dobCal = Calendar.getInstance();
		dobCal.set(1995, 7, 24);
		p1.setDob(dobCal);
		p1.setSsn("123-45-6789");

		p2 = new Patient();
		p2.setFirstName("Jed");
		p2.setLastName("Dockery");
		dobCal.set(1994, 4, 13);
		p2.setDob(dobCal);
		p2.setSsn("987-65-4321");

		p3 = new Patient();
		p3.setFirstName("John");
		p3.setLastName("Doe");
		dobCal.set(1985, 1, 8);
		p3.setDob(dobCal);
		p3.setSsn("589-24-1154");

		patientList.add(p1);
		patientList.add(p2);
		patientList.add(p3);
		
		Appointment a1 = new Appointment();
		a1.setPatient(patientList.get(0));
		
	}
	
	public void updateTimeSlots() {
		
	}
	
	public void clearFiltersPressed() {
		
	}
	
	public void createAppointmentPushed() {
		
	}
	
	public void editAppointmentPushed() {
		
	}
	
	public void deleteAppointmentPushed() {
		
	}
}
