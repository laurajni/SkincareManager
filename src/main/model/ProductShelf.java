package model;

import model.product.active.Active;
import model.product.cleanser.Cleanser;
import model.product.moisturizer.Moisturizer;

import java.util.HashMap;
import java.util.Map;

public class ProductShelf {

    private Map<String, Active> actives;
    private Map<String, Cleanser> cleansers;
    private Map<String, Moisturizer> moisturizers;

    public ProductShelf() {
        actives = new HashMap<>();
        cleansers = new HashMap<>();
        moisturizers = new HashMap<>();
    }

    // GETTERS
    public Map<String, Active> getActives() {
        return actives;
    }

    public Map<String, Cleanser> getCleansers() {
        return cleansers;
    }

    public Map<String, Moisturizer> getMoisturizers() {
        return moisturizers;
    }

    public Active getActive(String name) {
        return actives.get(name);
    }

    public Moisturizer getMoisturizer(String name) {
        return moisturizers.get(name);
    }

    public Cleanser getCleanser(String name) {
        return cleansers.get(name);
    }

    // REQUIRES: actives != null
    // MODIFIES: this
    // EFFECTS: adds the active to the list of actives
    public void addActive(String key, Active active) {
        actives.put(key, active);
    }

    // REQUIRES: cleansers != null
    // MODIFIES: this
    // EFFECTS: adds the cleanser to the list of cleansers
    public void addCleanser(String key, Cleanser cleanser) {
        cleansers.put(key, cleanser);
    }

    // REQUIRES: moisturizer != null
    // MODIFIES: this
    // EFFECTS: adds the moisturizer to the list of moisturizers
    public void addMoisturizer(String key, Moisturizer moisturizer) {
        moisturizers.put(key, moisturizer);
    }

    // REQUIRES: actives != null
    // MODIFIES: this
    // EFFECTS: removes the active from the list of actives
    public void removeActive(String key) {
        actives.remove(key);
    }

    // REQUIRES: cleansers != null
    // MODIFIES: this
    // EFFECTS: removes the cleanser from the list of cleansers
    public void removeCleanser(String key) {
        cleansers.remove(key);
    }

    // REQUIRES: moisturizers != null
    // MODIFIES: this
    // EFFECTS: removes the moisturizer from the list of moisturizers
    public void removeMoisturizer(String key) {
        moisturizers.remove(key);
    }

    // EFFECTS: returns true iff a is in the set
    public boolean containsActive(String key) {
        return actives.containsKey(key);
    }

    // EFFECTS: returns true iff c is in the set
    public boolean containsCleanser(String key) {
        return cleansers.containsKey(key);
    }

    // EFFECTS: returns true iff m is in the set
    public boolean containsMoisturizer(String key) {
        return moisturizers.containsKey(key);
    }

}
