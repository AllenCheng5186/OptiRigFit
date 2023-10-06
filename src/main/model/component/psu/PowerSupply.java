package model.component.psu;

import model.component.motherboard.FormSize;

import java.util.Comparator;

public class PowerSupply implements Comparable<PowerSupply> {

    private String model;
    private FormSize formSize;
    private int wattage;
    private boolean isModular;
    private double price;

    //EFFECTS: construct a PowerSupply object with given formSize factor,
    // wattage, whether it is modular, and price sale on Amazon or Canada Computer
    public PowerSupply(String model, FormSize formSize, int wattage, boolean isModular, double price) {
        this.model = model;
        this.formSize = formSize;
        this.wattage = wattage;
        this.isModular = isModular;
        this.price = price;
    }

    @Override
    public int compareTo(PowerSupply o) {
        return (this.getWattage() - o.getWattage());
    }

    // getters

    public String getModel() {
        return model;
    }

    public FormSize getFormSize() {
        return formSize;
    }

    public int getWattage() {
        return wattage;
    }

    public boolean isModular() {
        return isModular;
    }

    public double getPrice() {
        return price;
    }
}