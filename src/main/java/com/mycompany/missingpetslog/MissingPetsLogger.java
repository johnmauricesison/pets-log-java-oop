/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.missingpetslog;

import javax.swing.*;
import java.util.*;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;

public class MissingPetsLogger extends javax.swing.JFrame {

    /**
     * Creates new form MissingPetsLogger
    */
    private final DefaultListModel<String> lostPetsListModel = new DefaultListModel<>();
    private final DefaultListModel<String> foundPetsListModel = new DefaultListModel<>();
    private final DefaultListModel<String> archivePetsListModel = new DefaultListModel<>();
    
    List<PetLog> petLogs = new ArrayList<>();
    List<LostPetLog> lostPetLogs = new ArrayList<>();
    List<FoundPetLog> foundPetLogs = new ArrayList<>();
    List<ArchivePetLog> archivePetLogs = new ArrayList<>();
    
    int petLogCount = 0;
    int lostPetLogCount = 0;
    int foundPetLogCount = 0;
    int archivePetLogCount = 0;
    
    private boolean isLostNameAsc = true;
    private boolean isLostDateAsc = true;
    private boolean isFoundNameAsc = true;
    private boolean isFoundDateAsc = true;
    private boolean isArchiveNameAsc = true;
    private boolean isArchiveDateAsc = true;
    
    public MissingPetsLogger() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        LostPetsList.setModel(lostPetsListModel);
        FoundPetsList.setModel(foundPetsListModel);
        LostPetsList1.setModel(lostPetsListModel);
        FoundPetsList2.setModel(foundPetsListModel); 
        ArchivePetsList.setModel(archivePetsListModel);
        
        //Sample Entries
        lostPetLogs.add(new LostPetLog(1, "Bella", "Dog", "Brown dog, small size", "555-1234", "Park", "2023-01-10"));
        lostPetLogs.add(new LostPetLog(2, "Max", "Cat", "Black cat, medium size", "555-5678", "Street", "2023-01-15"));
        lostPetLogs.add(new LostPetLog(3, "Charlie", "Dog", "Golden retriever, large size", "555-4321", "Home", "2023-02-01"));

        foundPetLogs.add(new FoundPetLog(1, "Luna", "Rabbit", "White rabbit, fluffy", "555-8765", "Park", "2023-02-01"));
        foundPetLogs.add(new FoundPetLog(2, "Shadow", "Cat", "Grey tabby, small size", "555-9876", "Street", "2023-02-03"));
        foundPetLogs.add(new FoundPetLog(3, "Rex", "Dog", "Large German Shepherd", "555-1122", "Home", "2023-02-10"));

        updateLostPetsList();
        updateFoundPetsList();
        
        lostPetLogCount = lostPetLogs.size();
        foundPetLogCount = foundPetLogs.size();
        archivePetLogCount = archivePetLogs.size();
        
        this.LostPetsCount.setText(Integer.toString(lostPetLogCount));
        this.LostPetsCount1.setText(Integer.toString(lostPetLogCount));
        this.FoundPetsCount.setText(Integer.toString(foundPetLogCount));
        this.FoundPetsCount2.setText(Integer.toString(foundPetLogCount));
        this.ArchivePetsCount.setText(Integer.toString(archivePetLogCount));
        
        updateDemographics();
    }
   
    private void sortPetLogs(String criteria) {
        Comparator<LostPetLog> lostComparator = null;
        Comparator<FoundPetLog> foundComparator = null;
        Comparator<ArchivePetLog> archiveComparator = null;
        boolean isAsc;
        
        switch (criteria) {
            case "lostname" -> {
                lostComparator = Comparator.comparing(LostPetLog::getName);
                isAsc = isLostNameAsc;
            }
            case "foundname" -> {
                foundComparator = Comparator.comparing(FoundPetLog::getName);
                isAsc = isFoundNameAsc;
            }
            case "lostname1" -> {
                lostComparator = Comparator.comparing(LostPetLog::getName);
                isAsc = isLostNameAsc;
            }
            case "foundname1" -> {
                foundComparator = Comparator.comparing(FoundPetLog::getName);
                isAsc = isFoundNameAsc;
            }
            case "dateLost" -> {
                lostComparator = Comparator.comparing(LostPetLog::getDateLost);
                isAsc = isLostDateAsc;
            }
            case "dateFound" -> {
                foundComparator = Comparator.comparing(FoundPetLog::getDateFound);
                isAsc = isFoundDateAsc;
            }
            case "dateLost1" -> {
                lostComparator = Comparator.comparing(LostPetLog::getDateLost);
                isAsc = isLostDateAsc;
            }
            case "dateFound1" -> {
                foundComparator = Comparator.comparing(FoundPetLog::getDateFound);
                isAsc = isFoundDateAsc;
            }
            case "archivename" -> {
                archiveComparator = Comparator.comparing(ArchivePetLog::getName);
                isAsc = isArchiveNameAsc;
            }
            case "archivedate" -> {
                archiveComparator = Comparator.comparing(ArchivePetLog::getDateLost);
                isAsc = isArchiveDateAsc;
            }
            case "archivename1" -> {
                archiveComparator = Comparator.comparing(ArchivePetLog::getName);
                isAsc = isArchiveNameAsc;
            }
            case "archivedate1" -> {
                archiveComparator = Comparator.comparing(ArchivePetLog::getDateLost);
                isAsc = isArchiveDateAsc;
            }
            default -> {
                return;
            }
        }
        
        if (!isAsc) {
            if (lostComparator != null) lostComparator = lostComparator.reversed();
            if (foundComparator != null) foundComparator = foundComparator.reversed();
            if (archiveComparator != null) archiveComparator = archiveComparator.reversed();
        }

        if (lostComparator != null) {
            lostPetLogs.sort(lostComparator);       
        }
        if (foundComparator != null) {
            foundPetLogs.sort(foundComparator);
        }
        if (archiveComparator != null) {
            archivePetLogs.sort(archiveComparator);
        }

        updateLostPetsList();
        updateFoundPetsList();
        updateArchivePetsList();
  
        updateButtonTextAndToggleOrder(criteria, isAsc);
    }
    
    private void updateButtonTextAndToggleOrder(String criteria, boolean isAsc) {
        switch (criteria) {
            case "lostname" -> {
                lostNameSort.setText(isAsc ? "Name (DESC)" : "Name (ASC)");
                isLostNameAsc = !isAsc;
            }
            case "dateLost" -> {
                lostDateSort.setText(isAsc ? "Date (DESC)" : "Date (ASC)");
                isLostDateAsc = !isAsc;
            }
            case "lostname1" -> {
                lostNameSortX.setText(isAsc ? "Name (DESC)" : "Name (ASC)");
                isLostNameAsc = !isAsc;
            }
            case "dateLost1" -> {
                lostDateSortX.setText(isAsc ? "Date (DESC)" : "Date (ASC)");
                isLostDateAsc = !isAsc;
            }
            case "foundname" -> {
                foundNameSort.setText(isAsc ? "Name (DESC)" : "Name (ASC)");
                isFoundNameAsc = !isAsc;
            }
            case "dateFound" -> {
                foundDateSort.setText(isAsc ? "Date (DESC)" : "Date (ASC)");
                isFoundDateAsc = !isAsc;
            }
            case "foundname1" -> {
                foundNameSortX.setText(isAsc ? "Name (DESC)" : "Name (ASC)");
                isFoundNameAsc = !isAsc;
            }
            case "dateFound1" -> {
                foundDateSortX.setText(isAsc ? "Date (DESC)" : "Date (ASC)");
                isFoundDateAsc = !isAsc;
            }
            case "archivename" -> {
                archiveNameSort.setText(isAsc ? "Name (DESC)" : "Name (ASC)");
                isArchiveNameAsc = !isAsc;
            }
            case "archivedate" -> {
                archiveDateSort.setText(isAsc ? "Date (DESC)" : "Date (ASC)");
                isArchiveDateAsc = !isAsc;
            }
        }
    }
    
    private void updateLostPetsList() {
        lostPetsListModel.clear();
        for (LostPetLog petLog : lostPetLogs) {
            lostPetsListModel.addElement(petLog.getName()+"  -  Date Lost: "+ petLog.getDateLost());
            
        }
        lostPetLogCount = lostPetLogs.size();
    }

    private void updateFoundPetsList() {
        foundPetsListModel.clear();
        for (FoundPetLog petLog : foundPetLogs) {
            foundPetsListModel.addElement(petLog.getName()+"  -  Date Found: "+ petLog.getDateFound());
        }
        foundPetLogCount = foundPetLogs.size();
    }
    
    private void updateArchivePetsList() {
        archivePetsListModel.clear();
        for (ArchivePetLog petLog : archivePetLogs) {
            archivePetsListModel.addElement(petLog.getName()+"  -  Date Found: "+ petLog.getDateLost());
        }
        archivePetLogCount = archivePetLogs.size();
    }
    
    
    
    private void updateDemographics(){
        // Initialize counters for each of the 4 pet types
        int dogCount = 0, catCount = 0, birdCount = 0, others = 0;
        int totalLostPets = lostPetLogs.size();
    
        // Loop through the lostPetLogs list and count occurrences of each pet type
        for (LostPetLog petLog : lostPetLogs) {
            String petType = petLog.getType();

            if (petType.equalsIgnoreCase("Dog")) {
                dogCount++;
            } else if (petType.equalsIgnoreCase("Cat")) {
                catCount++;
            } else if (petType.equalsIgnoreCase("Bird")) {
                birdCount++;
            } else {
                others++;
            }
        }

        // Calculate percentages for each pet type
        double dogPercentage = (double) dogCount / totalLostPets * 100;
        double catPercentage = (double) catCount / totalLostPets * 100;
        double birdPercentage = (double) birdCount / totalLostPets * 100;
        double othersPercentage = (double) others / totalLostPets * 100;

        // Update your labels and progress bars (example)
        dogPercentageLabel.setText(String.format("%.0f%%", dogPercentage));
        catPercentageLabel.setText(String.format("%.0f%%", catPercentage));
        birdPercentageLabel.setText(String.format("%.0f%%", birdPercentage));
        hamsterPercentageLabel.setText(String.format("%.0f%%", othersPercentage));

        dogProgressBar.setValue((int) dogPercentage);
        catProgressBar.setValue((int) catPercentage);
        birdProgressBar.setValue((int) birdPercentage);
        hamsterProgressBar.setValue((int) othersPercentage);
    }
    

        /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        LostPetsCount = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        dogPercentageLabel = new javax.swing.JLabel();
        catPercentageLabel = new javax.swing.JLabel();
        hamsterPercentageLabel = new javax.swing.JLabel();
        birdPercentageLabel = new javax.swing.JLabel();
        dogProgressBar = new javax.swing.JProgressBar();
        catProgressBar = new javax.swing.JProgressBar();
        birdProgressBar = new javax.swing.JProgressBar();
        hamsterProgressBar = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        LostPetsList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        FoundPetsList = new javax.swing.JList<>();
        SearchButton = new javax.swing.JButton();
        DisplayButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lostNameSort = new javax.swing.JButton();
        lostDateSort = new javax.swing.JButton();
        foundDateSort = new javax.swing.JButton();
        foundNameSort = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        FoundPetsCount = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        DescriptionTextArea = new javax.swing.JTextArea();
        ContactTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        StatusComboBox = new javax.swing.JComboBox<>();
        ReportButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        NameTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        TypeTextField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        SeenLabel = new javax.swing.JLabel();
        SeenTextField = new javax.swing.JTextField();
        DateTextField = new javax.swing.JTextField();
        DateLabel = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        LostPetsCount1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        LostPetsList1 = new javax.swing.JList<>();
        LostSearch = new javax.swing.JButton();
        LostDisplay = new javax.swing.JButton();
        LostEdit = new javax.swing.JButton();
        LostCancel = new javax.swing.JButton();
        LostFound = new javax.swing.JButton();
        lostNameSortX = new javax.swing.JButton();
        lostDateSortX = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        FoundPetsCount2 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        FoundPetsList2 = new javax.swing.JList<>();
        FoundSearch = new javax.swing.JButton();
        FoundDisplay = new javax.swing.JButton();
        FoundRemove = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        foundDateSortX = new javax.swing.JButton();
        foundNameSortX = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        ArchivePetsCount = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ArchivePetsList = new javax.swing.JList<>();
        archiveSearch = new javax.swing.JButton();
        archiveDisplay = new javax.swing.JButton();
        archiveEdit = new javax.swing.JButton();
        archiveRestore = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        archiveDateSort = new javax.swing.JButton();
        archiveNameSort = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Missing Pets Logger");
        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setToolTipText(null);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(153, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 0), 5));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Lost Pets");

        LostPetsCount.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        LostPetsCount.setForeground(new java.awt.Color(255, 255, 255));
        LostPetsCount.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addComponent(LostPetsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39))
                    .addComponent(LostPetsCount))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(30, 120, 250, 80);

        jPanel12.setBackground(new java.awt.Color(102, 102, 102));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("LOST PET DEMOGRAPHICS");

        jLabel32.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("DOG");

        jLabel33.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("CAT");

        jLabel34.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("BIRD");

        jLabel35.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("OTHERS");

        dogPercentageLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        dogPercentageLabel.setForeground(new java.awt.Color(255, 255, 255));
        dogPercentageLabel.setText("0%");

        catPercentageLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        catPercentageLabel.setForeground(new java.awt.Color(255, 255, 255));
        catPercentageLabel.setText("0%");

        hamsterPercentageLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        hamsterPercentageLabel.setForeground(new java.awt.Color(255, 255, 255));
        hamsterPercentageLabel.setText("0%");

        birdPercentageLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        birdPercentageLabel.setForeground(new java.awt.Color(255, 255, 255));
        birdPercentageLabel.setText("0%");

        dogProgressBar.setBackground(new java.awt.Color(255, 255, 255));
        dogProgressBar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 10)); // NOI18N
        dogProgressBar.setForeground(new java.awt.Color(0, 0, 102));

        catProgressBar.setBackground(new java.awt.Color(255, 255, 255));
        catProgressBar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 10)); // NOI18N
        catProgressBar.setForeground(new java.awt.Color(0, 0, 102));

        birdProgressBar.setBackground(new java.awt.Color(255, 255, 255));
        birdProgressBar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 10)); // NOI18N
        birdProgressBar.setForeground(new java.awt.Color(0, 0, 102));

        hamsterProgressBar.setBackground(new java.awt.Color(255, 255, 255));
        hamsterProgressBar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 10)); // NOI18N
        hamsterProgressBar.setForeground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(catProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dogProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(dogPercentageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(birdProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(birdPercentageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 82, Short.MAX_VALUE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(catPercentageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(hamsterProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(hamsterPercentageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 323, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dogProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dogPercentageLabel)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(birdProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birdPercentageLabel))))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hamsterProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hamsterPercentageLabel)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(catProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(catPercentageLabel))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel12);
        jPanel12.setBounds(30, 470, 680, 170);

        LostPetsList.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(LostPetsList);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 210, 250, 180);

        FoundPetsList.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(FoundPetsList);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(300, 210, 250, 180);

        SearchButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        SearchButton.setText("search");
        SearchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });
        jPanel1.add(SearchButton);
        SearchButton.setBounds(730, 480, 100, 30);

        DisplayButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        DisplayButton.setText("display");
        DisplayButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DisplayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayButtonActionPerformed(evt);
            }
        });
        jPanel1.add(DisplayButton);
        DisplayButton.setBounds(730, 520, 100, 30);

        jLabel3.setFont(new java.awt.Font("Eras Demi ITC", 1, 48)); // NOI18N
        jLabel3.setText("DASHBOARD  ");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(30, 30, 390, 70);

        lostNameSort.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 8)); // NOI18N
        lostNameSort.setText("Name");
        lostNameSort.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lostNameSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lostNameSortActionPerformed(evt);
            }
        });
        jPanel1.add(lostNameSort);
        lostNameSort.setBounds(90, 400, 90, 20);

        lostDateSort.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 8)); // NOI18N
        lostDateSort.setText("Date");
        lostDateSort.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lostDateSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lostDateSortActionPerformed(evt);
            }
        });
        jPanel1.add(lostDateSort);
        lostDateSort.setBounds(190, 400, 90, 20);

        foundDateSort.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 8)); // NOI18N
        foundDateSort.setText("Date");
        foundDateSort.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        foundDateSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foundDateSortActionPerformed(evt);
            }
        });
        jPanel1.add(foundDateSort);
        foundDateSort.setBounds(460, 400, 90, 20);

        foundNameSort.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 8)); // NOI18N
        foundNameSort.setText("Name");
        foundNameSort.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        foundNameSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foundNameSortActionPerformed(evt);
            }
        });
        jPanel1.add(foundNameSort);
        foundNameSort.setBounds(360, 400, 90, 20);

        jPanel13.setBackground(new java.awt.Color(0, 102, 51));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 0), 5));

        FoundPetsCount.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        FoundPetsCount.setForeground(new java.awt.Color(255, 255, 255));
        FoundPetsCount.setText("0");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Found Pets");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(FoundPetsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(FoundPetsCount)
                .addGap(3, 3, 3))
        );

        jPanel1.add(jPanel13);
        jPanel13.setBounds(300, 120, 250, 80);

        jLabel37.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 8)); // NOI18N
        jLabel37.setText("Sort by:");
        jPanel1.add(jLabel37);
        jLabel37.setBounds(50, 400, 50, 20);

        jLabel38.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 8)); // NOI18N
        jLabel38.setText("Sort by:");
        jPanel1.add(jLabel38);
        jLabel38.setBounds(320, 400, 50, 20);

        jTabbedPane1.addTab("Dashboard", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setToolTipText("");
        jPanel4.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel4.setPreferredSize(new java.awt.Dimension(1000, 600));
        jPanel4.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel5.setText("Description:");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(90, 301, 120, 30);

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel8.setText("Contact:");
        jPanel4.add(jLabel8);
        jLabel8.setBounds(90, 230, 90, 30);

        DescriptionTextArea.setColumns(20);
        DescriptionTextArea.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        DescriptionTextArea.setRows(5);
        jScrollPane4.setViewportView(DescriptionTextArea);

        jPanel4.add(jScrollPane4);
        jScrollPane4.setBounds(250, 300, 504, 119);

        ContactTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel4.add(ContactTextField);
        ContactTextField.setBounds(250, 230, 213, 35);

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel9.setText("Status:");
        jPanel4.add(jLabel9);
        jLabel9.setBounds(90, 440, 62, 30);

        StatusComboBox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        StatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Status", "Lost", "Found" }));
        StatusComboBox.setToolTipText("");
        StatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusComboBoxActionPerformed(evt);
            }
        });
        jPanel4.add(StatusComboBox);
        StatusComboBox.setBounds(250, 440, 118, 35);

        ReportButton.setBackground(new java.awt.Color(179, 0, 0));
        ReportButton.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        ReportButton.setForeground(new java.awt.Color(255, 255, 255));
        ReportButton.setText("REPORT");
        ReportButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportButtonActionPerformed(evt);
            }
        });
        jPanel4.add(ReportButton);
        ReportButton.setBounds(590, 440, 160, 40);

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel10.setText("Pet Name:");
        jPanel4.add(jLabel10);
        jLabel10.setBounds(90, 130, 92, 30);

        NameTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        NameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameTextFieldActionPerformed(evt);
            }
        });
        jPanel4.add(NameTextField);
        NameTextField.setBounds(250, 130, 213, 35);

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel11.setText("Pet Type:");
        jPanel4.add(jLabel11);
        jLabel11.setBounds(90, 181, 83, 30);

        TypeTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel4.add(TypeTextField);
        TypeTextField.setBounds(250, 180, 213, 35);
        jPanel4.add(jLabel21);
        jLabel21.setBounds(649, 490, 0, 0);

        jLabel12.setFont(new java.awt.Font("Eras Demi ITC", 1, 36)); // NOI18N
        jLabel12.setText("REPORT A PET");
        jPanel4.add(jLabel12);
        jLabel12.setBounds(39, 28, 390, 48);

        SeenLabel.setVisible(false);
        SeenLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        SeenLabel.setText("Last Seen at:");
        jPanel4.add(SeenLabel);
        SeenLabel.setBounds(90, 500, 150, 30);

        SeenTextField.setVisible(false);
        SeenTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        SeenTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeenTextFieldActionPerformed(evt);
            }
        });
        jPanel4.add(SeenTextField);
        SeenTextField.setBounds(330, 500, 213, 40);

        DateTextField.setVisible(false);
        DateTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        DateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateTextFieldActionPerformed(evt);
            }
        });
        jPanel4.add(DateTextField);
        DateTextField.setBounds(330, 550, 213, 40);

        DateLabel.setVisible(false);
        DateLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        DateLabel.setText("Date Lost: ");
        jPanel4.add(DateLabel);
        DateLabel.setBounds(90, 550, 240, 30);

        ImageIcon icon2 = new ImageIcon(getClass().getResource("/com/mycompany/missingpetslog/sadpetbig.png"));
        jLabel28.setIcon(icon2);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/missingpetslog/sadpetbig.png"))); // NOI18N
        jLabel28.setAlignmentY(0.0F);
        jLabel28.setMaximumSize(new java.awt.Dimension(142, 100));
        jPanel4.add(jLabel28);
        jLabel28.setBounds(510, 100, 242, 200);

        jTabbedPane1.addTab("Report", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        jPanel7.setBackground(new java.awt.Color(153, 0, 0));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 0, 0), 7, true));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(545, 152));

        LostPetsCount1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        LostPetsCount1.setForeground(new java.awt.Color(255, 255, 255));
        LostPetsCount1.setText("0");

        ImageIcon icon3 = new ImageIcon(getClass().getResource("/com/mycompany/missingpetslog/sadpet (1).png"));
        jLabel23.setIcon(icon3);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/missingpetslog/sadpet (1).png"))); // NOI18N
        jLabel23.setText("jLabel6");
        jLabel23.setAlignmentY(0.0F);
        jLabel23.setMaximumSize(new java.awt.Dimension(142, 100));

        jLabel22.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Lost Pets Report");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel22)
                .addContainerGap(338, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LostPetsCount1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(LostPetsCount1)
                        .addGap(33, 33, 33))))
        );

        jPanel6.add(jPanel7);
        jPanel7.setBounds(100, 90, 540, 150);

        LostPetsList1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jScrollPane3.setViewportView(LostPetsList1);

        jPanel6.add(jScrollPane3);
        jScrollPane3.setBounds(100, 250, 340, 300);

        LostSearch.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        LostSearch.setText("Search");
        LostSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LostSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LostSearchActionPerformed(evt);
            }
        });
        jPanel6.add(LostSearch);
        LostSearch.setBounds(450, 270, 80, 30);

        LostDisplay.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        LostDisplay.setText("Display");
        LostDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LostDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LostDisplayActionPerformed(evt);
            }
        });
        jPanel6.add(LostDisplay);
        LostDisplay.setBounds(450, 310, 80, 30);

        LostEdit.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        LostEdit.setText("Edit");
        LostEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LostEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LostEditActionPerformed(evt);
            }
        });
        jPanel6.add(LostEdit);
        LostEdit.setBounds(540, 270, 80, 30);

        LostCancel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        LostCancel.setText("Cancel");
        LostCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LostCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LostCancelActionPerformed(evt);
            }
        });
        jPanel6.add(LostCancel);
        LostCancel.setBounds(540, 310, 80, 30);

        LostFound.setBackground(new java.awt.Color(0, 102, 51));
        LostFound.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        LostFound.setForeground(new java.awt.Color(255, 255, 255));
        LostFound.setText("FOUND!");
        LostFound.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LostFound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LostFoundActionPerformed(evt);
            }
        });
        jPanel6.add(LostFound);
        LostFound.setBounds(470, 430, 150, 50);

        lostNameSortX.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lostNameSortX.setActionCommand("SName");
        lostNameSortX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lostNameSortX.setLabel("Name");
        lostNameSortX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lostNameSortXActionPerformed(evt);
            }
        });
        jPanel6.add(lostNameSortX);
        lostNameSortX.setBounds(170, 560, 110, 30);
        lostNameSortX.getAccessibleContext().setAccessibleDescription("");

        lostDateSortX.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lostDateSortX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lostDateSortX.setLabel("Date");
        lostDateSortX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lostDateSortXActionPerformed(evt);
            }
        });
        jPanel6.add(lostDateSortX);
        lostDateSortX.setBounds(290, 560, 110, 30);

        jLabel26.setFont(new java.awt.Font("Eras Demi ITC", 1, 36)); // NOI18N
        jLabel26.setText("LOST PETS");
        jPanel6.add(jLabel26);
        jLabel26.setBounds(40, 40, 440, 42);

        jLabel39.setText("Sort By:");
        jPanel6.add(jLabel39);
        jLabel39.setBounds(100, 560, 60, 30);

        jTabbedPane1.addTab("Lost Pets", jPanel6);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(null);

        jPanel11.setBackground(new java.awt.Color(0, 102, 51));
        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 0), 7, true));

        ImageIcon icon4 = new ImageIcon(getClass().getResource("/com/mycompany/missingpetslog/happypet (1).png"));
        jLabel30.setIcon(icon4);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/missingpetslog/happypet (1).png"))); // NOI18N
        jLabel30.setText("jLabel6");
        jLabel30.setAlignmentY(0.0F);

        jLabel29.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Found Pets Report");

        FoundPetsCount2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        FoundPetsCount2.setForeground(new java.awt.Color(255, 255, 255));
        FoundPetsCount2.setText("0");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel29))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                .addComponent(FoundPetsCount2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(FoundPetsCount2))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel30)))
                .addGap(9, 9, 9))
        );

        jPanel9.add(jPanel11);
        jPanel11.setBounds(100, 90, 540, 150);

        FoundPetsList2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jScrollPane7.setViewportView(FoundPetsList2);

        jPanel9.add(jScrollPane7);
        jScrollPane7.setBounds(100, 250, 340, 300);

        FoundSearch.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        FoundSearch.setText("Search");
        FoundSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FoundSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FoundSearchActionPerformed(evt);
            }
        });
        jPanel9.add(FoundSearch);
        FoundSearch.setBounds(450, 270, 80, 30);

        FoundDisplay.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        FoundDisplay.setText("Display");
        FoundDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FoundDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FoundDisplayActionPerformed(evt);
            }
        });
        jPanel9.add(FoundDisplay);
        FoundDisplay.setBounds(450, 310, 80, 30);

        FoundRemove.setBackground(new java.awt.Color(153, 0, 0));
        FoundRemove.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        FoundRemove.setForeground(new java.awt.Color(255, 255, 255));
        FoundRemove.setText("REMOVE");
        FoundRemove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FoundRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FoundRemoveActionPerformed(evt);
            }
        });
        jPanel9.add(FoundRemove);
        FoundRemove.setBounds(470, 430, 150, 50);

        jLabel31.setFont(new java.awt.Font("Eras Demi ITC", 1, 36)); // NOI18N
        jLabel31.setText("FOUND PETS");
        jPanel9.add(jLabel31);
        jLabel31.setBounds(40, 40, 370, 42);

        foundDateSortX.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        foundDateSortX.setText("Date");
        foundDateSortX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        foundDateSortX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foundDateSortXActionPerformed(evt);
            }
        });
        jPanel9.add(foundDateSortX);
        foundDateSortX.setBounds(290, 560, 110, 30);

        foundNameSortX.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        foundNameSortX.setText("Name");
        foundNameSortX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        foundNameSortX.setMaximumSize(new java.awt.Dimension(95, 28));
        foundNameSortX.setMinimumSize(new java.awt.Dimension(95, 28));
        foundNameSortX.setPreferredSize(new java.awt.Dimension(95, 28));
        foundNameSortX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foundNameSortXActionPerformed(evt);
            }
        });
        jPanel9.add(foundNameSortX);
        foundNameSortX.setBounds(170, 560, 110, 30);

        jLabel40.setText("Sort By:");
        jPanel9.add(jLabel40);
        jLabel40.setBounds(100, 560, 60, 30);

        jTabbedPane1.addTab("Found Pets", jPanel9);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(null);

        jPanel10.setBackground(new java.awt.Color(2, 62, 138));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(3, 4, 94), 7, true));
        jPanel10.setForeground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Withdrawn Pets Report");

        ArchivePetsCount.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        ArchivePetsCount.setForeground(new java.awt.Color(255, 255, 255));
        ArchivePetsCount.setText("0");

        ImageIcon icon5 = new ImageIcon(getClass().getResource("/com/mycompany/missingpetslog/sadpet (1).png"));
        jLabel25.setIcon(icon5);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/missingpetslog/foundpett.png"))); // NOI18N
        jLabel25.setText("jLabel6");
        jLabel25.setAlignmentY(0.0F);
        jLabel25.setMaximumSize(new java.awt.Dimension(142, 100));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel24)
                .addContainerGap(277, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ArchivePetsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(ArchivePetsCount)
                        .addGap(27, 27, 27))
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8.add(jPanel10);
        jPanel10.setBounds(100, 90, 540, 150);

        ArchivePetsList.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jScrollPane5.setViewportView(ArchivePetsList);

        jPanel8.add(jScrollPane5);
        jScrollPane5.setBounds(100, 250, 340, 300);

        archiveSearch.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        archiveSearch.setText("Search");
        archiveSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        archiveSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveSearchActionPerformed(evt);
            }
        });
        jPanel8.add(archiveSearch);
        archiveSearch.setBounds(450, 270, 80, 30);

        archiveDisplay.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        archiveDisplay.setText("Display");
        archiveDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        archiveDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveDisplayActionPerformed(evt);
            }
        });
        jPanel8.add(archiveDisplay);
        archiveDisplay.setBounds(450, 310, 80, 30);

        archiveEdit.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        archiveEdit.setText("Edit");
        archiveEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        archiveEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveEditActionPerformed(evt);
            }
        });
        jPanel8.add(archiveEdit);
        archiveEdit.setBounds(540, 270, 80, 30);

        archiveRestore.setBackground(new java.awt.Color(2, 62, 138));
        archiveRestore.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        archiveRestore.setForeground(new java.awt.Color(255, 255, 255));
        archiveRestore.setText("RESTORE");
        archiveRestore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        archiveRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveRestoreActionPerformed(evt);
            }
        });
        jPanel8.add(archiveRestore);
        archiveRestore.setBounds(470, 430, 150, 50);

        jLabel27.setFont(new java.awt.Font("Eras Demi ITC", 1, 36)); // NOI18N
        jLabel27.setText("WITHDRAWN PETS");
        jPanel8.add(jLabel27);
        jLabel27.setBounds(40, 40, 345, 42);

        archiveDateSort.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        archiveDateSort.setText("Date");
        archiveDateSort.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        archiveDateSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveDateSortActionPerformed(evt);
            }
        });
        jPanel8.add(archiveDateSort);
        archiveDateSort.setBounds(290, 560, 110, 30);

        archiveNameSort.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        archiveNameSort.setText("Name");
        archiveNameSort.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        archiveNameSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveNameSortActionPerformed(evt);
            }
        });
        jPanel8.add(archiveNameSort);
        archiveNameSort.setBounds(170, 560, 110, 30);

        jLabel41.setText("Sort By:");
        jPanel8.add(jLabel41);
        jLabel41.setBounds(100, 560, 60, 30);

        jTabbedPane1.addTab("Archive", jPanel8);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Eras Demi ITC", 1, 36)); // NOI18N
        jLabel13.setText("MISSING PETS LOGGER");

        jLabel14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel14.setText("The Missing Pets Logger is here to lend a paw!");

        jLabel15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel15.setText("It's the perfect place to keep track of our furry (and feathered!)");

        jLabel16.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel16.setText("friends who have gone missing or been found.");

        jLabel17.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel17.setText("We know how much these little buddies mean to you, so we’ve made it");

        jLabel18.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel18.setText("super easy to log their adventures and update their status");

        jLabel19.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel19.setText("as they find their way home.");

        jLabel20.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel20.setText("Let’s bring those tails wagging back where they belong!");

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/missingpetslog/happypetbig.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel13)
                .addGap(33, 33, 33)
                .addComponent(jLabel14)
                .addGap(30, 30, 30)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(30, 30, 30)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addGap(30, 30, 30)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(41, 41, 41)
                .addComponent(jLabel42))
        );

        jTabbedPane1.addTab("About", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1032, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
        
    
    private void FoundSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FoundSearchActionPerformed
        String searchTerm = JOptionPane.showInputDialog(null, "Enter pet name to search:", "Search Pet", JOptionPane.QUESTION_MESSAGE);
        try {
            if (searchTerm != null && !searchTerm.isEmpty()) {
                boolean found = false;
                for (FoundPetLog foundPet : foundPetLogs) {
                    if (foundPet.getName().equalsIgnoreCase(searchTerm)) {
                        JOptionPane.showMessageDialog(null, "Found Pet Found: \n" + foundPet.logEntry(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "No pet found with the name: " + searchTerm + ".\nCheck Lost Pets.", "Search Result", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while performing the search. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_FoundSearchActionPerformed

    private void FoundDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FoundDisplayActionPerformed
        int foundSelectedIndex = FoundPetsList2.getSelectedIndex();

        if (foundSelectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "No pet selected. Please select a pet to view.", "Error", JOptionPane.ERROR_MESSAGE);
        
        } else if (foundSelectedIndex >= 0 && foundSelectedIndex < foundPetLogs.size()) {
            FoundPetsList.clearSelection();
            if (foundPetLogs.get(foundSelectedIndex) != null) {
                JOptionPane.showMessageDialog(null, foundPetLogs.get(foundSelectedIndex).logEntry(), "Found Pet Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Selected Found Pet data is missing or corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid pet selection. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        FoundPetsList.clearSelection();
    }//GEN-LAST:event_FoundDisplayActionPerformed

    private void FoundRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FoundRemoveActionPerformed
        int[] foundSelectedIndex = FoundPetsList2.getSelectedIndices();

        if (foundSelectedIndex.length==0) {
            JOptionPane.showMessageDialog(null, "No pet selected. Please select a pet to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the selected pet record(s)?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Arrays.sort(foundSelectedIndex);
                    for (int i = foundSelectedIndex.length - 1; i >= 0; i--) {
                        int selectedIndex = foundSelectedIndex[i];
                        if (selectedIndex >= 0 && selectedIndex < foundPetLogs.size()) {
                            foundPetsListModel.remove(selectedIndex);
                            foundPetLogs.remove(selectedIndex);
                        } else {
                            JOptionPane.showMessageDialog(null, "Selected pet record(s) unavailable or has been removed already.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    foundPetsListModel.clear();
                    for (FoundPetLog foundPet : foundPetLogs) {
                        foundPetsListModel.addElement(foundPet.getName()+" - Date Found: "+foundPet.getDateFound());
                    }

                    foundPetLogCount = foundPetLogs.size();
                    this.FoundPetsCount.setText(Integer.toString(foundPetLogCount));
                    this.FoundPetsCount2.setText(Integer.toString(foundPetLogCount));

                } catch (IndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while removing the selected pet record(s). Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            } 
        }
    }//GEN-LAST:event_FoundRemoveActionPerformed

    private void archiveSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveSearchActionPerformed
        // TODO add your handling code here:
        String searchTerm = JOptionPane.showInputDialog(null, "Enter pet name to search:", "Search Pet", JOptionPane.QUESTION_MESSAGE);

        try {
            if (searchTerm != null && !searchTerm.isEmpty()) {
                boolean found = false;

                for (ArchivePetLog archivePet : archivePetLogs) {
                    if (archivePet.getName().equalsIgnoreCase(searchTerm)) {
                        JOptionPane.showMessageDialog(null, "Archived Pet Found: \n" + archivePet.logEntry(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(null, "No pet found with the name: " + searchTerm, "Search Result", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while performing the search. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_archiveSearchActionPerformed

    private void archiveDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveDisplayActionPerformed
        // TODO add your handling code here:
        int archiveSelectedIndex = ArchivePetsList.getSelectedIndex();

        if (archiveSelectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "No pet selected. Please select a pet to view.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (archiveSelectedIndex >= 0 && archiveSelectedIndex < archivePetLogs.size()) {
            if (archivePetLogs.get(archiveSelectedIndex) != null) {
                JOptionPane.showMessageDialog(null, archivePetLogs.get(archiveSelectedIndex).logEntry(), "Archived Pet Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Selected Archived Pet data is missing or corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid pet selection. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        ArchivePetsList.clearSelection();
        
    }//GEN-LAST:event_archiveDisplayActionPerformed

    private void archiveEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveEditActionPerformed
        // TODO add your handling code here:
        int archiveSelectedIndex = ArchivePetsList.getSelectedIndex();

        try {
            if (archiveSelectedIndex >= 0 && archiveSelectedIndex < archivePetLogs.size()) {
                ArchivePetLog archivePet = archivePetLogs.get(archiveSelectedIndex);

                String archiveName = JOptionPane.showInputDialog(null, "Name: ", "Name", JOptionPane.QUESTION_MESSAGE);
                if (archiveName == null) {
                    JOptionPane.showMessageDialog(null, "Update cancelled.", "Canceled", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String archiveType = JOptionPane.showInputDialog(null, "Type: ", "Type", JOptionPane.QUESTION_MESSAGE);
                if (archiveType == null) {
                    JOptionPane.showMessageDialog(null, "Update cancelled.", "Canceled", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String archiveDescription = JOptionPane.showInputDialog(null, "Description: ", "Description", JOptionPane.QUESTION_MESSAGE);
                if (archiveDescription == null) {
                    JOptionPane.showMessageDialog(null, "Update cancelled.", "Canceled", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String archiveContact = JOptionPane.showInputDialog(null, "Contact: ", "Contact", JOptionPane.QUESTION_MESSAGE);
                if (archiveContact == null) {
                    JOptionPane.showMessageDialog(null, "Update cancelled.", "Canceled", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String archiveSeen = JOptionPane.showInputDialog(null, "Last Seen: ", "Last Seen", JOptionPane.QUESTION_MESSAGE);
                if (archiveSeen == null) {
                    JOptionPane.showMessageDialog(null, "Update cancelled.", "Canceled", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String dateLost;
                while (true) {
                    dateLost = JOptionPane.showInputDialog(null, "Date Found (YYYY-MM-DD): ", "Date Found", JOptionPane.QUESTION_MESSAGE);
                    if (dateLost == null) {
                        JOptionPane.showMessageDialog(null, "Update cancelled", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (dateLost.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please provide a valid date in the format YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            LocalDate.parse(dateLost.trim());
                            break;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Invalid date format/values. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                archivePet.setName(archiveName);
                archivePet.setType(archiveType);
                archivePet.setDescription(archiveDescription);
                archivePet.setContact(archiveContact);
                archivePet.setLastSeen(archiveSeen);
                archivePet.setDateLost(dateLost);

                archivePetsListModel.setElementAt(archivePet.getName()+" - Date Lost: "+archivePet.getDateLost(), archiveSelectedIndex);

                JOptionPane.showMessageDialog(null, "Archived Pet updated successfully!");

            }
            else {
                JOptionPane.showMessageDialog(null, "No pet selected or invalid selection.", "Error", JOptionPane.ERROR_MESSAGE);
            }


            archivePetLogCount = archivePetLogs.size();
            this.ArchivePetsCount.setText(Integer.toString(archivePetLogCount));

        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while selecting a pet. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_archiveEditActionPerformed

    private void archiveRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveRestoreActionPerformed
        // TODO add your handling code here:
        int[] archiveSelectedIndex = ArchivePetsList.getSelectedIndices();

        if (archiveSelectedIndex.length==0) {
            JOptionPane.showMessageDialog(null, "No pet selected. Please select a pet to restore.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to restore the selected pet record(s)?", "Confirm Restoration", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    for (int i = archiveSelectedIndex.length - 1; i >= 0; i--) {
                        int selectedIndex = archiveSelectedIndex[i];
                        if (selectedIndex >= 0 && selectedIndex < archivePetLogs.size()) {
                            ArchivePetLog archivePet = archivePetLogs.get(selectedIndex);

                            LostPetLog lostPet = new LostPetLog(
                                    lostPetLogCount + 1,
                                    archivePet.getName(), 
                                    archivePet.getType(), 
                                    archivePet.getDescription(), 
                                    archivePet.getContact(), 
                                    archivePet.getLastSeenLocation(), 
                                    archivePet.getDateLost());

                            lostPetLogs.add(lostPet);
                            archivePetLogs.remove(selectedIndex);

                            for (LostPetLog pet : lostPetLogs) {
                                lostPetsListModel.addElement(pet.getName()+" - Date Found: "+pet.getDateLost());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Selected pet record(s) unavailable or has already been cancelled.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    archivePetsListModel.clear();
                    for (ArchivePetLog archivePet : archivePetLogs) {
                        archivePetsListModel.addElement(archivePet.getName());
                    }

                    archivePetLogCount = archivePetLogs.size();
                    this.ArchivePetsCount.setText(Integer.toString(archivePetLogCount));
                    lostPetsListModel.clear();
                    archivePetsListModel.clear();
                    
                    for (LostPetLog pet : lostPetLogs) {
                        lostPetsListModel.addElement(pet.getName()+" - Date Lost: "+pet.getDateLost());
                    }

                    for (ArchivePetLog pet : archivePetLogs) {
                        archivePetsListModel.addElement(pet.getName()+" - Date Found: "+pet.getDateLost());
                    }

                    lostPetLogCount = lostPetLogs.size();
                    foundPetLogCount = foundPetLogs.size();
                    archivePetLogCount = archivePetLogs.size();
                    this.LostPetsCount.setText(Integer.toString(lostPetLogCount));
                    this.LostPetsCount1.setText(Integer.toString(lostPetLogCount));
                    this.FoundPetsCount.setText(Integer.toString(foundPetLogCount));
                    this.FoundPetsCount2.setText(Integer.toString(foundPetLogCount));
                    this.ArchivePetsCount.setText(Integer.toString(archivePetLogCount));
                    JOptionPane.showMessageDialog(null, "The Archive Pet report(s) has been restored.");
                
                } catch (IndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while cancelling the selected pet record(s). Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
                
                updateDemographics();
            } 
        }
    }//GEN-LAST:event_archiveRestoreActionPerformed

    private void foundDateSortXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foundDateSortXActionPerformed
        // TODO add your handling code here:
        sortPetLogs("dateFound1");
    }//GEN-LAST:event_foundDateSortXActionPerformed

    private void foundNameSortXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foundNameSortXActionPerformed
        // TODO add your handling code here:
        sortPetLogs("foundname1");
    }//GEN-LAST:event_foundNameSortXActionPerformed

    private void archiveDateSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveDateSortActionPerformed
        // TODO add your handling code here:
        sortPetLogs("archivedate");
        
    }//GEN-LAST:event_archiveDateSortActionPerformed

    private void archiveNameSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveNameSortActionPerformed
        // TODO add your handling code here:
        sortPetLogs("archivename");
    }//GEN-LAST:event_archiveNameSortActionPerformed

    private void lostDateSortXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lostDateSortXActionPerformed
        // TODO add your handling code here:
        sortPetLogs("dateLost1");
    }//GEN-LAST:event_lostDateSortXActionPerformed

    private void lostNameSortXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lostNameSortXActionPerformed
        // TODO add your handling code here:
        sortPetLogs("lostname1");
    }//GEN-LAST:event_lostNameSortXActionPerformed

    private void LostFoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LostFoundActionPerformed
        int lostSelectedIndex = LostPetsList1.getSelectedIndex();
        try {
            if (lostSelectedIndex >= 0 && lostSelectedIndex < lostPetLogs.size()) {
                LostPetLog lostPet = lostPetLogs.get(lostSelectedIndex);

                String foundAt = JOptionPane.showInputDialog(null, "Found At: ", "Found At", JOptionPane.QUESTION_MESSAGE);
                if (foundAt == null) {
                    JOptionPane.showMessageDialog(null, "Found Pet Report cancelled!", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String dateFound;

                while (true) {
                    dateFound = JOptionPane.showInputDialog(null, "Date Found (YYYY-MM-DD): ", "Date Found", JOptionPane.QUESTION_MESSAGE);
                    if (dateFound == null) {
                        JOptionPane.showMessageDialog(null, "Found Pet Report cancelled!", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (dateFound.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please provide a valid date in the format YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            LocalDate.parse(dateFound.trim());
                            break;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Invalid date format/values. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                if (foundAt.trim().isEmpty() || dateFound.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Both 'Found At' and 'Date Found' must be filled out. Transfer canceled.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                FoundPetLog foundPet = new FoundPetLog(
                    foundPetLogCount + 1,
                    lostPet.getName(),
                    lostPet.getType(),
                    lostPet.getDescription(),
                    lostPet.getContact(),
                    foundAt,
                    dateFound
                );

                foundPetLogs.add(foundPet);
                lostPetLogs.remove(lostSelectedIndex);

                lostPetsListModel.clear();
                foundPetsListModel.clear();

                for (LostPetLog pet : lostPetLogs) {
                    lostPetsListModel.addElement(pet.getName()+" - Date Lost: "+pet.getDateLost());
                }

                for (FoundPetLog pet : foundPetLogs) {
                    foundPetsListModel.addElement(pet.getName()+" - Date Found: "+pet.getDateFound());
                }

                lostPetLogCount = lostPetLogs.size();
                foundPetLogCount = foundPetLogs.size();
                archivePetLogCount = archivePetLogs.size();
                
                this.LostPetsCount.setText(Integer.toString(lostPetLogCount));
                this.LostPetsCount1.setText(Integer.toString(lostPetLogCount));
                this.FoundPetsCount.setText(Integer.toString(foundPetLogCount));
                this.FoundPetsCount2.setText(Integer.toString(foundPetLogCount));
                this.ArchivePetsCount.setText(Integer.toString(archivePetLogCount));
                JOptionPane.showMessageDialog(null, "The Lost Pet has been successfully transferred to Found Pets.");
                
                updateDemographics();
            } else {
                JOptionPane.showMessageDialog(null, "No Lost Pet selected or invalid selection.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred while selecting the Lost Pet. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_LostFoundActionPerformed

    private void LostCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LostCancelActionPerformed
        int[] lostSelectedIndex = LostPetsList1.getSelectedIndices();

        if (lostSelectedIndex.length==0) {
            JOptionPane.showMessageDialog(null, "No pet selected. Please select a pet to cancel.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to cancel the selected pet record(s)?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    for (int i = lostSelectedIndex.length - 1; i >= 0; i--) {
                        int selectedIndex = lostSelectedIndex[i];
                        if (selectedIndex >= 0 && selectedIndex < lostPetLogs.size()) {
                            LostPetLog lostPet = lostPetLogs.get(selectedIndex);

                            ArchivePetLog archivePet = new ArchivePetLog(
                                archivePetLogCount + 1,
                                lostPet.getName(),
                                lostPet.getType(),
                                lostPet.getDescription(),
                                lostPet.getContact(),
                                lostPet.getLastSeenLocation(),
                                lostPet.getDateLost());

                            archivePetLogs.add(archivePet);
                            lostPetLogs.remove(selectedIndex);

                            for (ArchivePetLog pet : archivePetLogs) {
                                archivePetsListModel.addElement(pet.getName()+" - Date Found: "+pet.getDateLost());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Selected pet record(s) unavailable or has already been cancelled.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    lostPetsListModel.clear();
                    for (LostPetLog lostPet : lostPetLogs) {
                        lostPetsListModel.addElement(lostPet.getName());
                    }

                    lostPetLogCount = lostPetLogs.size();
                    this.LostPetsCount.setText(Integer.toString(lostPetLogCount));

                    lostPetsListModel.clear();
                    archivePetsListModel.clear();

                    for (LostPetLog pet : lostPetLogs) {
                        lostPetsListModel.addElement(pet.getName()+" - Date Lost: "+pet.getDateLost());
                    }

                    for (ArchivePetLog pet : archivePetLogs) {
                        archivePetsListModel.addElement(pet.getName()+" - Date Found: "+pet.getDateLost());
                    }

                    lostPetLogCount = lostPetLogs.size();
                    archivePetLogCount = archivePetLogs.size();
                    this.LostPetsCount.setText(Integer.toString(lostPetLogCount));
                    this.LostPetsCount1.setText(Integer.toString(lostPetLogCount));
                    this.FoundPetsCount.setText(Integer.toString(foundPetLogCount));
                    this.FoundPetsCount2.setText(Integer.toString(foundPetLogCount));
                    this.ArchivePetsCount.setText(Integer.toString(archivePetLogCount));
                    JOptionPane.showMessageDialog(null, "The Lost Pet report(s) has been cancelled and moved to archive.");

                    
                    updateDemographics();
                } catch (IndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(null, "An error occurred while cancelling the selected pet record(s). Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_LostCancelActionPerformed

    private void LostEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LostEditActionPerformed
        int lostSelectedIndex = LostPetsList1.getSelectedIndex();

        try {
            if (lostSelectedIndex >= 0 && lostSelectedIndex < lostPetLogs.size()) {
                LostPetLog lostPet = lostPetLogs.get(lostSelectedIndex);

                String lostName = JOptionPane.showInputDialog(null, "Name: ", "Name", JOptionPane.QUESTION_MESSAGE);
                if (lostName == null) {
                    JOptionPane.showMessageDialog(null, "Update cancelled.", "Canceled", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String lostType = JOptionPane.showInputDialog(null, "Type: ", "Type", JOptionPane.QUESTION_MESSAGE);
                if (lostType == null) {
                    JOptionPane.showMessageDialog(null, "Update cancelled.", "Canceled", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String lostDescription = JOptionPane.showInputDialog(null, "Description: ", "Description", JOptionPane.QUESTION_MESSAGE);
                if (lostDescription == null) {
                    JOptionPane.showMessageDialog(null, "Update cancelled.", "Canceled", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String lostContact = JOptionPane.showInputDialog(null, "Contact: ", "Contact", JOptionPane.QUESTION_MESSAGE);
                if (lostContact == null) {
                    JOptionPane.showMessageDialog(null, "Update cancelled.", "Canceled", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String lastSeen = JOptionPane.showInputDialog(null, "Last Seen: ", "Last Seen", JOptionPane.QUESTION_MESSAGE);
                if (lastSeen == null) {
                    JOptionPane.showMessageDialog(null, "Update cancelled.", "Canceled", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String dateLost;
                while (true) {
                    dateLost = JOptionPane.showInputDialog(null, "Date Lost (YYYY-MM-DD): ", "Date Lost", JOptionPane.QUESTION_MESSAGE);
                    if (dateLost == null) {
                        JOptionPane.showMessageDialog(null, "Update cancelled", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (dateLost.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please provide a valid date in the format YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            LocalDate.parse(dateLost.trim());
                            break;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Invalid date format/values. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                lostPet.setName(lostName);
                lostPet.setType(lostType);
                lostPet.setDescription(lostDescription);
                lostPet.setContact(lostContact);
                lostPet.setLastSeen(lastSeen);
                lostPet.setDateLost(dateLost);

                lostPetsListModel.setElementAt(lostPet.getName()+" - Date Lost: "+lostPet.getDateLost(), lostSelectedIndex);

                JOptionPane.showMessageDialog(null, "Lost Pet updated successfully!");

                updateDemographics();
            }
            else {
                JOptionPane.showMessageDialog(null, "No pet selected or invalid selection.", "Error", JOptionPane.ERROR_MESSAGE);
            }


            lostPetLogCount = lostPetLogs.size();
            this.LostPetsCount.setText(Integer.toString(lostPetLogCount));
            this.LostPetsCount1.setText(Integer.toString(lostPetLogCount));

        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while selecting a pet. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_LostEditActionPerformed

    private void LostDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LostDisplayActionPerformed
        int lostSelectedIndex = LostPetsList1.getSelectedIndex();

        if (lostSelectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "No pet selected. Please select a pet to view.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lostSelectedIndex >= 0 && lostSelectedIndex < lostPetLogs.size()) {
            if (lostPetLogs.get(lostSelectedIndex) != null) {
                JOptionPane.showMessageDialog(null, lostPetLogs.get(lostSelectedIndex).logEntry(), "Lost Pet Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Selected Lost Pet data is missing or corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid pet selection. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        LostPetsList.clearSelection();
    }//GEN-LAST:event_LostDisplayActionPerformed

    private void LostSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LostSearchActionPerformed
        String searchTerm = JOptionPane.showInputDialog(null, "Enter pet name to search:", "Search Pet", JOptionPane.QUESTION_MESSAGE);

        try {
            if (searchTerm != null && !searchTerm.isEmpty()) {
                boolean found = false;

                for (LostPetLog lostPet : lostPetLogs) {
                    if (lostPet.getName().equalsIgnoreCase(searchTerm)) {
                        JOptionPane.showMessageDialog(null, "Lost Pet Found: \n" + lostPet.logEntry(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "No pet found with the name: " + searchTerm + ".\nCheck if it is Found.", "Search Result", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while performing the search. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_LostSearchActionPerformed

    private void DateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DateTextFieldActionPerformed

    private void SeenTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeenTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SeenTextFieldActionPerformed

    private void NameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameTextFieldActionPerformed

    private void ReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportButtonActionPerformed
        try {
            String name = this.NameTextField.getText().trim();
            if (name.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter pet name.", "Required Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String type = this.TypeTextField.getText().trim();
            if (type.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter pet type.", "Required Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String description = this.DescriptionTextArea.getText().trim();
            if (description.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter pet description.", "Required Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String contact = this.ContactTextField.getText().trim();
            if (contact.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter contact.", "Required Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String status = (String) this.StatusComboBox.getSelectedItem();
            if(status.equals("Lost")) {
                String lastSeen = this.SeenTextField.getText().trim();
                if (lastSeen == null) {
                    JOptionPane.showMessageDialog(null, "Lost Pet Report cancelled!", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String dateLost;

                while (true) {
                    dateLost = this.DateTextField.getText().trim();

                    if (dateLost == null) {
                        JOptionPane.showMessageDialog(null, "Lost Pet Report cancelled!", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (dateLost.trim().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please provide a valid date in the format YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    else{
                        try{
                            LocalDate.parse(dateLost.trim());
                            break;
                        }
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Invalid date format/values. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                if (lastSeen.trim().isEmpty() || dateLost.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please provide both 'Last Seen' and 'Date Lost' for lost pets.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LostPetLog lostPet = new LostPetLog(lostPetLogCount + 1, name, type, description, contact, lastSeen, dateLost);
                lostPetLogs.add(lostPet);

                JOptionPane.showMessageDialog(null, "Your lost pet has been registered!", "Register", JOptionPane.INFORMATION_MESSAGE);
                
                JOptionPane.showMessageDialog(null, lostPet.logEntry());
                
            } else if (status.equals("Found")) {
                String foundAt = this.SeenTextField.getText().trim();
                if (foundAt == null) {
                    JOptionPane.showMessageDialog(null, "Found Pet Report cancelled!", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String dateFound;

                while (true) {
                    dateFound = this.DateTextField.getText().trim();
                    if (dateFound == null) {
                        JOptionPane.showMessageDialog(null, "Found Pet Report cancelled!", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if (dateFound.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please provide a valid date in the format YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        try {
                            LocalDate.parse(dateFound.trim());
                            break;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Invalid date format/values. Please use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                if (foundAt.trim().isEmpty() || dateFound.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please provide both 'Found At' and 'Date Found' for found pets.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                FoundPetLog foundPet = new FoundPetLog(foundPetLogCount + 1, name, type, description, contact, foundAt, dateFound);
                foundPetLogs.add(foundPet);

                JOptionPane.showMessageDialog(null, "Your pet has been updated to 'Found'", "Register", JOptionPane.INFORMATION_MESSAGE);

                JOptionPane.showMessageDialog(null, foundPet.logEntry());
            } else {
                JOptionPane.showMessageDialog(null, "Invalid status selected. Please select 'Lost' or 'Found'.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.NameTextField.setText("");
            this.TypeTextField.setText("");
            this.DescriptionTextArea.setText("");
            this.ContactTextField.setText("");
            this.SeenTextField.setText("");
            this.DateTextField.setText("");
            this.StatusComboBox.setSelectedIndex(0);

            PetLog pet = new PetLog(petLogCount + 1, name, type, description, status, contact);

            petLogs.add(pet);

            lostPetsListModel.clear();
            foundPetsListModel.clear();

            for (LostPetLog lostPet : lostPetLogs) {
                lostPetsListModel.addElement(lostPet.getName()+" - Date Lost: "+lostPet.getDateLost());
            }

            for (FoundPetLog foundPet : foundPetLogs) {
                foundPetsListModel.addElement(foundPet.getName()+" - Date Found: "+foundPet.getDateFound());
            }

            petLogCount = petLogs.size();
            lostPetLogCount = lostPetLogs.size();
            foundPetLogCount = foundPetLogs.size();
            archivePetLogCount = archivePetLogs.size();
            
            this.LostPetsCount.setText(Integer.toString(lostPetLogCount));
            this.LostPetsCount1.setText(Integer.toString(lostPetLogCount));
            this.FoundPetsCount.setText(Integer.toString(foundPetLogCount));
            this.FoundPetsCount2.setText(Integer.toString(foundPetLogCount));
            this.ArchivePetsCount.setText(Integer.toString(archivePetLogCount));
            
            updateDemographics();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ReportButtonActionPerformed

    private void StatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusComboBoxActionPerformed
        // TODO add your handling code here:
        String status = (String) this.StatusComboBox.getSelectedItem();
        if(status.equals("Select Status")) {
            SeenLabel.setVisible(false);
            SeenTextField.setVisible(false);
            DateLabel.setVisible(false);
            DateTextField.setVisible(false);
        } else if(status.equals("Lost")){
            SeenLabel.setText("Last Seen at:");
            DateLabel.setText("Date Lost: (YYYY-MM-DD)");
            SeenLabel.setVisible(true);
            SeenTextField.setVisible(true);
            DateLabel.setVisible(true);
            DateTextField.setVisible(true);
        } else{
            SeenLabel.setText("Found at:");
            DateLabel.setText("Date Found: (YYYY-MM-DD)");
            SeenLabel.setVisible(true);
            SeenTextField.setVisible(true);
            DateLabel.setVisible(true);
            DateTextField.setVisible(true);
        }
    }//GEN-LAST:event_StatusComboBoxActionPerformed

    private void foundNameSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foundNameSortActionPerformed
        // TODO add your handling code here:
        sortPetLogs("foundname");
    }//GEN-LAST:event_foundNameSortActionPerformed

    private void foundDateSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foundDateSortActionPerformed
        // TODO add your handling code here:
        sortPetLogs("dateFound");
    }//GEN-LAST:event_foundDateSortActionPerformed

    private void lostDateSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lostDateSortActionPerformed
        // TODO add your handling code here:
        sortPetLogs("dateLost");
    }//GEN-LAST:event_lostDateSortActionPerformed

    private void lostNameSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lostNameSortActionPerformed
        // TODO add your handling code here:
        sortPetLogs("lostname");
    }//GEN-LAST:event_lostNameSortActionPerformed

    private void DisplayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayButtonActionPerformed
        int lostSelectedIndex = LostPetsList.getSelectedIndex();
        int foundSelectedIndex = FoundPetsList.getSelectedIndex();
        int archiveSelectedIndex = ArchivePetsList.getSelectedIndex();

        if (lostSelectedIndex == -1 && foundSelectedIndex == -1) {
            JOptionPane.showMessageDialog(null, "No pet selected. Please select a pet to view.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lostSelectedIndex >= 0 && lostSelectedIndex < lostPetLogs.size()) {
            if (lostPetLogs.get(lostSelectedIndex) != null) {
                JOptionPane.showMessageDialog(null, lostPetLogs.get(lostSelectedIndex).logEntry(), "Lost Pet Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Selected Lost Pet data is missing or corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (foundSelectedIndex >= 0 && foundSelectedIndex < foundPetLogs.size()) {
            LostPetsList.clearSelection();
            if (foundPetLogs.get(foundSelectedIndex) != null) {
                JOptionPane.showMessageDialog(null, foundPetLogs.get(foundSelectedIndex).logEntry(), "Found Pet Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Selected Found Pet data is missing or corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (archiveSelectedIndex >= 0 && archiveSelectedIndex < archivePetLogs.size()) {
            if (archivePetLogs.get(archiveSelectedIndex) != null) {
                JOptionPane.showMessageDialog(null, archivePetLogs.get(foundSelectedIndex).logEntry(), "Found Pet Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Selected Found Pet data is missing or corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
            } 
        }else {
            JOptionPane.showMessageDialog(null, "Invalid pet selection. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        FoundPetsList.clearSelection();
        LostPetsList.clearSelection();
        ArchivePetsList.clearSelection();
    }//GEN-LAST:event_DisplayButtonActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        String searchTerm = JOptionPane.showInputDialog(null, "Enter pet name to search:", "Search Pet", JOptionPane.QUESTION_MESSAGE);

        try {
            if (searchTerm != null && !searchTerm.isEmpty()) {
                boolean found = false;

                for (ArchivePetLog archivePet : archivePetLogs) {
                    if (archivePet.getName().equalsIgnoreCase(searchTerm)) {
                        JOptionPane.showMessageDialog(null, "Archived Pet Found: \n" + archivePet.logEntry(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                        found = true;
                    }
                }
                
                for (LostPetLog lostPet : lostPetLogs) {
                    if (lostPet.getName().equalsIgnoreCase(searchTerm)) {
                        JOptionPane.showMessageDialog(null, "Lost Pet Found: \n" + lostPet.logEntry(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                        found = true;
                    }
                }

                if (!found) {
                    for (FoundPetLog foundPet : foundPetLogs) {
                        if (foundPet.getName().equalsIgnoreCase(searchTerm)) {
                            JOptionPane.showMessageDialog(null, "Found Pet Found: \n" + foundPet.logEntry(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                            found = true;
                        }
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(null, "No pet found with the name: " + searchTerm, "Search Result", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while performing the search. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_SearchButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MissingPetsLogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MissingPetsLogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MissingPetsLogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MissingPetsLogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MissingPetsLogger().setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ArchivePetsCount;
    private javax.swing.JList<String> ArchivePetsList;
    private javax.swing.JTextField ContactTextField;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JTextField DateTextField;
    private javax.swing.JTextArea DescriptionTextArea;
    private javax.swing.JButton DisplayButton;
    private javax.swing.JButton FoundDisplay;
    private javax.swing.JLabel FoundPetsCount;
    private javax.swing.JLabel FoundPetsCount2;
    private javax.swing.JList<String> FoundPetsList;
    private javax.swing.JList<String> FoundPetsList2;
    private javax.swing.JButton FoundRemove;
    private javax.swing.JButton FoundSearch;
    private javax.swing.JButton LostCancel;
    private javax.swing.JButton LostDisplay;
    private javax.swing.JButton LostEdit;
    private javax.swing.JButton LostFound;
    private javax.swing.JLabel LostPetsCount;
    private javax.swing.JLabel LostPetsCount1;
    private javax.swing.JList<String> LostPetsList;
    private javax.swing.JList<String> LostPetsList1;
    private javax.swing.JButton LostSearch;
    private javax.swing.JTextField NameTextField;
    private javax.swing.JButton ReportButton;
    private javax.swing.JButton SearchButton;
    private javax.swing.JLabel SeenLabel;
    private javax.swing.JTextField SeenTextField;
    private javax.swing.JComboBox<String> StatusComboBox;
    private javax.swing.JTextField TypeTextField;
    private javax.swing.JButton archiveDateSort;
    private javax.swing.JButton archiveDisplay;
    private javax.swing.JButton archiveEdit;
    private javax.swing.JButton archiveNameSort;
    private javax.swing.JButton archiveRestore;
    private javax.swing.JButton archiveSearch;
    private javax.swing.JLabel birdPercentageLabel;
    private javax.swing.JProgressBar birdProgressBar;
    private javax.swing.JLabel catPercentageLabel;
    private javax.swing.JProgressBar catProgressBar;
    private javax.swing.JLabel dogPercentageLabel;
    private javax.swing.JProgressBar dogProgressBar;
    private javax.swing.JButton foundDateSort;
    private javax.swing.JButton foundDateSortX;
    private javax.swing.JButton foundNameSort;
    private javax.swing.JButton foundNameSortX;
    private javax.swing.JLabel hamsterPercentageLabel;
    private javax.swing.JProgressBar hamsterProgressBar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton lostDateSort;
    private javax.swing.JButton lostDateSortX;
    private javax.swing.JButton lostNameSort;
    private javax.swing.JButton lostNameSortX;
    // End of variables declaration//GEN-END:variables
}
