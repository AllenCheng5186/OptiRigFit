package model;

import model.component.cpu.Cpu;
import model.component.gpu.Gpu;
import model.component.motherboard.Motherboard;
import model.component.psu.PowerSupply;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// represent a configuration store cpu, motherboard, gpu (if possible), power supply
// and ram purchase suggestion and restriction
public class Configuration implements Writable {
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
        System.out.println("\nCPU: " + cpu.getModel() + "   " + cpu.getPrice());
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

    //getter

    public Cpu getCpu() {
        return cpu;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public PowerSupply getPowerSupply() {
        return powerSupply;
    }

    public Gpu getGpu() {
        return gpu;
    }

    public double getRamBudget() {
        return ramBudget;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ramBudget", ramBudget);
        json.put("components", componentsToJson());
        return json;
    }

    // EFFECTS: returns components in this configuration as a JSON array
    private JSONArray componentsToJson() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(cpu.toJson());
        if (gpu != null) {
            jsonArray.put(gpu.toJson());
        }
        jsonArray.put(motherboard.toJson());
        jsonArray.put(powerSupply.toJson());


        return jsonArray;
    }
}
