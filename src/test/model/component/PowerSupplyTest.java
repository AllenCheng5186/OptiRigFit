package model.component;

import model.component.cpu.Cpu;
import model.component.cpu.CpuMfr;
import model.component.motherboard.FormSize;
import model.component.psu.PowerSupply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PowerSupplyTest {
    private PowerSupply testPowerSupply1;
    private PowerSupply testPowerSupply2;

    @BeforeEach
    void setUpBeforeEachTest() {
        testPowerSupply1 = new PowerSupply("Cooler Master Elite 400W Ver.3",
                FormSize.ATX, 400, false, 89.99);
        testPowerSupply2 = new PowerSupply("Corsair HX1200i ",
                FormSize.ATX, 1200, true, 400);
    }

    @Test
    void testConstructor() {
        assertEquals("Cooler Master Elite 400W Ver.3", testPowerSupply1.getModel());
        assertEquals(FormSize.ATX, testPowerSupply1.getFormSize());
        assertEquals(400, testPowerSupply1.getWattage());
        assertFalse(testPowerSupply1.isModular());
        assertEquals(89.99, testPowerSupply1.getPrice());

        assertEquals("Corsair HX1200i ", testPowerSupply2.getModel());
        assertEquals(FormSize.ATX, testPowerSupply2.getFormSize());
        assertEquals(1200, testPowerSupply2.getWattage());
        assertTrue(testPowerSupply2.isModular());
        assertEquals(400, testPowerSupply2.getPrice());
    }

    @Test
    void testCompareTo() {
        assertEquals(-800, testPowerSupply1.compareTo(testPowerSupply2));
        assertEquals(800, testPowerSupply2.compareTo(testPowerSupply1));
    }

    @Test
    void testEquals() {
        assertTrue(testPowerSupply1.equals(testPowerSupply1));
        assertFalse(testPowerSupply1.equals(testPowerSupply2));
        assertTrue(testPowerSupply1.equals(new PowerSupply("Cooler Master Elite 400W Ver.3",
                FormSize.ATX, 400, false, 89.99)));
        assertFalse(testPowerSupply1.equals(new PowerSupply("Cooler Master Elite 400W Ver.1",
                FormSize.ITX, 400, false, 89.99)));
        assertFalse(testPowerSupply1.equals(new PowerSupply("Cooler Master Elite 400W Ver.3",
                FormSize.ATX, 401, false, 89.99)));
        assertFalse(testPowerSupply1.equals(new PowerSupply("Cooler Master Elite 400W Ver.3",
                FormSize.ATX, 400, true, 89.99)));
        assertFalse(testPowerSupply1.equals(new PowerSupply("Cooler Master Elite 400W Ver.3",
                FormSize.ATX, 400, false, 89.98)));
        assertFalse(testPowerSupply1.equals(new PowerSupply("Cooler Master Elite 400W Ver.3",
                FormSize.MATX, 400, false, 89.99)));
        assertFalse(testPowerSupply1.equals(null));
        assertTrue(testPowerSupply1.equals(new PowerSupply("Cooler Master Elite 400W Ver.3",
                FormSize.ATX, 400, false, 89.99)));
        assertFalse(testPowerSupply1.equals(new Cpu("1", 1, 1, CpuMfr.AMD, 1)));
    }

    @Test
    void testHashCode() {
        assertEquals(testPowerSupply1.hashCode(), testPowerSupply1.hashCode());
        assertNotEquals(testPowerSupply2.hashCode(), testPowerSupply1.hashCode());
    }
}
