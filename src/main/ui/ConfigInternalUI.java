package ui;

import model.Configuration;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ConfigInternalUI extends JInternalFrame {
    private static int configNum = 1;
    private Configuration config;
    private JPanel innerUpperPanel;
    private JPanel innerLowerPanel;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    private JButton editButton;
    private JButton saveButton;
    private JPanel configPanel;
    private ConfigsQueueInternalUI savingPanel;
    private int configId;


    public ConfigInternalUI(Configuration config, ConfigsQueueInternalUI savingPanel) {
        super("Configuration #" + configNum, true, true, false, false);
        this.configId = configNum;
        this.config = config;
        this.savingPanel = savingPanel;
        setUp();

        setContentPane(configPanel);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        addInternalFrameListener(new CloseWindowsEvent());

        configNum++;
    }


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

    private class CloseWindowsEvent extends InternalFrameAdapter {
        @Override
        public void internalFrameClosing(InternalFrameEvent e) {
            ConfigBuilderAppUI.getWorkspaceConfigIds().remove(Integer.valueOf(configId));
        }
    }


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



    private void setUp() {
        configPanel = new JPanel();
        configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.PAGE_AXIS));
        innerLowerPanel = new JPanel();
        innerLowerPanel.setLayout(new GridLayout(6, 2));

        innerUpperPanel = new JPanel();

        innerUpperPanel.add(new JLabel("Configuration", new ImageIcon("data/resource/ConfigIcon/configIcon.png"), 0));
        configPanel.add(innerUpperPanel);
        lowerPanelLabelAdd();

        saveButton = new JButton(new SaveButtonAction()); //action
        saveButton.setText("save");
        innerLowerPanel.add(saveButton);

        editButton = new JButton("Customize");
        innerLowerPanel.add(editButton);

        configPanel.add(innerLowerPanel);
    }

    private void lowerPanelLabelAdd() {
        innerLowerPanel.add(new JLabel("CPU", new ImageIcon("data/resource/ConfigIcon/cpu_size.png"), 2));
        innerLowerPanel.add(new JLabel(config.getCpu().getModel()));

        innerLowerPanel.add(new JLabel("RAM", new ImageIcon("data/resource/ConfigIcon/ram_size.png"), 2));
        innerLowerPanel.add(new JLabel(String.valueOf(config.getRamBudget())));

        innerLowerPanel.add(new JLabel("Motherboard", new ImageIcon("data/resource/ConfigIcon/mb_size.png"), 2));
        innerLowerPanel.add(new JLabel(config.getMotherboard().getName()));

        innerLowerPanel.add(new JLabel("Power Supply", new ImageIcon("data/resource/ConfigIcon/psu_size.png"), 2));
        innerLowerPanel.add(new JLabel(config.getPowerSupply().getModel()));

        innerLowerPanel.add(new JLabel("Discrete GPU", new ImageIcon("data/resource/ConfigIcon/gpu_size.png"), 2));
        JLabel gpuModel;
        if (config.getGpu() != null) {
            gpuModel = new JLabel(config.getGpu().getModel());
        } else {
            gpuModel = new JLabel("Integrated GPU");
        }
        innerLowerPanel.add(gpuModel);
    }


    // getter
    public static int getConfigNum() {
        return configNum;
    }

    public int getConfigId() {
        return configId;
    }
}
