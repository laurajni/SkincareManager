package model.product.active;

import model.product.SkincareProduct;

public class Active extends SkincareProduct {
    private ActiveType typeOfActive;

    public Active(String name, ActiveType typeOfActive) {
        super(name);
        this.typeOfActive = typeOfActive;

    }

    // getters
    public ActiveType getActiveType() {
        return typeOfActive;
    }


    // MODIFIES: this
    // EFFECTS: sets activeType to given
    public void setActiveType(ActiveType a) {
        this.typeOfActive = a;
    }
}
