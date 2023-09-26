package model.component.cpu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CpuList {
    private List<Cpu> listAllCpu;

    public CpuList() {
        this.listAllCpu = new ArrayList<>();
        addListIntel13Cpu(listAllCpu);
        addListIntel12Cpu(listAllCpu);
        addListAmdCpu(listAllCpu);
    }

    public void addListIntel13Cpu(List<Cpu> listAllCpu) {
        // 13th Intel Core CPU
        Cpu cpu13900ks = new Cpu("i9-13900KS", 150, 949.00, CpuMfr.INTEL, 62014);
        Cpu cpu13900k = new Cpu("i9-13900K", 125, 758.00, CpuMfr.INTEL, 59760);
        Cpu cpu13900kf = new Cpu("i9-13900KF", 125, 718.00, CpuMfr.INTEL, 59017);
        Cpu cpu13900f = new Cpu("i9-13900F", 65, 669.00, CpuMfr.INTEL, 52746);
        Cpu cpu13700k = new Cpu("i7-13700K", 125, 548.98, CpuMfr.INTEL, 46839);
        Cpu cpu13700kf = new Cpu("i7-13700KF", 125, 507.99, CpuMfr.INTEL, 46598);
        Cpu cpu13700f = new Cpu("i7-13700F", 65, 478.98, CpuMfr.INTEL, 39949);
        Cpu cpu13700 = new Cpu("i7-13700", 65, 509.99, CpuMfr.INTEL, 38678);
        Cpu cpu13600k = new Cpu("i5-13600K", 125, 408.98, CpuMfr.INTEL, 38350);
        Cpu cpu13600kf = new Cpu("i5-13600KF", 125, 378.98, CpuMfr.INTEL, 38226);
        Cpu cpu13500 = new Cpu("i5-13500", 65, 329.00, CpuMfr.INTEL, 32489);
        Cpu cpu13400f = new Cpu("i5-13400F", 65, 263.98, CpuMfr.INTEL, 25589);
        Cpu cpu13400 = new Cpu("i5-13400", 65, 298.98, CpuMfr.INTEL, 25354);
        Cpu cpu13100 = new Cpu("i3-13100", 58, 178.98, CpuMfr.INTEL, 14917);
        Cpu cpu13100f = new Cpu("i3-13100F", 58, 144.98, CpuMfr.INTEL, 14729);

        Collections.addAll(listAllCpu, cpu13900ks, cpu13900k, cpu13900kf, cpu13900f, cpu13700k, cpu13700f, cpu13700kf,
                cpu13700, cpu13600k, cpu13600kf, cpu13500, cpu13400f, cpu13400, cpu13100, cpu13100f);
    }

    public void addListIntel12Cpu(List<Cpu> listAllCpu) {
        // 12th Intel Core CPU
        Cpu cpu12900ks = new Cpu("i9-12900KS", 150, 587.99, CpuMfr.INTEL, 44307);
        Cpu cpu12900k = new Cpu("i9-12900K", 125, 568.98, CpuMfr.INTEL, 41470);
        Cpu cpu12900kf = new Cpu("i9-12900KF", 125, 516.45, CpuMfr.INTEL, 41244);
        Cpu cpu12900f = new Cpu("i9-12900F", 65, 661.61, CpuMfr.INTEL, 37072);
        Cpu cpu12700k = new Cpu("i7-12700K", 125, 409.98, CpuMfr.INTEL, 34747);
        Cpu cpu12700kf = new Cpu("i7-12700KF", 125, 389.00, CpuMfr.INTEL, 34516);
        Cpu cpu12700f = new Cpu("i7-12700F", 65, 478.98, CpuMfr.INTEL, 30994);
        Cpu cpu12700 = new Cpu("i7-12700", 65, 408.98, CpuMfr.INTEL, 30959);
        Cpu cpu12600k = new Cpu("i5-12600K", 125, 319.00, CpuMfr.INTEL, 27792);
        Cpu cpu12600kf = new Cpu("i5-12600KF", 125, 298.01, CpuMfr.INTEL, 27463);
        Cpu cpu12500 = new Cpu("i5-12500", 65, 317.00, CpuMfr.INTEL, 20003);
        Cpu cpu12400f = new Cpu("i5-12400F", 65, 199.00, CpuMfr.INTEL, 19637);
        Cpu cpu12400 = new Cpu("i5-12400", 65, 238.98, CpuMfr.INTEL, 19484);
        Cpu cpu12100 = new Cpu("i3-12100", 60, 154.69, CpuMfr.INTEL, 14252);
        Cpu cpu12100f = new Cpu("i3-12100F", 60, 119.00, CpuMfr.INTEL, 13821);

        Collections.addAll(listAllCpu,cpu12900ks, cpu12900k, cpu12900kf, cpu12900f, cpu12700k, cpu12700kf, cpu12700f,
                cpu12700, cpu12600k, cpu12600kf, cpu12500, cpu12500, cpu12400f, cpu12400, cpu12100, cpu12100f);
    }

    public void addListAmdCpu(List<Cpu> listAllCpu) {
        // AMD 7000 Series
        Cpu cpu7950x = new Cpu("R9-7950X", 170, 749.99, CpuMfr.AMD, 63380);
        Cpu cpu7950x3d = new Cpu("R9-7950X3D", 120, 889.00, CpuMfr.AMD, 62671);
        Cpu cpu7900x = new Cpu("R9-7900X", 170, 549.99, CpuMfr.AMD, 52217);
        Cpu cpu7900x3d = new Cpu("R9-7900X3D", 120, 658.58, CpuMfr.AMD, 50734);
        Cpu cpu7900 = new Cpu("R9-7900", 65, 499.00, CpuMfr.AMD, 49350);
        Cpu cpu7800x3d = new Cpu("R7-7800X3D", 120, 590.00, CpuMfr.AMD, 34718);
        Cpu cpu7700x = new Cpu("R7-7700X", 105, 439.99, CpuMfr.AMD, 36353);
        Cpu cpu7700 = new Cpu("R7-7700", 65, 439.00, CpuMfr.AMD, 35121);
        Cpu cpu7600x = new Cpu("R5-7600X", 105, 328.98, CpuMfr.AMD, 28831);
        Cpu cpu7600 = new Cpu("R5-7600", 65, 339.03, CpuMfr.AMD, 27644);

        Collections.addAll(listAllCpu, cpu7950x, cpu7950x3d, cpu7900x, cpu7900x3d, cpu7900, cpu7800x3d, cpu7700x,
                cpu7700, cpu7600x, cpu7600);
    }

}
