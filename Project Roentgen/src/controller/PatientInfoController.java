package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import model.Patient;

public class PatientInfoController {

	//View Objects
	@FXML private TextField firstNameTextField;
	@FXML private TextField lastNameTextField;
	@FXML private TextField middleInitialTextField;
	@FXML private ComboBox<String> genderComboBox;
	@FXML private ComboBox<String> ethnicityComboBox;
	@FXML private TextField otherEthnicityTextField;
	@FXML private TextField ssnTextField;
	@FXML private DatePicker dobDatePicker;
	@FXML private ComboBox<Integer> feetComboBox;
	@FXML private ComboBox<Integer> inchesComboBox;
	@FXML private Spinner<Integer> weightSpinner;
	@FXML private TextField phoneNumTextField;
	@FXML private TextField emailTextField;
	@FXML private TextField addressTextField;
	@FXML private TextField stateTextField;
	@FXML private TextField cityTextField;
	@FXML private TextField zipTextField;
	@FXML private Button editButton;
	
	//Other variables
	ObservableList<String> ethnicityChoices = FXCollections.observableArrayList();
	ObservableList<String> genderChoices = FXCollections.observableArrayList();
	
	//Selected Patient's information
	private Patient patient = new Patient();
	
	public void initialize() {
		initEthnicities();
		initGenders();
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
	
	public void middleInitialKeyTyped() {
		
	}
	
	public void ethnicitySelected() {
		
	}
	
	public void editButtonPressed() {
		if(editButton.getText().equals("Edit Patient")) {
			firstNameTextField.setEditable(true);
			lastNameTextField.setEditable(true);
			middleInitialTextField.setEditable(true);
			genderComboBox.setDisable(false);
			ethnicityComboBox.setDisable(false);
			ssnTextField.setEditable(true);
			dobDatePicker.setDisable(false);
			feetComboBox.setDisable(false);
			inchesComboBox.setDisable(false);
			weightSpinner.setDisable(false);
			phoneNumTextField.setEditable(true);
			emailTextField.setEditable(true);
			addressTextField.setEditable(true);
			cityTextField.setEditable(true);
			stateTextField.setEditable(true);
			zipTextField.setEditable(true);
			
			editButton.setText("Save Patient");
		} else {
			
			firstNameTextField.setEditable(false);
			lastNameTextField.setEditable(false);
			middleInitialTextField.setEditable(false);
			genderComboBox.setDisable(true);
			ethnicityComboBox.setDisable(true);
			ssnTextField.setEditable(false);
			dobDatePicker.setDisable(true);
			feetComboBox.setDisable(true);
			inchesComboBox.setDisable(true);
			weightSpinner.setDisable(true);
			phoneNumTextField.setEditable(false);
			emailTextField.setEditable(false);
			addressTextField.setEditable(false);
			cityTextField.setEditable(false);
			stateTextField.setEditable(false);
			zipTextField.setEditable(false);
			
			editButton.setText("Edit Patient");
		}
	}
	
	public void deleteButtonPressed() {
		
	}
}
