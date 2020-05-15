package tests;

import model.product.moisturizer.Moisturizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.product.moisturizer.MoisturizerType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMoisturizer {
    private Moisturizer cream;
    private Moisturizer sunscreen;
    private Moisturizer misc;

    @BeforeEach
    void beforeEachTest() {
        cream = new Moisturizer("cream", CREAM);
        sunscreen = new Moisturizer("sunscreen", SUNSCREEN);
        misc = new Moisturizer("misc", MISC);
    }

    @Test
    void testSetMoisturizerType() {
        assertEquals(CREAM, cream.getMoisturizerType());
        cream.setMoisturizerType(MISC);
        assertEquals(MISC, cream.getMoisturizerType());
    }
}
