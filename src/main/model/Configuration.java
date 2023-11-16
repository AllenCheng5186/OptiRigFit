package model;

import model.component.cpu.Cpu;
import model.component.gpu.Gpu;
import model.component.motherboard.Motherboard;
import model.component.psu.PowerSupply;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

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

    // setter
    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public void setPowerSupply(PowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }

    public void setGpu(Gpu gpu) {
        this.gpu = gpu;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Configuration that = (Configuration) o;

        if (Double.compare(that.ramBudget, ramBudget) != 0) {
            return false;
        }
        if (!cpu.equals(that.cpu)) {
            return false;
        }
        if (!motherboard.equals(that.motherboard)) {
            return false;
        }
        if (!powerSupply.equals(that.powerSupply)) {
            return false;
        }
        return Objects.equals(gpu, that.gpu);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cpu.hashCode();
        result = 31 * result + motherboard.hashCode();
        result = 31 * result + powerSupply.hashCode();
        result = 31 * result + (gpu != null ? gpu.hashCode() : 0);
        temp = Double.doubleToLongBits(ramBudget);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
