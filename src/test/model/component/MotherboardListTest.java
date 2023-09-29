package model.component;

import model.component.motherboard.FormSize;
import model.component.motherboard.Motherboard;
import model.component.motherboard.MotherboardList;
import model.component.motherboard.Socket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        assertEquals(82, testMotherboards.size());
        assertEquals("Asus ROG MAXIMUS Z690 EXTREME",
                testMotherboards.get(0).getName());
        assertEquals("Asus Pro B660M-C D4-CSM",
                testMotherboards.get(81).getName());
    }

    @Test
    void testFilterRightSocketMotherboard() {
        List<Motherboard> filteredList = testMotherboardList.filterRightSocketMotherboard(
                testMotherboards, Socket.LGA1700);
        assertEquals(65, filteredList.size());
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
                testMotherboards, FormSize.ATX);
        for (Motherboard mb : filteredList) {
            assertEquals(FormSize.ATX, mb.getFormSize());
        }
        filteredList = testMotherboardList.filterRightFormSizeMotherboards(
                testMotherboards, FormSize.MATX);
        for (Motherboard mb : filteredList) {
            assertEquals(FormSize.MATX, mb.getFormSize());
        }
        filteredList = testMotherboardList.filterRightFormSizeMotherboards(
                testMotherboards, FormSize.ITX);
        for (Motherboard mb : filteredList) {
            assertEquals(FormSize.ITX, mb.getFormSize());
        }
    }

    @Test
    void testSortMotherboardsByDecreasePrice() {
        List<Motherboard> mixedListOfMotherboard = new ArrayList<>();
        testMotherboardList.addAllLGA1700Motherboard1(mixedListOfMotherboard);
        testMotherboardList.addAllLGA1700Motherboard2(mixedListOfMotherboard);
        testMotherboardList.addAllLGA1700Motherboard3(mixedListOfMotherboard);
        testMotherboardList.addAllLGA1700Motherboard4(mixedListOfMotherboard);
        testMotherboardList.addAllLGA1700Motherboard5(mixedListOfMotherboard);
        testMotherboardList.addAllLGA1700Motherboard6(mixedListOfMotherboard);
        testMotherboardList.sortMotherboardsByDecreasePrice(mixedListOfMotherboard);
        double lastMotherboardPrice = 99999;
        for (Motherboard mb : mixedListOfMotherboard) {
            assertTrue(lastMotherboardPrice >= mb.getPrice());
            lastMotherboardPrice = mb.getPrice();
        }
    }
}
