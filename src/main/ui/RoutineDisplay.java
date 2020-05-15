package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import model.ProductShelf;
import model.Routines;
import model.product.SkincareProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static ui.SkincareRoutine.*;

public class RoutineDisplay {
    public JPanel routineDisplayPanel;
    private JTabbedPane tabbedPane1;
    private JPanel amPanel;
    private JPanel pmPanel;
    private JTable amTable;
    private JTable pmTable;
    private JButton returnAMButton;
    private JButton savePMButton;
    private JButton resetUsedPMButton;
    private JButton sortPMButton;
    private JButton removePMButton;
    private JButton returnPMButton;
    private JButton sortAMButton;
    private JButton removeAMButton;
    private JButton useAMButton;
    private JButton resetUsedAMButton;
    private JButton saveAMButton;
    private JButton usePMButton;
    private Routines routines;
    private ProductShelf shelf;

    RoutineDisplay() {
        loadRoutines();
        loadShelf();
        loadTables();
        loadButtonListeners();
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
        amTable.setModel(amTableModel(routines.getAM()));
        amTable.setDefaultEditor(Object.class, null);
        pmTable.setModel(pmTableModel(routines.getPM()));
        pmTable.setDefaultEditor(Object.class, null);
    }

    private TableModel amTableModel(ArrayList<SkincareProduct> am) {
        DefaultTableModel model = new DefaultTableModel(new Object[] {"Product", "Name", "Used"}, 0);
        int counter = 0;
        for (SkincareProduct sp : am) {
            checkAMInShelf(sp, counter);
            counter++;
        }
        routines.sortAM();
        counter = 0;
        for (SkincareProduct sp : am) {
            String type = checkAMInShelf(sp, counter);
            model.addRow(new Object[] {type, sp.getName(), sp.getUsed()});
            counter++;
        }
        return model;
    }

    private TableModel pmTableModel(ArrayList<SkincareProduct> pm) {
        DefaultTableModel model = new DefaultTableModel(new Object[] {"Product Name", "Type", "Used"}, 0);
        int counter = 0;
        for (SkincareProduct sp : pm) {
            checkPMInShelf(sp, counter);
            counter++;
        }
        routines.sortPM();
        counter = 0;
        for (SkincareProduct sp : pm) {
            String type = checkPMInShelf(sp, counter);
            model.addRow(new Object[] {type, sp.getName(), sp.getUsed()});
            counter++;
        }
        return model;
    }

    private String checkAMInShelf(SkincareProduct sp, int index) {
        if (shelf.containsActive((sp.getName()))) {
            routines.getAM().set(index, shelf.getActive(sp.getName()));
            return "Active";
        } else if (shelf.containsCleanser((sp.getName()))) {
            routines.getAM().set(index, shelf.getCleanser(sp.getName()));
            return "Cleanser";
        } else if (shelf.containsMoisturizer((sp.getName()))) {
            routines.getAM().set(index, shelf.getMoisturizer(sp.getName()));
            return "Moisturizer";
        } else {
            routines.removeFromAM(index);
            return "false";
        }
    }

    private String checkPMInShelf(SkincareProduct sp, int index) {
        if (shelf.containsActive((sp.getName()))) {
            routines.getPM().set(index, shelf.getActive(sp.getName()));
            return "Active";
        } else if (shelf.containsCleanser((sp.getName()))) {
            routines.getPM().set(index, shelf.getCleanser(sp.getName()));
            return "Cleanser";
        } else if (shelf.containsMoisturizer((sp.getName()))) {
            routines.getPM().set(index, shelf.getMoisturizer(sp.getName()));
            return "Moisturizer";
        } else {
            routines.removeFromPM(index);
            return "false";
        }
    }


    private void loadButtonListeners() {
        resetUsedAMButton.addActionListener(new ResetUsedAM());
        resetUsedPMButton.addActionListener(new ResetUsedPM());
        useAMButton.addActionListener(new UseAM());
        usePMButton.addActionListener(new UsePM());
        saveAMButton.addActionListener(new SaveRoutine());
        savePMButton.addActionListener(new SaveRoutine());
        sortAMButton.addActionListener(new SortAM());
        sortPMButton.addActionListener(new SortPM());
        removeAMButton.addActionListener(new RemoveAM());
        removePMButton.addActionListener(new RemovePM());
        returnAMButton.addActionListener(new ReturnToMainMenu());
        returnPMButton.addActionListener(new ReturnToMainMenu());
    }


    private class ResetUsedAM implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (SkincareProduct sp : routines.getAM()) {
                sp.resetUse();
            }
            loadTables();
        }
    }

    private class ResetUsedPM implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (SkincareProduct sp : routines.getPM()) {
                sp.resetUse();
            }
            loadTables();
        }
    }

    private class UseAM implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = amTable.getSelectedRow();
            int column = amTable.getSelectedColumn();
            if (row >= 0 || column >= 0) {
                String name = (String) amTable.getValueAt(row, column);
                routines.getAMProduct(name).use();
            }
            loadTables();
        }
    }

    private class UsePM implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = pmTable.getSelectedRow();
            int column = pmTable.getSelectedColumn();
            if (row >= 0 || column >= 0) {
                String name = (String) pmTable.getValueAt(row, column);
                routines.getPMProduct(name).use();
            }
            loadTables();
        }
    }

    private class SaveRoutine implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveRoutines();
            saveShelf();
            loadTables();
        }
    }

    private class SortAM implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            routines.sortAM();
            loadTables();
        }
    }

    private class SortPM implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            routines.sortPM();
            loadTables();
        }
    }

    private class RemoveAM implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = amTable.getSelectedRow();
            int column = amTable.getSelectedColumn();
            if (row >= 0 || column >= 0) {
                String name = (String) amTable.getValueAt(row, column);
                if (routines.getAM().contains(routines.getAMProduct(name))) {
                    routines.removeFromAM(routines.getAM().indexOf(routines.getAMProduct(name)));
                    loadTables();
                }
            }
        }
    }

    private class RemovePM implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = pmTable.getSelectedRow();
            int column = pmTable.getSelectedColumn();
            if (row >= 0 || column >= 0) {
                String name = (String) pmTable.getValueAt(row, column);
                if (routines.getPM().contains(routines.getPMProduct(name))) {
                    routines.removeFromPM(routines.getPM().indexOf(routines.getPMProduct(name)));
                    loadTables();
                }

            }
        }
    }

    private class ReturnToMainMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            routineFrame.setVisible(false);
            mainFrame.setVisible(true);
        }
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


}
