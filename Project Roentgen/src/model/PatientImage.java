package model;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class PatientImage {
	private Image rawImage;
	private Image thumbnail;
	private LocalDate dateAdded;
	private String description;
	
	public PatientImage(){
		
	}
	
	public Image getRawImage() {
		return rawImage;
	}
	
	public Image getThumbnail() {
		return thumbnail;
	}
	
	public LocalDate getDateAdded() {
		return dateAdded;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setRawImage(Image i) {
		this.rawImage = i;
	}
	
	public void setThumbnail(Image i) {
		this.thumbnail = i;
	}
	
	public void setDateAdded(LocalDate ld) {
		this.dateAdded = ld;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
}
