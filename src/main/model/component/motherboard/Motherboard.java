package model.component.motherboard;

import model.component.PcComponent;
import org.json.JSONObject;
import persistence.Writable;

//represent a motherboard object with model name, compatible cpu socket, case size,
// max ram capacity, number of ram slot and price
public class Motherboard implements Comparable<Motherboard>, Writable, PcComponent {
    private String model;
    private Socket socket;
    private FormSize formSize;
    private int maxRam;
    private int ramSlot;
    private double price;

    //EFFECTS: constructor a MotherBoard object with given name, socket factor,
    // form factor, maximum ram capacity, number of ram slots, and selling price.
    public Motherboard(String name, Socket socket, FormSize formSize, int maxRam, int ramSlot, double price) {
        this.model = name;
        this.socket = socket;
        this.formSize = formSize;
        this.maxRam = maxRam;
        this.ramSlot = ramSlot;
        this.price = price;
    }


    @Override
    public int compareTo(Motherboard o) {
        if (o.getPrice() > this.price) {
            return 1;
        } else if (o.getPrice() == this.price) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("model", model);
        json.put("socket", socket);
        json.put("formSize", formSize);
        json.put("maxRam", maxRam);
        json.put("ramSlot", ramSlot);
        json.put("price", price);

        return json;
    }

    @Override
    public String getModel() {
        return model;
    }

    public Socket getSocket() {
        return socket;
    }

    public FormSize getFormSize() {
        return formSize;
    }

    public int getMaxRam() {
        return maxRam;
    }

    public int getRamSlot() {
        return ramSlot;
    }

    public double getPrice() {
        return price;
    }
}
