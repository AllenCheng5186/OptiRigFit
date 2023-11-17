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
        innerLowerPanel.setLayout(new GridLayout(6, 2));

        JPanel innerUpperPanel = new JPanel();

        innerUpperPanel.add(new JLabel("Configuration",
                new ImageIcon("data/resource/ConfigIcon/configIcon.png"), SwingConstants.CENTER));
        configPanel.add(innerUpperPanel);
        lowerPanelLabelAdd();

        JButton saveButton = new JButton(new SaveButtonAction()); //action
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
        innerLowerPanel.add(new JLabel("$" + String.valueOf(config.getRamBudget())));

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
        //TODO save new customise to queue!
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
        private FormSize size = config.getMotherboard().getFormSize();
        private Purpose purpose = (config.getCpu().hasIntegratedGraphics()) ? Purpose.ENTRY_LEVEL : Purpose.GAMING;
        private JLabel cpuEditLabel;
        private JLabel mbEditLabel;
        private JLabel psuEditLabel;
        private JLabel gpuEditLabel;
        private JFrame popupFrame;
        private List<PcComponent> cpuOptions = new ArrayList<>(filterCpuListWithinInterval());
        private List<String> cpuModels = convertComponentListToStringList(cpuOptions);
        private JComboBox cpuSelection = new JComboBox<>(cpuModels.toArray());
        private List<PcComponent> mbOptions = new ArrayList<>(getMotherboards());
        private List<String> mbModels = convertComponentListToStringList(mbOptions);
        private JComboBox mbSelection = new JComboBox<>(mbModels.toArray());
        private List<PcComponent> psuOptions = new ArrayList<>(getPowerSupplies());
        private List<String> psuModels = convertComponentListToStringList(psuOptions);
        private JComboBox psuSelection = new JComboBox<>(psuModels.toArray());
        private List<PcComponent> gpuOptions = new ArrayList<>(getOptionsGpus());
        private List<String> gpuModels = convertComponentListToStringList(gpuOptions);
        private JComboBox gpuSelection = new JComboBox<>(gpuModels.toArray());

        public CustomizeButtonAction() {
            super("customize the config by yourself!");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            popupFrame = new JFrame("Customize your own configuration!");
            JPanel popupPanel = new JPanel();
            popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.PAGE_AXIS));
            popupFrame.setSize(400, 300);
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

        private JPanel componentSelectionPanel() {
            JPanel innerPanel = new JPanel(new GridLayout(5, 2));

            addSelectionComponent(innerPanel);

            if (config.getGpu() != null) {
                gpuEditLabel = new JLabel(config.getGpu().getModel(),
                        new ImageIcon("data/resource/ConfigIcon/gpu_size.png"), SwingConstants.LEFT);
                innerPanel.add(gpuEditLabel);
                innerPanel.add(gpuSelection);
            } else {
                innerPanel.add(new JLabel("Integrated GPU: Inside CPU",
                        new ImageIcon("data/resource/ConfigIcon/gpu_size.png"), SwingConstants.LEFT));
                innerPanel.add(new JComboBox<>());
            }

            return innerPanel;
        }

        private List<String> convertComponentListToStringList(List<PcComponent> components) {
            List<String> componentModels = new ArrayList<>();
            for (PcComponent component:components) {
                componentModels.add(component.getModel() + "   $" + component.getPrice());
            }
            return componentModels;
        }

        private void addSelectionComponent(JPanel innerPanel) {
            cpuEditLabel = new JLabel(config.getCpu().getModel(),
                    new ImageIcon("data/resource/ConfigIcon/cpu_size.png"),SwingConstants.LEFT);
            innerPanel.add(cpuEditLabel);
            innerPanel.add(cpuSelection);

            mbEditLabel = new JLabel(config.getMotherboard().getModel(),
                    new ImageIcon("data/resource/ConfigIcon/mb_size.png"), SwingConstants.LEFT);
            innerPanel.add(mbEditLabel);
            innerPanel.add(mbSelection);

            psuEditLabel = new JLabel(config.getPowerSupply().getModel(),
                    new ImageIcon("data/resource/ConfigIcon/psu_size.png"), SwingConstants.LEFT);
            innerPanel.add(psuEditLabel);
            innerPanel.add(psuSelection);
        }

        // EFFECTS: filter out same socket cpus, if user's purpose is entry level,
        // make sure cpu has integrated graphics, filter cpus that in budget interval
        private List<Cpu> filterCpuListWithinInterval() {

            CpuList cpuList = new CpuList();
            java.util.List<Cpu> correctSocketCpus = getCorrectSocketCpus(cpuList);
            java.util.List<Cpu> cpusWithIG = new ArrayList<>();
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
            int minWatt = config.getPowerSupply().getWattage();
            List<PowerSupply> compatiblePowerSupplies = powerSuppliesList.filterGreaterWattPSUs(minWatt,
                    powerSupplies);
            List<PowerSupply> correctSizePowerSupplies = powerSuppliesList.filterFormSizePSUs(size,
                    compatiblePowerSupplies);
            return powerSuppliesList.filterPowerSupplyInPriceInterval(
                    correctSizePowerSupplies,
                    (config.getPowerSupply().getPrice() + UPPER_DOWN_BOUNDARY_INTERVAL),
                    (config.getPowerSupply().getPrice() - UPPER_DOWN_BOUNDARY_INTERVAL));
        }

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
            List<Motherboard> withinBudgetMBs = motherboardList.filterMotherboardsInPriceInterval(
                    correctSizeMotherboards, (config.getMotherboard().getPrice() + UPPER_DOWN_BOUNDARY_INTERVAL),
                    (config.getMotherboard().getPrice() - UPPER_DOWN_BOUNDARY_INTERVAL));
            return withinBudgetMBs;
        }

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

    // getter

    public int getConfigId() {
        return configId;
    }
}
