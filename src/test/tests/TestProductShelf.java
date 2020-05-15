package tests;

import model.ProductShelf;
import model.product.active.Active;
import model.product.cleanser.Cleanser;
import model.product.moisturizer.Moisturizer;
import model.product.moisturizer.MoisturizerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.product.active.ActiveType.ACID;
import static model.product.active.ActiveType.VITAMIN;
import static org.junit.jupiter.api.Assertions.*;

class TestProductShelf {
    private ProductShelf ps;
    private Active active;
    private Active active2;
    private Cleanser cleanser;
    private Moisturizer moisturizer;

    @BeforeEach
    void beforeEachTest() {
        ps = new ProductShelf();
        active = new Active("The Ordinary Glycolic Acid", ACID);
        active2 = new Active("The Ordinary Vitamin C", VITAMIN);
        cleanser = new Cleanser("Cleansing Foam");
        moisturizer = new Moisturizer("Hydroboost", MoisturizerType.CREAM);
    }

    @Test
    void testGetActive() {
        ps.addActive(active.getName(), active);
        assertEquals(active, ps.getActive(active.getName()));
    }

    @Test
    void testGetCleanser() {
        ps.addCleanser(cleanser.getName(), cleanser);
        assertEquals(cleanser, ps.getCleanser(cleanser.getName()));
    }

    @Test
    void testGetMoisturizer() {
        ps.addMoisturizer(moisturizer.getName(), moisturizer);
        assertEquals(moisturizer, ps.getMoisturizer(moisturizer.getName()));
    }

    @Test
    void testAddActive() {
        ps.addActive(active.getName(), active);
        assertEquals(1, ps.getActives().size());
        assertTrue(ps.getActives().containsValue(active));
        ps.addActive(active2.getName(), active2);
        assertEquals(2, ps.getActives().size());
        assertTrue(ps.containsActive(active2.getName()));
    }

    @Test
    void testAddCleanser() {
        ps.addCleanser(cleanser.getName(), cleanser);
        assertEquals(1, ps.getCleansers().size());
        assertTrue(ps.containsCleanser(cleanser.getName()));
    }

    @Test
    void testAddMoisturizer() {
        ps.addMoisturizer(moisturizer.getName(), moisturizer);
        assertEquals(1, ps.getMoisturizers().size());
        assertTrue(ps.containsMoisturizer(moisturizer.getName()));
    }

    @Test
    void testRemoveActive() {
        ps.addActive(active.getName(), active);
        assertEquals(1, ps.getActives().size());
        ps.removeActive(active.getName());
        assertEquals(0, ps.getActives().size());
        assertFalse(ps.containsActive(active.getName()));
    }

    @Test
    void testRemoveCleansers() {
        ps.addCleanser(cleanser.getName(), cleanser);
        ps.removeCleanser(cleanser.getName());
        assertEquals(0, ps.getCleansers().size());
        assertFalse(ps.containsCleanser(cleanser.getName()));
    }

    @Test
    void testRemoveMoisturizer() {
        ps.addMoisturizer(moisturizer.getName(), moisturizer);
        ps.removeMoisturizer(moisturizer.getName());
        assertEquals(0, ps.getMoisturizers().size());
        assertFalse(ps.containsMoisturizer(moisturizer.getName()));
    }

}
