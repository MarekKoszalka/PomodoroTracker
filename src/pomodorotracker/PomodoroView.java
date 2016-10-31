/*
* View represents the visualization of the data that model contains.
*/package pomodorotracker;

import java.awt.BorderLayout;
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

    private final ArrayList<PomDataSingleDisplayer> PomDisplArrList;
    //---------------------------------------------- SWING COMPONENTS ----------
    private final Container container;
    private final BorderLayout containerBorderLayout;
    private final ArrayList<JButton> jButtonArrayList;
    private final ArrayList<JLabel> jLabelArrayList;
    private final ArrayList<JTextField> jTextFieldArrayList;
    private final JPanel jCenterPanel, jAddingPomPanel;
    private final JLabel jLabel, jLabel2, jLabel3;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ CONSTRUCTOR +++++++
    public PomodoroView(){
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("PomodoroTracker");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        PomDisplArrList = new ArrayList();
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
        containerBorderLayout = new BorderLayout();
        container.setLayout(containerBorderLayout);
        jLabelArrayList = new ArrayList();
//-------------------------BUTTONS----------------------------------------------        
        jButtonArrayList = new ArrayList();
        jButtonArrayList.add(new JButton("START"));
        jButtonArrayList.add(new JButton("STOP"));
        jButtonArrayList.add(new JButton("SET requested time"));
        jButtonArrayList.add(new JButton("Add Pomodoro"));
        jButtonArrayList.get(0).setBackground(Color.GREEN);
        jButtonArrayList.get(1).setBackground(Color.RED);
        
//LEARNING how to add lambda action listener   
        jButtonArrayList.get(1).addActionListener(e -> System.out.println("LambdaExpression XDDD"));
        
        
        for(int i=0; i< jButtonArrayList.size(); i++){
            if(i==3)break;
            jButtonArrayList.get(i).setPreferredSize(new Dimension(350, 130));
            jButtonArrayList.get(i).setFont(new Font("Times New Roman", Font.BOLD, 25));
            jCenterPanel.add(jButtonArrayList.get(i));
        }
        jLabel = new JLabel();
        jLabel2 = new JLabel("Category: ");
        jLabel3 = new JLabel("Description: ");
        jLabel.setFont(new Font("Times New Roman", Font.BOLD, 80));
        jLabel.setText("00:00");
        jCenterPanel.add(jTextFieldArrayList.get(0));
        jCenterPanel.add(jLabel);
        
        jAddingPomPanel.add(jLabel2);
        jAddingPomPanel.add(jTextFieldArrayList.get(1));
        jAddingPomPanel.add(jLabel3);
        jAddingPomPanel.add(jTextFieldArrayList.get(2));
        jAddingPomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        jAddingPomPanel.add(jButtonArrayList.get(3));
        jCenterPanel.add(jAddingPomPanel);
        container.add(jCenterPanel, BorderLayout.CENTER);
        this.validate();
    }
//++++++++++++++++++++ METHODS OF POMODORO VIEW ++++++++++++++++++++++++++++++++
    //----------------------------------------------------PRIVATE---------------
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
    public ArrayList getPomDisplArrList(){
    return this.PomDisplArrList;
    }
    public ArrayList getTextFieldList(){
        return this.jTextFieldArrayList;
    }
    public JTextField getTextListFromList(int index){
        return this.jTextFieldArrayList.get(index);
    }
}