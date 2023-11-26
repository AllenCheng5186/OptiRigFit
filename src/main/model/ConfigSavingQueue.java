package model;

import model.component.gpu.Gpu;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a configuration saving queue (Y) list of configurations
 */
public class ConfigSavingQueue {
    private static List<Configuration> savingList;

    /**
     * Constructor set up the field initialization
     */
    public ConfigSavingQueue() {
        savingList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add given configuration to the list
    public void add(Configuration newConfig, int configId) {
        savingList.add(newConfig);
        EventLog.getInstance().logEvent(new Event(" configuration #" + configId + " added to queue"));
        String newConfigEvent = " The added configuration has cpu of " + newConfig.getCpu().getModel() + ",";
        newConfigEvent += "\nbudget for ram " + newConfig.getRamBudget() + ",";
        newConfigEvent += "\nmotherboard of " + newConfig.getMotherboard().getModel() + ",";
        Gpu gpu = newConfig.getGpu();
        newConfigEvent += (newConfig.getGpu() == null) ? "\ngpu of Integrated graphics," : "\ngpu of " + gpu.getModel();
        newConfigEvent += "\npower supply of " + newConfig.getPowerSupply().getModel();
        int totalPrice = (int) (newConfig.getCpu().getPrice() + newConfig.getMotherboard().getPrice());
        totalPrice += newConfig.getMotherboard().getPrice() + newConfig.getRamBudget();
        totalPrice += (newConfig.getGpu() == null) ? 0 : newConfig.getGpu().getPrice();
        newConfigEvent += "\ntotal price of " + totalPrice;
        EventLog.getInstance().logEvent(new Event(newConfigEvent));
    }

    // EFFECTS: return true if the savingList contains given configuration
    // with same cpu, gpu, motherboard, power supply and ram budget,
    // otherwise, return false
    public boolean contains(Configuration config) {
        return savingList.contains(config);
    }

    // EFFECTS: return the configuration at given index
    public Configuration getConfig(int index) {
        return savingList.get(index);
    }

    // getter
    public static List<Configuration> getSavingList() {
        return savingList;
    }
}
