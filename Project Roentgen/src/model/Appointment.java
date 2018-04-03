package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Appointment {
	private LocalDate date;
	private LocalDateTime time;
	private String displayTime;
	private Patient patient;
	private String modality;
	private String bodyPart;
	private String desc;
	
	public Appointment() {
		
	}

	public LocalDate getDate() {
		return date;
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

	public void setDateTime(LocalDate date, LocalDateTime time) {
		this.date = date;
		this.time = time;
		int hours = time.getHour();
		int mins = time.getMinute();
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
		
		displayTime += mins + afternoon;
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
