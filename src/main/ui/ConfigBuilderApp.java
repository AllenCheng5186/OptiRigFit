package ui;


import model.Configuration;
import model.ConfigurationGenerator;
import model.Purpose;
import model.component.motherboard.FormSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConfigBuilderApp {
    private List<Configuration> savedConfigs;
    private Scanner input;

    // EFFECTS: runs the teller application
    public ConfigBuilderApp() {
        runConfigBuilder();
    }

    // MODIFIES: this
    // EFFECTS: main method which
    private void runConfigBuilder() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayFunMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("p")) {
                checkOutSavedConfig();
            } else if (command.equals("n")) {
                callConfigGenerator();
            }
        }

        System.out.println("\nThanks you for using our App!");
    }

    private void init() {
        savedConfigs = new ArrayList<>();
        System.out.println("Welcome to use OptiRigFit \n"
                + "-Java-Driven Personalized PC Hardware Configuration Builder");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayFunMenu() {
        System.out.println("\nSelect from following function:");
        System.out.println("\tn -> build new configuration");
        System.out.println("\tp -> check out saved configurations");
        System.out.println("\tq -> quit");
    }

    private void checkOutSavedConfig() {
        if (!savedConfigs.isEmpty()) {
            for (Configuration config : savedConfigs) {
                config.printOutConfiguration();
            }
        } else {
            System.out.println("You do not have any saved configuration yet!");
        }
    }

    private void callConfigGenerator() {
        int budgetInput = getBudgetInput();
        Purpose usrPurpose = getUsrPurpose();
        FormSize usrSizeInput = getUsrSizeInput();
        ConfigurationGenerator cg = new ConfigurationGenerator(budgetInput, usrSizeInput, usrPurpose);
        try {
            Configuration usrConfig = cg.configGenerate();
            usrConfig.printOutConfiguration();
            saveOrNot(usrConfig);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, no such configuration! :(");
            System.out.println("Please consider input higher budget or change the size!");
        }
    }

    private void saveOrNot(Configuration usrConfig) {
        System.out.println("\nWould like to save this configuration?");
        System.out.println("\ty -> Yes. Save it!");
        System.out.println("\tn -> Nope. Discard it!");
        String saveOrNot = input.next();
        saveOrNot = saveOrNot.toLowerCase();
        if (saveOrNot.equals("y")) {
            savedConfigs.add(usrConfig);
        }
    }

    private FormSize getUsrSizeInput() {
        System.out.println("\nWhat is the size of your computer？");
        System.out.println("\tSelect from following options:");
        System.out.println("\t s -> small size");
        System.out.println("\t m -> middle size");
        System.out.println("\t l -> large size");
        String sizeInput = input.next();
        sizeInput = sizeInput.toLowerCase();
        FormSize usrSizeInput = null;
        if (sizeInput.equals("s")) {
            usrSizeInput = FormSize.ITX;
        } else if (sizeInput.equals("m")) {
            usrSizeInput = FormSize.ATX;
        } else {
            usrSizeInput = FormSize.EATX;
        }
        return usrSizeInput;
    }

    private Purpose getUsrPurpose() {
        System.out.println("What is the primary purpose of your computer?");
        System.out.println("\nSelect from following purpose:");
        System.out.println("\tf -> Office Work (Entry-Level)");
        System.out.println("\tg -> Gaming");
        System.out.println("\tp -> Productivity, Video Editing");
        System.out.println("\tw -> 3d modelling");
        String purposeInput = input.next();
        purposeInput = purposeInput.toLowerCase();
        Purpose usrPurpose = null;
        if (purposeInput.equals("f")) {
            usrPurpose = Purpose.ENTRY_LEVEL;
        } else if (purposeInput.equals("g")) {
            usrPurpose = Purpose.GAMING;
        } else if (purposeInput.equals("p")) {
            usrPurpose = Purpose.PRODUCTIVITY;
        } else {
            usrPurpose = Purpose.WORK_STATION;
        }
        return usrPurpose;
    }

    private int getBudgetInput() {
        System.out.println("What were you planning on spending for your desktop?");
        return Integer.parseInt(input.next());
    }

}
