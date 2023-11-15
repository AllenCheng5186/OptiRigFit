package ui;

import model.Configuration;
import model.ConfigurationGenerator;
import model.Purpose;
import model.component.cpu.Cpu;
import model.component.cpu.CpuMfr;
import model.component.gpu.Gpu;
import model.component.gpu.GpuMfr;
import model.component.motherboard.FormSize;
import model.component.motherboard.Motherboard;
import model.component.psu.PowerSupply;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static model.component.motherboard.FormSize.ATX;
import static model.component.motherboard.Socket.LGA1700;

public class ConfigBuilderAppUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JFrame frame;
    private JDesktopPane desktop;
    private List<Configuration> savedConfigs;

    public ConfigBuilderAppUI() {
        savedConfigs = new ArrayList<>();
        desktop = new JDesktopPane();

        setContentPane(desktop);
        setVisible(true);
        setSize(WIDTH,HEIGHT);
        setTitle("OptiRigFit -Config Builder App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


//        this.setIconImage(imageIcon.getImage());
        addMenu();


    }

    /**
     * Adds menu bar.
     */
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu configMenu = new JMenu("Configuration");
        configMenu.setMnemonic('N');
        addMenuItem(configMenu, new AddNewConfigAction(),
                KeyStroke.getKeyStroke("control N"));
        menuBar.add(configMenu);

        JMenu codeMenu = new JMenu("File");
        codeMenu.setMnemonic('C');
//        addMenuItem(codeMenu, new AddCodeAction(), null);
//        addMenuItem(codeMenu, new RemoveCodeAction(), null);
        menuBar.add(codeMenu);

        JMenu systemMenu = new JMenu("View");
        systemMenu.setMnemonic('y');
//        addMenuItem(systemMenu, new ArmAction(),
//                KeyStroke.getKeyStroke("control A"));
//        addMenuItem(systemMenu, new DisarmAction(),
//                KeyStroke.getKeyStroke("control D"));
        menuBar.add(systemMenu);

        setJMenuBar(menuBar);
    }

    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }

    /**
     * Represents the action to be taken when the user wants to add a new
     * sensor to the system.
     */
    private class AddNewConfigAction extends AbstractAction {

        AddNewConfigAction() {
            super("Build new config");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String usrBudgetInput = getUserBudgetInputPanel();
            while (!usrBudgetInput.matches("\\d+")) {
                JOptionPane.showConfirmDialog(null,
                        "Invalid Input! Please input proper digit for budget!",
                        "Invalid Input", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("./data/resource/ConfigInputIcon/InvalidInputExclamation.png"));
                usrBudgetInput = getUserBudgetInputPanel();
            }
            int usrBudget = Integer.parseInt(usrBudgetInput);

            Purpose usrPurpose = usrPurposeChoice();
            FormSize usrSize = usrSizeChoicePanel();
            ConfigurationGenerator cg = new ConfigurationGenerator(usrBudget, usrSize, usrPurpose);
            try {
                Configuration usrConfig = cg.configGenerate();
                desktop.add(new ConfigInternalUI(usrConfig, savedConfigs));
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null,
                        "Sorry, no such configuration! :( \nPlease consider input higher budget or change the size!",
                        "Generate error!", JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("./data/resource/ConfigInputIcon/ConfigGenerateFailIcon.png"));
            }

        }

        private FormSize usrSizeChoicePanel() {
            String[] sizeOptions = {"large", "middle", "small"};
            var usrSizeChoice = JOptionPane.showOptionDialog(null,
                    "What is the size of your computer?",
                    "Size selection",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("./data/resource/ConfigInputIcon/SizeChoiceIcon.png"),
                    sizeOptions, null);
            if (usrSizeChoice == 2) {
                return FormSize.ITX;
            } else if (usrSizeChoice == 1) {
                return FormSize.ATX;
            } else {
                return FormSize.EATX;
            }
        }

        // EFFECTS: display the purpose selection panel to user and return user's choice on
        // purpose of their new config PC working for
        private Purpose usrPurposeChoice() {
            String[] purposeOptions = {"Work Station", "Productivity", "Gaming", "Office Work"};
            var usrPurposeChoice = JOptionPane.showOptionDialog(null,
                    "What is the primary purpose of your computer?",
                    "What would you want your computer work for?",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("./data/resource/ConfigInputIcon/PurposeChoiceQuestionMarkIcon.png"),
                    purposeOptions, null);

            if (usrPurposeChoice == 3) {
                return Purpose.ENTRY_LEVEL;
            } else if (usrPurposeChoice == 2) {
                return Purpose.GAMING;
            } else if (usrPurposeChoice == 1) {
                return Purpose.PRODUCTIVITY;
            } else {
                return Purpose.WORK_STATION;
            }
        }

        // EFFECTS: display the budget input dialog panel to user and return user's input
        private String getUserBudgetInputPanel() {
            UIManager.put("OptionPane.minimumSize",new Dimension(300,130));
            return (String) JOptionPane.showInputDialog(null,
                    "Enter you budget for your desktop ($CAD)",
                    "Desktop budget",
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("./data/resource/ConfigInputIcon/budgetIcon.png"), null, 0);
        }
    }



    public static void main(String[] args) {
        new ConfigBuilderAppUI();
    }
}
