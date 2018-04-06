package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
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
	@FXML private ComboBox<String> modalityComboBox;
	@FXML private TextField bodyPartTextField;
	@FXML private TextArea additionalTextArea;

	//Appointment Time Slot TableView
	@FXML private TableView<Appointment> appointmentTableView;
	@FXML private TableColumn<Appointment, String> timeTableColumn;
	@FXML private TableColumn<Appointment, String> patientTableColumn;
	@FXML private TableColumn<Appointment, String> last4TableColumn;
	@FXML private TableColumn<Appointment, String> modalityTableColumn;

	//Appointment Info
	@FXML private DatePicker apptInfoDatePicker;
	@FXML private ComboBox<LocalTime> apptInfoTimeComboBox;
	@FXML private ComboBox<Patient> apptInfoPatientComboBox;
	@FXML private ComboBox<String> apptInfoModalityComboBox;
	@FXML private TextField apptInfoBodyPartsTextField;
	@FXML private TextArea apptInfoDescTextArea;
	@FXML private Button editButton;
	@FXML private Button patInfoButton;
	@FXML private Button deleteButton;

	//Other Variables
	ObservableList<LocalTime> timeSlotCombos = FXCollections.observableArrayList();
	ObservableList<String> patientCombos = FXCollections.observableArrayList();
	ObservableList<String> modalityCombos = FXCollections.observableArrayList();
	ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
	ObservableList<Appointment> dayView = FXCollections.observableArrayList();
	ObservableList<Patient> patientList = FXCollections.observableArrayList();
	LocalDate selectedDate = LocalDate.now();
	
	public void initialize(){

		initTimeCombos(); //populates time slot combos to be used when creating a new appointment or editing one
		initPatientCombos();//populates patient Combos
		initModalityCombos();//populates modality Combos
		initAppointmentList();//creates static appointment data
		
		displayDatePicker.setValue(selectedDate);
		
		updateTimeSlots();
		
		timeTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("displayTime"));
		patientTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patientName"));
		last4TableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("last4"));
		modalityTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("modality"));
		
		appointmentTableView.setItems(dayView);
	}
	
	public void initTimeCombos() {
		for(int hours = OPENING_TIME.getHour(); hours < CLOSING_TIME.getHour(); hours++) {
			for(int mins = 0; mins < 60; mins += 15) {
				LocalTime time = LocalTime.of(hours, mins);
				
				//System.out.println(time.toString()); //uncomment for testing
				timeSlotCombos.add(time);
			}
		}
		
		newApptTimeComboBox.setItems(timeSlotCombos);
		apptInfoTimeComboBox.setItems(timeSlotCombos);
	}
	
	public void initPatientCombos() {
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
		apptInfoPatientComboBox.setItems(patientList);
		
		newApptPatientComboBox.setConverter(new StringConverter<Patient>(){
			@Override
		    public String toString(Patient object) {
		        return object.getDisplayName();
		    }

			@Override
			public Patient fromString(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		apptInfoPatientComboBox.setConverter(new StringConverter<Patient>(){
			@Override
		    public String toString(Patient object) {
		        return object.getDisplayName();
		    }

			@Override
			public Patient fromString(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
	
	public void initModalityCombos() {
		modalityCombos.add("X-Ray");
		modalityCombos.add("CT Scan");
		modalityCombos.add("Ultrasound");
		modalityCombos.add("MRI");
		
		modalityComboBox.setItems(modalityCombos);
		apptInfoModalityComboBox.setItems(modalityCombos);
	}
	
	public void initAppointmentList(){
		//input SQL Select Statement
		
		//using static data for now
			Appointment a1 = new Appointment();
			a1.setPatient(patientList.get(0));
			LocalDateTime a1Time = LocalDateTime.of(2018, 4, 4, 9, 30);
			a1.setDateTime(a1Time);
			a1.setBodyPart("Arm");
			a1.setDesc("Test Description");
			a1.setModality("X-Ray");
			
			Appointment a2 = new Appointment();
			a2.setPatient(patientList.get(1));
			LocalDateTime a2Time = LocalDateTime.of(2018, 4, 4, 13, 30);
			a2.setDateTime(a2Time);
			a2.setBodyPart("Leg");
			a2.setDesc("Another Test");
			a2.setModality("MRI");
			
			Appointment a3 = new Appointment();
			a3.setPatient(patientList.get(2));
			LocalDateTime a3Time = LocalDateTime.of(2018, 4, 5, 16, 15);
			a3.setDateTime(a3Time);
			a3.setBodyPart("Brain");
			a3.setDesc("I dunno anymore");
			a3.setModality("Ultrasound");
			
			Appointment a4 = new Appointment();
			a4.setPatient(patientList.get(0));
			LocalDateTime a4Time = LocalDateTime.of(2018, 4, 5, 12, 0);
			a4.setDateTime(a3Time);
			a4.setBodyPart("Brain");
			a4.setDesc("I dunno anymore");
			a4.setModality("CT Scan");
			
			appointmentList.add(a1);
			appointmentList.add(a2);
			appointmentList.add(a3);
			appointmentList.add(a4);
			
			/* Comment for Testing purposes
			for(Appointment appt: appointmentList) {
				System.out.println(appt.getDateTime().toString() + "\n" + appt.getPatientName() + "\n" + appt.getLast4() + "\n\n");
			}
			// */
	}
	
	public ObservableList<Appointment> getAppointmentsForDate(LocalDate date) {
		//input SQL Select statement
		
		ObservableList<Appointment> temp = FXCollections.observableArrayList();
		for(Appointment appt:appointmentList) {
			if(appt.getDateTime().getYear() == date.getYear()) {
				if(appt.getDateTime().getDayOfYear() == date.getDayOfYear()) {
					temp.add(appt);
				}
			}
		}
		
		return temp;
	}
	
	public void updateTimeSlots() { 
		if(editButton.getText().equals("Save Appointment")) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
			alert.setTitle("Save Changes?");
			alert.setHeaderText("Would you like to save your changes?");
			alert.setContentText("Pressing \"No\" will discard your changes.");
			

			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.get() == ButtonType.YES) {
				//Save the appointment if they click YES
				editAppointmentPushed();
			}
		}
		
		selectedDate = displayDatePicker.getValue();
		dayView.clear();
		dayView = getAppointmentsForDate(selectedDate);
		appointmentTableView.setItems(dayView);
		
		apptInfoDatePicker.setValue(null);
		apptInfoTimeComboBox.setValue(null);
		apptInfoPatientComboBox.setValue(null);
		apptInfoModalityComboBox.setValue(null);
		apptInfoBodyPartsTextField.setText(null);
		apptInfoDescTextArea.setText(null);
		
		patInfoButton.setDisable(true);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		editButton.setText("Edit Appointment");
		
		/*System.out.println(selectedDate.toString());//uncomment for testing
		System.out.println(appointmentList.toString());
		System.out.println(dayView.toString());//*/
	}
	
	public void filterPressed() {
		ObservableList<Appointment> temp = getAppointmentsForDate(selectedDate);
		ObservableList<Appointment> xRays = FXCollections.observableArrayList();
		ObservableList<Appointment> ct = FXCollections.observableArrayList();
		ObservableList<Appointment> ultrasound = FXCollections.observableArrayList();
		ObservableList<Appointment> mri = FXCollections.observableArrayList();
		
		for(Appointment appt:temp) {
			if(appt.getModality().equals("X-Ray")) {
				xRays.add(appt);
			}
			if(appt.getModality().equals("CT Scan")) {
				ct.add(appt);
			}
			if(appt.getModality().equals("Ultrasound")) {
				ultrasound.add(appt);
			}
			if(appt.getModality().equals("MRI")) {
				mri.add(appt);
			}
		}
		dayView = FXCollections.observableArrayList();
		
		for(Appointment appt:temp) {
			if(xRayFilterCheckBox.isSelected() && appt.getModality().equals("X-Ray")) {
				dayView.add(appt);
			}
			
			if(ctFilterCheckBox.isSelected() && appt.getModality().equals("CT Scan")) {
				dayView.add(appt);
			}
			
			if(ultrasoundFilterCheckBox.isSelected() && appt.getModality().equals("Ultrasound")) {
				dayView.add(appt);
			}
			
			if(mriFilterCheckBox.isSelected() && appt.getModality().equals("MRI")) {
				dayView.add(appt);
			}
		}
		
		appointmentTableView.setItems(dayView);
	}
	
	public void clearFiltersPressed() { //When the clear filters button is pressed, this sets all filter checkboxes to be unchecked
		xRayFilterCheckBox.setSelected(true);
		ctFilterCheckBox.setSelected(true);
		ultrasoundFilterCheckBox.setSelected(true);
		mriFilterCheckBox.setSelected(true);
		
		dayView = getAppointmentsForDate(selectedDate);
		appointmentTableView.setItems(dayView);
	}
	
	public void createAppointmentPushed(){
		try{
			if(newApptPatientComboBox.getValue() == null ||
					modalityComboBox.getValue() == null ||
					bodyPartTextField.getText() == "" ||
					bodyPartTextField.getText() == null){
				throw new NullPointerException();
			}
			
			Appointment appt = new Appointment();
			LocalDate newApptDate = newApptDatePicker.getValue();
			LocalTime newApptTime = newApptTimeComboBox.getValue();
			LocalDateTime newApptDateTime = LocalDateTime.of(newApptDate, newApptTime);
			appt.setDateTime(newApptDateTime);
			appt.setPatient((Patient) newApptPatientComboBox.getValue());
			appt.setModality((String) modalityComboBox.getValue());
			appt.setBodyPart(bodyPartTextField.getText());
			appt.setDesc(additionalTextArea.getText());
			
			System.out.println("\"" + bodyPartTextField.getText() + "\"");
			appointmentList.add(appt);
			
			newApptDatePicker.setValue(null);
			newApptTimeComboBox.setValue(null);
			newApptPatientComboBox.setValue(null);
			modalityComboBox.setValue(null);
			bodyPartTextField.setText("");
			additionalTextArea.setText("");
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Appointment Created!");
			alert.setHeaderText("Your appointment was created successfully");
			alert.setContentText("Press \"OK\" to reload appointments");

			alert.showAndWait();
			
			//update appointments to reflect new changes
			
			dayView = getAppointmentsForDate(selectedDate);
			appointmentTableView.setItems(dayView);
			
		} catch (NullPointerException e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Fields left blank");
			alert.setContentText("Make sure you have filled in all information neccessary to create an appointment.");

			alert.showAndWait();
		}
	}
	
	public void appointmentClicked() {
		apptInfoPatientComboBox.setConverter(new StringConverter<Patient>(){
			@Override
		    public String toString(Patient object) {
		        return object.getDisplayName();
		    }

			@Override
			public Patient fromString(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		Appointment selected = appointmentTableView.getSelectionModel().getSelectedItem();
		
		apptInfoDatePicker.setValue(selected.getDateTime().toLocalDate());
		apptInfoTimeComboBox.setValue(selected.getDateTime().toLocalTime());
		apptInfoPatientComboBox.setValue(selected.getPatient());
		apptInfoModalityComboBox.setValue(selected.getModality());
		apptInfoBodyPartsTextField.setText(selected.getBodyPart());
		apptInfoDescTextArea.setText(selected.getDesc());
		
		patInfoButton.setDisable(false);
		editButton.setDisable(false);
		deleteButton.setDisable(false);
	}
	
	public void viewPatInfoPushed() {
		Stage stage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/PatientInfoView.fxml"));
			Scene scene = new Scene(root, 800, 600);//should match the above view's dimensions **look in scenebuilder to find dimensions**
			stage.setTitle("Patient Information");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void editAppointmentPushed() {
		if(editButton.getText().equals("Edit Appointment")) {
			apptInfoDatePicker.setDisable(false);
			apptInfoTimeComboBox.setDisable(false);
			apptInfoPatientComboBox.setDisable(false);
			apptInfoModalityComboBox.setDisable(false);
			apptInfoBodyPartsTextField.setEditable(true);
			apptInfoDescTextArea.setEditable(true);
			
			editButton.setText("Save Appointment");
		} else {
			Appointment selected = appointmentTableView.getSelectionModel().getSelectedItem();
			int selIndex = appointmentList.indexOf(selected);
			
			Appointment altered = new Appointment();
			LocalDate alteredDate = apptInfoDatePicker.getValue();
			LocalTime alteredTime = apptInfoTimeComboBox.getValue();
			LocalDateTime alteredDT = LocalDateTime.of(alteredDate, alteredTime);
			altered.setDateTime(alteredDT);
			altered.setPatient(apptInfoPatientComboBox.getValue());
			altered.setBodyPart(apptInfoBodyPartsTextField.getText());
			altered.setModality(apptInfoModalityComboBox.getValue());
			altered.setDesc(apptInfoDescTextArea.getText());
			
			appointmentList.remove(selected);
			appointmentList.add(selIndex, altered);
			
			apptInfoDatePicker.setDisable(true);
			apptInfoTimeComboBox.setDisable(true);
			apptInfoPatientComboBox.setDisable(true);
			apptInfoModalityComboBox.setDisable(true);
			apptInfoBodyPartsTextField.setEditable(false);
			apptInfoDescTextArea.setEditable(false);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Appointment Saved");
			alert.setHeaderText("Appointment has been changed Successfully");
			alert.setContentText("Press \"OK\" to reload appointments");

			alert.showAndWait();
			
			dayView = getAppointmentsForDate(selectedDate);
			appointmentTableView.setItems(dayView);
			
			editButton.setText("Edit Appointment");
		}
	}
	
	public void deleteAppointmentPushed() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm Delete");
		alert.setHeaderText("Are you sure you want to delete this appointment?");
		//alert.setContentText("Make sure you have filled in all information necessary to create an appointment.");

		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK) {
			//Delete the appointment if they click ok
			Appointment selected = appointmentTableView.getSelectionModel().getSelectedItem();
			appointmentList.remove(selected);
			dayView = getAppointmentsForDate(selectedDate);
			appointmentTableView.setItems(dayView);
		}
	}
}
