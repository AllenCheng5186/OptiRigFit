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

import java.util.Collections;
import java.util.List;

import static model.Purpose.*;

public class ConfigurationGenerator {
    private CpuList cpuList;
    private List<Cpu> properCPUs;
    private MotherboardList motherboardList;
    private List<Motherboard> properMotherBoards;
    private PowerSuppliesList powerSuppliesList;
    private List<PowerSupply> properPowerSupplies;
    private GpuList gpuList;
    private List<Gpu> properGpus;
    private FormSize formSize;
    private Purpose purpose;
    private double totalBudget;
    private double cpuBudget;
    private double ramBudget;
    private double motherboardBudget;
    private double psuBudget;
    private double gpuBudget;

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

    public void budgetDistribution() {
        if (purpose == ENTRY_LEVEL) {
            cpuBudget = totalBudget * 0.3;
            ramBudget = totalBudget * 0.2;
            motherboardBudget = totalBudget * 0.25;
            psuBudget = totalBudget * 0.25;
        } else {
            cpuBudget = totalBudget * 0.2;
            ramBudget = totalBudget * 0.1;
            gpuBudget =  totalBudget * 0.4;
            motherboardBudget = totalBudget * 0.15;
            psuBudget = totalBudget * 0.15;
        }
    }

    public Configuration configGenerate() {
        budgetDistribution();
        Cpu configCpu = getConfigCpu();
        Socket cpuSocket = getCpuSocket(configCpu);
        Motherboard configMotherboard = getConfigMotherboard(cpuSocket);
        int minWatt = configCpu.getBasePower() + 150 + 100;
        Gpu configGpu = null;
        if (gpuBudget != 0) {
            List<Gpu> budgetFriendGpus = gpuList.filterGpusInPriceInterval(properGpus, (gpuBudget + totalBudget * 0.1),
                    gpuBudget);
            configGpu = budgetFriendGpus.get(0);
            minWatt += configGpu.getBasePower();
        }
        PowerSupply configPsu = getConfigPowerSupply(minWatt);
        Configuration config = new Configuration(configCpu, configMotherboard, configGpu, configPsu);
        return config;
    }

    private PowerSupply getConfigPowerSupply(int minWatt) {
        List<PowerSupply> compatiblePowerSupplies = powerSuppliesList.filterGreaterWattPSUs(minWatt,
                properPowerSupplies);
        List<PowerSupply> correctSizePowerSupplies = powerSuppliesList.filterFormSizePSUs(formSize,
                compatiblePowerSupplies);
        List<PowerSupply> budgetFriendPowerSupplies = powerSuppliesList.filterPowerSupplyInPriceInterval(
                correctSizePowerSupplies, (psuBudget + totalBudget * 0.05), psuBudget);
        return budgetFriendPowerSupplies.get(0);
    }

    private Motherboard getConfigMotherboard(Socket cpuSocket) {
        List<Motherboard> compatibleMotherboards = motherboardList.filterRightSocketMotherboard(properMotherBoards,
                cpuSocket);
        List<Motherboard> correctSizeMotherboards = motherboardList.filterRightFormSizeMotherboards(
                compatibleMotherboards, formSize);
        List<Motherboard> budgetFriendMotherboards = motherboardList.filterMotherboardsInPriceInterval(
                correctSizeMotherboards, (motherboardBudget + totalBudget * 0.05), (motherboardBudget * 0.7));
        return budgetFriendMotherboards.get(0);
    }

    private Cpu getConfigCpu() {
        List<Cpu> withinBudgetCpus = cpuList.filterCPUsPriceInterval(properCPUs, (cpuBudget + totalBudget * 0.05),
                cpuBudget);
        Collections.sort(withinBudgetCpus);
        return withinBudgetCpus.get(0);
    }

    private Socket getCpuSocket(Cpu configCpu) {
        Socket cpuSocket = null;
        if (configCpu.getCpuMfr() == CpuMfr.INTEL) {
            cpuSocket = Socket.LGA1700;
        } else {
            cpuSocket = Socket.AM5;
        }
        return cpuSocket;
    }
}
