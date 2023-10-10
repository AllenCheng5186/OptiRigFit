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

public class ConfigurationTest {
    private Configuration testConfiguration;
    private Cpu testCpu;
    private Motherboard testMotherboard;
    private Gpu testGpu;
    private PowerSupply testPowerSupply;

    @BeforeEach
    void setUpBeforeEachTest() {
        testCpu = new Cpu("i9-13900KS", 150, 949.00, CpuMfr.INTEL, 62014);
        testMotherboard = new Motherboard("Asus ROG MAXIMUS Z790 HERO", LGA1700, ATX, 192,
                4, 789.98);
        testGpu = new Gpu("GeForce RTX 4090", 450, 2298.98, GpuMfr.NVIDIA, 38929);
        testPowerSupply = new PowerSupply("Corsair SF1000L ", FormSize.ITX, 1000, true,
                269.99);
        testConfiguration = new Configuration(testCpu, testMotherboard, testGpu, testPowerSupply, 500);
    }

    @Test
    void testConstructor() {
        assertEquals(testCpu, testConfiguration.getCpu());
        assertEquals(testMotherboard, testConfiguration.getMotherboard());
        assertEquals(testGpu, testConfiguration.getGpu());
        assertEquals(testPowerSupply, testConfiguration.getPowerSupply());
        assertEquals(500, testConfiguration.getRamBudget());
    }
}
