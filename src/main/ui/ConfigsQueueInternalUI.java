package ui;

import model.Configuration;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;

public class ConfigsQueueInternalUI extends JInternalFrame {
    private static final int HEIGHT = 550;
    private static final int WIDTH = 300;
    private static DefaultListModel<String> savedConfigs = new DefaultListModel<>();
    private JList<String> jsavelist;
    private static List<Configuration> savingList = new ArrayList<>();
    private JPanel queuePanel;
    private JButton showButton;
    private Boolean movable = false;
    private static final String JSON_STORE = "./data/configurations.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    public ConfigsQueueInternalUI() {
        super("Configs Saving List", false, false, false, false);
        queuePanel = new JPanel();
        queuePanel.setSize(WIDTH, HEIGHT);
        queuePanel.setVisible(true);
        queuePanel.setLayout(new BoxLayout(queuePanel, BoxLayout.PAGE_AXIS));

        showButton = new JButton(new ShowConfigAction());
        showButton.setText("Show");
        showButton.setVisible(true);

        setLocation(ConfigBuilderAppUI.WIDTH - WIDTH,0);
        setContentPane(queuePanel);
        setSize(WIDTH, HEIGHT);
        setVisible(true);

        JPanel listPanel = setUpInnerListPanel();
        queuePanel.add(listPanel);
        queuePanel.add(showButton);
        showButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private JPanel setUpInnerListPanel() {
        JPanel listPanel = new JPanel();
        listPanel.setSize(WIDTH, HEIGHT - 50);
        listPanel.setLayout(null);
        listPanel.setVisible(true);
        jsavelist = new JList<>(savedConfigs);
        jsavelist.setBounds(15,0, (WIDTH - 20 * 3),HEIGHT - 50);
        listPanel.add(jsavelist);
        return listPanel;
    }

    public void addConfigToQueue(int configId, Configuration newConfig) {
        savedConfigs.addElement("Configuration #" + configId);
        savingList.add(newConfig);

    }

    public Boolean contains(int configId, Configuration newConfig) {
        return savedConfigs.contains(configId) || savingList.contains(newConfig);
    }

    private class ShowConfigAction extends AbstractAction {
        public ShowConfigAction() {
            super("Show selected config on workspace!");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Configuration config = savingList.get(jsavelist.getSelectedIndex());
            ConfigInternalUI showAgainUI = new ConfigInternalUI(jsavelist.getSelectedValue(),
                    config, ConfigsQueueInternalUI.this);
            if (!ConfigBuilderAppUI.getWorkspaceConfigIds().contains(Integer.valueOf(showAgainUI.getConfigId()))) {
                ConfigBuilderAppUI.getDesktop().add(showAgainUI);
                ConfigBuilderAppUI.getWorkspaceConfigIds().add(showAgainUI.getConfigId());
            } else {
                JOptionPane.showConfirmDialog(null,
                        "Configuration already shows up on workspace!",
                        "Duplicate Configuration!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("./data/resource/crossIcon.png"));
            }

        }
    }



    public void saveToFile() {
        JSONObject json = new JSONObject();
        try {
            jsonWriter.open();
            jsonWriter.write(savingList);
            jsonWriter.close();
            JOptionPane.showConfirmDialog(null, "Save to file successful!",
                    "Config Queue Saving",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("./data/resource/checkIcon.png"));
        } catch (FileNotFoundException e) {
            JOptionPane.showConfirmDialog(null, "Unable to Save to file!",
                    "Config Queue Saving fail",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("./data/resource/crossIcon.png"));
        }
    }

    public void loadFileToQueue() {
        try {
            List<Configuration> loadConfigsList = jsonReader.read();
            for (Configuration config:loadConfigsList) {
                ConfigInternalUI configPanel = new ConfigInternalUI(config, this);
                ConfigBuilderAppUI.getWorkspaceConfigIds().add(configPanel.getConfigId());
                ConfigBuilderAppUI.getDesktop().add(configPanel);
                configPanel.toFront();
                addConfigToQueue(configPanel.getConfigId(), config);
            }
            JOptionPane.showConfirmDialog(null,
                    "Loaded saved configuration list from \n" + JSON_STORE,
                    "Load successful!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("./data/resource/checkIcon.png"));
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null,
                    "Unable to read from file: " + JSON_STORE,
                    "Load fail!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("./data/resource/crossIcon.png"));
        }
    }
}
