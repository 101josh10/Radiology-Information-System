package model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Appointment {
	private LocalDate dateTime;
	private String displayTime;

	private Patient patient;
	private String modality;
	private String bodyPart;
	private String desc;
	
	public Appointment() {
		
	}

	public LocalDate getDateTime() {
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

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
		Date time = this.dateTime.getTime();
		int hours = time.getHours();
		int mins = time.getMinutes();
		String afternoon = " AM";
		if(hours >= 12) {
			afternoon = " PM";
		}
		if(hours > 12) {
			hours %= 12;
		}
		
		displayTime = hours + ":";
		if(mins < 10) {
			displayTime.concat("0");
		}
		
		displayTime.concat(mins + afternoon);
		System.out.println(displayTime); //uncomment for testing
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
