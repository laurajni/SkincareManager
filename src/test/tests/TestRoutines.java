package tests;

import model.Routines;
import model.product.SkincareProduct;
import model.product.active.Active;
import model.product.cleanser.Cleanser;
import model.product.moisturizer.Moisturizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.product.active.ActiveType.ACID;
import static model.product.active.ActiveType.VITAMIN;
import static model.product.moisturizer.MoisturizerType.CREAM;
import static model.product.moisturizer.MoisturizerType.SUNSCREEN;
import static org.junit.jupiter.api.Assertions.*;

class TestRoutines {
    private Routines testRoutines;
    private Active active;
    private Active active2;
    private Cleanser cleanser;
    private Cleanser cleanser2;
    private Moisturizer moisturizer;
    private Moisturizer moisturizer2;

    @BeforeEach
    void beforeEachTest() {
        testRoutines = new Routines();
        active = new Active("The Ordinary Glycolic Acid", ACID);
        active2 = new Active("The Ordinary Vitamin C", VITAMIN);
        cleanser = new Cleanser("Cleansing Foam");
        cleanser2 = new Cleanser("Cleansing Oil");
        moisturizer = new Moisturizer("Hydroboost", CREAM);
        moisturizer2 = new Moisturizer("Kikumasamune", SUNSCREEN);
    }

    @Test
    void testAddAM() {
        assertEquals(0, testRoutines.getAM().size());
        testRoutines.addProductAM(active);
        assertEquals(1, testRoutines.getAM().size());
        assertTrue(testRoutines.getAM().contains(active));
    }

    @Test
    void testAddPM() {
        assertEquals(0, testRoutines.getPM().size());
        testRoutines.addProductPM(cleanser);
        assertEquals(1, testRoutines.getPM().size());
        assertTrue(testRoutines.getPM().contains(cleanser));
    }

    @Test
    void testRemoveAM() {
        testRoutines.addProductAM(active);
        testRoutines.addProductAM(moisturizer);
        testRoutines.removeFromAM(0);
        assertEquals(1, testRoutines.getAM().size());
        assertFalse(testRoutines.getAM().contains(active));
        assertTrue(testRoutines.getAM().contains(moisturizer));
    }

    @Test
    void testRemovePM() {
        testRoutines.addProductPM(cleanser);
        testRoutines.addProductPM(moisturizer);
        testRoutines.removeFromPM(0);
        assertEquals(1, testRoutines.getPM().size());
        assertFalse(testRoutines.getPM().contains(cleanser));
        assertTrue(testRoutines.getPM().contains(moisturizer));
    }

    @Test
    void testSortAM1() {
        testRoutines.addProductAM(active);
        testRoutines.addProductAM(cleanser);
        testRoutines.addProductAM(moisturizer);
        testRoutines.sortAM();
        assertEquals(cleanser, testRoutines.getAM().get(0));
        assertEquals(active, testRoutines.getAM().get(1));
        assertEquals(moisturizer, testRoutines.getAM().get(2));
    }

    @Test
    void testSortAM2() {
        testRoutines.addProductAM(active);
        testRoutines.addProductAM(cleanser);
        testRoutines.addProductAM(active2);
        testRoutines.sortAM();
        assertEquals(cleanser, testRoutines.getAM().get(0));
        assertEquals(active, testRoutines.getAM().get(1));
        assertEquals(active2, testRoutines.getAM().get(2));
    }

    @Test
    void testSortAM3() {
        testRoutines.addProductAM(cleanser2);
        testRoutines.addProductAM(cleanser);
        testRoutines.sortAM();
        assertEquals(cleanser2, testRoutines.getAM().get(0));
        assertEquals(cleanser, testRoutines.getAM().get(1));
    }

    @Test
    void testSortPM1() {
        testRoutines.addProductPM(moisturizer);
        testRoutines.addProductPM(active);
        testRoutines.addProductPM(cleanser);
        testRoutines.sortPM();
        assertEquals(cleanser, testRoutines.getPM().get(0));
        assertEquals(active, testRoutines.getPM().get(1));
        assertEquals(moisturizer, testRoutines.getPM().get(2));
    }

    @Test
    void testSortPM2() {
        testRoutines.addProductPM(moisturizer);
        testRoutines.addProductPM(active);
        testRoutines.addProductPM(active2);
        testRoutines.sortPM();
        assertEquals(active, testRoutines.getPM().get(0));
        assertEquals(active2, testRoutines.getPM().get(1));
        assertEquals(moisturizer, testRoutines.getPM().get(2));
    }

    @Test
    void testSortPM3() {
        testRoutines.addProductPM(moisturizer);
        testRoutines.addProductPM(moisturizer2);
        testRoutines.addProductPM(cleanser);
        testRoutines.sortPM();
        assertEquals(cleanser, testRoutines.getPM().get(0));
        assertEquals(moisturizer, testRoutines.getPM().get(1));
        assertEquals(moisturizer2, testRoutines.getPM().get(2));
    }

    @Test
    void testContainsAM() {
        testRoutines.addProductAM(active);
        assertTrue(testRoutines.containsAM(active.getName()));
        assertFalse(testRoutines.containsAM("fail"));
    }

    @Test
    void testContainsPM() {
        testRoutines.addProductPM(active);
        assertTrue(testRoutines.containsPM(active.getName()));
        assertFalse(testRoutines.containsPM("fail"));
    }

    @Test
    void testGetAMProduct() {
        testRoutines.addProductAM(active);
        SkincareProduct product = testRoutines.getAMProduct(active.getName());
        assertEquals(active, product);
        assertNull(testRoutines.getAMProduct("fail"));
    }

    @Test
    void testGetPMProduct() {
        testRoutines.addProductPM(active);
        SkincareProduct product = testRoutines.getPMProduct(active.getName());
        assertEquals(active, product);
        assertNull(testRoutines.getPMProduct("fail"));
    }

}
