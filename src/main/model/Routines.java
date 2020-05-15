package model;

import model.product.SkincareProduct;
import model.product.active.Active;
import model.product.cleanser.Cleanser;
import model.product.moisturizer.Moisturizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Routines implements Comparator<SkincareProduct> {
    private ArrayList<SkincareProduct> amRoutine;
    private ArrayList<SkincareProduct> pmRoutine;

    public Routines() {
        amRoutine = new ArrayList<>();
        pmRoutine = new ArrayList<>();
    }

    // GETTERS
    public ArrayList<SkincareProduct> getAM() {
        return amRoutine;
    }

    public ArrayList<SkincareProduct> getPM() {
        return pmRoutine;
    }

    // MODIFIES: this
    // EFFECTS: adds a skincare product to the AM routine list
    public void addProductAM(SkincareProduct sp) {
        amRoutine.add(sp);
    }

    // MODIFIES: this
    // EFFECTS: adds a skincare product to the PM routine list
    public void addProductPM(SkincareProduct sp) {
        pmRoutine.add(sp);
    }

    // MODIFIES: this
    // EFFECTS: removes a skincare product from the AM routine list
    public void removeFromAM(int i) {
        amRoutine.remove(i);
    }

    // MODIFIES: this
    // EFFECTS: removes a skincare product from the PM routine list
    public void removeFromPM(int i) {
        pmRoutine.remove(i);
    }

    // MODIFIES: this
    // EFFECTS: sorts the AM list by cleanser, active, then moisturizer
    public void sortAM() {
        Collections.sort(amRoutine, this);
    }

    // MODIFIES: this
    // EFFECTS: sorts the PM list by cleanser, active, then moisturizer
    public void sortPM() {
        Collections.sort(pmRoutine, this);
    }

    public Boolean containsAM(String name) {
        for (SkincareProduct sp : amRoutine) {
            if (name.equals(sp.getName())) {
                return true;
            }
        }
        return false;
    }

    public Boolean containsPM(String name) {
        for (SkincareProduct sp : pmRoutine) {
            if (name.equals(sp.getName())) {
                return true;
            }
        }
        return false;
    }

    public SkincareProduct getAMProduct(String name) {
        for (SkincareProduct sp : amRoutine) {
            if (name.equals(sp.getName())) {
                return sp;
            }
        }
        return null;
    }

    public SkincareProduct getPMProduct(String name) {
        for (SkincareProduct sp : pmRoutine) {
            if (name.equals(sp.getName())) {
                return sp;
            }
        }
        return null;
    }

    @Override
    public int compare(SkincareProduct o1, SkincareProduct o2) {
        if (o1 instanceof Cleanser) {
            if (o2 instanceof Cleanser) {
                return 0;
            }
        } else if (o1 instanceof Active) {
            if (o2 instanceof Cleanser) {
                return 1;
            } else if (o2 instanceof Active) {
                return 0;
            }
        } else {
            if (o2 instanceof Moisturizer) {
                return 0;
            } else {
                return 1;
            }
        }
        return -1;
    }
}
