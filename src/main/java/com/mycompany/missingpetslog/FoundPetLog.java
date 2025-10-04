/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missingpetslog;

public class FoundPetLog extends PetLog {
    private String foundAt;
    private String dateFound;

    public FoundPetLog(int id, String name, String type, String description, String contact, String foundAt, String dateFound) {
        super(id, name, type, description, "found", contact);
        this.foundAt = foundAt;
        this.dateFound = dateFound;
    }

    public String getFoundAt() {
        return foundAt;
    }

    public void setFoundAt(String foundAt) {
        this.foundAt = foundAt;
    }

    public String getDateFound() {
        return dateFound;
    }

    public void setDateFound(String dateFound) {
        this.dateFound = dateFound;
    }
    
    @Override
    public String logEntry() {
        return super.logEntry() + "\nFound on: " + getDateFound() + "\nFound at: " + getFoundAt();
    }
}