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
    private double ramBudget;

    //EFFECTS: construct a configuration object with given cpu, motherboard, gpu,
    // and power supply
    public Configuration(Cpu cpu, Motherboard motherboard, Gpu gpu, PowerSupply powerSupply, double ramBudget) {
        this.cpu = cpu;
        this.motherboard = motherboard;
        this.powerSupply = powerSupply;
        this.gpu = gpu;
        this.ramBudget = ramBudget;
    }

    //EFFECTS: print out configuration and total aggregate
    public void printOutConfiguration() {
        System.out.println("CPU: " + cpu.getModel() + "   " + cpu.getPrice());
        if (motherboard.getName().contains("D4")) {
            System.out.println("RAM: DDR4" + "   " + ramBudget);
        } else {
            System.out.println("RAM: DDR5" + "   " + ramBudget);
        }
        System.out.println("MotherBoard: " + motherboard.getName() + "   " + motherboard.getPrice());
        if (gpu != null) {
            System.out.println("GPU: " + gpu.getModel() + "   " + gpu.getPrice());
        }
        System.out.println("Power Supply: " + powerSupply.getModel() + "   " + powerSupply.getPrice());
        double configurationAggregate = cpu.getPrice() + motherboard.getPrice() + powerSupply.getPrice() + ramBudget;
        if (gpu != null) {
            configurationAggregate += gpu.getPrice();
        }
        configurationAggregate = Math.round(configurationAggregate * 100.0) / 100.0;
        System.out.println("Aggregate: " + configurationAggregate);
    }

}
