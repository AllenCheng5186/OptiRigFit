package model.component;

import model.component.motherboard.FormSize;
import model.component.psu.PowerSuppliesList;
import model.component.psu.PowerSupply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PowerSuppliesListTest {
    private PowerSuppliesList testPowerSuppliesList;

    @BeforeEach
    void setUpBeforeEachTest() {
        testPowerSuppliesList = new PowerSuppliesList();
    }

    @Test
    void testConstructor() {
        List<PowerSupply> testPSUList = testPowerSuppliesList.getListAllPowerSupply();
        assertEquals(74, testPSUList.size());
        PowerSupply previousPSU = testPSUList.get(0);
        for (int i = 1; i < testPSUList.size(); i++) {
            PowerSupply nextPSU = testPSUList.get(i);
            assertTrue(nextPSU.getWattage() >= previousPSU.getWattage());
            previousPSU = nextPSU;
        }
    }

    @Test
    void testSortByWatt() {
        List<PowerSupply> testPSUList = testPowerSuppliesList.getListAllPowerSupply();
        List<PowerSupply> actual = testPowerSuppliesList.sortByWatt(testPSUList);
        PowerSupply previousPSU = actual.get(0);
        for (int i = 1; i < actual.size(); i++) {
            PowerSupply nextPSU = actual.get(i);
            assertTrue(nextPSU.getWattage() >= previousPSU.getWattage());
            previousPSU = nextPSU;
        }
    }

    @Test
    void testFilterFormSizePSUs() {
        List<PowerSupply> testPSUList = testPowerSuppliesList.getListAllPowerSupply();
        List<PowerSupply> actual = testPowerSuppliesList.filterFormSizePSUs(FormSize.ITX, testPSUList);
        for (PowerSupply psu : actual) {
            assertEquals(FormSize.ITX, psu.getFormSize());
        }
        actual = testPowerSuppliesList.filterFormSizePSUs(FormSize.ATX, testPSUList);
        for (PowerSupply psu : actual) {
            assertEquals(FormSize.ATX, psu.getFormSize());
        }
    }

    @Test
    void testFilterGreaterWattPSUs() {
        List<PowerSupply> testPSUList = testPowerSuppliesList.getListAllPowerSupply();
        List<PowerSupply> actual = testPowerSuppliesList.filterGreaterWattPSUs(550, testPSUList);
        for (PowerSupply psu : actual) {
            assertTrue(psu.getWattage() >= 550);
        }
    }

    @Test
    void testFilterPowerSupplyInPriceInterval() {
        List<PowerSupply> testPSUList = testPowerSuppliesList.getListAllPowerSupply();
        List<PowerSupply> actual = testPowerSuppliesList.filterPowerSupplyInPriceInterval(testPSUList,
                300, 200);
        for (PowerSupply psu : actual) {
            assertTrue(psu.getPrice() >= 200 && psu.getPrice() <= 300);
        }
    }

}
