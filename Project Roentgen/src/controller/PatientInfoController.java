package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PatientInfoController {

	@FXML private TextField firstNameTextField;
	@FXML private TextField lastNameTextField;
	@FXML private TextField middleInitialTextField;
	@FXML private ComboBox<String> genderComboBox;
	@FXML private ComboBox<String> ethnicityComboBox;
	@FXML private TextField otherEthnicityTextField;
	@FXML private TextField ssnTextField;
	@FXML private DatePicker dobDatePicker;
	@FXML private TextField phoneNumTextField;
	@FXML private TextField emailTextField;
	@FXML private TextField addressTextField;
	@FXML private TextField stateTextField;
	@FXML private TextField cityTextField;
	@FXML private TextField zipTextField;
	@FXML private Button editButton;
	
	public void initialize() {
		
	}
	
	public void middleInitialKeyTyped() {
		
	}
	
	public void ethnicitySelected() {
		
	}
	
	public void editButtonPressed() {
		
	}
	
	public void deleteButtonPressed() {
		
	}
}
