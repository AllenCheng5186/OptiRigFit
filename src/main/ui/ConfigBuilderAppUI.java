package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigBuilderAppUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JFrame frame;

    public ConfigBuilderAppUI() {
        this.setVisible(true);
        this.setSize(WIDTH,HEIGHT);
        this.setTitle("OptiRigFit -Config Builder App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

//        this.setIconImage(imageIcon.getImage());
        addMenu();


    }

    /**
     * Adds menu bar.
     */
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu sensorMenu = new JMenu("Configuration");
        sensorMenu.setMnemonic('N');
        addMenuItem(sensorMenu, new AddSensorAction(),
                KeyStroke.getKeyStroke("control N"));
        menuBar.add(sensorMenu);

        JMenu codeMenu = new JMenu("File");
        codeMenu.setMnemonic('C');
//        addMenuItem(codeMenu, new AddCodeAction(), null);
//        addMenuItem(codeMenu, new RemoveCodeAction(), null);
        menuBar.add(codeMenu);

        JMenu systemMenu = new JMenu("View");
        systemMenu.setMnemonic('y');
//        addMenuItem(systemMenu, new ArmAction(),
//                KeyStroke.getKeyStroke("control A"));
//        addMenuItem(systemMenu, new DisarmAction(),
//                KeyStroke.getKeyStroke("control D"));
        menuBar.add(systemMenu);

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
    private class AddSensorAction extends AbstractAction {

        AddSensorAction() {
            super("Build new config");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            ImageIcon msgIcon = new ImageIcon("./data/resource/budgetIcon.png");
            Image image = msgIcon.getImage();
            Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            msgIcon = new ImageIcon(newimg);

            UIManager.put("OptionPane.minimumSize",new Dimension(300,130));
            String usrBudgetInput = (String) JOptionPane.showInputDialog(null,
                    "Enter you budget for your desktop ($CAD)",
                    "Desktop budget",
                    JOptionPane.QUESTION_MESSAGE,
                    msgIcon, null, 0);
            System.out.println(usrBudgetInput);

//            try {
//                if (sensorLoc != null) {
//                    Sensor s = new Sensor(sensorLoc, ac);
//                    desktop.add(new SensorUI(s, AlarmControllerUI.this));
//                }
//            } catch (DuplicateSensorException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
        }
    }


    public static void main(String[] args) {
        new ConfigBuilderAppUI();
    }
}
