package ui;

import model.Configuration;
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

/**
 * Represent user interface for configs saving queue
 */
public class ConfigsQueueInternalUI extends JInternalFrame {
    private static final int HEIGHT = 550;
    private static final int WIDTH = 300;
    private static final DefaultListModel<String> savedConfigs = new DefaultListModel<>();
    private JList<String> jsavelist;
    private static final List<Configuration> savingList = new ArrayList<>();
    private static final String JSON_STORE = "./data/configurations.json";
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);

    /**
     * Constructor set up user interface for the only one main queue
     */
    public ConfigsQueueInternalUI() {
        super("Configs Saving List", false, false, false, false);
        JPanel queuePanel = new JPanel();
        queuePanel.setSize(WIDTH, HEIGHT);
        queuePanel.setVisible(true);
        queuePanel.setLayout(new BoxLayout(queuePanel, BoxLayout.PAGE_AXIS));

        JButton showButton = new JButton(new ShowConfigAction());
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

    /**
     * Helper to set up inner JList panel
     * @return a panel with a JList for visualize saving queue
     */
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

    /**
     * Add given configuration and ID to the queue
     * @param configId ID associate with config
     * @param newConfig the given config
     */
    public void addConfigToQueue(int configId, Configuration newConfig) {
        savedConfigs.addElement("Configuration #" + configId);
        savingList.add(newConfig);

    }

    // EFFECTS: return true if there is no same configID or same config in the queue,
    // otherwise return false
    public Boolean contains(int configId, Configuration newConfig) {
        return savedConfigs.contains(configId) || savingList.contains(newConfig);
    }

    /**
     * Represent an action to be token when user want to add closed config
     * to the workspace (desktop panel) again
     */
    private class ShowConfigAction extends AbstractAction {
        public ShowConfigAction() {
            super("Show selected config on workspace!");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Configuration config = savingList.get(jsavelist.getSelectedIndex());
            ConfigInternalUI showAgainUI = new ConfigInternalUI(jsavelist.getSelectedValue(),
                    config, ConfigsQueueInternalUI.this);
            if (!ConfigBuilderAppUI.getWorkspaceConfigIds().contains(showAgainUI.getConfigId())) {
                ConfigBuilderAppUI.getDesktop().add(showAgainUI);
                showAgainUI.setLocation(ConfigBuilderAppUI.getConfigInternalWindowX(),
                        ConfigBuilderAppUI.getConfigInternalWindowY());
                showAgainUI.toFront();
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

    // EFFECTS: save the list of configs (saving queue) to the file
    public void saveToFile() {
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

    // EFFECTS: load the list of configs from file to queue and workspace (desktop panel)
    public void loadFileToQueue() {
        try {
            List<Configuration> loadConfigsList = jsonReader.read();
            addLoadedConfigsToWorkspace(loadConfigsList);
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null,
                    "Unable to read from file: " + JSON_STORE,
                    "Load fail!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    new ImageIcon("./data/resource/crossIcon.png"));
        }
    }

    /**
     * Helper to add loaded list of configs to the queue and workspace (desktop panel)
     * check whether same config was added to the both queue and workspace to
     * avoid load same file multiple times
     * @param loadConfigsList loaded list of configs
     */
    private void addLoadedConfigsToWorkspace(List<Configuration> loadConfigsList) {
        for (Configuration config: loadConfigsList) {
            if (!ConfigBuilderAppUI.getOpenedConfigs().contains(config)) {
                ConfigInternalUI configPanel = new ConfigInternalUI(config, this);
                ConfigBuilderAppUI.getWorkspaceConfigIds().add(configPanel.getConfigId());
                ConfigBuilderAppUI.getDesktop().add(configPanel);
                configPanel.toFront();
                configPanel.setLocation(ConfigBuilderAppUI.getConfigInternalWindowX(),
                        ConfigBuilderAppUI.getConfigInternalWindowY());
                addConfigToQueue(configPanel.getConfigId(), config);
            } else {
                JOptionPane.showConfirmDialog(null,
                        "Already loaded to the workspace!",
                        "Load fail!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        new ImageIcon("./data/resource/crossIcon.png"));
                return;
            }
        }
        JOptionPane.showConfirmDialog(null,
                "Loaded saved configuration list from \n" + JSON_STORE,
                "Load successful!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("./data/resource/checkIcon.png"));
    }
}
