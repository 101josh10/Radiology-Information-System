package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
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
	@FXML private ComboBox feetComboBox;
	@FXML private ComboBox inchesComboBox;
	@FXML private Spinner weightSpinner;
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
