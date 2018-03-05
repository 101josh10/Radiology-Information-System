package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	@FXML private TableColumn<Patient, String> ssnNameTableColumn;
	@FXML private TableColumn<Patient, String> lastApptTableColumn;
	@FXML private TableColumn<Patient, String> nextApptTableColumn;

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

	public void initialize(){

	}
}
