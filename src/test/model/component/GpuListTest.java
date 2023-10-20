package model.component;

import model.component.gpu.Gpu;
import model.component.gpu.GpuList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GpuListTest {
    private GpuList testGpuList;
    private List<Gpu> testGpus;

    @BeforeEach
    void SetUpBeforeEachTest() {
        testGpuList = new GpuList();
        testGpus = testGpuList.getListAllGpu();
    }

    @Test
    void testConstructor() {
        assertEquals(24, testGpuList.getListAllGpu().size());
        Gpu prevGpu = testGpus.get(0);
        for (int i = 1; i < testGpus.size(); i++) {
            Gpu nextGpu = testGpus.get(i);
            assertTrue(nextGpu.getBenchMark() <= prevGpu.getBenchMark());
        }

    }

    @Test
    void testFilterGpuInPriceInterval() {
        List<Gpu> actual = testGpuList.filterGpusInPriceInterval(testGpus, 800, 700);
        for (Gpu gpu : actual) {
            assertTrue(gpu.getPrice() >= 700 && gpu.getPrice() <= 800);
        }
    }
}
