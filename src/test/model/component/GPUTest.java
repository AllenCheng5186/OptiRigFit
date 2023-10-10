package model.component;

import model.component.gpu.Gpu;
import model.component.gpu.GpuMfr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GPUTest {
    private Gpu testGpu1;
    private Gpu testGpu2;

    @BeforeEach
    void setUpBeforeEachTest() {
        testGpu1 = new Gpu("GeForce RTX 4080", 320, 1599.99, GpuMfr.NVIDIA, 34820);
        testGpu2 = new Gpu("Radeon RX 7900 XT", 315, 1145.61, GpuMfr.AMD, 29040);
    }

    @Test
    void testConstructor() {
        assertEquals("GeForce RTX 4080", testGpu1.getModel());
        assertEquals(320, testGpu1.getBasePower());
        assertEquals(1599.99, testGpu1.getPrice());
        assertEquals(GpuMfr.NVIDIA, testGpu1.getGpuMfr());
        assertEquals(34820, testGpu1.getBenchMark());

        assertEquals("Radeon RX 7900 XT", testGpu2.getModel());
        assertEquals(315, testGpu2.getBasePower());
        assertEquals(1145.61, testGpu2.getPrice());
        assertEquals(GpuMfr.AMD, testGpu2.getGpuMfr());
        assertEquals(29040, testGpu2.getBenchMark());
    }

    @Test
    void testCompareTo() {
        assertEquals(-5780, testGpu1.compareTo(testGpu2));
        assertEquals(5780, testGpu2.compareTo(testGpu1));
    }
}
