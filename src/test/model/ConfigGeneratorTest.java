package model;

import model.component.motherboard.FormSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigGeneratorTest {
    private ConfigurationGenerator testConfigGenerator;
    private ConfigurationGenerator testConfigGenerator2;

    @BeforeEach
    void SetUpBeforeEachTest() {
        testConfigGenerator = new ConfigurationGenerator(1500, FormSize.ATX, Purpose.GAMING);
        testConfigGenerator2 = new ConfigurationGenerator(1000, FormSize.ITX, Purpose.ENTRY_LEVEL);
    }

    @Test
    void testConstructor() {
        assertEquals(40, testConfigGenerator.getCpuList().getListAllCpu().size());
        assertEquals(80, testConfigGenerator.getMotherboardList().getListAllMotherboard().size());
        assertEquals(24, testConfigGenerator.getGpuList().getListAllGpu().size());
        assertEquals(74, testConfigGenerator.getPowerSuppliesList().getListAllPowerSupply().size());
        assertEquals(FormSize.ATX, testConfigGenerator.getFormSize());
        assertEquals(Purpose.GAMING, testConfigGenerator.getPurpose());
        assertEquals(1500, testConfigGenerator.getTotalBudget());

        assertEquals(40, testConfigGenerator2.getCpuList().getListAllCpu().size());
        assertEquals(80, testConfigGenerator2.getMotherboardList().getListAllMotherboard().size());
        assertEquals(24, testConfigGenerator2.getGpuList().getListAllGpu().size());
        assertEquals(74, testConfigGenerator2.getPowerSuppliesList().getListAllPowerSupply().size());
        assertEquals(FormSize.ITX, testConfigGenerator2.getFormSize());
        assertEquals(Purpose.ENTRY_LEVEL, testConfigGenerator2.getPurpose());
        assertEquals(1000, testConfigGenerator2.getTotalBudget());
    }

    @Test
    void testBudgetDistribution() {
        testConfigGenerator.budgetDistribution();
        assertEquals(300, testConfigGenerator.getCpuBudget());
        assertEquals(150, testConfigGenerator.getRamBudget());
        assertEquals(600, testConfigGenerator.getGpuBudget());
        assertEquals(225, testConfigGenerator.getPsuBudget());
        assertEquals(225, testConfigGenerator.getMotherboardBudget());

        testConfigGenerator2.budgetDistribution();
        assertEquals(300, testConfigGenerator2.getCpuBudget());
        assertEquals(200, testConfigGenerator2.getRamBudget());
        assertEquals(250, testConfigGenerator2.getPsuBudget());
        assertEquals(250, testConfigGenerator2.getMotherboardBudget());
    }

    @Test
    void testConfigGenerate() {
        Configuration actualConfig1 = testConfigGenerator.configGenerate();
        assertEquals("i5-13500", actualConfig1.getCpu().getModel());
        assertEquals(150, actualConfig1.getRamBudget());
        assertEquals("Asus TUF GAMING Z790-PLUS D4", actualConfig1.getMotherboard().getName());
        assertEquals("Radeon RX 7800 XT", actualConfig1.getGpu().getModel());
        assertEquals("Cooler Master XG650 Plus", actualConfig1.getPowerSupply().getModel());

        Configuration actualConfig2 = testConfigGenerator2.configGenerate();
        assertEquals("i5-13500", actualConfig2.getCpu().getModel());
        assertEquals(200.0, actualConfig2.getRamBudget());
        assertEquals("Asus ROG STRIX B760-I GAMING", actualConfig2.getMotherboard().getName());
        assertEquals("Corsair SF1000L", actualConfig2.getPowerSupply().getModel());
    }
}
