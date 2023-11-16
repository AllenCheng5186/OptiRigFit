package ui;

import model.Configuration;
import model.component.PcComponent;
import model.component.cpu.Cpu;
import model.component.gpu.Gpu;
import model.component.motherboard.Motherboard;
import model.component.psu.PowerSupply;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CustomizeSelectionUI extends JFrame {
    private List<PcComponent> components;
    private List<String> componentModels;
    private JPanel contentPanel;
    private JComboBox newsSelectionBox;
    private Configuration config;
    private JLabel editLabel;
    private JLabel displayLabel;

    public CustomizeSelectionUI(List<PcComponent> components, Configuration oldConfig,
                                JLabel editLabel, JLabel displayLabel) {
        super("Customize your own PC!");
        this.components = new ArrayList<>(components);
        this.config = oldConfig;
        this.displayLabel = displayLabel;
        this.editLabel = editLabel;
        componentModels = new ArrayList<>();
        for (PcComponent component:this.components) {
            componentModels.add(component.getModel() + "   $" + component.getPrice());
        }

        setSize(300, 300);
        setVisible(true);
        setResizable(false);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setContentPane(contentPanel);

        JButton replaceButton = new JButton(new ReplaceButtonAction());
        replaceButton.setText("Replace");
        replaceButton.setHorizontalAlignment(JLabel.CENTER);

        newsSelectionBox = new JComboBox<>(componentModels.toArray());
        contentPanel.add(newsSelectionBox);
        contentPanel.add(replaceButton);
    }

    private class ReplaceButtonAction extends AbstractAction {

        public ReplaceButtonAction() {
            super("Replace the component with current selection");
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = newsSelectionBox.getSelectedIndex();
            PcComponent component = components.get(selectedIndex);
            if (component.getClass().getName().equals("model.component.cpu.Cpu")) {
                config.setCpu((Cpu) component);
            } else if (component.getClass().getName().equals("model.component.motherboard.Motherboard")) {
                config.setMotherboard((Motherboard) component);
            } else if (component.getClass().getName().equals("model.component.psu.PowerSupply")) {
                config.setPowerSupply((PowerSupply) component);
            } else {
                config.setGpu((Gpu) component);
            }
            editLabel.setText(componentModels.get(selectedIndex));
            displayLabel.setText(componentModels.get(selectedIndex));
            setVisible(false);
        }
    }
}
