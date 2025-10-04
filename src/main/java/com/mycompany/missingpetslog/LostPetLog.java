/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.missingpetslog;

public class LostPetLog extends PetLog {
    private String lastSeen;
    private String dateLost;

    public LostPetLog(int id, String name, String type, String description, String contact, String lastSeen, String dateLost) {
        super(id, name, type, description, "lost", contact);
        this.lastSeen = lastSeen;
        this.dateLost = dateLost;
    }

    public String getLastSeenLocation() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getDateLost() {
        return dateLost;
    }
    
    public void setDateLost(String dateLost) {
        this.dateLost = dateLost;
    }
 
    @Override
    public String logEntry() {
        return super.logEntry() + "\nLost on: " + getDateLost() + "\nLast seen: " + getLastSeenLocation();
    }
}


