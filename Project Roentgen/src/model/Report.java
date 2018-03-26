package model;

import java.io.File;
import java.util.Calendar;

import javafx.scene.control.Button;

public class Report {
	private File reportFile;
	private String title;
	private Calendar dateAdded;
	private Button downloadButton;

	public Report(){
		downloadButton = new Button();
		downloadButton.setText("Download");
	}

	public File getReportFile() {
		return reportFile;
	}

	public String getTitle() {
		return title;
	}

	public Calendar getDateAdded() {
		return dateAdded;
	}

	public Button getDownloadButton() {
		return downloadButton;
	}

	public void setReportFile(File reportFile) {
		this.reportFile = reportFile;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDateAdded(Calendar dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	
}
