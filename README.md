# pets-log-java-oop
Pets Log - Java OOP Project 

# 🐾 Missing Pets Log

The **Missing Pets Log** is a Java desktop application designed to make it easier to keep track of pets that are lost or found. It helps users manage reports by logging entries that can be updated as the pet’s status changes. The project offers a simple interface for reporting lost or found pets with details such as name, type, description, and contact information. It applies the concepts of object-oriented programming (OOP), error handling, file handling, and user interface design using JavaFX.

The application provides features to log, edit, update, and archive pet entries. It includes a dashboard that lists all lost and found pets, with functions for searching, sorting, and displaying complete details for each entry. Users can view demographic charts for lost pets, helping visualize data by type and percentage.

The system is built in **Java 17** using **NetBeans Apache** as the IDE. It uses **DefaultListModel** and **ArrayList** for file handling (temporary data storage) and **JavaFX** for the user interface. It runs on **Windows**, **macOS**, or **Linux** with JDK 17 or higher, and does not require additional software.

---

## Features

- **Log Lost Pets:** Users can create new entries for lost pets with name, breed, age, color, distinguishing features, and contact details.  
- **Log Found Pets:** Allows users to submit reports for found pets, including description and contact details of the finder.  
- **Update Status:** Users can change a report’s status from “Lost” to “Found” or vice versa.  
- **Edit Entries:** Existing logs can be modified to update information such as name, description, and contact details.  
- **Dashboard:** Displays all current lost and found pet reports, includes search and sorting functions by name or date, and provides a display function for complete records. It also shows a lost pet demographics chart summarizing the pet types and their percentages.  
- **Archive Reports:** Old or inactive reports can be archived to keep the dashboard clean while preserving historical data. Archived reports can still be searched and retrieved when needed.

---

## Usage

To use the project:
1. Open the project in **NetBeans Apache**.  
2. Compile and run it using the IDE.  
3. Use the user interface to add, edit, or update pet logs.

Each Lost or Found Pet entry includes:
- Name  
- Type  
- Contact  
- Description  
- Status (Lost or Found)  
- Last Seen At / Found At  
- Date Lost / Date Found  

When an entry is added or updated, a confirmation message appears, and the updated list of entries is displayed.

---

## Object-Oriented Design and Implementation

The project is designed around object-oriented principles, including **abstraction**, **inheritance**, and **encapsulation**. It uses an interface and several related classes to handle different types of pet logs.

The `Loggable` interface defines the base behavior for logging pet entries through the method `logEntry()`. This interface is implemented by the classes `LostPetLog`, `FoundPetLog`, and `ArchivePetLog`.

The main class `PetLog` represents the shared structure for all pet records. It contains attributes such as `id`, `name`, `type`, `description`, `status`, and `contact`, with getter and setter methods for each property. The `logEntry()` method in `PetLog` returns a formatted summary of a pet’s details.

`LostPetLog` extends `PetLog` and adds two specific attributes: `lastSeen` (the last known location) and `dateLost` (the date the pet was lost). It overrides `logEntry()` to include these additional details in the formatted string representation of a lost pet report.

`FoundPetLog` also extends `PetLog` but includes the attributes `foundAt` (the location where the pet was found) and `dateFound` (the date it was found). Like `LostPetLog`, it overrides `logEntry()` to provide a formatted representation of found pet entries.

`ArchivePetLog` extends `PetLog` as well, representing pets that have been archived after being missing. It includes the attributes `lastSeen` and `dateLost`, similar to `LostPetLog`, and provides a specialized `logEntry()` output indicating that the record is archived.

Together, these classes demonstrate proper use of inheritance to share common logic while customizing behavior for each specific type of pet record.

---

## Example Use Cases

A user opens the Missing Pets Log and adds a new record for a lost pet, entering all required information such as the name, type, description, and contact details. Later, the pet is found, and the user simply changes the status of the same record from “Lost” to “Found.” The user can also edit or update the information at any time to correct or expand the description. When older reports are no longer active, they can be archived, but still searched or viewed later if needed.

---

## Troubleshooting

- **Application not running:** Ensure that Java 17 or a higher version is properly installed.  
- **Compile errors:** Verify that JavaFX dependencies are correctly configured in NetBeans.  

---

## Future Improvements

Possible enhancements for future versions include:
- Adding image upload support for each pet entry.  
- Implementing a database management system (DBMS) for permanent storage and improved search capabilities, especially for pets with identical names.  
- Introducing filters to refine searches by pet type or category.  
- Adding a notification system to alert users when a pet’s status changes or when similar pets are reported.  

---

## Author

**John Maurice Sison**  
Student Developer — Missing Pets Log  
Developed using **Java 17**, **JavaFX**, and **Object-Oriented Programming principles**.
