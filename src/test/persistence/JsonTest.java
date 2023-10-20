package persistence;

import model.Configuration;
import model.component.cpu.Cpu;
import model.component.cpu.CpuMfr;
import model.component.gpu.Gpu;
import model.component.gpu.GpuMfr;
import model.component.motherboard.FormSize;
import model.component.motherboard.Motherboard;
import model.component.psu.PowerSupply;

import java.util.List;

import static model.component.motherboard.FormSize.ATX;
import static model.component.motherboard.Socket.LGA1700;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected Configuration testConfiguration;
    protected Configuration testConfigurationRamD4;
    protected Configuration testConfigurationWithoutGpu;
    protected Motherboard testMotherboardD4;
    protected Cpu testCpu;
    protected Motherboard testMotherboard;
    protected Gpu testGpu;
    protected PowerSupply testPowerSupply;

    protected void setUpBeforeEachTest() {
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
    }



    protected void checkListOfConfigs(List<Configuration> expectConfigs, List<Configuration> actualConfigs) {
        for (int i = 0; i < expectConfigs.size(); i++) {
            Configuration expectConfig = expectConfigs.get(i);
            Configuration actualConfig = actualConfigs.get(i);

            // CPU test
            assertEquals(expectConfig.getCpu().getModel(), actualConfig.getCpu().getModel());
            assertEquals(expectConfig.getCpu().getCpuMfr(), actualConfig.getCpu().getCpuMfr());
            assertEquals(expectConfig.getCpu().getPrice(), actualConfig.getCpu().getPrice());
            assertEquals(expectConfig.getCpu().getBasePower(), actualConfig.getCpu().getBasePower());
            assertEquals(expectConfig.getCpu().getBenchMark(), actualConfig.getCpu().getBenchMark());
            // GPU test
            if (expectConfig.getGpu() != null) {
                assertEquals(expectConfig.getGpu().getModel(), actualConfig.getGpu().getModel());
                assertEquals(expectConfig.getGpu().getPrice(), actualConfig.getGpu().getPrice());
                assertEquals(expectConfig.getGpu().getGpuMfr(), actualConfig.getGpu().getGpuMfr());
                assertEquals(expectConfig.getGpu().getBasePower(), actualConfig.getGpu().getBasePower());
                assertEquals(expectConfig.getGpu().getBenchMark(), actualConfig.getGpu().getBenchMark());
            }
            // Motherboard test
            assertEquals(expectConfig.getMotherboard().getName(), actualConfig.getMotherboard().getName());
            assertEquals(expectConfig.getMotherboard().getFormSize(), actualConfig.getMotherboard().getFormSize());
            assertEquals(expectConfig.getMotherboard().getPrice(), actualConfig.getMotherboard().getPrice());
            assertEquals(expectConfig.getMotherboard().getSocket(), actualConfig.getMotherboard().getSocket());
            assertEquals(expectConfig.getMotherboard().getMaxRam(), actualConfig.getMotherboard().getMaxRam());
            assertEquals(expectConfig.getMotherboard().getRamSlot(), actualConfig.getMotherboard().getRamSlot());
            // Power Supply test
            assertEquals(expectConfig.getPowerSupply().getModel(), actualConfig.getPowerSupply().getModel());
            assertEquals(expectConfig.getPowerSupply().getWattage(), actualConfig.getPowerSupply().getWattage());
            assertEquals(expectConfig.getPowerSupply().getPrice(), actualConfig.getPowerSupply().getPrice());
            assertEquals(expectConfig.getPowerSupply().getFormSize(), actualConfig.getPowerSupply().getFormSize());
            // ram budget test
            assertEquals(expectConfig.getRamBudget(), actualConfig.getRamBudget());
        }
    }
}
