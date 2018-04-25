package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import model.DBConnection;
import model.Patient;

public class ReceptionistController {
	//Declare Static Objects
	private final LocalTime OPENING_TIME = LocalTime.of(8, 0);
	private final LocalTime CLOSING_TIME = LocalTime.of(17, 0);

	@FXML private TabPane receptionistTabPane;

	//Declare variables
	@FXML private Tab appointmentsTab;
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
	private ObservableList<LocalTime> timeSlotCombos = FXCollections.observableArrayList();
	private ObservableList<String> patientCombos = FXCollections.observableArrayList();
	private ObservableList<String> modalityCombos = FXCollections.observableArrayList();
	private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
	private ObservableList<Appointment> dayView = FXCollections.observableArrayList();
	private ObservableList<Patient> patientList = FXCollections.observableArrayList();
	private LocalDate selectedDate = LocalDate.now();

	//Patient Info Tab
	@FXML private Tab patInfoTab;
	@FXML private TextField firstNameTextField;
	@FXML private TextField lastNameTextField;
	@FXML private TextField middleNameTextField;
	@FXML private ComboBox<String> genderComboBox;
	@FXML private ComboBox<String> ethnicityComboBox;
	@FXML private TextField otherEthnicityTextField;
	@FXML private TextField ssnTextField;
	@FXML private DatePicker dobDatePicker;
	@FXML private ComboBox<Integer> feetComboBox;
	@FXML private ComboBox<Integer> inchesComboBox;
	@FXML private Spinner<Integer> weightSpinner;
	@FXML private Button editPatInfoButton;
	@FXML private Button newPatButton;
	@FXML private Button deletePatButton;

	//Other variables
	ObservableList<String> ethnicityChoices = FXCollections.observableArrayList();
	ObservableList<String> genderChoices = FXCollections.observableArrayList();
	ObservableList<Integer> feetChoices = FXCollections.observableArrayList();
	ObservableList<Integer> inchesChoices = FXCollections.observableArrayList();
	Connection conn = DBConnection.dbConnection();

	public void initialize(){

		initTimeCombos(); //populates time slot combos to be used when creating a new appointment or editing one
		initPatientCombos();//populates patient Combos
		initModalityCombos();//populates modality Combos
		initAppointmentList();//creates static appointment data

		initEthnicities();//populates ethnicity combo
		initGenders();//populates gender combo
		initFeet();//populates feet combo
		initInches();//populates inches combo

		displayDatePicker.setValue(selectedDate);

		updateTimeSlots();

		timeTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("displayTime"));
		patientTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patientName"));
		last4TableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("last4"));
		modalityTableColumn.setCellValueFactory(new PropertyValueFactory<Appointment, String>("modality"));

		appointmentTableView.setItems(dayView);
	}
	
	private Patient getPatient(int patientID) {
		Patient p = null;
		for(Patient pat : patientList) {
			if(pat.getId() == patientID) {
				p = pat;
				break;
			}
		}
		
		return p;
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
		/*Patient p1, p2, p3;

		p1 = new Patient();
		p1.setFirstName("Joshua");
		p1.setLastName("Knight");
		p1.setMiddleName("Matthew");
		p1.setMale(true);
		p1.setAddress("17 Bluebird Tr.");
		p1.setCity("Dahlonega");
		p1.setState("GA");
		p1.setZip("30533");
		p1.setEthnicity("Buddhist or some shit");
		p1.setHeight(69);
		p1.setWeight(195);
		p1.setPhoneNum("(706) 201-9393");
		p1.setEmail("jmknig0314@ung.edu");
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
		patientList.add(p3);*/

		String query = "SELECT * from patient";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Patient temp = new Patient();
				temp.setId(rs.getInt(1));
				temp.setFirstName(rs.getString(2));
				temp.setLastName(rs.getString(3));
				temp.setMiddleInit(rs.getString(4));
				Date dob = rs.getDate(5);
				temp.setDob(dob.toLocalDate());
				temp.setSsn(rs.getString(6));

				patientList.add(temp);
				System.out.println("Name: " + temp.getLastName() + ", " + temp.getFirstName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		/*using static data for now
			Appointment a1 = new Appointment();
			a1.setPatient(patientList.get(0));
			LocalDateTime a1Time = LocalDateTime.of(2018, 4, 17, 9, 30);
			a1.setDateTime(a1Time);
			a1.setBodyPart("Arm");
			a1.setDesc("Test Description");
			a1.setModality("X-Ray");

			Appointment a2 = new Appointment();
			a2.setPatient(patientList.get(1));
			LocalDateTime a2Time = LocalDateTime.of(2018, 4, 17, 13, 30);
			a2.setDateTime(a2Time);
			a2.setBodyPart("Leg");
			a2.setDesc("Another Test");
			a2.setModality("MRI");

			Appointment a3 = new Appointment();
			a3.setPatient(patientList.get(2));
			LocalDateTime a3Time = LocalDateTime.of(2018, 4, 17, 16, 15);
			a3.setDateTime(a3Time);
			a3.setBodyPart("Brain");
			a3.setDesc("I dunno anymore");
			a3.setModality("Ultrasound");

			Appointment a4 = new Appointment();
			a4.setPatient(patientList.get(0));
			LocalDateTime a4Time = LocalDateTime.of(2018, 4, 17, 12, 0);
			a4.setDateTime(a3Time);
			a4.setBodyPart("Brain");
			a4.setDesc("I dunno anymore");
			a4.setModality("CT Scan");

			appointmentList.add(a1);
			appointmentList.add(a2);
			appointmentList.add(a3);
			appointmentList.add(a4);*/

		String query = "SELECT * from appointment";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Appointment appt = new Appointment();
				Date apptDate = rs.getDate(2);
				Time apptTime = rs.getTime(3);
				LocalDateTime ldt = LocalDateTime.of(apptDate.toLocalDate(), apptTime.toLocalTime());
				appt.setDateTime(ldt);
				appt.setModality(rs.getString(4));
				appt.setBodyPart(rs.getString(5));
				appt.setDesc(rs.getString(6));
				
				int patID = rs.getInt(7);
				appt.setPatient(getPatient(patID));
				
				appointmentList.add(appt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			/* Comment for Testing purposes
			for(Appointment appt: appointmentList) {
				System.out.println(appt.getDateTime().toString() + "\n" + appt.getPatientName() + "\n" + appt.getLast4() + "\n\n");
			}
			// */
	}

	public void initEthnicities(){
		ethnicityChoices.add("Caucasian");
		ethnicityChoices.add("African American");
		ethnicityChoices.add("Latino/Hispanic");
		ethnicityChoices.add("Asian");
		ethnicityChoices.add("Other");

		ethnicityComboBox.setItems(ethnicityChoices);
	}

	public void initGenders(){
		genderChoices.add("Male");
		genderChoices.add("Female");

		genderComboBox.setItems(genderChoices);
	}

	public void initFeet(){
		for(int a = 3; a <= 8; a++){
			feetChoices.add(a);
		}

		feetComboBox.setItems(feetChoices);
	}

	public void initInches(){
		for(int a = 0; a <= 11; a++){
			inchesChoices.add(a);
		}

		inchesComboBox.setItems(inchesChoices);
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

			ObservableList<Appointment> daysAppts = getAppointmentsForDate(newApptDate);
			boolean isTaken = false;
			for(Appointment a : daysAppts){
				if(a.getPatient() == newApptPatientComboBox.getValue() && a.getDateTime().equals(newApptDateTime)){//if patient is already supposed to be somewhere else
					String message = "Patient already scheduled at that time.\n Please select another time for the patient.";
					IllegalArgumentException e = new IllegalArgumentException(message);
					isTaken = true;
					throw e;
				}
				if(a.getModality() == modalityComboBox.getValue() && a.getDateTime().equals(newApptDateTime)){//if modality is already scheduled
					String message = modalityComboBox.getValue() + " already scheduled at that time.\n Please select another time for the patient.";
					IllegalArgumentException e = new IllegalArgumentException(message);
					isTaken = true;
					throw e;
				}

			}
			
			if(!isTaken) {
				String query = "INSERT INTO appointment (date, time, modality, bodypart, notes, Patient_idPatient) VALUES (?,?,?,?,?,?)";
				try {
					PreparedStatement pst = conn.prepareStatement(query);
					Date d = Date.valueOf(newApptDate);
					Time t = Time.valueOf(newApptTime);
					
					pst.setDate(1, d);
					pst.setTime(2, t);
					pst.setString(3, appt.getModality());
					pst.setString(4, appt.getBodyPart());
					pst.setString(5, appt.getDesc());
					pst.setInt(6, appt.getPatient().getId());
					
					boolean rs = pst.execute();
					
					if(rs) {
						System.out.println("Appointment Created Successfully");
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			//System.out.println("\"" + bodyPartTextField.getText() + "\"");//uncomment for testing
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
		} catch(IllegalArgumentException e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Cannot Create Appointment");
			alert.setContentText(e.getMessage());

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
		receptionistTabPane.getSelectionModel().selectNext();
		String m = "Male";
		String f = "Female";

		//Take patient info and put it into the fields
		Patient pat = appointmentTableView.getSelectionModel().getSelectedItem().getPatient();
		firstNameTextField.setText(pat.getFirstName());
		lastNameTextField.setText(pat.getLastName());
		middleNameTextField.setText(pat.getMiddleInit());
		if(pat.isMale()) {
			genderComboBox.getSelectionModel().select(m);
		} else {
			genderComboBox.getSelectionModel().select(f);
		}

		String ethnicity = pat.getEthnicity();
		boolean ethnicityExists = false;

		for(String s : ethnicityChoices) {
			if(s.equals(ethnicity)) {
				ethnicityExists = true;
				break;
			}
		}

		if(ethnicityExists) {
			ethnicityComboBox.getSelectionModel().select(ethnicity);
		} else {
			ethnicityComboBox.getSelectionModel().select("Other");
			otherEthnicityTextField.setText(ethnicity);
		}

		ssnTextField.setText(pat.getSsn());
		dobDatePicker.setValue(pat.getDob());
		feetComboBox.getSelectionModel().select(pat.getFeet());
		inchesComboBox.getSelectionModel().select(pat.getInches());
		weightSpinner.getValueFactory().setValue(pat.getWeight());
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

			/*appointmentList.remove(selected);
			appointmentList.add(selIndex, altered);*/
			
			String query = "DROP * FROM appointment WHERE idappointment = ?";
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			query = "INSERT INTO appointment (date, time, modality, bodypart, notes, Patient_idPatient) VALUES (?,?,?,?,?,?)";
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				Date d = Date.valueOf(alteredDate);
				Time t = Time.valueOf(alteredTime);
				
				pst.setDate(1, d);
				pst.setTime(2, t);
				pst.setString(3, altered.getModality());
				pst.setString(4, altered.getBodyPart());
				pst.setString(5, altered.getDesc());
				pst.setInt(6, altered.getPatient().getId());
				
				boolean rs = pst.execute();
				
				if(rs) {
					System.out.println("Appointment Saved Successfully");
					initAppointmentList();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
		Alert alert = new Alert(AlertType.CONFIRMATION,"", ButtonType.YES, ButtonType.NO);
		alert.setTitle("Confirm Delete");
		alert.setHeaderText("Are you sure you want to delete this appointment?");
		//alert.setContentText("Make sure you have filled in all information necessary to create an appointment.");

		Optional<ButtonType> result = alert.showAndWait();

		if(result.get() == ButtonType.YES) {
			//Delete the appointment if they click yes
			Appointment selected = appointmentTableView.getSelectionModel().getSelectedItem();
			appointmentList.remove(selected);
			dayView = getAppointmentsForDate(selectedDate);
			appointmentTableView.setItems(dayView);
			
			//INSERT SQL statement here
		}
	}

	public void ethnicitySelected() {
		String selection = ethnicityComboBox.getValue();

		if(selection.equals("Other")){
			otherEthnicityTextField.setDisable(false);
		} else{
			otherEthnicityTextField.setDisable(true);
		}
	}

	public void editPatButtonPressed() {
		if(editPatInfoButton.getText().equals("Edit Patient")) {
			firstNameTextField.setEditable(true);
			lastNameTextField.setEditable(true);
			middleNameTextField.setEditable(true);
			genderComboBox.setDisable(false);
			ethnicityComboBox.setDisable(false);
			ssnTextField.setEditable(true);
			dobDatePicker.setDisable(false);
			feetComboBox.setDisable(false);
			inchesComboBox.setDisable(false);
			weightSpinner.setDisable(false);

			editPatInfoButton.setText("Save Patient");
		} else {
			Patient temp = appointmentTableView.getSelectionModel().getSelectedItem().getPatient();
			Patient p = new Patient();
			p.setLastName(lastNameTextField.getText());
			p.setFirstName(firstNameTextField.getText());
			p.setMiddleInit(middleNameTextField.getText());
			boolean isMale = false;
			if(genderComboBox.getValue().equals("Male")) {
				isMale = true;
			}
			p.setMale(isMale);
			if(ethnicityComboBox.getValue().equals("Other")) {
				p.setEthnicity(otherEthnicityTextField.getText());
			} else {
				p.setEthnicity(ethnicityComboBox.getValue());
			}
			p.setSsn(ssnTextField.getText());
			p.setDob(dobDatePicker.getValue());
			int height = feetComboBox.getValue() * 12 + inchesComboBox.getValue();
			p.setHeight(height);
			p.setWeight(weightSpinner.getValue());

			patientList.remove(temp);
			patientList.add(p);
			
			Date d = Date.valueOf(p.getDob());
			
			String query = "INSERT INTO patient(FName, LName, Minitial, DOB, SSN, Height, Race, Gender, Weight) VALUES (?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, p.getFirstName());
				pst.setString(2, p.getLastName());
				pst.setString(3, p.getMiddleInit());
				pst.setDate(4, d);
				pst.setString(5, p.getSsn());
				pst.setInt(6, height);
				pst.setString(7, p.getEthnicity());
				pst.setBoolean(8, isMale);
				pst.setInt(9, p.getWeight());
				
				boolean rs = pst.execute();
				
				if(rs) {
					System.out.println("Registration Successful!");
				}

			firstNameTextField.setEditable(false);
			lastNameTextField.setEditable(false);
			middleNameTextField.setEditable(false);
			genderComboBox.setDisable(true);
			ethnicityComboBox.setDisable(true);
			ssnTextField.setEditable(false);
			dobDatePicker.setDisable(true);
			feetComboBox.setDisable(true);
			inchesComboBox.setDisable(true);
			weightSpinner.setDisable(true);

			editPatInfoButton.setText("Edit Patient");
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deletePatButtonPressed() {
		Alert alert = new Alert(AlertType.CONFIRMATION,"", ButtonType.YES, ButtonType.NO);
		alert.setTitle("Confirm Delete");
		alert.setHeaderText("Are you sure you want to delete this Patient?");
		//alert.setContentText("Make sure you have filled in all information necessary to create an appointment.");

		Optional<ButtonType> result = alert.showAndWait();

		if(result.get() == ButtonType.YES) {
			//Delete the appointment if they click yes
			Patient p = appointmentTableView.getSelectionModel().getSelectedItem().getPatient();
			patientList.remove(p);
		}
	}

	public void appointmentsTabClicked() {
		/*if(editPatInfoButton.getText().equals("Save Patient")) {
			Alert alert = new Alert(AlertType.CONFIRMATION,"", ButtonType.YES, ButtonType.NO);
			alert.setTitle("Save Patient");
			alert.setHeaderText("There are unsaved changes. Would you like to save the patient info?");
			//alert.setContentText("Make sure you have filled in all information necessary to create an appointment.");

			Optional<ButtonType> result = alert.showAndWait();

			if(result.get() == ButtonType.YES) {
				Patient temp = appointmentTableView.getSelectionModel().getSelectedItem().getPatient();
				Patient p = new Patient();
				p.setLastName(lastNameTextField.getText());
				p.setFirstName(firstNameTextField.getText());
				p.setMiddleInit(middleNameTextField.getText());
				boolean isMale = false;
				if(genderComboBox.getValue().equals("Male")) {
					isMale = true;
				}
				p.setMale(isMale);
				if(ethnicityComboBox.getValue().equals("Other")) {
					p.setEthnicity(otherEthnicityTextField.getText());
				} else {
					p.setEthnicity(ethnicityComboBox.getValue());
				}
				p.setSsn(ssnTextField.getText());
				p.setDob(dobDatePicker.getValue());
				int height = feetComboBox.getValue() * 12 + inchesComboBox.getValue();
				p.setHeight(height);
				p.setWeight(weightSpinner.getValue());
				p.setPhoneNum(phoneNumTextField.getText());
				p.setEmail(emailTextField.getText());
				p.setAddress(addressTextField.getText());
				p.setCity(cityTextField.getText());
				p.setState(stateTextField.getText());
				p.setZip(zipTextField.getText());

				patientList.remove(temp);
				patientList.add(p);
			}
		}*/
	}

	public void newPatButtonPressed(){
		if(newPatButton.getText().equals("New Patient")){
			editPatInfoButton.setDisable(true);
			deletePatButton.setDisable(true);
			newPatButton.setText("Save Patient");

			firstNameTextField.setEditable(true);
			lastNameTextField.setEditable(true);
			middleNameTextField.setEditable(true);
			genderComboBox.setDisable(false);
			ethnicityComboBox.setDisable(false);
			ssnTextField.setEditable(true);
			dobDatePicker.setDisable(false);
			feetComboBox.setDisable(false);
			inchesComboBox.setDisable(false);
			weightSpinner.setDisable(false);
		} else if(newPatButton.getText().equals("Save Patient")){
			String fn, ln, mi, eth, ssn;
			LocalDate dob;
			int height, weight;
			boolean isMale;

			fn = firstNameTextField.getText();
			ln = lastNameTextField.getText();
			mi = middleNameTextField.getText();
			if(genderComboBox.getValue().equals("Male")){
				isMale = true;
			} else{
				isMale = false;
			}
			if(ethnicityComboBox.getValue().equals("Other")){
				eth = otherEthnicityTextField.getText();
			} else{
				eth = ethnicityComboBox.getValue();
			}
			ssn = ssnTextField.getText();
			dob = dobDatePicker.getValue();
			Date dobDate = Date.valueOf(dob);
			height = feetComboBox.getValue() * 12 + inchesComboBox.getValue();
			weight = weightSpinner.getValue();

			String query = "INSERT INTO patient(FName, LName, Minitial, DOB, SSN, Height, Race, Gender, Weight) VALUES (?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, fn);
				pst.setString(2, ln);
				pst.setString(3, mi);
				pst.setDate(4, dobDate);
				pst.setString(5, ssn);
				pst.setInt(6, height);
				pst.setString(7, eth);
				pst.setBoolean(8, isMale);
				pst.setInt(9, weight);
				
				boolean rs = pst.execute();
				
				if(rs) {
					System.out.println("Registration Successful!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			editPatInfoButton.setDisable(false);
			deletePatButton.setDisable(false);
			newPatButton.setText("New Patient");

			firstNameTextField.setEditable(false);
			lastNameTextField.setEditable(false);
			middleNameTextField.setEditable(false);
			genderComboBox.setDisable(true);
			ethnicityComboBox.setDisable(true);
			ssnTextField.setEditable(false);
			dobDatePicker.setDisable(true);
			feetComboBox.setDisable(true);
			inchesComboBox.setDisable(true);
			weightSpinner.setDisable(true);
			otherEthnicityTextField.setEditable(false);
		}
	}
}
