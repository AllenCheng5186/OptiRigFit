package ui;

import model.Configuration;
import model.Purpose;
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
import java.util.Scanner;

import static model.Purpose.ENTRY_LEVEL;

public class ConfigEditor {
    private static final int UPPER_DOWN_BOUNDARY_INTERVAL = 150;

    private Scanner input;
    private final double cpuBudget;
    private final double gpuBudget;
    private final double motherboardBudget;
    private final double psuBudget;
    private final Configuration oldConfig;
    private final FormSize formSize;
    private final Purpose purpose;

    public ConfigEditor(double cpuBudget, double gpuBudget, double motherboardBudget, double psuBudget,
                        Configuration oldConfig, FormSize formSize, Purpose purpose) {
        this.cpuBudget = cpuBudget;
        this.gpuBudget = gpuBudget;
        this.motherboardBudget = motherboardBudget;
        this.psuBudget = psuBudget;
        this.oldConfig = oldConfig;
        this.formSize = formSize;
        this.purpose = purpose;
    }

    public Configuration changeConfig() {
        boolean keepGoing = true;
        String usrChangeConfig = null;

        init();
        while (keepGoing) {
            askUsrChangeConfig();
            usrChangeConfig = input.next();
            usrChangeConfig = usrChangeConfig.toLowerCase();
            Configuration returnConfig = oldConfig;
            if (usrChangeConfig.equals("n")) {
                return returnConfig;
            }
            int usrChoiceChangeComp = askUsrChangeWhichComponent();
            returnConfig = componentEdit(usrChoiceChangeComp);
            returnConfig.printOutConfiguration();

        }
        System.out.println("\nEdit configuration not successful!");
        return oldConfig;
    }

    private Configuration componentEdit(int usrChoiceChangeComp) {
        Configuration returnConfig = oldConfig;
        if (usrChoiceChangeComp == 1) {
            returnConfig = cpuEdit();
            checkEditSuccess(returnConfig);
        } else if (usrChoiceChangeComp == 2) {
            returnConfig = motherboardEdit();
            checkEditSuccess(returnConfig);
        } else if (usrChoiceChangeComp == 3) {
            returnConfig = psuEdit();
            checkEditSuccess(returnConfig);
        } else if (oldConfig.getGpu() != null && usrChoiceChangeComp == 4) {
            returnConfig = gpuEdit();
            checkEditSuccess(returnConfig);
        } else {
            System.out.println("Invalid input!");
        }
        return returnConfig;
    }

    private void checkEditSuccess(Configuration returnConfig) {
        if (oldConfig == returnConfig) {
            System.out.println("\nEdit unsuccessful! Change do not saved!");
        } else {
            System.out.println("\nEdit successful!");
        }
    }

    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void askUsrChangeConfig() {
        System.out.println("\nWould you like to customise this configuration?");
        System.out.println("\ty -> Yes, I want to customise components.");
        System.out.println("\tn -> Nope, it looks good!");
    }

    private int askUsrChangeWhichComponent() {
        System.out.println("\nWhich component would you like to change?");
        System.out.println("\t1 -> CPU");
        System.out.println("\t2 -> Motherboard");
        System.out.println("\t3 -> Power Supply");
        if (oldConfig.getGpu() != null) {
            System.out.println("\t4 -> GPU");
        }
        return input.nextInt();
    }

    private Configuration gpuEdit() {
        GpuList gpuList = new GpuList();
        List<Gpu> gpus = gpuList.getListAllGpu();
        List<Gpu> withinBudgetGpus = gpuList.filterGpusInPriceInterval(gpus,
                (gpuBudget + UPPER_DOWN_BOUNDARY_INTERVAL),
                (gpuBudget - UPPER_DOWN_BOUNDARY_INTERVAL));
        System.out.println("\nHere are some items you can select to replace:");
        for (int i = 0; i < withinBudgetGpus.size(); i++) {
            String gpuName = withinBudgetGpus.get(i).getModel();
            double gpuPrice = withinBudgetGpus.get(i).getPrice();
            System.out.println("\t" + i + " -> " + gpuName + "   " + gpuPrice);
        }
        System.out.println("\nPlease input the number of item you would like to select");
        int usrChoice = input.nextInt();
        Gpu newGpu = null;
        try {
            newGpu = withinBudgetGpus.get(usrChoice);
            return new Configuration(oldConfig.getCpu(), oldConfig.getMotherboard(), newGpu,
                    oldConfig.getPowerSupply(), oldConfig.getRamBudget());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nThe number you input is invalid!");
        }
        return oldConfig;
    }

    private Configuration psuEdit() {
        List<PowerSupply> withinBudgetPsus = getPowerSupplies();
        for (int i = 0; i < withinBudgetPsus.size(); i++) {
            String psuName = withinBudgetPsus.get(i).getModel();
            double psuPrice = withinBudgetPsus.get(i).getPrice();
            boolean psuModular = withinBudgetPsus.get(i).isModular();
            if (psuModular) {
                System.out.println("\t" + i + " -> " + psuName + "   " + "full/semi modular" + "   " + psuPrice);
            } else {
                System.out.println("\t" + i + " -> " + psuName + "   " + "not modular" + "   " + psuPrice);
            }
        }
        System.out.println("\nPlease input the number of item you would like to select");
        int usrChoice = input.nextInt();
        PowerSupply newPsu = null;
        try {
            newPsu = withinBudgetPsus.get(usrChoice);
            return new Configuration(oldConfig.getCpu(), oldConfig.getMotherboard(), oldConfig.getGpu(),
                    newPsu, oldConfig.getRamBudget());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nThe number you input is invalid!");
        }
        return oldConfig;
    }

    private List<PowerSupply> getPowerSupplies() {
        PowerSuppliesList powerSuppliesList = new PowerSuppliesList();
        List<PowerSupply> powerSupplies = powerSuppliesList.getListAllPowerSupply();
        int minWatt = oldConfig.getPowerSupply().getWattage();
        List<PowerSupply> compatiblePowerSupplies = powerSuppliesList.filterGreaterWattPSUs(minWatt,
                powerSupplies);
        List<PowerSupply> correctSizePowerSupplies = powerSuppliesList.filterFormSizePSUs(formSize,
                compatiblePowerSupplies);
        List<PowerSupply> withinBudgetPsus = powerSuppliesList.filterPowerSupplyInPriceInterval(
                correctSizePowerSupplies,
                (psuBudget + UPPER_DOWN_BOUNDARY_INTERVAL),
                (psuBudget - UPPER_DOWN_BOUNDARY_INTERVAL));
        System.out.println("\nHere are some items you can select to replace:");
        return withinBudgetPsus;
    }

    private Configuration motherboardEdit() {
        List<Motherboard> withinBudgetMBs = getMotherboards();
        System.out.println("\nHere are some items you can select to replace:");
        for (int i = 0; i < withinBudgetMBs.size(); i++) {
            String motherboardName = withinBudgetMBs.get(i).getName();
            double motherboardPrice = withinBudgetMBs.get(i).getPrice();
            System.out.println("\t" + i + " -> " + motherboardName + "   " + motherboardPrice);
        }
        System.out.println("\nPlease input the number of item you would like to select");
        int usrChoice = input.nextInt();
        Motherboard newMotherboard = null;
        try {
            newMotherboard = withinBudgetMBs.get(usrChoice);
            return new Configuration(oldConfig.getCpu(), newMotherboard, oldConfig.getGpu(),
                    oldConfig.getPowerSupply(), oldConfig.getRamBudget());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nThe number you input is invalid!");
        }
        return oldConfig;
    }

    private List<Motherboard> getMotherboards() {
        MotherboardList motherboardList = new MotherboardList();
        List<Motherboard> motherboards = motherboardList.getListAllMotherboard();
        Socket socket = Socket.LGA1700;
        if (oldConfig.getCpu().getCpuMfr() == CpuMfr.AMD) {
            socket = Socket.AM5;
        }
        List<Motherboard> compatibleMotherboards = motherboardList.filterRightSocketMotherboard(motherboards,
                socket);
        List<Motherboard> correctSizeMotherboards = motherboardList.filterRightFormSizeMotherboards(
                compatibleMotherboards, formSize);
        List<Motherboard> withinBudgetMBs = motherboardList.filterMotherboardsInPriceInterval(
                correctSizeMotherboards, (motherboardBudget + UPPER_DOWN_BOUNDARY_INTERVAL),
                (motherboardBudget - UPPER_DOWN_BOUNDARY_INTERVAL));
        return withinBudgetMBs;
    }

    private Configuration cpuEdit() {
        Cpu newCpu = displayCpuListWithinInterval();
        if (newCpu != null) {
            return new Configuration(newCpu, oldConfig.getMotherboard(), oldConfig.getGpu(),
                    oldConfig.getPowerSupply(), oldConfig.getRamBudget());
        } else {
            System.out.println("\nEdit configuration not successful!");
            return oldConfig;
        }
    }


    private Cpu displayCpuListWithinInterval() {
        CpuList cpuList = new CpuList();
        List<Cpu> correctSocketCpus = getCorrectSocketCpus(cpuList);
        List<Cpu> cpusWithIG = new ArrayList<>();
        if (purpose == ENTRY_LEVEL) {
            cpusWithIG = cpuList.returnCpusHasIG(correctSocketCpus);
        } else {
            cpusWithIG = correctSocketCpus;
        }
        List<Cpu> withinBudgetCpus = cpuList.filterCPUsPriceInterval(cpusWithIG,
                (cpuBudget + UPPER_DOWN_BOUNDARY_INTERVAL), (cpuBudget - UPPER_DOWN_BOUNDARY_INTERVAL));
        Collections.sort(withinBudgetCpus);
        return getNewCpu(withinBudgetCpus);
    }

    private List<Cpu> getCorrectSocketCpus(CpuList cpuList) {
        List<Cpu> cpus = cpuList.getListAllCpu();
        Socket mbSocket = oldConfig.getMotherboard().getSocket();
        List<Cpu> correctSocketCpus = new ArrayList<>();
        if (mbSocket == Socket.LGA1700) {
            for (Cpu cpu : cpus) {
                if (cpu.getCpuMfr() == CpuMfr.INTEL) {
                    correctSocketCpus.add(cpu);
                }
            }
        } else {
            for (Cpu cpu : cpus) {
                if (cpu.getCpuMfr() == CpuMfr.AMD) {
                    correctSocketCpus.add(cpu);
                }
            }
        }
        return correctSocketCpus;
    }

    private Cpu getNewCpu(List<Cpu> withinBudgetCpus) {
        System.out.println("\nHere are some items you can select to replace:");
        for (int i = 0; i < withinBudgetCpus.size(); i++) {
            String cpuName = withinBudgetCpus.get(i).getModel();
            double cpuPrice = withinBudgetCpus.get(i).getPrice();
            System.out.println("\t" + i + " -> " + cpuName + "   " + cpuPrice);
        }
        System.out.println("\nPlease input the number of item you would like to select");
        int usrChoice = input.nextInt();
        Cpu newCpu = null;
        try {
            newCpu = withinBudgetCpus.get(usrChoice);
            return newCpu;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nThe number you input is invalid!");
        }
        return  null;
    }
}
