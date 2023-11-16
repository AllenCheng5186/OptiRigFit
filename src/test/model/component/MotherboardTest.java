package model.component;

import model.component.motherboard.Motherboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.component.motherboard.FormSize.ATX;
import static model.component.motherboard.Socket.LGA1700;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MotherboardTest {
    private Motherboard testMotherboard;

    @BeforeEach
    void SetUpBeforeEachTest() {
        testMotherboard = new Motherboard("Asus ROG MAXIMUS Z690 APEX",
                LGA1700, ATX, 96, 2, 1483.65);
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

}
