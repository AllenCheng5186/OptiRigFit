package model.component.cpu;

public class Cpu implements Comparable<Cpu> {
    private String name;
    private int basePower;
    private double price;
    private CpuMfr cpuMfr;
    private boolean integratedGraphics;
    private int benchMark;

    //EFFECTS: construct a CPU object with given name, baser power,
    // price, manufacturer and benchmark
    public Cpu(String name, int basePower, double price, CpuMfr manufacturer, int benchMark) {
        this.name = name;
        this.basePower = basePower;
        this.price = price;
        this.cpuMfr = manufacturer;
        this.integratedGraphics = !name.contains("F");
        this.benchMark = benchMark;
    }

    //EFFECTS: return the difference between CPU object given benchmark
    // and this object's benchmark for sorting CPUs in list of CPUs
    @Override
    public int compareTo(Cpu o) {
        return o.getBenchMark() - this.benchMark;
    }

    // getter
    public String getName() {
        return name;
    }

    public int getBasePower() {
        return basePower;
    }

    public double getPrice() {
        return price;
    }

    public CpuMfr getCpuMfr() {
        return cpuMfr;
    }

    public boolean hasIntegratedGraphics() {
        return integratedGraphics;
    }

    public int getBenchMark() {
        return benchMark;
    }

}