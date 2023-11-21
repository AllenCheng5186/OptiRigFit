package model.component;

import model.component.motherboard.Motherboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.component.motherboard.FormSize.ATX;
import static model.component.motherboard.FormSize.ITX;
import static model.component.motherboard.Socket.AM5;
import static model.component.motherboard.Socket.LGA1700;
import static org.junit.jupiter.api.Assertions.*;

public class MotherboardTest {
    private Motherboard testMotherboard;
    private Motherboard testMotherboard2;

    @BeforeEach
    void SetUpBeforeEachTest() {
        testMotherboard = new Motherboard("Asus ROG MAXIMUS Z690 APEX",
                LGA1700, ATX, 96, 2, 1483.65);
        testMotherboard2 = new Motherboard("Asus ROG MAXIMUS Z690 FORMULA",
                LGA1700, ATX, 192, 4, 693.39);
    }

    @Test
    void testConstructor() {
        assertEquals("Asus ROG MAXIMUS Z690 APEX", testMotherboard.getModel());
        assertEquals(LGA1700, testMotherboard.getSocket());
        assertEquals(ATX, testMotherboard.getFormSize());
        assertEquals(96, testMotherboard.getMaxRam());
        assertEquals(2, testMotherboard.getRamSlot());
        assertEquals(1483.65, testMotherboard.getPrice());
    }

    @Test
    void testEquals() {
        assertTrue(testMotherboard.equals(testMotherboard));
        assertFalse(testMotherboard.equals(testMotherboard2));
        assertTrue(testMotherboard.equals(new Motherboard("Asus ROG MAXIMUS Z690 APEX",
                LGA1700, ATX, 96, 2, 1483.65)));
        assertFalse(testMotherboard.equals(new Motherboard("Asus ROG MAXIMUS Z690 APEX1",
                LGA1700, ATX, 96, 2, 1483.65)));
        assertFalse(testMotherboard.equals(new Motherboard("Asus ROG MAXIMUS Z690 APEX",
                AM5, ATX, 96, 2, 1483.65)));
        assertFalse(testMotherboard.equals(new Motherboard("Asus ROG MAXIMUS Z690 APEX",
                LGA1700, ITX, 96, 2, 1483.65)));
        assertFalse(testMotherboard.equals(new Motherboard("Asus ROG MAXIMUS Z690 APEX",
                LGA1700, ATX, 95, 2, 1483.65)));
        assertFalse(testMotherboard.equals(new Motherboard("Asus ROG MAXIMUS Z690 APEX",
                LGA1700, ATX, 96, 1, 1483.65)));
        assertFalse(testMotherboard.equals(new Motherboard("Asus ROG MAXIMUS Z690 APEX",
                LGA1700, ATX, 96, 2, 1483.63)));
        assertFalse(testMotherboard.equals(null));
    }

}
