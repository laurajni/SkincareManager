package tests;

import model.product.active.Active;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.product.active.ActiveType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestActive {
    private Active acid;
    private Active vitamin;
    private Active misc;

    @BeforeEach
    void beforeEachTest() {
        acid = new Active("acid", ACID);
        vitamin = new Active("vitamin", VITAMIN);
        misc = new Active("misc", MISC);
    }

    @Test
    void testSetActiveType() {
        assertEquals(ACID, acid.getActiveType());
        acid.setActiveType(MISC);
        assertEquals(MISC, acid.getActiveType());
    }
}
