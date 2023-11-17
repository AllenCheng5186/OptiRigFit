package ui;

import model.Configuration;
import model.Purpose;
import model.component.PcComponent;
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

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static model.Purpose.ENTRY_LEVEL;

/**
 *  Represent user interface for configurations
 */
public class ConfigInternalUI extends JInternalFrame {
    private static final int UPPER_DOWN_BOUNDARY_INTERVAL = 400;
    private static int configNum = 1;
    private final Configuration config;
    private JPanel innerLowerPanel;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    private JPanel configPanel;
    private final ConfigsQueueInternalUI savingPanel;
    private final int configId;
    private JLabel cpuDisplayLabel;
    private JLabel mbDisplayLabel;
    private JLabel psuDisplayLabel;
    private JLabel gpuDisplayLabel;
    private final DecimalFormat df = new DecimalFormat("0.00");
    private JLabel aggregateDisplayLabel;

    /**
     * Constructor set up user interface for a given configuration added to workspace (desktop panel) for the first time
     * @param config the configuration
     * @param savingPanel the config saving queue panel
     */
    public ConfigInternalUI(Configuration config, ConfigsQueueInternalUI savingPanel) {
        super("Configuration #" + configNum, true, true, false, false);
        this.configId = configNum;
        this.config = config;
        this.savingPanel = savingPanel;
        ConfigBuilderAppUI.getOpenedConfigs().add(config);
        setUp();

        setContentPane(configPanel);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        addInternalFrameListener(new CloseWindowsEvent());

        configNum++;
    }

    /**
     * Constructor set up user interface for a given configuration which added workspace (desktop panel) again
     * @param configTitle the title associate with config at first time added
     * @param config the configuration
     * @param savingPanel the config saving queue panel
     */
    public ConfigInternalUI(String configTitle, Configuration config, ConfigsQueueInternalUI savingPanel) {
        super(configTitle, true, true, false, false);
        this.configId = Integer.parseInt(configTitle.substring(configTitle.length() - 1));
        this.config = config;
        this.savingPanel = savingPanel;
        setUp();

        setContentPane(configPanel);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        addInternalFrameListener(new CloseWindowsEvent());
    }

    /**
     * Helper to set up visual configuration window
     */
    private void setUp() {
        configPanel = new JPanel();
        configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.PAGE_AXIS));
        innerLowerPanel = new JPanel();
        innerLowerPanel.setLayout(new GridLayout(7, 2));

        JPanel innerUpperPanel = new JPanel();

        innerUpperPanel.add(new JLabel("Configuration",
                new ImageIcon("data/resource/ConfigIcon/configIcon.png"), SwingConstants.CENTER));
        configPanel.add(innerUpperPanel);
        lowerPanelLabelAdd();

        innerLowerPanel.add(new JLabel("Aggregate", SwingConstants.CENTER));
        double aggregate = getConfigAggregate();
        aggregateDisplayLabel = new JLabel(" $" + aggregate, SwingConstants.CENTER);
        innerLowerPanel.add(aggregateDisplayLabel);

        JButton saveButton = new JButton(new SaveButtonAction());
        saveButton.setText("save");
        innerLowerPanel.add(saveButton);

        JButton editButton = new JButton(new CustomizeButtonAction());
        editButton.setText("Customize");
        innerLowerPanel.add(editButton);


        configPanel.add(innerLowerPanel);
    }

    /**
     * Helper to set up visual configuration window
     */
    private void lowerPanelLabelAdd() {
        innerLowerPanel.add(new JLabel("CPU",
                new ImageIcon("data/resource/ConfigIcon/cpu_size.png"), SwingConstants.LEFT));
        cpuDisplayLabel = new JLabel(config.getCpu().getModel() + "   $" + config.getCpu().getPrice());
        innerLowerPanel.add(cpuDisplayLabel);

        innerLowerPanel.add(new JLabel("RAM",
                new ImageIcon("data/resource/ConfigIcon/ram_size.png"), SwingConstants.LEFT));
        innerLowerPanel.add(new JLabel("$" + (config.getRamBudget())));

        innerLowerPanel.add(new JLabel("Motherboard",
                new ImageIcon("data/resource/ConfigIcon/mb_size.png"), SwingConstants.LEFT));
        mbDisplayLabel = new JLabel(config.getMotherboard().getModel() + "   $" + config.getMotherboard().getPrice());
        innerLowerPanel.add(mbDisplayLabel);

        innerLowerPanel.add(new JLabel("Power Supply",
                new ImageIcon("data/resource/ConfigIcon/psu_size.png"), SwingConstants.LEFT));
        psuDisplayLabel = new JLabel(config.getPowerSupply().getModel() + "   $" + config.getPowerSupply().getPrice());
        innerLowerPanel.add(psuDisplayLabel);

        innerLowerPanel.add(new JLabel("Discrete GPU",
                new ImageIcon("data/resource/ConfigIcon/gpu_size.png"), SwingConstants.LEFT));
        if (config.getGpu() != null) {
            gpuDisplayLabel = new JLabel(config.getGpu().getModel() + "   $" + config.getGpu().getPrice());
        } else {
            gpuDisplayLabel = new JLabel("Integrated GPU");
        }
        innerLowerPanel.add(gpuDisplayLabel);
    }

    /**
     * Represents action to be token when user close the internal frame of config
     */
    private class CloseWindowsEvent extends InternalFrameAdapter {
        @Override
        public void internalFrameClosing(InternalFrameEvent e) {
            ConfigBuilderAppUI.getWorkspaceConfigIds().remove(Integer.valueOf(configId));
            ConfigBuilderAppUI.getOpenedConfigs().remove(config);
            ConfigBuilderAppUI.configWindowLocationReset();
        }
    }

    /**
     * Represents action to be token when user click the save button
     * to save the current configuration to the saving queue
     */
    private class SaveButtonAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!savingPanel.contains(configId, config)) {
                savingPanel.addConfigToQueue(configId, config);
                JOptionPane.showConfirmDialog(null,
                        "The current configuration is saved successfully!",
                        "Saved successfully!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("./data/resource/checkIcon.png"));
            } else {
                JOptionPane.showConfirmDialog(null,
                        "The current configuration is already saved",
                        "Save fail!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("./data/resource/crossIcon.png"));
            }
        }
    }

    /**
     * Represents action to be token when user click the customize button
     * to change the components of configuration
     */
    private class CustomizeButtonAction extends AbstractAction {
        private final FormSize size = config.getMotherboard().getFormSize();
        private final Purpose purpose = (config.getCpu().hasIntegratedGraphic()) ? Purpose.ENTRY_LEVEL : Purpose.GAMING;
        private JFrame popupFrame;
        private final List<PcComponent> cpuOptions = new ArrayList<>(filterCpuListWithinInterval());
        private final JComboBox<String> cpuSelection;
        private final List<PcComponent> mbOptions = new ArrayList<>(getMotherboards());
        private final JComboBox<String> mbSelection;
        private final List<PcComponent> psuOptions = new ArrayList<>(getPowerSupplies());
        private final JComboBox<String> psuSelection;
        private final List<PcComponent> gpuOptions = new ArrayList<>(getOptionsGpus());
        private final JComboBox<String> gpuSelection;
        private JLabel aggregateLabel;

        public CustomizeButtonAction() {
            super("customize the config by yourself!");
            this.cpuSelection = new JComboBox<>();
            for (String cpuModel: convertComponentListToStringList(cpuOptions)) {
                cpuSelection.addItem(cpuModel);
            }
            mbSelection = new JComboBox<>();
            for (String mbModel: convertComponentListToStringList(mbOptions)) {
                mbSelection.addItem(mbModel);
            }
            psuSelection = new JComboBox<>();
            for (String psuModel:convertComponentListToStringList(psuOptions)) {
                psuSelection.addItem(psuModel);
            }
            gpuSelection = new JComboBox<>();
            for (String gpuModel:convertComponentListToStringList(gpuOptions)) {
                gpuSelection.addItem(gpuModel);
            }
            cpuSelection.addActionListener(new ChangedAction(config.getCpu(), cpuSelection, cpuOptions));
            mbSelection.addActionListener(new ChangedAction(config.getMotherboard(), mbSelection, mbOptions));
            psuSelection.addActionListener(new ChangedAction(config.getPowerSupply(), psuSelection, psuOptions));
            gpuSelection.addActionListener(new ChangedAction(config.getGpu(), gpuSelection, gpuOptions));
        }

        /**
         * Represents the action to be token when user choose other component from the list
         */
        class ChangedAction implements ActionListener {
            private final PcComponent oldComponent;
            private final JComboBox<String> componentSelection;
            private final List<PcComponent> components;

            public ChangedAction(PcComponent oldComponent, JComboBox<String> componentSelection,
                                 List<PcComponent> components) {
                this.oldComponent = oldComponent;
                this.componentSelection = componentSelection;
                this.components = components;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                double newAggregate = getConfigAggregate() - oldComponent.getPrice();
                newAggregate += components.get(componentSelection.getSelectedIndex()).getPrice();
                aggregateLabel.setText(" $" + df.format(newAggregate));
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            popupFrame = new JFrame("Customize your own configuration!");
            JPanel popupPanel = new JPanel();
            popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.PAGE_AXIS));
            popupFrame.setSize(600, 300);
            popupFrame.setLocation(ConfigBuilderAppUI.WIDTH / 2, 200);
            popupFrame.setVisible(true);
            popupFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
            popupFrame.setResizable(true);
            popupFrame.setContentPane(popupPanel);
            popupFrame.toFront();

            JPanel questionMsgPanel = new JPanel();
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setText("Which component would you like to change?");
            questionMsgPanel.add(label);
            popupPanel.add(questionMsgPanel);
            popupPanel.add(componentSelectionPanel());

            JButton saveCustomize = new JButton(new ReplaceButtonAction());
            saveCustomize.setText("Save");
            popupPanel.add(saveCustomize);
        }

        /**
         * Helper to add component selection windows for customize function
         * @return main panel of component customize
         */
        private JPanel componentSelectionPanel() {
            JPanel innerPanel = new JPanel(new GridLayout(6, 2));

            addSelectionComponent(innerPanel);

            if (config.getGpu() != null) {
                innerPanel.add(new JLabel(config.getGpu().getModel() + config.getGpu().getPrice(),
                        new ImageIcon("data/resource/ConfigIcon/gpu_size.png"), SwingConstants.LEFT));
                innerPanel.add(gpuSelection);
            } else {
                innerPanel.add(new JLabel("Integrated GPU: Inside CPU",
                        new ImageIcon("data/resource/ConfigIcon/gpu_size.png"), SwingConstants.LEFT));
                innerPanel.add(new JComboBox<>());
            }

            innerPanel.add(new JLabel("Aggregate", SwingConstants.CENTER));
            double aggregate = getConfigAggregate();
            aggregateLabel = new JLabel(" $" + df.format(aggregate), SwingConstants.CENTER);
            innerPanel.add(aggregateLabel);

            return innerPanel;
        }

        // EFFECTS: return a list of string represent the model of each element of component
        private List<String> convertComponentListToStringList(List<PcComponent> components) {
            List<String> componentModels = new ArrayList<>();
            for (PcComponent component:components) {
                componentModels.add(component.getModel() + "   $" + component.getPrice());
            }
            return componentModels;
        }

        /**
         * Helper to visualize configuration in the customize windows
         * @param innerPanel the customize content panel
         */
        private void addSelectionComponent(JPanel innerPanel) {
            innerPanel.add(new JLabel(config.getCpu().getModel() + " $" + config.getCpu().getPrice(),
                    new ImageIcon("data/resource/ConfigIcon/cpu_size.png"), SwingConstants.LEFT));
            innerPanel.add(cpuSelection);

            innerPanel.add(new JLabel(config.getMotherboard().getModel() + " $" + config.getMotherboard().getPrice(),
                    new ImageIcon("data/resource/ConfigIcon/mb_size.png"), SwingConstants.LEFT));
            innerPanel.add(mbSelection);

            innerPanel.add(new JLabel(config.getPowerSupply().getModel() + " $" + config.getPowerSupply().getPrice(),
                    new ImageIcon("data/resource/ConfigIcon/psu_size.png"), SwingConstants.LEFT));
            innerPanel.add(psuSelection);
        }

        // EFFECTS: filter out same socket cpus, if user's purpose is entry level,
        // make sure cpu has integrated graphics, filter cpus that in budget interval
        private List<Cpu> filterCpuListWithinInterval() {

            CpuList cpuList = new CpuList();
            List<Cpu> correctSocketCpus = getCorrectSocketCpus(cpuList);
            List<Cpu> cpusWithIG;
            if (purpose == ENTRY_LEVEL) {
                cpusWithIG = cpuList.returnCpusHasIG(correctSocketCpus);
            } else {
                cpusWithIG = correctSocketCpus;
            }
            java.util.List<Cpu> withinBudgetCpus = cpuList.filterCPUsPriceInterval(cpusWithIG,
                    (config.getCpu().getPrice() + UPPER_DOWN_BOUNDARY_INTERVAL),
                    (config.getCpu().getPrice() - UPPER_DOWN_BOUNDARY_INTERVAL));
            Collections.sort(withinBudgetCpus);
            return withinBudgetCpus;
        }

        // EFFECTS: find the socket of cpu in the old config, filter correct socket cpus
        private List<Cpu> getCorrectSocketCpus(CpuList cpuList) {
            java.util.List<Cpu> cpus = cpuList.getListAllCpu();
            Socket mbSocket = config.getMotherboard().getSocket();
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

        //EFFECTS: return a list of power supplies that watt greater than min watt the user's desktop required
        // and price within user's budget
        private List<PowerSupply> getPowerSupplies() {
            PowerSuppliesList powerSuppliesList = new PowerSuppliesList();
            List<PowerSupply> powerSupplies = powerSuppliesList.getListAllPowerSupply();
            int minWatt = config.getCpu().getBasePower() + 150 + 100;
            if (config.getGpu() != null) {
                minWatt += config.getGpu().getBasePower();
            }
            List<PowerSupply> compatiblePowerSupplies = powerSuppliesList.filterGreaterWattPSUs(minWatt,
                    powerSupplies);
            List<PowerSupply> correctSizePowerSupplies = getCorrectSizePowerSupplies(compatiblePowerSupplies);
            return powerSuppliesList.filterPowerSupplyInPriceInterval(
                    correctSizePowerSupplies,
                    (config.getPowerSupply().getPrice() + UPPER_DOWN_BOUNDARY_INTERVAL),
                    (config.getPowerSupply().getPrice() - UPPER_DOWN_BOUNDARY_INTERVAL));
        }

        // EFFECTS: return a list of power supplies which size is compatible with case size
        private List<PowerSupply> getCorrectSizePowerSupplies(List<PowerSupply> compatiblePowerSupplies) {
            List<PowerSupply> correctSizePowerSupplies = new ArrayList<>();
            if (size == FormSize.ITX || size == FormSize.MATX) {
                for (PowerSupply psu: compatiblePowerSupplies) {
                    if (psu.getFormSize() == FormSize.ITX) {
                        correctSizePowerSupplies.add(psu);
                    }
                }
            } else if (size == FormSize.ATX) {
                for (PowerSupply psu: compatiblePowerSupplies) {
                    if (psu.getFormSize() != FormSize.EATX) {
                        correctSizePowerSupplies.add(psu);
                    }
                }
            } else {
                correctSizePowerSupplies = new ArrayList<>(compatiblePowerSupplies);
            }
            return correctSizePowerSupplies;
        }

        /**
         * Represents the action to be token when user want to save their change on configuration
         */
        private class ReplaceButtonAction extends AbstractAction {

            public ReplaceButtonAction() {
                super("Replace the component with current selection");
            }


            @Override
            public void actionPerformed(ActionEvent e) {
                Cpu newCpu = (Cpu) cpuOptions.get(cpuSelection.getSelectedIndex());
                Motherboard newMb = (Motherboard) mbOptions.get(mbSelection.getSelectedIndex());
                PowerSupply newPsu = (PowerSupply) psuOptions.get(psuSelection.getSelectedIndex());
                if (config.getGpu() != null) {
                    Gpu newGpu = (Gpu) gpuOptions.get(gpuSelection.getSelectedIndex());
                    config.setGpu(newGpu);
                    gpuDisplayLabel.setText(newGpu.getModel());
                }
                config.setCpu(newCpu);
                config.setMotherboard(newMb);
                config.setPowerSupply(newPsu);
                cpuDisplayLabel.setText(newCpu.getModel());
                mbDisplayLabel.setText(newMb.getModel());
                psuDisplayLabel.setText(newPsu.getModel());
                popupFrame.setVisible(false);
                aggregateLabel.setText(" $" + df.format(getConfigAggregate()));
                aggregateDisplayLabel.setText(" $" + df.format(getConfigAggregate()));
            }
        }

        //EFFECTS: return a list of motherboards that compatible with correct cpu
        // socket, user's preference about computer size, and within reasonable price interval
        private List<Motherboard> getMotherboards() {
            MotherboardList motherboardList = new MotherboardList();
            List<Motherboard> motherboards = motherboardList.getListAllMotherboard();
            Socket socket = Socket.LGA1700;
            if (config.getCpu().getCpuMfr() == CpuMfr.AMD) {
                socket = Socket.AM5;
            }
            List<Motherboard> compatibleMotherboards = motherboardList.filterRightSocketMotherboard(motherboards,
                    socket);
            List<Motherboard> correctSizeMotherboards = motherboardList.filterRightFormSizeMotherboards(
                    compatibleMotherboards, size);
            return motherboardList.filterMotherboardsInPriceInterval(
                    correctSizeMotherboards, (config.getMotherboard().getPrice() + UPPER_DOWN_BOUNDARY_INTERVAL),
                    (config.getMotherboard().getPrice() - UPPER_DOWN_BOUNDARY_INTERVAL));
        }

        // EFFECTS: return a list of gpus that within reasonable price interval
        private List<Gpu> getOptionsGpus() {
            if (config.getGpu() != null) {
                GpuList gpuList = new GpuList();
                List<Gpu> gpus = gpuList.getListAllGpu();
                List<Gpu> withinBudgetGpus =  gpuList.filterGpusInPriceInterval(gpus,
                        (config.getGpu().getPrice() + UPPER_DOWN_BOUNDARY_INTERVAL),
                        (config.getGpu().getPrice() - UPPER_DOWN_BOUNDARY_INTERVAL));
                return new ArrayList<>(withinBudgetGpus);
            } else {
                return new ArrayList<>();
            }
        }

    }


    // EFFECTS: calculate the total cost for the current configuration
    private double getConfigAggregate() {
        double aggregate = config.getCpu().getPrice() + config.getRamBudget() + config.getMotherboard().getPrice();
        aggregate += config.getPowerSupply().getPrice();
        aggregate += (config.getGpu() == null) ? 0 : config.getGpu().getPrice();
        return aggregate;
    }

    // getter

    public int getConfigId() {
        return configId;
    }
}
