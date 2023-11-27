package model;

import model.component.cpu.Cpu;
import model.component.cpu.CpuMfr;
import model.component.gpu.Gpu;
import model.component.gpu.GpuMfr;
import model.component.motherboard.FormSize;
import model.component.motherboard.Motherboard;
import model.component.psu.PowerSupply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.component.motherboard.FormSize.ATX;
import static model.component.motherboard.Socket.LGA1700;
import static org.junit.jupiter.api.Assertions.*;

public class ConfigSavingQueueTest {
    private ConfigSavingQueue csq;
    private Configuration config;


    @BeforeEach
    void setUp() {
        csq = new ConfigSavingQueue();
        Cpu testCpu = new Cpu("i9-13900KS", 150, 949.00, CpuMfr.INTEL, 62014);
        Motherboard testMotherboard = new Motherboard("Asus ROG MAXIMUS Z790 HERO", LGA1700, ATX, 192,
                4, 789.98);
        Gpu testGpu = new Gpu("GeForce RTX 4090", 450, 2298.98, GpuMfr.NVIDIA, 38929);
        PowerSupply testPowerSupply = new PowerSupply("Corsair SF1000L ", FormSize.ITX, 1000, true,
                269.99);
        config = new Configuration(testCpu, testMotherboard, testGpu, testPowerSupply, 500);
    }

    @Test
    void testConstructor() {
        assertEquals(0, csq.getSavingList().size());
    }

    @Test
    void testAdd() {
        csq.add(config, 1);
        assertEquals(1, csq.getSavingList().size());
        assertEquals(config, csq.getConfig(0));
    }

    @Test
    void testContains() {
        assertFalse(csq.contains(config));
        csq.add(config, 1);
        assertTrue(csq.contains(config));
        assertFalse(csq.contains(null));
    }

    @Test
    void testGetConfig() {
        csq.add(config, 1);
        assertEquals(config, csq.getConfig(0));
    }
}
