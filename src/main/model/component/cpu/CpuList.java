package model.component.cpu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//represent a list of cpus, which have all method that works on a list of cpus it contains
public class CpuList {

    private List<Cpu> listAllCpu;

    //EFFECTS: construct a CpuList object which store the list of CPUs on sale,
    // which sorted along CPU's benchmark
    public CpuList() {
        this.listAllCpu = new ArrayList<>();
        addListIntel13Cpu(listAllCpu);
        addListIntel12Cpu(listAllCpu);
        addListAmdCpu(listAllCpu);
        Collections.sort(listAllCpu);
    }

    //REQUIRE: upperPrice > downPrice
    //EFFECTS: return a list of CPUs those prices in the given interval
    public List<Cpu> filterCPUsPriceInterval(List<Cpu> cpuList, double upperPrice, double downPrice) {
        List<Cpu> priceWithinIntervalCpus = new ArrayList<>();
        for (Cpu cpu : cpuList) {
            if (upperPrice >= cpu.getPrice() && cpu.getPrice() >= downPrice) {
                priceWithinIntervalCpus.add(cpu);
            }
        }

        return priceWithinIntervalCpus;
    }

    //EFFECTS: return a list of CPUs those has integrated graphics filtered from
    // given list
    public List<Cpu> returnCpusHasIG(List<Cpu> cpuList) {
        List<Cpu> cpusHasIG = new ArrayList<>();
        for (Cpu cpu : cpuList) {
            if (cpu.hasIntegratedGraphic()) {
                cpusHasIG.add(cpu);
            }
        }
        return cpusHasIG;
    }


    // getter
    public List<Cpu> getListAllCpu() {
        return listAllCpu;
    }

    //MODIFIES: List<Cpu>
    //EFFECTS: add the list of 13th Intel Core CPUs to the given list
    public void addListIntel13Cpu(List<Cpu> listAllCpu) {
        // 13th Intel Core CPU
        Collections.addAll(listAllCpu, new Cpu("i9-13900KS", 150, 949.00,
                CpuMfr.INTEL, 62014),
                new Cpu("i9-13900K", 125, 758.00, CpuMfr.INTEL, 59760),
                new Cpu("i9-13900KF", 125, 718.00, CpuMfr.INTEL, 59017),
                new Cpu("i9-13900F", 65, 669.00, CpuMfr.INTEL, 52746),
                new Cpu("i7-13700K", 125, 548.98, CpuMfr.INTEL, 46839),
                new Cpu("i7-13700KF", 125, 507.99, CpuMfr.INTEL, 46598),
                new Cpu("i7-13700F", 65, 478.98, CpuMfr.INTEL, 39949),
                new Cpu("i7-13700", 65, 509.99, CpuMfr.INTEL, 38678),
                new Cpu("i5-13600K", 125, 408.98, CpuMfr.INTEL, 38350),
                new Cpu("i5-13600KF", 125, 378.98, CpuMfr.INTEL, 38226),
                new Cpu("i5-13500", 65, 329.00, CpuMfr.INTEL, 32489),
                new Cpu("i5-13400F", 65, 263.98, CpuMfr.INTEL, 25589),
                new Cpu("i5-13400", 65, 298.98, CpuMfr.INTEL, 25354),
                new Cpu("i3-13100", 58, 178.98, CpuMfr.INTEL, 14917),
                new Cpu("i3-13100F", 58, 144.98, CpuMfr.INTEL, 14729));
    }

    //MODIFIES: List<Cpu>
    //EFFECTS: add the list of 12th Intel Core CPUs to the given list
    public void addListIntel12Cpu(List<Cpu> listAllCpu) {
        // 12th Intel Core CPU
        Collections.addAll(listAllCpu, new Cpu("i9-12900KS", 150, 587.99,
                CpuMfr.INTEL, 44307),
                new Cpu("i9-12900K", 125, 568.98, CpuMfr.INTEL, 41470),
                new Cpu("i9-12900KF", 125, 516.45, CpuMfr.INTEL, 41244),
                new Cpu("i9-12900F", 65, 661.61, CpuMfr.INTEL, 37072),
                new Cpu("i7-12700K", 125, 409.98, CpuMfr.INTEL, 34747),
                new Cpu("i7-12700KF", 125, 389.00, CpuMfr.INTEL, 34516),
                new Cpu("i7-12700F", 65, 478.98, CpuMfr.INTEL, 30994),
                new Cpu("i7-12700", 65, 408.98, CpuMfr.INTEL, 30959),
                new Cpu("i5-12600K", 125, 319.00, CpuMfr.INTEL, 27792),
                new Cpu("i5-12600KF", 125, 298.01, CpuMfr.INTEL, 27463),
                new Cpu("i5-12500", 65, 317.00, CpuMfr.INTEL, 20003),
                new Cpu("i5-12400F", 65, 199.00, CpuMfr.INTEL, 19637),
                new Cpu("i5-12400", 65, 238.98, CpuMfr.INTEL, 19484),
                new Cpu("i3-12100", 60, 154.69, CpuMfr.INTEL, 14252),
                new Cpu("i3-12100F", 60, 119.00, CpuMfr.INTEL, 13821));
    }

    //MODIFIES: List<Cpu>
    //EFFECTS: add the list of AMD 7000 series Ryzen CPUs to the given list
    public void addListAmdCpu(List<Cpu> listAllCpu) {
        // AMD 7000 Series
        Collections.addAll(listAllCpu,new Cpu("R9-7950X", 170, 749.99,
                CpuMfr.AMD, 63380),
                new Cpu("R9-7950X3D", 120, 889.00, CpuMfr.AMD, 62671),
                new Cpu("R9-7900X", 170, 549.99, CpuMfr.AMD, 52217),
                new Cpu("R9-7900X3D", 120, 658.58, CpuMfr.AMD, 50734),
                new Cpu("R9-7900", 65, 499.00, CpuMfr.AMD, 49350),
                new Cpu("R7-7800X3D", 120, 590.00, CpuMfr.AMD, 34718),
                new Cpu("R7-7700X", 105, 439.99, CpuMfr.AMD, 36353),
                new Cpu("R7-7700", 65, 439.00, CpuMfr.AMD, 35121),
                new Cpu("R5-7600X", 105, 328.98, CpuMfr.AMD, 28831),
                new Cpu("R5-7600", 65, 339.03, CpuMfr.AMD, 27644));
    }
}
