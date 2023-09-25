package model.component.Cpu;

public class Cpu {
    private String name;
    private int basePower;
    private double price;
    private CpuManufacturer manufacturer;
    private boolean integratedGraphics;

    public Cpu(String name, int basePower, double price, CpuManufacturer manufacturer) {
        this.name = name;
        this.basePower = basePower;
        this.price = price;
        this.manufacturer = manufacturer;
        this.integratedGraphics = !name.contains("F");
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

    public CpuManufacturer getManufacturer() {
        return manufacturer;
    }

    public boolean hasIntegratedGraphics() {
        return integratedGraphics;
    }
}