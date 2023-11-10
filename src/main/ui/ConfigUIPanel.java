package ui;

import model.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConfigUIPanel extends JInternalFrame {
    private Configuration config;
    private static final int  WIDTH = 400;
    private static final int  HEIGHT = 320;
    private JLabel cpuLabel;
    private JLabel configTitle;
    private JLabel ramLabel;
    private JLabel motherboardLabel;
    private JLabel psuLabel;
    private JLabel gpuLabel;
    private JButton editButton;
    private JButton saveButton;
    private JLabel cpuModel;
    private JLabel ramModel;
    private JLabel motherboardModel;
    private JLabel psuModel;
    private JLabel gpuModel;
    private JPanel configPanel;

    public ConfigUIPanel(Configuration config, List<Configuration> savingList) {
        super("Configuration", true, true, false, false);
        this.config = config;

        setUpLabelContent(config);
        setContentPane(configPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savingList.add(config);
                JOptionPane.showMessageDialog(null,
                        "The current configuration is saved successfully!",
                        "Saved successfully!",
                        JOptionPane.QUESTION_MESSAGE);
            }
        });
    }

    private void setUpLabelContent(Configuration config) {
        configTitle.setIcon(new ImageIcon("data/resource/ConfigIcon/configIcon.png"));
        cpuLabel.setIcon(new ImageIcon("data/resource/ConfigIcon/cpu_size.png"));
        ramLabel.setIcon(new ImageIcon("data/resource/ConfigIcon/ram_size.png"));
        psuLabel.setIcon(new ImageIcon("data/resource/ConfigIcon/psu_size.png"));
        motherboardLabel.setIcon(new ImageIcon("data/resource/ConfigIcon/mb_size.png"));
        gpuLabel.setIcon(new ImageIcon("data/resource/ConfigIcon/gpu_size.png"));

        cpuModel.setText(config.getCpu().getModel());
        ramModel.setText(String.valueOf(config.getRamBudget()));
        motherboardModel.setText(config.getMotherboard().getName());
        if (config.getGpu() != null) {
            gpuModel.setText(config.getGpu().getModel());
        } else {
            gpuModel.setText("Integrated GPU");
        }
        psuModel.setText(config.getPowerSupply().getModel());
    }
}
