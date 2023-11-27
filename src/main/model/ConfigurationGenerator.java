package model;

import model.component.cpu.Cpu;
import model.component.cpu.CpuList;
import model.component.cpu.CpuMfr;
import model.component.gpu.Gpu;
import model.component.gpu.GpuList;
import model.component.motherboard.FormSize;
import model.component.motherboard.Motherboard;
import model.component.motherboard.MotherboardList;
import model.component.motherboard.Socket;
import model.component.psu.PowerSuppliesList;
import model.component.psu.PowerSupply;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static model.Purpose.*;

// Represent a configuration generator using given budget, size preference and purpose
public class ConfigurationGenerator {
    private final CpuList cpuList;
    private final List<Cpu> properCPUs;
    private final MotherboardList motherboardList;
    private final List<Motherboard> properMotherBoards;
    private final PowerSuppliesList powerSuppliesList;
    private final List<PowerSupply> properPowerSupplies;
    private final GpuList gpuList;
    private final List<Gpu> properGpus;
    private final FormSize formSize;
    private final Purpose purpose;
    private final double totalBudget;
    private double cpuBudget;
    private double ramBudget;
    private double motherboardBudget;
    private double psuBudget;
    private double gpuBudget;
    private int minWatt;

    //EFFECTS: construct a ConfigurationGenerator object with given totalBudget,
    // formSize and purpose
    public ConfigurationGenerator(int totalBudget, FormSize formSize, Purpose purpose) {
        cpuList = new CpuList();
        properCPUs = cpuList.getListAllCpu();
        motherboardList = new MotherboardList();
        properMotherBoards = motherboardList.getListAllMotherboard();
        powerSuppliesList = new PowerSuppliesList();
        properPowerSupplies = powerSuppliesList.getListAllPowerSupply();
        gpuList = new GpuList();
        properGpus = gpuList.getListAllGpu();
        this.formSize = formSize;
        this.purpose = purpose;
        this.totalBudget = totalBudget;
    }

    //MODIFIES: this
    //EFFECTS: separate total budget into budgets for each component
    public void budgetDistribution() {
        if (purpose == ENTRY_LEVEL) {
            cpuBudget = totalBudget * 0.3;
            ramBudget = totalBudget * 0.2;
            motherboardBudget = totalBudget * 0.25;
            psuBudget = totalBudget * 0.25;
        } else {
            cpuBudget = totalBudget * 0.2;
            ramBudget = totalBudget * 0.1;
            gpuBudget = totalBudget * 0.4;
            motherboardBudget = totalBudget * 0.15;
            psuBudget = totalBudget * 0.15;
        }
    }

    //MODIFIES: this
    //EFFECTS: main configuration generation method, collaborate with following helpers,
    //first, get the most powerful cpu within budget interval and has the highest benchmark
    //second, get the cpu socket and find the best motherboard that is compatible
    //third, if it needs to have gpu, get the most powerful gpu within budget interval and has the highest benchmark
    //fourth, calculate the lowest watt require to boot and get best psu
    //construct above as a Configuration object
    public Configuration configGenerate() {
        EventLog.getInstance().logEvent(new Event(" New Config generation start!"));
        budgetDistribution();
        Cpu configCpu = getConfigCpu();
        Socket cpuSocket = getCpuSocket(configCpu);
        Motherboard configMotherboard = getConfigMotherboard(cpuSocket);
        minWatt = configCpu.getBasePower() + 150 + 100;
        Gpu configGpu = getConfigGpu();
        PowerSupply configPsu = getConfigPowerSupply(minWatt);
        logEventOfNewConfigGenerateSuccessfully();
        return new Configuration(configCpu, configMotherboard, configGpu, configPsu, ramBudget);
    }

    // MODIFIES: EventLog
    // EFFECTS: log the event of new config generated successfully,
    // recording new config of given cpu, motherboard, gpu, power supply
    private void logEventOfNewConfigGenerateSuccessfully() {
        EventLog.getInstance().logEvent(new Event(" New configuration is generated successfully!"));
    }

    // MODIFIES: this
    // EFFECTS: return the first gpu which within budget interval
    private Gpu getConfigGpu() {
        Gpu configGpu = null;
        if (gpuBudget != 0) {
            List<Gpu> budgetFriendGpus = gpuList.filterGpusInPriceInterval(properGpus, (gpuBudget + totalBudget * 0.1),
                    gpuBudget);
            try {
                if (gpuBudget > properGpus.get(0).getPrice()) {
                    configGpu = properGpus.get(0);
                } else {
                    configGpu = budgetFriendGpus.get(0);
                }
                EventLog.getInstance().logEvent(new Event(" Fitted GPU found: " + configGpu.getModel()));
            } catch (IndexOutOfBoundsException e) {
                EventLog.getInstance().logEvent(new Event(" Fail to find fitted Gpu!"));
                throw e;
            }
            minWatt += configGpu.getBasePower();
        }
        return configGpu;
    }

    //REQUIRE: minWatt >= 400;
    //EFFECTS: return the first power supply which above the lowest watt require to boot
    public PowerSupply getConfigPowerSupply(int minWatt) {
        List<PowerSupply> compatiblePowerSupplies = powerSuppliesList.filterGreaterWattPSUs(minWatt,
                properPowerSupplies);
        List<PowerSupply> correctSizePowerSupplies = powerSuppliesList.filterFormSizePSUs(formSize,
                compatiblePowerSupplies);
        List<PowerSupply> budgetFriendPowerSupplies = powerSuppliesList.filterPowerSupplyInPriceInterval(
                correctSizePowerSupplies, (psuBudget + totalBudget * 0.05), psuBudget);
        PowerSupply returnedPsu = null;
        try {
            if (psuBudget >= properPowerSupplies.get(properPowerSupplies.size() - 2).getPrice()) {
                returnedPsu =  properPowerSupplies.get(properPowerSupplies.size() - 2);
            } else {
                returnedPsu =  budgetFriendPowerSupplies.get(0);
            }
            EventLog.getInstance().logEvent(new Event(" Fitted power supply found: " + returnedPsu.getModel()));
        } catch (IndexOutOfBoundsException e) {
            EventLog.getInstance().logEvent(new Event(" Fail to find fitted power supply!"));
            throw e;
        }
        return returnedPsu;
    }

    //REQUIRE: cpuSocket in LGA1700 & AM5
    //MODIFIES: return the most expensive motherboard that is compatible to the cpu socket
    private Motherboard getConfigMotherboard(Socket cpuSocket) {
        List<Motherboard> compatibleMotherboards = motherboardList.filterRightSocketMotherboard(properMotherBoards,
                cpuSocket);
        List<Motherboard> correctSizeMotherboards = motherboardList.filterRightFormSizeMotherboards(
                compatibleMotherboards, formSize);
        List<Motherboard> budgetFriendMotherboards = motherboardList.filterMotherboardsInPriceInterval(
                correctSizeMotherboards, (motherboardBudget + totalBudget * 0.05), (motherboardBudget * 0.7));
        Motherboard returnedMb = null;
        try {
            if (motherboardBudget >= properMotherBoards.get(0).getPrice()) {
                returnedMb = properMotherBoards.get(0);
            } else {
                returnedMb = budgetFriendMotherboards.get(0);
            }
            EventLog.getInstance().logEvent(new Event(" Fitted motherboard found: " + returnedMb.getModel()));
        } catch (IndexOutOfBoundsException e) {
            EventLog.getInstance().logEvent(new Event(" Fail to find fitted motherboard!"));
            throw e;
        }
        return returnedMb;
    }

    //EFFECTS: return the most powerful cpu within budget interval and has the highest benchmark
    private Cpu getConfigCpu() {
        List<Cpu> cpusWithIG = new ArrayList<>();
        if (purpose == ENTRY_LEVEL) {
            cpusWithIG = cpuList.returnCpusHasIG(properCPUs);
        } else {
            cpusWithIG = properCPUs;
        }
        List<Cpu> withinBudgetCpus = cpuList.filterCPUsPriceInterval(cpusWithIG, (cpuBudget + totalBudget * 0.05),
                cpuBudget);
        Collections.sort(withinBudgetCpus);
        Cpu returnedCpu = null;
        try {
            if (cpuBudget >= properCPUs.get(0).getPrice()) {
                returnedCpu = properCPUs.get(0);
            } else {
                returnedCpu = withinBudgetCpus.get(0);
            }
            EventLog.getInstance().logEvent(new Event(" Fitted cpu found: " + returnedCpu.getModel()));
        } catch (IndexOutOfBoundsException e) {
            EventLog.getInstance().logEvent(new Event(" Fail to find fitted cpu!"));
            throw e;
        }
        return returnedCpu;
    }

    //REQUIRE: configCpu 12 & 13th Intel core CPU or 7000 series AMD Reyzen CPU
    //EFFECTS: return the corresponding cpu socket that is compatible with given CPU
    private Socket getCpuSocket(Cpu configCpu) {
        Socket cpuSocket = null;
        if (configCpu.getCpuMfr() == CpuMfr.INTEL) {
            cpuSocket = Socket.LGA1700;
        } else {
            cpuSocket = Socket.AM5;
        }
        return cpuSocket;
    }
    // setter

    public void setPsuBudget(double psuBudget) {
        this.psuBudget = psuBudget;
    }


    // getter

    public CpuList getCpuList() {
        return cpuList;
    }

    public MotherboardList getMotherboardList() {
        return motherboardList;
    }

    public PowerSuppliesList getPowerSuppliesList() {
        return powerSuppliesList;
    }

    public GpuList getGpuList() {
        return gpuList;
    }


    public FormSize getFormSize() {
        return formSize;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public double getCpuBudget() {
        return cpuBudget;
    }

    public double getRamBudget() {
        return ramBudget;
    }

    public double getMotherboardBudget() {
        return motherboardBudget;
    }

    public double getPsuBudget() {
        return psuBudget;
    }

    public double getGpuBudget() {
        return gpuBudget;
    }
}
