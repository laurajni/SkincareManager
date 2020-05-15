package model.product;

public class SkincareProduct {

    private String name;
    private boolean am;
    private boolean pm;
    private boolean used;

    public SkincareProduct(String name) {
        this.name = name;
        am = false;
        pm = false;
        used = false;
    }

    // getters
    public String getName() {
        return name;
    }

    public boolean getAM() {
        return am;
    }

    public boolean getPM() {
        return pm;
    }

    public boolean getUsed() {
        return used;
    }

    // MODIFIES: this
    // EFFECTS: sets am to given boolean
    public void setAM(Boolean am) {
        this.am = am;
    }

    // MODIFIES: this
    // EFFECTS: sets routines to given boolean
    public void setPM(Boolean pm) {
        this.pm = pm;
    }

    // MODIFIES: this
    // EFFECTS: adds 1 to timesUsed
    public void use() {
        used = true;
    }

    // MODIFIES: this
    // EFFECTS: sets timesUsed to 0
    public void resetUse() {
        used = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SkincareProduct a = (SkincareProduct) obj;
        return name.equals(a.getName());
    }

}

