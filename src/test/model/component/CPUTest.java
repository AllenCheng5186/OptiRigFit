package model.component;

import model.component.cpu.Cpu;
import model.component.cpu.CpuMfr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CPUTest {
    private Cpu testCpuWithIG;
    private Cpu testCpuWithoutIG;

    @BeforeEach
    void setUpBeforeEachTest() {
        testCpuWithIG = new Cpu("i9-13900K", 150, 949.00, CpuMfr.INTEL, 62014);
        testCpuWithoutIG = new Cpu("i9-13900KF", 125, 718.00, CpuMfr.INTEL, 59017);
    }

    @Test
    void testConstructor() {
        //test CPU i9-13900K
        assertEquals("i9-13900K", testCpuWithIG.getModel());
        assertEquals(150, testCpuWithIG.getBasePower());
        assertEquals(949.00, testCpuWithIG.getPrice());
        assertEquals(CpuMfr.INTEL, testCpuWithIG.getCpuMfr());
        assertTrue(testCpuWithIG.hasIntegratedGraphic());
        assertEquals(62014, testCpuWithIG.getBenchMark());
        //test CPU i9-13900KF
        assertEquals("i9-13900KF", testCpuWithoutIG.getModel());
        assertEquals(125, testCpuWithoutIG.getBasePower());
        assertEquals(718.00, testCpuWithoutIG.getPrice());
        assertEquals(CpuMfr.INTEL, testCpuWithoutIG.getCpuMfr());
        assertFalse(testCpuWithoutIG.hasIntegratedGraphic());
        assertEquals(59017, testCpuWithoutIG.getBenchMark());
    }

    @Test
    void testCompareTo(){
        assertEquals(-2997, testCpuWithIG.compareTo(testCpuWithoutIG));
        assertEquals(2997, testCpuWithoutIG.compareTo(testCpuWithIG));
    }

}
