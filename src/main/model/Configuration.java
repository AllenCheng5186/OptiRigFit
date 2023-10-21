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
