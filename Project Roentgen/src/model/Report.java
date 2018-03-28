package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class Report {
	private File reportFile;
	private String title;
	private Calendar dateAdded;
	private Button downloadButton;
	private String dateString;

	public Report(){
		downloadButton = new Button();
		downloadButton.setText("Download");
		downloadButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				/*FileChooser saveChooser = new FileChooser();
				File saveToPath = saveChooser.showSaveDialog(null);

				if(saveToPath != null){
					System.out.println(saveToPath.getPath());
				}
				else{
					System.out.println("Path is null");
				}*/
				FileChooser saveChooser = new FileChooser();
				File saveToPath = saveChooser.showSaveDialog(null);
				saveChooser.setTitle("Select Folder");
				saveChooser.setInitialDirectory(new File(System.getProperty("user.home")));


			}

		});
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
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		if (dateAdded != null) {
		dateString = sdf.format(dateAdded.getTime());
		}

		System.out.println(dateString);
	}


}
