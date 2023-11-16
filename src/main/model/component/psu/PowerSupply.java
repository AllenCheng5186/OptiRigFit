package model.component.psu;

import model.component.PcComponent;
import model.component.motherboard.FormSize;
import org.json.JSONObject;
import persistence.Writable;

//represent a power supply with model name, compatible case size, rated power, is modular or not and price
public class PowerSupply implements Comparable<PowerSupply>, Writable, PcComponent {

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("model", model);
        json.put("formSize", formSize);
        json.put("wattage", wattage);
        json.put("isModular", isModular);
        json.put("price", price);

        return json;
    }

    // getters

    @Override
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
