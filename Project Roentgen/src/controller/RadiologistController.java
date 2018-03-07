package controller;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	@FXML private TableColumn<Patient, Date> dobTableColumn;
	@FXML private TableColumn<Patient, String> ssnTableColumn;
	@FXML private TableColumn<Patient, Date> lastApptTableColumn;
	@FXML private TableColumn<Patient, Date> nextApptTableColumn;

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

	//* Declare Other Variables
	private ObservableList<Patient> patientList = FXCollections.observableArrayList();

	public void initialize(){
		pullInfoFromDB();

		//Configure Patient Table Columns
		lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
		firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
		dobTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, Date>("dob"));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
