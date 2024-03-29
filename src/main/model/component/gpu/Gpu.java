package model.component.gpu;

import model.component.PcComponent;
import org.json.JSONObject;
import persistence.Writable;

// represent a gpu with given model name, base power to boot, chip manufacturer, benchmark and price
public class Gpu implements Comparable<Gpu>, Writable, PcComponent {
    private String model;
    private int basePower;
    private double price;
    private GpuMfr gpuMfr;
    private int benchMark;

    //EFFECTS: construct a Gpu object with given model name, base power, price,
    // chip manufacturer and benchmark
    public Gpu(String model, int basePower, double price, GpuMfr gpuMfr, int benchMark) {
        this.model = model;
        this.basePower = basePower;
        this.price = price;
        this.gpuMfr = gpuMfr;
        this.benchMark = benchMark;
    }

    //EFFECTS: return the difference between given gpu object with this in benchmark to sort
    @Override
    public int compareTo(Gpu o) {
        return o.getBenchMark() - this.benchMark;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("model", model);
        json.put("basePower", basePower);
        json.put("price", price);
        json.put("gpuMfr", gpuMfr);
        json.put("bench mark", benchMark);

        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Gpu gpu = (Gpu) o;

        if (basePower != gpu.basePower) {
            return false;
        }
        if (Double.compare(gpu.price, price) != 0) {
            return false;
        }
        if (benchMark != gpu.benchMark) {
            return false;
        }
        if (!model.equals(gpu.model)) {
            return false;
        }
        return gpuMfr == gpu.gpuMfr;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = model.hashCode();
        result = 31 * result + basePower;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + gpuMfr.hashCode();
        result = 31 * result + benchMark;
        return result;
    }

    // getter

    @Override
    public String getModel() {
        return model;
    }

    public int getBasePower() {
        return basePower;
    }

    public double getPrice() {
        return price;
    }

    public GpuMfr getGpuMfr() {
        return gpuMfr;
    }

    public int getBenchMark() {
        return benchMark;
    }
}
