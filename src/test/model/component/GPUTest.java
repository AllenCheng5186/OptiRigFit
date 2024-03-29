package model.component;

import model.component.cpu.Cpu;
import model.component.cpu.CpuMfr;
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

    @Test
    void testEquals() {
        assertTrue(testGpu1.equals(testGpu1));
        assertFalse(testGpu1.equals(testGpu2));
        assertFalse(testGpu1.equals(new Cpu("i9-13900K", 150, 949.00, CpuMfr.INTEL, 62014)));
        assertTrue(testGpu1.equals(new Gpu("GeForce RTX 4080", 320, 1599.99, GpuMfr.NVIDIA, 34820)));
        assertFalse(testGpu1.equals(new Gpu("GeForce RTX 40801", 320, 1599.99, GpuMfr.NVIDIA, 34820)));
        assertFalse(testGpu1.equals(new Gpu("GeForce RTX 4080", 321, 1599.99, GpuMfr.NVIDIA, 34820)));
        assertFalse(testGpu1.equals(new Gpu("GeForce RTX 4080", 320, 1598.99, GpuMfr.NVIDIA, 34820)));
        assertFalse(testGpu1.equals(new Gpu("GeForce RTX 4080", 320, 1599.99, GpuMfr.AMD, 34820)));
        assertFalse(testGpu1.equals(new Gpu("GeForce RTX 4080", 320, 1599.99, GpuMfr.NVIDIA, 34821)));
    }

    @Test
    void testHashCode() {
        assertEquals(testGpu1.hashCode(), testGpu1.hashCode());
        assertNotEquals(testGpu2.hashCode(), testGpu1.hashCode());
    }
}
