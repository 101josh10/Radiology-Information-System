package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class Appointment {
	private int id;
	private LocalDateTime dateTime;
	private String displayTime;
	private Patient patient;
	private String modality;
	private String bodyPart;
	private String desc;
	private String patientName;
	private String last4;
	
	public Appointment() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public Patient getPatient() {
		return patient;
	}

	public String getBodyPart() {
		return bodyPart;
	}

	public String getDesc() {
		return desc;
	}
	
	public String getDisplayTime() {
		return displayTime;
	}

	public String getModality() {
		return modality;
	}
	
	public String getLast4() {
		return last4;
	}
	
	public String getPatientName() {
		return patientName;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
		LocalTime time = LocalTime.of(dateTime.getHour(), dateTime.getMinute());
		displayTime = time.toString();
		//System.out.println(displayTime); //uncomment for testing
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
		patientName = patient.getDisplayName();
		last4 = patient.getProtectedSSN();
	}

	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}
	
	/*public boolean equals(Appointment appt) {				//Doesnt work yet. Returns false even if appt1 = appt2
		boolean equals = false;
		if(this.dateTime.equals(appt.getDateTime()) &&
				this.bodyPart.equals(appt.getBodyPart()) &&
				this.desc.equals(appt.getBodyPart()) &&
				this.modality.equals(appt.getModality()) &&
				this.last4.equals(appt.getLast4()) &&
				this.patient.equals(appt.getPatient())) {
			equals = true;
		}
		
		return equals;
	}*/
}
