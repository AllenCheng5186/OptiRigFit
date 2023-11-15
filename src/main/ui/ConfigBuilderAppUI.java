package ui;

import model.Configuration;
import model.ConfigurationGenerator;
import model.Purpose;
import model.component.motherboard.FormSize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigBuilderAppUI extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int INTERNAL_WINDOWS_NEXT_GAP = 20;
    private static int configInternalWindowX = 0;
    private static int configInternalWindowY = 0;
    private static JDesktopPane desktop;
    private final ConfigsQueueInternalUI savingQueue = new ConfigsQueueInternalUI();
    private static List<Integer> workspaceConfigIds = new ArrayList<>();

    public ConfigBuilderAppUI() {
        desktop = new JDesktopPane();
        desktop.setDesktopManager(new ProxyDesktopManager(desktop.getDesktopManager()));

        setContentPane(desktop);
        setVisible(true);
        setSize(WIDTH,HEIGHT);
        setTitle("OptiRigFit -Config Builder App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        addMenu();
        desktop.add(new ConfigsQueueInternalUI());


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

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('C');
        addMenuItem(fileMenu, new FileSavingAction(),
                KeyStroke.getKeyStroke("control S"));
        addMenuItem(fileMenu, new FileLoadingAction(),
                KeyStroke.getKeyStroke("control O"));
        menuBar.add(fileMenu);

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
            int usrBudget = Integer.parseInt(usrBudgetInput);
            Purpose usrPurpose = usrPurposeChoice();
            FormSize usrSize = usrSizeChoicePanel();

            ConfigurationGenerator cg = new ConfigurationGenerator(usrBudget, usrSize, usrPurpose);
            try {
                Configuration usrConfig = cg.configGenerate();
                ConfigInternalUI generatedConfigUI = new ConfigInternalUI(usrConfig, savingQueue);
                desktop.add(generatedConfigUI);
                workspaceConfigIds.add(generatedConfigUI.getConfigId());
                generatedConfigUI.toFront();
                configInternalWindowX += INTERNAL_WINDOWS_NEXT_GAP;
                configInternalWindowY += INTERNAL_WINDOWS_NEXT_GAP;
                generatedConfigUI.setLocation(configInternalWindowX, configInternalWindowY);
                configInternalWindowX += INTERNAL_WINDOWS_NEXT_GAP;
                configInternalWindowY += INTERNAL_WINDOWS_NEXT_GAP;
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null,
                        "Sorry, no such configuration! :( \nPlease consider input higher budget or change the size!",
                        "Generate error!", JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("./data/resource/ConfigInputIcon/ConfigGenerateFailIcon.png"));
            }
        }

        // EFFECTS: display the size selection panel to user and return user's choice on
        // size of their new config PC
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
        // purpose of their new config PC
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
            String usrBudgetInput = (String) JOptionPane.showInputDialog(null,
                    "Enter you budget for your desktop ($CAD)",
                    "Desktop budget",
                    JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("./data/resource/ConfigInputIcon/budgetIcon.png"), null, 0);
            while (!usrBudgetInput.matches("\\d+")) {
                JOptionPane.showConfirmDialog(null,
                        "Invalid Input! Please input proper digit for budget!",
                        "Invalid Input", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("./data/resource/ConfigInputIcon/InvalidInputExclamation.png"));
                usrBudgetInput = getUserBudgetInputPanel();
            }
            return usrBudgetInput;
        }
    }

    private class FileSavingAction extends AbstractAction {

        public FileSavingAction() {
            super("Save List to file");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            savingQueue.saveToFile();
        }
    }

    private class FileLoadingAction extends AbstractAction {

        public FileLoadingAction() {
            super("Load file to workspace");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            savingQueue.loadFileToQueue();
        }
    }

    private class ProxyDesktopManager implements DesktopManager {

        private final DesktopManager delegate;

        public ProxyDesktopManager(DesktopManager delegate) {
            this.delegate = Objects.requireNonNull(delegate);
        }

        // Check whether frame is moveable
        private boolean checkFrameMovable(JComponent frame) {
            if (frame instanceof JInternalFrame) {
                return !frame.getClass().equals(getSavingQueue().getClass());
            }
            return true;
        }

        @Override
        public void beginDraggingFrame(JComponent f) {
            if (checkFrameMovable(f)) {
                delegate.beginDraggingFrame(f);
            }
        }

        @Override
        public void dragFrame(JComponent f, int newX, int newY) {
            if (checkFrameMovable(f)) {
                delegate.dragFrame(f, newX, newY);
            }
        }

        @Override
        public void endDraggingFrame(JComponent f) {
            if (checkFrameMovable(f)) {
                delegate.endDraggingFrame(f);
            }
        }

        @Override
        public void beginResizingFrame(JComponent f, int direction) {

        }

        @Override
        public void openFrame(JInternalFrame f) {
            delegate.openFrame(f);
        }

        @Override
        public void closeFrame(JInternalFrame f) {
            delegate.closeFrame(f);
        }

        @Override
        public void maximizeFrame(JInternalFrame f) {
            delegate.maximizeFrame(f);
        }

        @Override
        public void minimizeFrame(JInternalFrame f) {
            delegate.minimizeFrame(f);
        }

        @Override
        public void iconifyFrame(JInternalFrame f) {
            delegate.iconifyFrame(f);
        }

        @Override
        public void deiconifyFrame(JInternalFrame f) {
            delegate.deiconifyFrame(f);
        }

        @Override
        public void activateFrame(JInternalFrame f) {
            delegate.deactivateFrame(f);
        }

        @Override
        public void deactivateFrame(JInternalFrame f) {
            delegate.deactivateFrame(f);
        }

        @Override
        public void resizeFrame(JComponent f, int newX, int newY, int newWidth, int newHeight) {
            delegate.resizeFrame(f, newX, newY, newWidth, newHeight);
        }

        @Override
        public void endResizingFrame(JComponent f) {

        }

        @Override
        public void setBoundsForFrame(JComponent f, int newX, int newY, int newWidth, int newHeight) {

        }

        // IMPORTANT: simply delegate all another methods like openFrame or
        // resizeFrame
    }

    public static void configWindowLocationReset() {
        configInternalWindowX -= INTERNAL_WINDOWS_NEXT_GAP;
        configInternalWindowY -= INTERNAL_WINDOWS_NEXT_GAP;
    }

    //getter
    public static JDesktopPane getDesktop() {
        return desktop;
    }

    public static List<Integer> getWorkspaceConfigIds() {
        return workspaceConfigIds;
    }

    public ConfigsQueueInternalUI getSavingQueue() {
        return savingQueue;
    }

    public static int getConfigInternalWindowX() {
        configInternalWindowX += INTERNAL_WINDOWS_NEXT_GAP;
        return configInternalWindowX;
    }

    public static int getConfigInternalWindowY() {
        configInternalWindowY += INTERNAL_WINDOWS_NEXT_GAP;
        return configInternalWindowY;
    }

    public static void main(String[] args) {
        new ConfigBuilderAppUI();
    }
}
