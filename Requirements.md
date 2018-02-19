# Requirements
---
## Overall

### Functional
1. The RIS should be able to provide a login system, with users that have different permissions.
2. The RIS should be able to shedule patients for different radiology exams
3. The RIS should be able send patient records to the PACS for storage
4. The RIS should be compatable with different modalities.
5. The RIS should be able to display the aquired images.
6. The RIS should be able to create reports.

### Non-Functional
1. The RIS should be able to pull the user's data off of a database.
2. The RIS will be able to view occupied time slots and be able to create time slots in empty areas.
3. The RIS will be able to upload data to the database (PACS)
4. The different modalities will produce similar file types that can be displayed on the reading stations.
5. The reading station will be able to read images from the PACS with its original resolution.
6. The reading station will be able to support the creation and editing of fillable pdfs

---
## Radiologist

### Functional
1. Images are tagged with descriptions to distinguish between images
2. Facilitate the ability to create, edit, and delete images.
3. Create the ability to do reports

### Non-Functional
1. Images have text descriptions attached to them along with timestamps, patient ID
2. Create a button that allows the image fields to become editable
3. Create a fillable pdf that allows the radiologist to fill in the details of the report.

---
## Scheduling

### Functional
1. User should be able to select a day and see the timeslots occupied that day
2. User should be able to see the occupied modality at any given time
3. User should be able to add/edit/delete appointments
4. User should be able to search for when a given modality will be available.

### Non-Functional
1. The User should be able to see the dates in a calendar format in order to select which day. Once selected, the timeslots will automatically update.
2. The system will use a table type system with different timeslots with the occupied timeslots blocked off.
3. There will be an add button. If you click on an occupied timeslot, you will be given options to edit the appointment or delete it completely.
4. The user should be able to choose which modalities to view or hide in order to view which timeslots are open for any given modality.
