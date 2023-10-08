package model;

import model.component.cpu.Cpu;
import model.component.cpu.CpuList;
import model.component.cpu.CpuMfr;
import model.component.motherboard.FormSize;
import model.component.motherboard.Motherboard;
import model.component.motherboard.MotherboardList;
import model.component.motherboard.Socket;
import model.component.psu.PowerSuppliesList;
import model.component.psu.PowerSupply;

import java.util.Collections;
import java.util.List;

import static model.Purpose.GAMING;

public class ConfigurationGenerator {
    private CpuList cpuList;
    private List<Cpu> properCPUs;
    private MotherboardList motherboardList;
    private List<Motherboard> properMotherBoards;
    private PowerSuppliesList powerSuppliesList;
    private List<PowerSupply> properPowerSupplies;
    private int budgetUpperBound;
    private int budgetLowerBound;
    private FormSize formSize;
    private Purpose purpose;

    public ConfigurationGenerator(int budgetUpperBound, int budgetLowerBound, FormSize formSize, Purpose purpose) {
        cpuList = new CpuList();
        properCPUs = cpuList.getListAllCpu();
        motherboardList = new MotherboardList();
        properMotherBoards = motherboardList.getListAllMotherboard();
        powerSuppliesList = new PowerSuppliesList();
        properPowerSupplies = powerSuppliesList.getListAllPowerSupply();
        this.budgetUpperBound = budgetUpperBound;
        this.budgetLowerBound = budgetLowerBound;
        this.formSize = formSize;
        this.purpose = purpose;
    }

    public void budgetDistribution() {
        int totalBudget = budgetUpperBound - budgetLowerBound;
        if (purpose == GAMING) {
            double cpuBudget = totalBudget * 0.2;
            double ramBudget = totalBudget * 0.1;
            double gpuBudget =  totalBudget * 0.3;
            double motherboardBudget = totalBudget * 0.2;
            double psuBudget = totalBudget * 0.2;

            List<Cpu> withinBudgetCpus = cpuList.filterCPUsPriceInterval(properCPUs, cpuBudget,
                    (cpuBudget + totalBudget * 0.05));
            Collections.sort(withinBudgetCpus);
            Cpu configCpu = withinBudgetCpus.get(0);
            Socket cpuSocket = null;
            if (configCpu.getCpuMfr() == CpuMfr.INTEL) {
                cpuSocket = Socket.LGA1700;
            } else {
                cpuSocket = Socket.AM5;
            }

            List<Motherboard> compatibleMotherboards = motherboardList.filterRightSocketMotherboard(properMotherBoards,
                    cpuSocket);
            List<Motherboard> correctSizeMotherboards = motherboardList.filterRightFormSizeMotherboards(
                    compatibleMotherboards, formSize);
            // TODO Motherboard within budget filter method


        }
    }





}
