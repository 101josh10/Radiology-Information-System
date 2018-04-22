package controller;
import java.util.Calendar;

import javax.swing.JOptionPane;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import model.DBConnection;
import model.Patient;
import model.PatientImage;
import model.Report;

public class MDController 
{
	//Set up the Main M.D. Tab Fields
	@FXML private Tab mdTab = new Tab();
	@FXML private TableView<Patient> mdTableView = new TableView();
	@FXML private TableColumn<Patient, String> mdCurrentPatientsTableColumn = new TableColumn();
	@FXML private TableView<PatientImage> imagesTableView = new TableView();
	@FXML private TableColumn<PatientImage, Image> thumbnailTableColumn = new TableColumn();
	@FXML private TableColumn<PatientImage, String> descriptionTableColumn = new TableColumn();
	@FXML private TableColumn<PatientImage, Calendar> dateAddedTableColumn = new TableColumn();
	@FXML private TableView<Patient> mdPatientInfoTableView = new TableView<Patient>();
	@FXML private TableColumn<Patient, String> mdPatientInfoTableColumn = new TableColumn();
	@FXML private TableView radiologistReportTableView = new TableView();
	@FXML private TableColumn<Report, String> radiologistReportTitleColumn = new TableColumn();
	@FXML private TableColumn<Report, Button> radiologistReportDownloadColumn = new TableColumn();
	//Set up the M.D. Request Tab Fields
	@FXML private Tab patientRequestTab = new Tab();
	@FXML private TableView<Patient> mdPatientRequestTableView = new TableView();
	@FXML private TableColumn<Patient, String> mdPatientIDTableColumn = new TableColumn();
	@FXML private TableColumn<Patient, String> mdPatientFirstNameTableColumn = new TableColumn();
	@FXML private TableColumn<Patient, String> mdPatientLastNameTableColumn = new TableColumn();
	@FXML private TextField patientId = new TextField();
	@FXML private TextField patientFirstName = new TextField();
	@FXML private TextField patientLastName = new TextField();
	@FXML private TextField requestBodyPart = new TextField();
	@FXML private TextArea requestDescription = new TextArea();
	@FXML private ChoiceBox procedureType = new ChoiceBox();
	@FXML private Button submitRequest = new Button();
	//Other needed variables
	private ObservableList<Patient> patientList = FXCollections.observableArrayList();
	private ObservableList<Report> patientReports = FXCollections.observableArrayList();
	private ObservableList<PatientImage> patientImages = FXCollections.observableArrayList();
	private Patient selectedPatient;
	
	//M.D. Current Patients Setup
	public void initialize(){
		//Using radiologist for now until I figure out database stuff
		RadiologistController rc = new RadiologistController();
		rc.pullInfoFromDB();
		rc.addStaticPatientData();
		//Configure Patient Table Columns
		mdCurrentPatientsTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName" + " " + "lastName"));
		mdPatientIDTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientID"));
		mdPatientFirstNameTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
		mdPatientLastNameTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
		mdPatientInfoTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientInfo"));

		mdPatientRequestTableView.setItems(patientList);
		mdTableView.setItems(patientList);

		//Configure Image Table Columns
		thumbnailTableColumn.setCellValueFactory(new PropertyValueFactory<PatientImage, Image>("thumbnail"));
		descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<PatientImage, String>("description"));
		dateAddedTableColumn.setCellValueFactory(new PropertyValueFactory<PatientImage, Calendar>("dateAdded"));

		//ConfigureReportTableColumns
		radiologistReportTitleColumn.setCellValueFactory(new PropertyValueFactory<Report, String>("title"));
		radiologistReportDownloadColumn.setCellValueFactory(new PropertyValueFactory<Report, Button>("downloadButton"));
		
		//Setting up the choice box
		ObservableList<String> options = FXCollections.observableArrayList("X-ray","MRI","Ultrasound","CT-Scan");
		procedureType.setValue(options);

		//M.D. View Current Patients Click
		mdTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		                selectedPatient = mdTableView.getSelectionModel().getSelectedItem();
		            	//System.out.println(selectedPatient.getFirstName() + " " + selectedPatient.getLastName() + " selected."); //uncomment for testing purposes
		                mdPatientInfoTableView.setItems(patientList);

		                //Select Images for selectedPatient; populate patientImages
		                imagesTableView.setItems(patientImages);
		                //Select Reports for selectedPatient; populate patientReports
		                radiologistReportTableView.setItems(patientReports);
		            }
		        }
		    }
		});
		//Patient Request Current Patients Click
		mdPatientRequestTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		                selectedPatient = mdTableView.getSelectionModel().getSelectedItem();
		            	//System.out.println(selectedPatient.getFirstName() + " " + selectedPatient.getLastName() + " selected."); //uncomment for testing purposes
		                
		                //Fill in the text fields
		                patientId.setText(Integer.toString(selectedPatient.getId()));
		                patientFirstName.setText(selectedPatient.getFirstName());
		                patientLastName.setText(selectedPatient.getLastName());
		            }
		        }
		    }
		});
		submitRequest.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			public void handle(MouseEvent mouseEvent) 
			{
				if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
				{
					//send request to technician
					//JOptionPane.showMessageDialog(MDController,"Request Has been sent to the Technician");
				}
			}
		});;
	}
	
	
	
	
	//Database Stuff
	
}
