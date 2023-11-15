package ui;

import model.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ConfigInternalUI extends JInternalFrame {
    private Configuration config;
    private List<Configuration> savingList;
    private JPanel innerUpperPanel;
    private JPanel innerLowerPanel;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    private JButton editButton;
    private JButton saveButton;
    private JPanel configPanel;


    public ConfigInternalUI(Configuration config, List<Configuration> savingList) {
        super("Configuration", true, true, false, false);
        this.config = config;
        this.savingList = savingList;
        setUp();

        setContentPane(configPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }


    private class SaveButtonAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!savingList.contains(config)) {
                savingList.add(config);
                JOptionPane.showMessageDialog(null,
                        "The current configuration is saved successfully!",
                        "Saved successfully!",
                        JOptionPane.QUESTION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "The current configuration is already saved",
                        "Save fail!",
                        JOptionPane.QUESTION_MESSAGE);
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
}
