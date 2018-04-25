package controller;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.DBConnection;
import model.Patient;
import model.Report;
import model.PatientImage;

public class RadiologistController {
	//* Declare View Properties
	@FXML private TabPane radiologistTabPane;
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
	@FXML private TableView<PatientImage> imageTableView;
	@FXML private TableColumn<PatientImage, Image> thumbnailTableColumn;
	@FXML private TableColumn<PatientImage, String> descriptionTableColumn;
	@FXML private TableColumn<PatientImage, Calendar> dateAddedTableColumn;
	@FXML private Label selectedPatientImageLabel;

	//*** Image Section
	@FXML private ImageView mainImageView;
	@FXML private Label zoomLabel;

	//** Report Tab
	@FXML private Tab patReportTab;
	@FXML private TableView<Report> reportTableView;
	@FXML private TableColumn<Report, String> titleTableColumn;
	@FXML private TableColumn<Report, String> reportDateAddedTableColumn;
	@FXML private TableColumn<Report, Button> downloadTableColumn;
	@FXML private Label uploadFilePath;
	@FXML private Label selectedPatientReportLabel;
	@FXML private TextField titleTextField;

	//* Declare Other Variables
	private ObservableList<Patient> patientList = FXCollections.observableArrayList();
	private ObservableList<Report> patientReports = FXCollections.observableArrayList();
	private ObservableList<PatientImage> patientImages = FXCollections.observableArrayList();
	private Patient selectedPatient;
	private File reportFile;
	Connection conn = DBConnection.dbConnection();

	public void initialize(){
		pullInfoFromDB();
		//addStaticPatientData();
		//Configure Patient Table Columns
		lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
		firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
		dobTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("dobString"));
		ssnTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("ssn"));
		lastApptTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, Calendar>("mostRecentAppt"));
		nextApptTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, Calendar>("nextAppt"));

		patientTableView.setItems(patientList);

		//Configure Image Table Columns
		thumbnailTableColumn.setCellValueFactory(new PropertyValueFactory<PatientImage, Image>("thumbnail"));
		descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<PatientImage, String>("description"));
		dateAddedTableColumn.setCellValueFactory(new PropertyValueFactory<PatientImage, Calendar>("dateAdded"));

		//ConfigureReportTableColumns
		titleTableColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("title"));
		reportDateAddedTableColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("dateString"));
		downloadTableColumn.setCellValueFactory(new PropertyValueFactory<Report, Button>("downloadButton"));

		patientTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		                selectedPatient = patientTableView.getSelectionModel().getSelectedItem();
		            	//System.out.println(selectedPatient.getFirstName() + " " + selectedPatient.getLastName() + " selected."); //uncomment for testing purposes
		                String displayText = selectedPatient.getLastName() + ", " + selectedPatient.getFirstName();
		                selectedPatientImageLabel.setText(displayText);
		                selectedPatientReportLabel.setText(displayText);

		                //Select Images for selectedPatient; populate patientImages
		                imageTableView.setItems(patientImages);
		                //Select Reports for selectedPatient; populate patientReports
		                reportTableView.setItems(patientReports);
		                radiologistTabPane.getSelectionModel().selectNext();
		            }
		        }
		    }
		});
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

		Report e = new Report();
		e.setTitle("Test");
		File testFile = new File("/Users/jmknig0314/Downloads/Baker_2.png");
		e.setReportFile(testFile);
		patientReports.add(e);
	}

	/* pullInfoFromDB
	 * Purpose:
	 * To connect to the PACS system and pull all
	 * available patients along with their images and reports
	 */
	public void pullInfoFromDB(){
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
		
		patientTableView.setItems(patientList);
	}

	/* selectFilePressed()
	 * Purpose: Opens a file selection window for the user to select a file from the system
	 *
	 * Triggered when user presses the select file button from the Patient report screen
	 */
	public void selectFilePressed(){
		if(selectedPatient == null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("No Patient Selected");
			alert.setContentText("You must select a patient from the Patients tab.");

			alert.showAndWait();
		}
		else{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select Report File");

			reportFile = fileChooser.showOpenDialog(null);


			if(reportFile != null){
				//System.out.println("File Name: " + reportFile.getName()); //uncomment for testing
				//System.out.println("File Path: " + reportFile.getPath()); //uncomment for testing
				uploadFilePath.setText(reportFile.getPath());
			} else {
				//ystem.out.println("File Selection Cancelled");//uncomment for testing
				uploadFilePath.setText("user/directory/file.docx");
			}
		}
	}

	/*uploadButtonPressed
	 * Purpose: takes the selected file and uploads it to the database
	 *
	 * Triggered when user presses the upload button in the Reports tab
	 */
	public void uploadButtonPressed(){
		if(selectedPatient == null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("No Patient Selected");
			alert.setContentText("You must select a patient from the Patients tab.");

			alert.showAndWait();
		}
		else if(reportFile == null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("No File Selected");
			alert.setContentText("You have not selected a file.\nTo select a file, press the \"Select File\" button and choose a file.");

			alert.showAndWait();
		} else if(titleTextField.getText().equals("") || titleTextField.getText() == null){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Title Blank");
			alert.setContentText("You must name your report.");

			alert.showAndWait();
		}
		else {
			//upload reportFile to PACS
			//if(uploadSuccessful)
			Report newReport = new Report();
			newReport.setTitle(titleTextField.getText());
			newReport.setReportFile(reportFile);
			LocalDateTime thisDate = LocalDateTime.now();
			newReport.setDateAdded(thisDate);
			//System.out.println(thisDate.getTime().toString());//uncomment for testing
			patientReports.add(newReport);
			//upload report to DB
			reportTableView.refresh();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Upload Successful!");
			alert.setHeaderText("Your upload was successful");
			alert.setContentText("Press \"OK\" to reload table");

			alert.showAndWait();

			titleTextField.setText("");
			uploadFilePath.setText("user/directory/file.docx");

		}
	}
}
