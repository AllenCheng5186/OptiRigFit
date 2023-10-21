package ui;

import model.Configuration;
import model.component.cpu.Cpu;
import model.component.gpu.Gpu;
import model.component.motherboard.Motherboard;
import model.component.psu.PowerSupply;

public class ConfigPrinter {

    // EFFECTS: print out the configuration in the console
    protected void printOutConfiguration(Configuration config) {
        Cpu cpu = config.getCpu();
        Motherboard motherboard = config.getMotherboard();
        double ramBudget = config.getRamBudget();
        Gpu gpu = config.getGpu();
        PowerSupply powerSupply = config.getPowerSupply();

        System.out.println("\nCPU: " + cpu.getModel() + "   " + cpu.getPrice());
        motherboardInfoPrint(motherboard, ramBudget);
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

    // EFFECTS: helper function to print info about motherboard
    private static void motherboardInfoPrint(Motherboard motherboard, double ramBudget) {
        if (motherboard.getName().contains("D4")) {
            System.out.println("RAM: DDR4" + "   "
                    + "Max RAM:" + motherboard.getMaxRam()
                    + "   " + "RAM slot: " + motherboard.getRamSlot()
                    + "   " + ramBudget);
        } else {
            System.out.println("RAM: DDR5" + "   "
                    + "Max RAM:" + motherboard.getMaxRam()
                    + "   " + "RAM slot: " + motherboard.getRamSlot()
                    + "   " + ramBudget);
        }
    }
}
