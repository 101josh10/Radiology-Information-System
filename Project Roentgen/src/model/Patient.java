package model;

import java.sql.Date;
import java.util.Calendar;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Patient {
	private int id;
	private String lastName, firstName, middleName;
	private String ssn;
	private Calendar dob;
	private String dobString;
	private String phoneNum, email;
	private String address, city, state, zip;
	private int weight, height;
	private boolean isMale;
	private String race;
	private Calendar mostRecentAppt;
	private Calendar nextAppt;

	private ObservableList<Calendar> appointmentHistory = FXCollections.observableArrayList();

	public Patient() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getSsn() {
		return ssn;
	}

	public Calendar getDob() {
		return dob;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public int getWeight() {
		return weight;
	}

	public int getHeight() {
		return height;
	}

	public boolean isMale() {
		return isMale;
	}

	public String getRace() {
		return race;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
		int year = dob.getTime().getYear();
		int day = dob.getTime().getDate();
		int month = dob.getTime().getMonth();

		dobString = month + "/" + day + "/" + year;
		System.out.println(dobString);
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Calendar getMostRecentAppointment(){
		Calendar today = Calendar.getInstance();


		return mostRecentAppt;
	}

	public Calendar getNextAppointment(){

		return nextAppt;
	}

	private void sortApptHistory(){
		ObservableList<Calendar> temp = appointmentHistory;
		//Comparator<Calendar> comparator = Comparator.comparingLong(keyExtractor);

		//FXCollections.sort(appointmentHistory, comparator);
	}
}
