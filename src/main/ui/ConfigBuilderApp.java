package ui;


import model.Configuration;
import model.ConfigurationGenerator;
import model.Purpose;
import model.component.motherboard.FormSize;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Configuration Generator App main method
public class ConfigBuilderApp {
    private static final String JSON_STORE = "./data/configurations.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private List<Configuration> savedConfigs;
    private Scanner input;

    // EFFECTS: runs the teller application
    public ConfigBuilderApp() {
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
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
                case "l" :
                    loadConfigsList();
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
        System.out.println("\tl -> load the configurations from the file");
        System.out.println("\tp -> check out saved configurations");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: display user's saved configuration
    private void checkOutSavedConfig() {
        if (!savedConfigs.isEmpty()) {
            showSaveConfigsOneByOne();
            saveListOfConfigurations();
        } else {
            System.out.println("You do not have any saved configuration yet!");
        }
    }

    //EFFECTS: show the save configs one by one in the list, and ask user would like
    // to check the next one in the list
    private void showSaveConfigsOneByOne() {
        boolean keepCheckingNext = true;
        int nextConfigIndex = 0;
        while (keepCheckingNext) {
            savedConfigs.get(nextConfigIndex).printOutConfiguration();
            if ((nextConfigIndex + 2) > savedConfigs.size()) {
                System.out.println("\n No more saved configurations");
                break;
            } else {
                while (true) {
                    displayCheckNextOneMenu();
                    String usrInput = getUsrInput();
                    if (usrInput.equals("n")) {
                        keepCheckingNext = false;
                        break;
                    } else if (usrInput.equals("y")) {
                        nextConfigIndex++;
                        break;
                    } else {
                        System.out.println("Invalid input! Input (y/n)!");
                    }
                }
            }
        }
    }

    //EFFECTS: return user input in case of character
    private String getUsrInput() {
        String usrInput = null;
        usrInput = input.next();
        usrInput = usrInput.toLowerCase();
        return usrInput;
    }

    //EFFECTS: display the menu of asking user whether you would like to check next one (if possible)
    private static void displayCheckNextOneMenu() {
        System.out.println("\nWould like check out next saved configuration you had saved?");
        System.out.println("\ty -> Yes, see next one");
        System.out.println("\tn -> No, go back to menu");
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
            usrConfig.printOutConfiguration();
            ConfigEditor needEdit = new ConfigEditor(cg.getCpuBudget(), cg.getGpuBudget(), cg.getMotherboardBudget(),
                    cg.getPsuBudget(), usrConfig, cg.getFormSize(), cg.getPurpose());
            usrConfig = needEdit.changeConfig();
            usrConfig.printOutConfiguration();
            saveOrNot(usrConfig);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, no such configuration! :(");
            System.out.println("Please consider input higher budget or change the size!");
        }
    }


    //MODIFIES: this
    //EFFECTS: helper method to associate with upper method to save user's generated configuration
    private void saveOrNot(Configuration usrConfig) {
        while (true) {
            System.out.println("\nWould like to save this configuration?");
            System.out.println("\ty -> Yes. Save it!");
            System.out.println("\tn -> Nope. Discard it!");
            String saveOrNot = input.next();
            saveOrNot = saveOrNot.toLowerCase();
            if (saveOrNot.equals("y")) {
                savedConfigs.add(usrConfig);
                break;
            } else if (saveOrNot.equals("n")) {
                System.out.println("Configuration does not save!");
                break;
            } else {
                System.out.println("Invalid Input! Input (y/n)!");
            }
        }
    }

    //EFFECTS: show desktop case size options to user and return their choice in terms of FormSize object
    private FormSize getUsrSizeInput() {
        FormSize usrSizeInput = null;
        while (true) {
            System.out.println("\nWhat is the size of your computer?");
            System.out.println("\tSelect from following options:");
            System.out.println("\t s -> small size");
            System.out.println("\t m -> middle size");
            System.out.println("\t l -> large size");
            String sizeInput = input.next();
            sizeInput = sizeInput.toLowerCase();
            if (sizeInput.equals("s")) {
                usrSizeInput = FormSize.ITX;
                break;
            } else if (sizeInput.equals("m")) {
                usrSizeInput = FormSize.ATX;
                break;
            } else if (sizeInput.equals("l")) {
                usrSizeInput = FormSize.EATX;
                break;
            } else {
                System.out.println("Invalid Input! Input (s/m/l)!");
            }
        }
        return usrSizeInput;
    }

    //EFFECTS: show purposes options to user and return their choice in terms of Purpose object
    private Purpose getUsrPurpose() {
        Purpose usrPurpose = null;
        while (true) {
            displayPurposeMenu();
            String purposeInput = input.next();
            purposeInput = purposeInput.toLowerCase();
            if (purposeInput.equals("f")) {
                usrPurpose = Purpose.ENTRY_LEVEL;
                return usrPurpose;
            } else if (purposeInput.equals("g")) {
                usrPurpose = Purpose.GAMING;
                return usrPurpose;
            } else if (purposeInput.equals("p")) {
                usrPurpose = Purpose.PRODUCTIVITY;
                return usrPurpose;
            } else if (purposeInput.equals("w")) {
                usrPurpose = Purpose.WORK_STATION;
                return usrPurpose;
            } else {
                System.out.println("Invalid Input");
            }
        }
    }

    //EFFECTS: display the purpose selection menu to the user
    private static void displayPurposeMenu() {
        System.out.println("What is the primary purpose of your computer?");
        System.out.println("\nSelect from following purpose:");
        System.out.println("\tf -> Office Work (Entry-Level)");
        System.out.println("\tg -> Gaming");
        System.out.println("\tp -> Productivity, Video Editing");
        System.out.println("\tw -> Work Station, 3d modelling");
    }

    //EFFECTS: ask user for their budget they would like to spend for their new desktop
    private int getBudgetInput() {
        System.out.println("What were you planning on spending for your desktop ($CAD)?");
        String usrInput = input.next();

        while (!usrInput.matches("\\d+")) {
            System.out.println("\nInvalid Input!");
            System.out.println("\nWhat were you planning on spending for your desktop ($CAD)?");
            usrInput = input.next();
        }
        return Integer.parseInt(usrInput);

    }

    // EFFECTS: ask user whether they would like to save the saved list of configurations to file
    private void saveListOfConfigurations() {
        while (true) {
            System.out.println("\nWould like to save the list of configurations above to file?");
            System.out.println("\ty -> Yep, save them.");
            System.out.println("\tn -> Nope, discard them.");
            String usrInput = input.next();
            usrInput = usrInput.toLowerCase();
            if (usrInput.equals("y")) {
                processListConfigsSave();
                break;
            } else if (usrInput.equals("n")) {
                System.out.println("\nConfigurations not save!");
                break;
            } else {
                System.out.println("Invalid Input! Please input following above instruction!");
            }
        }
    }

    //EFFECTS: saves the saved list of configurations to file
    private void processListConfigsSave() {
        JSONObject json = new JSONObject();
        try {
            jsonWriter.open();
            jsonWriter.write(savedConfigs);
            jsonWriter.close();
            System.out.println("The printed list of configurations saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads saved list of configurations from file
    private void loadConfigsList() {
        try {
            List<Configuration> loadConfigsList = jsonReader.read();
            System.out.println("\nLoaded saved configuration list from " + JSON_STORE);
            for (Configuration config:loadConfigsList) {
                savedConfigs.add(config);
            }
            System.out.println("The saved configurations has been added to the end of list");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
