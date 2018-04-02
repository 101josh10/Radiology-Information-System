package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;

public class MDController 
{
	
	@FXML private ListView mdListView = new ListView();
	@FXML private AnchorPane images = new AnchorPane();
	@FXML private ListView mdPatientInfoListView = new ListView();
	@FXML private MediaView radiologistReportMediaView = new MediaView();
	@FXML private ListView patientRequestListView = new ListView();
	@FXML private TextField patientId = new TextField();
	@FXML private TextField patientFirstName = new TextField();
	@FXML private TextField patientLastName = new TextField();
	@FXML private TextField requestBodyPart = new TextField();
	@FXML private TextArea requestDescription = new TextArea();
	@FXML private ChoiceBox procedureType = new ChoiceBox();
	@FXML private Button submitRequest = new Button();
}
