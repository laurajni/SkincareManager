package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import model.ProductShelf;
import model.Routines;
import model.product.active.Active;
import model.product.active.ActiveType;
import model.product.cleanser.Cleanser;
import model.product.moisturizer.Moisturizer;
import model.product.moisturizer.MoisturizerType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static ui.SkincareRoutine.*;

public class ShelfDisplay {
    public JPanel shelfDisplayPanel;
    public JTable activesTable;
    public JTextField textFieldActive;
    private JTabbedPane tabbedPane1;
    private JPanel activePanel;
    private JPanel cleanserPanel;
    private JPanel moisturizerPanel;
    private JRadioButton miscAButton;
    private JRadioButton vitaminAButton;
    private JRadioButton acidAButton;
    private JButton addAButton;
    private JTable cleansersTable;
    private JButton addCButton;
    private JRadioButton cleanserButton;
    private JTextField textFieldCleanser;
    private JTable moisturizersTable;
    private JTextField textFieldMoisturizer;
    private JRadioButton creamMButton;
    private JRadioButton sunscreenMButton;
    private JRadioButton miscMButton;
    private JButton addMButton;
    private JButton returnMButton;
    private JButton returnCButton;
    private JButton saveAButton;
    private JButton saveCButton;
    private JButton saveMButton;
    private JButton returnAButton;
    private JButton removeSelectedC;
    private JButton addRoutineAMM;
    private JButton removeSelectedM;
    private JButton removeSelectedA;
    private JButton addRoutinePMA;
    private JButton addRoutinePMC;
    private JButton addRoutinePMM;
    private JButton addRoutineAMA;
    private JButton addRoutineAMC;
    private ProductShelf shelf;
    private Routines routines;

    ShelfDisplay() {
        loadShelf();
        loadRoutines();
        addButtonListeners();
        loadTables();
    }

    private static TableModel activeTableModel(Map<String, Active> map) {
        DefaultTableModel model = new DefaultTableModel(new Object[] {"Name", "Type"}, 0);
        for (Map.Entry<String, Active> entry : map.entrySet()) {
            model.addRow(new Object[] {entry.getKey(), entry.getValue().getActiveType().toString()});
        }
        return model;
    }

    private static TableModel moisturizerTableModel(Map<String, Moisturizer> map) {
        DefaultTableModel model = new DefaultTableModel(new Object[] {"Name", "Type"}, 0);
        for (Map.Entry<String, Moisturizer> entry : map.entrySet()) {
            model.addRow(new Object[] {entry.getKey(), entry.getValue().getMoisturizerType().toString()});
        }
        return model;
    }

    private static TableModel cleanserTableModel(Map<String, Cleanser> map) {
        DefaultTableModel model = new DefaultTableModel(new Object[] {"Name"}, 0);
        for (Map.Entry<String, Cleanser> entry : map.entrySet()) {
            model.addRow(new Object[] {entry.getKey()});
        }
        return model;
    }

    private void loadShelf() {
        try {
            JsonReader reader = new JsonReader(new FileReader(ShelfFile));
            Gson gson = new Gson();
            shelf = gson.fromJson(reader, ProductShelf.class);
            System.out.println("Your shelf has been loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("Shelf file not found, starting new shelf");
            shelf = new ProductShelf();
        }
    }

    private void loadRoutines() {
        try {
            JsonReader reader = new JsonReader(new FileReader(RoutineFile));
            Gson gson = new Gson();
            routines = gson.fromJson(reader, Routines.class);
            System.out.println("Your shelf has been loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("Routine file not found");
            routines = new Routines();
        }
    }

    private void loadTables() {
        activesTable.setModel(activeTableModel(shelf.getActives()));
        activesTable.setDefaultEditor(Object.class, null);
        cleansersTable.setModel(cleanserTableModel(shelf.getCleansers()));
        cleansersTable.setDefaultEditor(Object.class, null);
        moisturizersTable.setModel(moisturizerTableModel(shelf.getMoisturizers()));
        moisturizersTable.setDefaultEditor(Object.class, null);
    }

    private void saveShelf() {
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.setPrettyPrinting().create();
        String json = gson.toJson(shelf);
        FileWriter writer;
        try {
            writer = new FileWriter(ShelfFile);
            writer.write(json);
            writer.close();
            System.out.println("Saved shelf!");

        } catch (IOException ioe) {
            System.out.println("Shelf file cannot be written");
            ioe.printStackTrace();
        }
    }

    private void saveRoutines() {
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.setPrettyPrinting().create();
        String json = gson.toJson(routines);
        FileWriter writer;
        try {
            writer = new FileWriter(RoutineFile);
            writer.write(json);
            writer.close();
            System.out.println("Saved routines!");

        } catch (IOException ioe) {
            System.out.println("Routine file cannot be written");
            ioe.printStackTrace();
        }
    }

    private void addButtonListeners() {
        addAButton.addActionListener(new AddActive());
        addCButton.addActionListener(new AddCleanser());
        addMButton.addActionListener(new AddMoisturizer());
        returnMButton.addActionListener(new ReturnButton());
        returnAButton.addActionListener(new ReturnButton());
        returnCButton.addActionListener(new ReturnButton());
        saveAButton.addActionListener(new SaveShelf());
        saveCButton.addActionListener(new SaveShelf());
        saveMButton.addActionListener(new SaveShelf());
        removeSelectedA.addActionListener(new RemoveActive());
        removeSelectedC.addActionListener(new RemoveCleanser());
        removeSelectedM.addActionListener(new RemoveMoisturizer());
        addRoutinePMA.addActionListener(new AddActiveToRoutine(false));
        addRoutineAMA.addActionListener(new AddActiveToRoutine(true));
        addRoutineAMC.addActionListener(new AddCleanserToRoutine(true));
        addRoutinePMC.addActionListener(new AddCleanserToRoutine(false));
        addRoutineAMM.addActionListener(new AddMoisturizerToRoutine(true));
        addRoutinePMM.addActionListener(new AddMoisturizerToRoutine(false));

    }

    private class AddActive implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = textFieldActive.getText();
            if (shelf.containsActive(name) || !name.equals("")) {
                if (acidAButton.isSelected()) {
                    shelf.addActive(name, new Active(name, ActiveType.ACID));
                } else if (vitaminAButton.isSelected()) {
                    shelf.addActive(name, new Active(name, ActiveType.VITAMIN));
                } else if (miscAButton.isSelected()) {
                    shelf.addActive(name, new Active(name, ActiveType.MISC));
                } else {
                    System.out.println("Please select a type");
                }
            } else {
                System.out.println("Please put in a valid name");
            }
            loadTables();
        }
    }

    private class AddCleanser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = textFieldCleanser.getText();
            if (shelf.containsCleanser(name) || !name.equals("")) {
                System.out.println(name + " is added");
                shelf.addCleanser(name, new Cleanser(name));
            } else {
                System.out.println("Please put in a valid name");
            }
            loadTables();
        }
    }

    private class AddMoisturizer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = textFieldMoisturizer.getText();
            if (shelf.containsMoisturizer(name) || !name.equals("")) {
                if (creamMButton.isSelected()) {
                    shelf.addMoisturizer(name, new Moisturizer(name, MoisturizerType.CREAM));
                } else if (sunscreenMButton.isSelected()) {
                    shelf.addMoisturizer(name, new Moisturizer(name, MoisturizerType.SUNSCREEN));
                } else if (miscMButton.isSelected()) {
                    shelf.addMoisturizer(name, new Moisturizer(name, MoisturizerType.MISC));
                } else {
                    System.out.println("Please select a type");
                }
            } else {
                System.out.println("Please put in a valid name");
            }
            loadTables();
        }
    }

    public class AddActiveToRoutine implements ActionListener {
        Boolean isAM;

        AddActiveToRoutine(Boolean isAM) {
            this.isAM = isAM;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = activesTable.getSelectedRow();
            int column = activesTable.getSelectedColumn();
            String name = (String)activesTable.getValueAt(row, column);
            if (shelf.containsActive(name) || shelf.containsCleanser(name) || shelf.containsMoisturizer(name)) {
                if (isAM) {
                    if (!routines.containsAM(name)) {
                        routines.addProductAM(shelf.getActive(name));
                        shelf.getActive(name).setAM(true);
                    }
                } else if (!routines.containsPM(name)) {
                    shelf.getActive(name).setPM(true);
                    routines.addProductPM(shelf.getActive(name));
                }
                saveShelf();
                saveRoutines();
            } else {
                System.out.println("Select cell with name");
            }
        }
    }

    public class AddCleanserToRoutine implements ActionListener {
        Boolean isAM;

        AddCleanserToRoutine(Boolean isAM) {
            this.isAM = isAM;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = cleansersTable.getSelectedRow();
            int column = cleansersTable.getSelectedColumn();
            String name = (String)cleansersTable.getValueAt(row, column);
            if (shelf.containsActive(name) || shelf.containsCleanser(name) || shelf.containsMoisturizer(name)) {
                if (isAM) {
                    if (!routines.containsAM(name)) {
                        routines.addProductAM(shelf.getCleanser(name));
                        shelf.getCleanser(name).setAM(true);
                    }
                } else if (!routines.containsPM(name)) {
                    shelf.getCleanser(name).setPM(true);
                    routines.addProductPM(shelf.getCleanser(name));
                }
                saveShelf();
                saveRoutines();
            } else {
                System.out.println("Select cell with name");
            }
        }
    }

    public class AddMoisturizerToRoutine implements ActionListener {
        Boolean isAM;

        AddMoisturizerToRoutine(Boolean isAM) {
            this.isAM = isAM;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = moisturizersTable.getSelectedRow();
            int column = moisturizersTable.getSelectedColumn();
            String name = (String)moisturizersTable.getValueAt(row, column);
            if (shelf.containsActive(name) || shelf.containsCleanser(name) || shelf.containsMoisturizer(name)) {
                if (isAM) {
                    if (!routines.containsAM(name)) {
                        shelf.getMoisturizer(name).setAM(true);
                        routines.addProductAM(shelf.getMoisturizer(name));
                    }
                } else if (!routines.containsPM(name)) {
                    shelf.getMoisturizer(name).setPM(true);
                    routines.addProductPM(shelf.getMoisturizer(name));
                }
                saveShelf();
                saveRoutines();
            } else {
                System.out.println("Select cell with name");
            }
        }
    }

    public class RemoveActive implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = activesTable.getSelectedRow();
            int column = activesTable.getSelectedColumn();
            if (row >= 0 || column >= 0) {
                String name = (String) activesTable.getValueAt(row, column);
                if (shelf.containsActive(name)) {
                    shelf.removeActive(name);
                    loadTables();
                } else {
                    System.out.println("Select a cell with the name of the product you want to remove");
                }
            } else {
                System.out.println("Please select a cell");
            }
        }
    }

    public class RemoveCleanser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = cleansersTable.getSelectedRow();
            int column = cleansersTable.getSelectedColumn();
            if (row >= 0 || column >= 0) {
                String name = (String)cleansersTable.getValueAt(row, column);
                if (shelf.containsCleanser(name)) {
                    shelf.removeCleanser(name);
                    loadTables();
                } else {
                    System.out.println("Select a cell with the name of the product you want to remove");
                }
            } else {
                System.out.println("Please select a cell");
            }

        }
    }

    public class RemoveMoisturizer implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = moisturizersTable.getSelectedRow();
            int column = moisturizersTable.getSelectedColumn();
            if (row >= 0 || column >= 0) {
                String name = (String) moisturizersTable.getValueAt(row, column);
                if (shelf.containsMoisturizer(name)) {
                    shelf.removeMoisturizer(name);
                    loadTables();
                } else {
                    System.out.println("Select a cell with the name of the product you want to remove");
                }
            } else {
                System.out.println("Please select a cell");
            }


        }
    }

    public class SaveShelf implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveShelf();
        }
    }

    public class ReturnButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.setVisible(true);
            shelfFrame.setVisible(false);
        }
    }









}
