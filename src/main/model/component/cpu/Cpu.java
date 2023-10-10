package model.component.cpu;

//represent a cpu object with given model name, base power to boot, chip manufacturer, benchmark and price
public class Cpu implements Comparable<Cpu> {
    private String model;
    private int basePower;
    private double price;
    private CpuMfr cpuMfr;
    private boolean integratedGraphics;
    private int benchMark;

    //EFFECTS: construct a CPU object with given name, baser power,
    // price, manufacturer and benchmark
    public Cpu(String model, int basePower, double price, CpuMfr manufacturer, int benchMark) {
        this.model = model;
        this.basePower = basePower;
        this.price = price;
        this.cpuMfr = manufacturer;
        this.integratedGraphics = !model.contains("F");
        this.benchMark = benchMark;
    }

    //EFFECTS: return the difference between CPU object given benchmark
    // and this object's benchmark for sorting CPUs in list of CPUs
    @Override
    public int compareTo(Cpu o) {
        return o.getBenchMark() - this.benchMark;
    }

    // getter
    public String getModel() {
        return model;
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