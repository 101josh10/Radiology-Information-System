package controller;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Patient;
import model.Report;

public class RadiologistController {
	//* Declare View Properties

	//** Patients Tab
	@FXML private Tab patientsTab;
	//*** Patients Table
	@FXML private TableView<Patient> patientTableView;
	@FXML private TableColumn<Patient, String> lastNameTableColumn;
	@FXML private TableColumn<Patient, String> firstNameTableColumn;
	@FXML private TableColumn<Patient, String> dobTableColumn;
	@FXML private TableColumn<Patient, String> ssnTableColumn;
	@FXML private TableColumn<Patient, Calendar> lastApptTableColumn;
	@FXML private TableColumn<Patient, Calendar> nextApptTableColumn;

	//** Images Tab
	@FXML private Tab patImagesTab;

	//*** Images Table
	@FXML private TableView<Patient> imageTableView;
	@FXML private TableColumn<Patient, Image> thumbnailTableColumn;
	@FXML private TableColumn<Patient, String> descriptionTableColumn;
	@FXML private TableColumn<Patient, String> dateAddedTableColumn;

	//*** Image Section
	@FXML private ImageView mainImageView;
	@FXML private Label zoomLabel;

	//** Report Tab
	@FXML private Tab patReportTab;
	@FXML private TableView<Report> reportTableView;
	@FXML private TableColumn<Report, String> TitleTableColumn;
	@FXML private TableColumn<Report, String> reportDateAddedTableColumn;
	@FXML private TableColumn<Report, String> lastRevisedTableColumn;
	@FXML private TableColumn<Report, Button> downloadTableColumn;
	@FXML private TableColumn<Report, Button> uploadTableColumn;
	@FXML private Label uploadFilePath;

	//* Declare Other Variables
	private ObservableList<Patient> patientList = FXCollections.observableArrayList();
	private Patient selectedPatient;
	private File reportFile;

	public void initialize(){
		pullInfoFromDB();
		addStaticPatientData();
		//Configure Patient Table Columns
		lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
		firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
		dobTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("dobString"));
		ssnTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("ssn"));
		lastApptTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, Calendar>("mostRecentAppt"));
		nextApptTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, Calendar>("nextAppt"));

		patientTableView.setItems(patientList);
	}

	/*addStaticPatientData()
	 * Purpose:
	 * To create static patients to use for testing
	 */
	public void addStaticPatientData(){
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
	}

	/* pullInfoFromDB
	 * Purpose:
	 * To connect to the PACS system and pull all
	 * available patients along with their images and reports
	 */
	public void pullInfoFromDB(){
		try {
			Class.forName("com.mySQL.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sonoo", "root", "root"); //Input with correct info
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Patients"); //Double check name of Patient table
		while(rs.next()){
			Patient temp = new Patient();
			temp.setId(rs.getInt(1));
			temp.setFirstName(rs.getString(2));
			//so on so forth for all properties
			//need to see layout of DB in order to properly implement this
			patientList.add(temp);
		}

		rs = stmt.executeQuery("select * from Images");
		while(rs.next()){

		}

		rs = stmt.executeQuery("select * from Reports");
		while(rs.next()){

		}
		con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* patientSelected()
	 * Purpose: Assigns the selectedPatient variable the value of the
	 * patient that the user selects from the table of patients.
	 *
	 * Triggered when user selects a patient from the table of available patients.
	 */
	public void patientSelected(){

	}

	/* selectFilePressed()
	 * Purpose: Opens a file selection window for the user to select a file from the system
	 *
	 * Triggered when user presses the select file button from the Patient report screen
	 */
	public void selectFilePressed(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Report File");

		reportFile = fileChooser.showOpenDialog(null);

		if(reportFile != null){
			//System.out.println("File Name: " + reportFile.getName()); //uncomment for testing
			//System.out.println("File Path: " + reportFile.getPath()); //uncomment for testing
			uploadFilePath.setText(reportFile.getPath());
		} else {
			System.out.println("File Selection Cancelled");
			uploadFilePath.setText("user/directory/file.docx");
		}
	}

	/*uploadButtonPressed
	 * Purpose: takes the selected file and uploads it to the database
	 *
	 * Triggered when user presses the upload button in the Reports tab
	 */
	public void uploadButtonPressed(){
		if(reportFile == null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("No File Selected");
			alert.setContentText("You have not selected a file.\nTo select a file, press the \"Select File\" button and choose a file.");

			alert.showAndWait();
		} else {
			//upload reportFile to PACS
			//if(uploadSuccessful)
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Upload Successful!");
			alert.setHeaderText("Your upload was successful");
			alert.setContentText("Press \"OK\" to reload table");

			alert.showAndWait();
		}
	}
}
