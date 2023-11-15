package ui;

import model.Configuration;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigsQueueInternalUI extends JInternalFrame {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 300;
    private static DefaultListModel<String> savedConfigs = new DefaultListModel<>();
    private static List<Configuration> savingList = new ArrayList<>();
    private JPanel queuePanel;
    private static final String JSON_STORE = "./data/configurations.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    public ConfigsQueueInternalUI() {
        super("Configs Saving List", true, false, false, false);
        queuePanel = new JPanel();

        setLocation(ConfigBuilderAppUI.WIDTH - WIDTH,0);
        setContentPane(queuePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JList<String> list = new JList<>(savedConfigs);
        list.setBounds(15,0, (WIDTH - 20 * 3),HEIGHT);
        queuePanel.add(list);
        queuePanel.setLayout(null);
        queuePanel.setVisible(true);
    }

    public void addConfigToQueue(int configId, Configuration newConfig) {
        savedConfigs.addElement("Configuration #" + configId);
        savingList.add(newConfig);

    }

    public Boolean contains(int configId, Configuration newConfig) {
        return savedConfigs.contains(configId) || savingList.contains(newConfig);
    }

    public void saveToFile() {
        JSONObject json = new JSONObject();
        try {
            jsonWriter.open();
            jsonWriter.write(savingList);
            jsonWriter.close();
            System.out.println("The printed list of configurations saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public void loadFileToQueue() {
        try {
            List<Configuration> loadConfigsList = jsonReader.read();
            for (Configuration config:loadConfigsList) {
                ConfigInternalUI configPanel = new ConfigInternalUI(config, this);
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
