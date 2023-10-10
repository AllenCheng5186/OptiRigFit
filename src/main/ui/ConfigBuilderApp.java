package ui;


import model.Configuration;
import model.ConfigurationGenerator;
import model.Purpose;
import model.component.motherboard.FormSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Configuration Generator App main method
public class ConfigBuilderApp {
    private List<Configuration> savedConfigs;
    private Scanner input;

    // EFFECTS: runs the teller application
    public ConfigBuilderApp() {
        runConfigBuilder();
    }

    // MODIFIES: this
    // EFFECTS: main method which the entire App control flow
    private void runConfigBuilder() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayFunMenu();
            command = input.next();
            command = command.toLowerCase();

            switch (command) {
                case "q":
                    keepGoing = false;
                    break;
                case "p":
                    checkOutSavedConfig();
                    break;
                case "n":
                    callConfigGenerator();
                    break;
            }
        }

        System.out.println("\nThanks you for using our App!");
    }

    //MODIFIES: this
    //EFFECTS: initialize user input sensor and configuration storage list
    private void init() {
        savedConfigs = new ArrayList<>();
        System.out.println("Welcome to use OptiRigFit \n"
                + "-Java-Driven Personalized PC Hardware Configuration Builder");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: display the main function menu
    private void displayFunMenu() {
        System.out.println("\nSelect from following function:");
        System.out.println("\tn -> build new configuration");
        System.out.println("\tp -> check out saved configurations");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: display user's saved configuration
    private void checkOutSavedConfig() {
        if (!savedConfigs.isEmpty()) {
            showSaveConfigsOneByOne();
        } else {
            System.out.println("You do not have any saved configuration yet!");
        }
    }

    //EFFECTS: show the save configs one by one in the list, and ask user would like
    // to check the next one in the list
    private void showSaveConfigsOneByOne() {
        boolean keepCheckingNext = true;
        String usrInput = null;
        int nextConfigIndex = 0;
        while (keepCheckingNext) {
            savedConfigs.get(nextConfigIndex).printOutConfiguration();
            if ((nextConfigIndex + 2) > savedConfigs.size()) {
                System.out.println("\n No more saved configurations");
                break;
            } else {
                System.out.println("\nWould like check out next saved configuration you had saved?");
                System.out.println("\ty -> Yes, see next one");
                System.out.println("\tn -> No, go back to menu");
                usrInput = input.next();
                usrInput = usrInput.toLowerCase();
                if (usrInput.equals("n")) {
                    keepCheckingNext = false;
                } else {
                    nextConfigIndex++;
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: call config generator method to generator a configuration for user according
    // to their budget, case size and purpose, try to figure out best fit configuration, fail to
    // ask user increase their budget or loosen the restrictions on computer size and
    // save to the list according to user's choice
    private void callConfigGenerator() {
        int budgetInput = getBudgetInput();
        Purpose usrPurpose = getUsrPurpose();
        FormSize usrSizeInput = getUsrSizeInput();
        ConfigurationGenerator cg = new ConfigurationGenerator(budgetInput, usrSizeInput, usrPurpose);
        try {
            Configuration usrConfig = cg.configGenerate();
//            usrConfig.printOutConfiguration();
            saveOrNot(usrConfig);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, no such configuration! :(");
            System.out.println("Please consider input higher budget or change the size!");
        }
    }

    //MODIFIES: this
    //EFFECTS: helper method to associate with upper method to save user's generated configuration
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

    //EFFECTS: show desktop case size options to user and return their choice in terms of FormSize object
    private FormSize getUsrSizeInput() {
        System.out.println("\nWhat is the size of your computer?");
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

    //EFFECTS: show purposes options to user and return their choice in terms of Purpose object
    private Purpose getUsrPurpose() {
        System.out.println("What is the primary purpose of your computer?");
        System.out.println("\nSelect from following purpose:");
        System.out.println("\tf -> Office Work (Entry-Level)");
        System.out.println("\tg -> Gaming");
        System.out.println("\tp -> Productivity, Video Editing");
        System.out.println("\tw -> Work Station, 3d modelling");
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

    //EFFECTS: ask user for their budget they would like to spend for their new desktop
    private int getBudgetInput() {
        System.out.println("What were you planning on spending for your desktop?");
        return Integer.parseInt(input.next());
    }

}
