package model.component.gpu;

public class Gpu implements Comparable<Gpu> {
    private String model;
    private int basePower;
    private double price;
    private GpuMfr gpuMfr;
    private int benchMark;

    public Gpu(String model, int basePower, double price, GpuMfr gpuMfr, int benchMark) {
        this.model = model;
        this.basePower = basePower;
        this.price = price;
        this.gpuMfr = gpuMfr;
        this.benchMark = benchMark;
    }

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
