package tests;

import model.product.SkincareProduct;
import model.product.active.Active;
import model.product.cleanser.Cleanser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.product.active.ActiveType.MISC;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSkincareProduct {
    private SkincareProduct skincareTest;

    @BeforeEach
    void beforeEachTest() {
        skincareTest = new Cleanser("Trial Product");
    }


    @Test
    void testSetAM() {
        assertFalse(skincareTest.getAM());
        skincareTest.setAM(true);
        assertTrue(skincareTest.getAM());
        skincareTest.setAM(false);
        assertFalse(skincareTest.getAM());
    }

    @Test
    void testSetPM() {
        assertFalse(skincareTest.getPM());
        skincareTest.setPM(true);
        assertTrue(skincareTest.getPM());
        skincareTest.setPM(false);
        assertFalse(skincareTest.getPM());
    }

    @Test
    void useTest() {
        assertFalse(skincareTest.getUsed());
        skincareTest.use();
        assertTrue(skincareTest.getUsed());
        skincareTest.resetUse();
        assertFalse(skincareTest.getUsed());
    }

    @Test
    void equalsOverride() {
        SkincareProduct skincareTest2 = new Cleanser("Trial Product");
        assertTrue(skincareTest.equals(skincareTest2));
        SkincareProduct skincareTest3 = skincareTest;
        assertTrue(skincareTest.equals(skincareTest3));
        SkincareProduct skincareTest4 = new Active("Trial Product", MISC);
        assertFalse(skincareTest.equals(skincareTest4));
        SkincareProduct skincareTest5 = null;
        assertFalse(skincareTest.equals(skincareTest5));
    }
}
