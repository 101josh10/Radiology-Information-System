package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Patient {
	private int id;
	private String lastName, firstName, middleName;
	private String displayName;
	private String ssn;
	private LocalDate dob;
	private String dobString;
	private String protectedSSN;
	private String phoneNum, email;
	private String address, city, state, zip;
	private int weight, height;
	private boolean isMale;
	private String race;
	private LocalDate mostRecentAppt;
	private LocalDate nextAppt;

	private ArrayList<LocalDateTime> appointmentHistory = new ArrayList<>();

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
	
	public String getDisplayName() {
		return displayName;
	}

	public String getSsn() {
		return ssn;
	}

	public LocalDate getDob() {
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
	
	public String getProtectedSSN() {
		return protectedSSN;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		this.displayName = lastName + ", " + firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		this.displayName = lastName + ", " + firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
		protectedSSN = "xxx-xx-";
		if(ssn.length() == 9) {
			protectedSSN += ssn.substring(5);
		} else {
			protectedSSN += ssn.substring(7);
		}
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
		int year = dob.getYear();
		int day = dob.getDayOfMonth();
		int month = dob.getMonthValue();

		dobString = month + "/" + day + "/" + year;
		//System.out.println(dobString);//uncomment for testing
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

	public LocalDate getMostRecentAppointment(){
		

		return mostRecentAppt;
	}

	public LocalDate getNextAppointment(){

		return nextAppt;
	}

	private void sortApptHistory(){
		ArrayList<LocalDateTime> temp = appointmentHistory;
		//Comparator<Calendar> comparator = Comparator.comparingLong(keyExtractor);

		//FXCollections.sort(appointmentHistory, comparator);
	}
}
