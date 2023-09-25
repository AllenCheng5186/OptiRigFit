package model.component.Cpu;

public class Cpu {
    private String name;
    private int basePower;
    private double price;
    private CpuMfr cpuMfr;
    private boolean integratedGraphics;
    private int score;

    public Cpu(String name, int basePower, double price, CpuMfr manufacturer, int score) {
        this.name = name;
        this.basePower = basePower;
        this.price = price;
        this.cpuMfr = manufacturer;
        this.integratedGraphics = !name.contains("F");
        this.score = score;
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

    public int getScore() {
        return score;
    }
}