package model.component;

import model.component.motherboard.FormSize;
import model.component.motherboard.Motherboard;
import model.component.motherboard.MotherboardList;
import model.component.motherboard.Socket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MotherboardListTest {
    private MotherboardList testMotherboardList;
    private List<Motherboard> testMotherboards;

    @BeforeEach
    void setUpBeforeEachTest() {
        testMotherboardList = new MotherboardList();
        testMotherboards = testMotherboardList.getListAllMotherboard();
    }

    @Test
    void testConstructor() {
        assertEquals(80, testMotherboards.size());
        assertEquals("Asus ROG MAXIMUS Z690 EXTREME",
                testMotherboards.get(0).getName());
        assertEquals("Asus Pro B660M-C D4-CSM",
                testMotherboards.get(79).getName());
    }

    @Test
    void testFilterRightSocketMotherboard() {
        List<Motherboard> filteredList = testMotherboardList.filterRightSocketMotherboard(
                testMotherboards, Socket.LGA1700);
        assertEquals(63, filteredList.size());
        for (Motherboard mb : filteredList) {
            assertSame(Socket.LGA1700, mb.getSocket());
        }
        filteredList = testMotherboardList.filterRightSocketMotherboard(
                testMotherboards, Socket.AM5);
        assertEquals(82 - 65, filteredList.size());
        for (Motherboard mb : filteredList) {
            assertSame(Socket.AM5, mb.getSocket());
        }
    }

    @Test
    void testFilterRightFormSizeMotherboards() {
        List<Motherboard> filteredList = testMotherboardList.filterRightFormSizeMotherboards(
                testMotherboards, FormSize.ITX);
        for (Motherboard mb : filteredList) {
            assertEquals(FormSize.ITX, mb.getFormSize());
        }
        filteredList = testMotherboardList.filterRightFormSizeMotherboards(
                testMotherboards, FormSize.MATX);
        for (Motherboard mb : filteredList) {
            assertTrue(FormSize.MATX == mb.getFormSize() || FormSize.ITX == mb.getFormSize());
        }
        filteredList = testMotherboardList.filterRightFormSizeMotherboards(
                testMotherboards, FormSize.ATX);
        for (Motherboard mb : filteredList) {
            assertNotSame(FormSize.EATX, mb.getFormSize());
        }
        filteredList = testMotherboardList.filterRightFormSizeMotherboards(
                testMotherboards, FormSize.EATX);
        for (Motherboard mb : filteredList) {
            assertTrue(FormSize.EATX == mb.getFormSize() || FormSize.ATX == mb.getFormSize() ||
                    FormSize.MATX == mb.getFormSize() || FormSize.ITX == mb.getFormSize());
        }
    }

    @Test
    void testSortMotherboardsByDecreasePrice() {
        List<Motherboard> actual = testMotherboardList.sortMotherboardsByDecreasePrice(testMotherboards);
        assertEquals(80, actual.size());
        double lastMotherboardPrice = 99999;
        for (Motherboard mb : actual) {
            assertTrue(lastMotherboardPrice >= mb.getPrice());
            lastMotherboardPrice = mb.getPrice();
        }
    }

    @Test
    void testFilterMotherboardsInPriceInterval() {
        List<Motherboard> actual = testMotherboardList.filterMotherboardsInPriceInterval(testMotherboards,
                300.00, 500.00);
        for (Motherboard mb : actual) {
            assertTrue(mb.getPrice() >= 300.00 && mb.getPrice() <= 500.00);
        }
    }
}
