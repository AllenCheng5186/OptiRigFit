package model;

import model.component.cpu.Cpu;
import model.component.gpu.Gpu;
import model.component.motherboard.Motherboard;
import model.component.psu.PowerSupply;

public class Configuration {
    private Cpu cpu;
    private Motherboard motherboard;
    private PowerSupply powerSupply;
    private Gpu gpu;

    public Configuration(Cpu cpu, Motherboard motherboard, Gpu gpu, PowerSupply powerSupply) {
        this.cpu = cpu;
        this.motherboard = motherboard;
        this.powerSupply = powerSupply;
        this.gpu = gpu;
    }

    public void printOutConfiguration() {
        System.out.println("CPU: " + cpu.getModel() + "   " + cpu.getPrice());
        System.out.println("RAM: ");
        System.out.println("MotherBoard: " + motherboard.getName() + "   " + motherboard.getPrice());
        if (gpu != null) {
            System.out.println("GPU: " + gpu.getModel() + "   " + gpu.getPrice());
        }
        System.out.println("Power Supply: " + powerSupply.getModel() + "   " + powerSupply.getPrice());
        double configurationAggregate = cpu.getPrice() + motherboard.getPrice();
        configurationAggregate += powerSupply.getPrice() + powerSupply.getPrice();
        System.out.println("Aggregate: " + configurationAggregate);
    }

}
