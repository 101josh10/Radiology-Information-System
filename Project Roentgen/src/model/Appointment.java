package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class Appointment {
	private LocalDateTime dateTime;
	private String displayTime;
	private Patient patient;
	private String modality;
	private String bodyPart;
	private String desc;
	
	public Appointment() {
		
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

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
		LocalTime time = LocalTime.of(dateTime.getHour(), dateTime.getMinute());
		displayTime = time.toString();
		//System.out.println(displayTime); //uncomment for testing
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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
}
