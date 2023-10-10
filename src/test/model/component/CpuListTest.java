package model.component;

import model.component.cpu.Cpu;
import model.component.cpu.CpuList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CpuListTest {
    private CpuList testCpuList;

    @BeforeEach
    void SetUpBeforeEachTest() {
        testCpuList = new CpuList();
    }

    @Test
    void testConstructor() {
        List<String> cpuRankList = new ArrayList<>();
        Collections.addAll(cpuRankList, "R9-7950X", "R9-7950X3D", "i9-13900KS", "i9-13900K", "i9-13900KF",
                "i9-13900F", "R9-7900X", "R9-7900X3D", "R9-7900", "i7-13700K", "i7-13700KF", "i9-12900KS", "i9-12900K",
                "i9-12900KF", "i7-13700F", "i7-13700", "i5-13600K", "i5-13600KF", "i9-12900F", "R7-7700X", "R7-7700",
                "i7-12700K", "R7-7800X3D", "i7-12700KF", "i5-13500", "i7-12700F", "i7-12700", "R5-7600X", "i5-12600K",
                "R5-7600", "i5-12600KF", "i5-13400F", "i5-13400", "i5-12500", "i5-12400F", "i5-12400",
                "i3-13100", "i3-13100F", "i3-12100", "i3-12100F");
        for (int i = 0; i < cpuRankList.size(); i++) {
            assertEquals(cpuRankList.get(i), testCpuList.getListAllCpu().get(i).getModel());
        }
    }

    @Test
    void testFilterCPUsPriceWithinInterval() {
        List<Cpu> filteredCPUs;
        filteredCPUs = testCpuList.filterCPUsPriceInterval(testCpuList.getListAllCpu(), 150, 100);
        assertEquals(144.98, filteredCPUs.get(0).getPrice());
        assertEquals(119.00, filteredCPUs.get(1).getPrice());
        assertEquals(2, filteredCPUs.size());
    }

    @Test
    void testReturnCpusHasIG() {
        List<Cpu> testCpus = testCpuList.getListAllCpu();
        List<Cpu> actualCpus = testCpuList.returnCpusHasIG(testCpus);
        for (Cpu cpu : actualCpus) {
            assertTrue(cpu.hasIntegratedGraphics());
        }
    }

    
}
