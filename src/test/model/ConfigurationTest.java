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
    private Configuration testConfigurationRamD4;
    private Configuration testConfigurationWithoutGpu;
    private Motherboard testMotherboardD4;
    private Cpu testCpu;
    private Motherboard testMotherboard;
    private Gpu testGpu;
    private PowerSupply testPowerSupply;
    private Configuration testBlankConfig;

    @BeforeEach
    void setUpBeforeEachTest() {
        testCpu = new Cpu("i9-13900KS", 150, 949.00, CpuMfr.INTEL, 62014);
        testMotherboard = new Motherboard("Asus ROG MAXIMUS Z790 HERO", LGA1700, ATX, 192,
                4, 789.98);
        testGpu = new Gpu("GeForce RTX 4090", 450, 2298.98, GpuMfr.NVIDIA, 38929);
        testPowerSupply = new PowerSupply("Corsair SF1000L ", FormSize.ITX, 1000, true,
                269.99);
        testConfiguration = new Configuration(testCpu, testMotherboard, testGpu, testPowerSupply, 500);
        testMotherboardD4 = new Motherboard("Asus TUF GAMING Z690-PLUS D4",
                LGA1700, ATX, 128, 4, 373.53);
        testConfigurationRamD4 = new Configuration(testCpu,testMotherboardD4, testGpu, testPowerSupply, 600);
        testConfigurationWithoutGpu = new Configuration(testCpu, testMotherboardD4, null,
                testPowerSupply, 300);
        testBlankConfig = new Configuration(null, null, null, null, 0);
    }

    @Test
    void testConstructor() {
        assertEquals(testCpu, testConfiguration.getCpu());
        assertEquals(testMotherboard, testConfiguration.getMotherboard());
        assertEquals(testGpu, testConfiguration.getGpu());
        assertEquals(testPowerSupply, testConfiguration.getPowerSupply());
        assertEquals(500, testConfiguration.getRamBudget());
    }

    @Test
    void testEquals() {
        assertTrue(testConfiguration.equals(testConfiguration));
        assertTrue(testConfiguration.equals(new Configuration(testCpu, testMotherboard, testGpu, testPowerSupply, 500)));
        assertFalse(testConfiguration.equals(testBlankConfig));
        assertFalse(testConfiguration.equals(testCpu));
        assertFalse(testConfiguration.equals(new Configuration(null, testMotherboard, testGpu, testPowerSupply, 500)));
        assertFalse(testConfiguration.equals(new Configuration(testCpu, null, testGpu, testPowerSupply, 500)));
        assertFalse(testConfiguration.equals(new Configuration(testCpu, testMotherboard, null, testPowerSupply, 500)));
        assertFalse(testConfiguration.equals(new Configuration(testCpu, testMotherboard, testGpu, null, 500)));
        assertFalse(testConfiguration.equals(new Configuration(testCpu, testMotherboard, testGpu, testPowerSupply, 200)));
    }

    @Test
    void testSetter() {
        testBlankConfig.setCpu(testCpu);
        testBlankConfig.setGpu(testGpu);
        testBlankConfig.setMotherboard(testMotherboard);
        testBlankConfig.setPowerSupply(testPowerSupply);
        testBlankConfig.setRamBudget(200);
        assertEquals(testCpu, testBlankConfig.getCpu());
        assertEquals(testMotherboard, testBlankConfig.getMotherboard());
        assertEquals(testGpu, testBlankConfig.getGpu());
        assertEquals(testPowerSupply, testBlankConfig.getPowerSupply());
        assertEquals(200, testBlankConfig.getRamBudget());
    }
}
