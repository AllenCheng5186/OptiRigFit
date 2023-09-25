package model.component;

import model.component.Cpu.Cpu;
import model.component.Cpu.CpuManufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CPUtest {
    private Cpu testCpuWithIG;
    private Cpu testCpuWithoutIG;

    @BeforeEach
    void setUpBeforeEachTest() {
        testCpuWithIG = new Cpu("i9-13900K", 125, 758.00, CpuManufacturer.INTEL);
        testCpuWithoutIG = new Cpu("i9-13900KF", 125, 718.00, CpuManufacturer.INTEL);
    }

    @Test
    void testConstructor() {
        //test CPU i9-13900K
        assertEquals("i9-13900K", testCpuWithIG.getName());
        assertEquals(125, testCpuWithIG.getBasePower());
        assertEquals(758.00, testCpuWithIG.getPrice());
        assertEquals(CpuManufacturer.INTEL, testCpuWithIG.getManufacturer());
        assertTrue(testCpuWithIG.hasIntegratedGraphics());
        //test CPU i9-13900KF
        assertEquals("i9-13900KF", testCpuWithoutIG.getName());
        assertEquals(125, testCpuWithoutIG.getBasePower());
        assertEquals(718.00, testCpuWithoutIG.getPrice());
        assertEquals(CpuManufacturer.INTEL, testCpuWithoutIG.getManufacturer());
        assertFalse(testCpuWithoutIG.hasIntegratedGraphics());
    }

}
