package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Patient {
	private int id;
	private String lastName, firstName, middleInit;
	private String displayName;
	private String ssn;
	private LocalDate dob;
	private String dobString;
	private String protectedSSN;
	private int weight, height;
	private boolean isMale;
	private String ethnicity;
	private int feet, inches;

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

	public String getMiddleInit() {
		return middleInit;
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

	public int getWeight() {
		return weight;
	}

	public int getHeight() {
		return height;
	}

	public boolean isMale() {
		return isMale;
	}

	public String getEthnicity() {
		return ethnicity;
	}
	
	public String getProtectedSSN() {
		return protectedSSN;
	}
	
	public int getFeet() {
		return feet;
	}
	
	public int getInches() {
		return inches;
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

	public void setMiddleInit(String middleInit) {
		this.middleInit = middleInit;
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

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setHeight(int height) {
		this.height = height;
		this.feet = height / 12;
		this.inches = height % 12;
	}

	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
}
