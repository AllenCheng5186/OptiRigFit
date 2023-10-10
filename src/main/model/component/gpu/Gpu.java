package model.component.gpu;

// represent a gpu with given model name, base power to boot, chip manufacturer, benchmark and price
public class Gpu implements Comparable<Gpu> {
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

    public GpuMfr getGpuMfr() {
        return gpuMfr;
    }

    public int getBenchMark() {
        return benchMark;
    }
}
