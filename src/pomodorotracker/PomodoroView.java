/*
* View represents the visualization of the data that model contains.
*/package pomodorotracker;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
/**
 * @author Marek
 */
public class PomodoroView extends JFrame{

    //---------------------------------------------- SWING COMPONENTS ----------
    private final Container container;
    private final GridBagConstraints GBConstraints1, GBConstraints2;
    private final GridBagLayout containerGridBagLayout;
    private ArrayList<JButton>     jButtonArrayList;
    private ArrayList<JLabel>     jLabelArrayList;
    private ArrayList<JTextField> jTextFieldArrayList;
    private JButton button;
    private JPanel jCenterPanel, jAddingPomPanel, jTimerPanel;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ CONSTRUCTOR +++++++
    public PomodoroView(){
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("PomodoroTracker");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        /*TODO przenieść ten kod do osobnej klasy w tym pliku lub innym.
        settingsFrame = new JFrame("Settings");
        settingsFrame.setVisible(true);
        settingsFrame.setSize(new Dimension(300, 500));
        int LocationWidth, LocationHeight;
        LocationWidth = Toolkit.getDefaultToolkit().getScreenSize().width/2 - 150;
        LocationHeight = Toolkit.getDefaultToolkit().getScreenSize().height/2 - 250;
        settingsFrame.setLocation(LocationWidth, LocationHeight);
        */
//------------------------CONTAINER AND LAYOUTS-???-----------------------------
        container    = this.getContentPane();
        jCenterPanel = new JPanel();
        jAddingPomPanel = new JPanel();
        jTextFieldArrayList = new ArrayList();
        jTextFieldArrayList.add(new JTextField("set PomTime in milis"));
        jTextFieldArrayList.add(new JTextField());
        jTextFieldArrayList.add(new JTextField());
        jTextFieldArrayList.get(0).setColumns(14);
        jTextFieldArrayList.get(1).setColumns(10);
        jTextFieldArrayList.get(2).setColumns(10);
        containerGridBagLayout = new GridBagLayout();
        container.setLayout(containerGridBagLayout);
//-------------------------BUTTONS----------------------------------------------        
        jButtonArrayList = new ArrayList();
        jButtonArrayList.add(new JButton("START"));
        jButtonArrayList.add(new JButton("STOP"));
        jButtonArrayList.add(new JButton("SET requested time"));
        jButtonArrayList.add(new JButton("Add Pomodoro"));
        jButtonArrayList.add(new JButton("SETTINGS"));
        button = new JButton();
        button.setName("SettingsButton");
        jButtonArrayList.add(button);
        jButtonArrayList.get(0).setBackground(Color.GREEN);
        jButtonArrayList.get(1).setBackground(Color.RED);      
        
        for(int i=0; i< jButtonArrayList.size(); i++){
            if(i==3)break;
            jButtonArrayList.get(i).setPreferredSize(new Dimension(200, 130));
            jButtonArrayList.get(i).setFont(new Font("Times New Roman", Font.BOLD, 25));
            jCenterPanel.add(jButtonArrayList.get(i));
        }
        jLabelArrayList = new ArrayList();
        jLabelArrayList.add(new JLabel());
        jLabelArrayList.add(new JLabel("Category: "));
        jLabelArrayList.add(new JLabel("Description: "));
        jLabelArrayList.get(0).setFont(new Font("Times New Roman", Font.BOLD, 80));
        jLabelArrayList.get(0).setText("00:00");
        jCenterPanel.add(jTextFieldArrayList.get(0));
        jCenterPanel.add(jLabelArrayList.get(0));
        
        jAddingPomPanel.add(jLabelArrayList.get(1));
        jAddingPomPanel.add(jTextFieldArrayList.get(1));
        jAddingPomPanel.add(jLabelArrayList.get(2));
        jAddingPomPanel.add(jTextFieldArrayList.get(2));
        jAddingPomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        jAddingPomPanel.add(jButtonArrayList.get(3));
        jCenterPanel.add(jAddingPomPanel);
        jCenterPanel.setPreferredSize(new Dimension(500, 400));
        GBConstraints1 = new GridBagConstraints(2, 0,
                                                2, 2,
                                                0.5d, 0.5d,
                                                GridBagConstraints.FIRST_LINE_END,
                                                GridBagConstraints.NONE,
                                                new Insets(10,10,10,10),
                                                0, 0);
        container.add(jCenterPanel, GBConstraints1);
        //-------------------------------- TIMER PANEL -------------------------
        jTimerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jTimerPanel.setBorder(BorderFactory.createTitledBorder("Timer Panel"));
        jTimerPanel.add(jButtonArrayList.get(0));
        jTimerPanel.add(jButtonArrayList.get(1));
        jTimerPanel.add(jLabelArrayList.get(0));
        jTimerPanel.setPreferredSize(new Dimension(500, 250));
        GBConstraints2 = new GridBagConstraints(0, 0,
                                                2, 2,
                                                0.5d, 0.5d,
                                                GridBagConstraints.FIRST_LINE_START,
                                                GridBagConstraints.NONE,
                                                new Insets(10,10,10,10),
                                                0, 0);
        container.add(jTimerPanel, GBConstraints2);    
        
        this.validate();
    }
//++++++++++++++++++++ METHODS OF POMODORO VIEW ++++++++++++++++++++++++++++++++
    //--------------------------------------------------- PUBLIC ---------------
    public ArrayList getButtonList(){
        return this.jButtonArrayList;
    }
    public JButton getButtonFromList(int index){
        return jButtonArrayList.get(index);
    }
    public ArrayList getLabelList(){
        return this.jLabelArrayList;
    }
    public JLabel getLabelFromList(int index){
        return this.jLabelArrayList.get(index);
    }
    public ArrayList getTextFieldList(){
        return this.jTextFieldArrayList;
    }
    public JTextField getTextListFromList(int index){
        return this.jTextFieldArrayList.get(index);
    }
    public void setStartButtonMode(String modeName){
        switch(modeName){
            case "START":
                jButtonArrayList.get(0).setText(modeName);
                this.repaint();
                this.validate();
                break;
            case "PAUSE":
                jButtonArrayList.get(0).setText(modeName);
                this.repaint();
                this.validate();
                break;
            case "RESUME":
                jButtonArrayList.get(0).setText(modeName);
                this.repaint();
                this.validate();
                break;
        }
    }
}