package model.product.moisturizer;

import model.product.SkincareProduct;

public class Moisturizer extends SkincareProduct {
    private MoisturizerType typeOfMoisturizer;

    public Moisturizer(String name, MoisturizerType typeOfMoisturizer) {
        super(name);
        this.typeOfMoisturizer = typeOfMoisturizer;
    }

    // GETTERS
    public MoisturizerType getMoisturizerType() {
        return typeOfMoisturizer;
    }

    // MODIFIES: this
    // EFFECTS: sets moisturizerType to given
    public void setMoisturizerType(MoisturizerType m) {
        this.typeOfMoisturizer = m;
    }
}
