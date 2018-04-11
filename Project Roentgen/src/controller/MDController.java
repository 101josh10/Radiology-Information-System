package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import model.Patient;
import model.PatientImage;
import model.Report;

public class MDController 
{
	//Set up the Main M.D. Tab Fields
	@FXML private ListView<Patient> mdListView = new ListView();
	@FXML private ImageView images = new ImageView();
	@FXML private ListView<Patient> mdPatientInfoListView = new ListView();
	@FXML private ImageView radiologistReportImageView = new ImageView();
	@FXML private ListView<Patient> patientRequestListView = new ListView();
	//Set up the M.D. Request Tab Fields
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
	
	//M.D. View Current Patients Click
	mdListview.@setOnItemClickListener(new OnItemClickListener() 
	{
		public void OnItemClick()
		{
			//images set
			images.
			//patient Info set
			//Report set
		}
	});
	
	//Database Stuff
	
}
