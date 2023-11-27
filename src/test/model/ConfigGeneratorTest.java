package model;

import model.component.motherboard.FormSize;
import model.component.psu.PowerSupply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigGeneratorTest {
    private ConfigurationGenerator testConfigGeneratorGamingATX;
    private ConfigurationGenerator testConfigGeneratorEntryLevelITX;
    private ConfigurationGenerator testConfigGeneratorHighBudget;

    @BeforeEach
    void SetUpBeforeEachTest() {
        testConfigGeneratorGamingATX = new ConfigurationGenerator(1500, FormSize.ATX, Purpose.GAMING);
        testConfigGeneratorEntryLevelITX = new ConfigurationGenerator(1000, FormSize.ITX, Purpose.ENTRY_LEVEL);
        testConfigGeneratorHighBudget = new ConfigurationGenerator(50000, FormSize.EATX, Purpose.WORK_STATION);
    }

    @Test
    void testConstructor() {
        assertEquals(40, testConfigGeneratorGamingATX.getCpuList().getListAllCpu().size());
        assertEquals(80, testConfigGeneratorGamingATX.getMotherboardList().getListAllMotherboard().size());
        assertEquals(24, testConfigGeneratorGamingATX.getGpuList().getListAllGpu().size());
        assertEquals(74, testConfigGeneratorGamingATX.getPowerSuppliesList().getListAllPowerSupply().size());
        assertEquals(FormSize.ATX, testConfigGeneratorGamingATX.getFormSize());
        assertEquals(Purpose.GAMING, testConfigGeneratorGamingATX.getPurpose());
        assertEquals(1500, testConfigGeneratorGamingATX.getTotalBudget());

        assertEquals(40, testConfigGeneratorEntryLevelITX.getCpuList().getListAllCpu().size());
        assertEquals(80, testConfigGeneratorEntryLevelITX.getMotherboardList().getListAllMotherboard().size());
        assertEquals(24, testConfigGeneratorEntryLevelITX.getGpuList().getListAllGpu().size());
        assertEquals(74, testConfigGeneratorEntryLevelITX.getPowerSuppliesList().getListAllPowerSupply().size());
        assertEquals(FormSize.ITX, testConfigGeneratorEntryLevelITX.getFormSize());
        assertEquals(Purpose.ENTRY_LEVEL, testConfigGeneratorEntryLevelITX.getPurpose());
        assertEquals(1000, testConfigGeneratorEntryLevelITX.getTotalBudget());
    }

    @Test
    void testBudgetDistribution() {
        testConfigGeneratorGamingATX.budgetDistribution();
        assertEquals(300, testConfigGeneratorGamingATX.getCpuBudget());
        assertEquals(150, testConfigGeneratorGamingATX.getRamBudget());
        assertEquals(600, testConfigGeneratorGamingATX.getGpuBudget());
        assertEquals(225, testConfigGeneratorGamingATX.getPsuBudget());
        assertEquals(225, testConfigGeneratorGamingATX.getMotherboardBudget());

        testConfigGeneratorEntryLevelITX.budgetDistribution();
        assertEquals(300, testConfigGeneratorEntryLevelITX.getCpuBudget());
        assertEquals(200, testConfigGeneratorEntryLevelITX.getRamBudget());
        assertEquals(250, testConfigGeneratorEntryLevelITX.getPsuBudget());
        assertEquals(250, testConfigGeneratorEntryLevelITX.getMotherboardBudget());

        testConfigGeneratorHighBudget.budgetDistribution();
        assertEquals(10000.0, testConfigGeneratorHighBudget.getCpuBudget());
        assertEquals(5000.0, testConfigGeneratorHighBudget.getRamBudget());
        assertEquals(7500.0, testConfigGeneratorHighBudget.getPsuBudget());
        assertEquals(7500.0, testConfigGeneratorHighBudget.getMotherboardBudget());
    }

    @Test
    void testConfigGenerate() {
        Configuration config1500GamingATX = testConfigGeneratorGamingATX.configGenerate();
        assertEquals("i5-13500", config1500GamingATX.getCpu().getModel());
        assertEquals(150, config1500GamingATX.getRamBudget());
        assertEquals("Asus TUF GAMING Z790-PLUS D4", config1500GamingATX.getMotherboard().getModel());
        assertEquals("Radeon RX 7800 XT", config1500GamingATX.getGpu().getModel());
        assertEquals("Cooler Master XG650 Plus", config1500GamingATX.getPowerSupply().getModel());

        Configuration config1000EntryLevelITX = testConfigGeneratorEntryLevelITX.configGenerate();
        assertEquals("i5-13500", config1000EntryLevelITX.getCpu().getModel());
        assertEquals(200.0, config1000EntryLevelITX.getRamBudget());
        assertEquals("Asus ROG STRIX B760-I GAMING", config1000EntryLevelITX.getMotherboard().getModel());
        assertEquals("Corsair SF1000L", config1000EntryLevelITX.getPowerSupply().getModel());

        Configuration configHighWSEATX = testConfigGeneratorHighBudget.configGenerate();
        assertEquals("R9-7950X", configHighWSEATX.getCpu().getModel());
        assertEquals(5000.0, configHighWSEATX.getRamBudget());
        assertEquals("Asus ROG MAXIMUS Z690 EXTREME", configHighWSEATX.getMotherboard().getModel());
        assertEquals("GeForce RTX 4090", configHighWSEATX.getGpu().getModel());
        assertEquals("EVGA Supernova 1300 G+", configHighWSEATX.getPowerSupply().getModel());
    }

    @Test
    void testConfigGenerateTryCatch() {
        ConfigurationGenerator failConfig = new ConfigurationGenerator(0, FormSize.ATX, Purpose.GAMING);
        try {
            Configuration fail = failConfig.configGenerate();
            fail("Exception was not thrown!");
        } catch (IndexOutOfBoundsException e) {
            // expected cpu
        }
        failConfig = new ConfigurationGenerator(500, FormSize.ITX, Purpose.GAMING);
        try {
            Configuration fail = failConfig.configGenerate();
            fail("Exception was not thrown!");
        } catch (IndexOutOfBoundsException e) {
            // expected motherboard
        }
        failConfig = new ConfigurationGenerator(750, FormSize.ATX, Purpose.GAMING);
        try {
            Configuration fail = failConfig.configGenerate();
            fail("Exception was not thrown!");
        } catch (IndexOutOfBoundsException e) {
            // expected gpu
        }
        failConfig = new ConfigurationGenerator(450, FormSize.ATX, Purpose.ENTRY_LEVEL);
        failConfig.setPsuBudget(0);
        assertEquals(0, failConfig.getPsuBudget());
        PowerSupply failPsu = null;
        try {
            failPsu = failConfig.getConfigPowerSupply(100);
            fail("Exception was not thrown!");
        } catch (IndexOutOfBoundsException e) {
            // expected power supply
        }
    }
}
