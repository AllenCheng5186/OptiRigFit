package model.component.motherboard;

public class Motherboard implements Comparable<Motherboard> {
    private String name;
    private Socket socket;
    private FormSize formSize;
    private int maxRam;
    private int ramSlot;
    private double price;

    public Motherboard(String name, Socket socket, FormSize formSize, int maxRam, int ramSlot, double price) {
        this.name = name;
        this.socket = socket;
        this.formSize = formSize;
        this.maxRam = maxRam;
        this.ramSlot = ramSlot;
        this.price = price;
    }

    @Override
    public int compareTo(Motherboard o) {
        if (o.getPrice() - this.price < 0) {
            return -1;
        } else if (o.getPrice() - this.price == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public String getName() {
        return name;
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
