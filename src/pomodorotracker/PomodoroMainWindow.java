package pomodorotracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.JLabel;
/**
 * @author Marek
 */
public class PomodoroMainWindow extends JFrame implements ActionListener, KeyListener{
//++++++++++++++++++++++++VARIABLES AND OBJECTS+++++++++++++++++++++++++++++++++
    
    private static final int TENTH_OF_SEC = 100;
    private int buttonMode;
    private static final int START_BUTTON_MODE = 1;
    private static final int RESUME_BUTTON_MODE = 2;
    private final PomodoroTimer pomodoroTimer;
    private final ArrayList<PomodoroUnit> PomUnitArrlist;
    //---------------------------------------------- SWING COMPONENTS ----------
    private final Container container;
    private final BorderLayout containerBorderLayout;
    private final ArrayList<JButton> jButtonArrayList;
    private final JPanel jCenterPanel, jBottomPanel;
    private final JLabel jLabel, jLabel2, jLabel3;
    private final JTextField jTextField1, jTextField2, jTextField3;
    private Timer timer;
//++++++++++++++++++++++++CONSTRUCTOR+++++++++++++++++++++++++++++++++++++++++++
    public PomodoroMainWindow(){
        PomUnitArrlist  = new ArrayList();
        pomodoroTimer   = new PomodoroTimer(10000);
        timer           = new javax.swing.Timer(TENTH_OF_SEC, this);
        buttonMode      = START_BUTTON_MODE;
//------------------------CONTAINER AND LAYOUTS-???-----------------------------
        container    = this.getContentPane();
        jCenterPanel = new JPanel();
        jBottomPanel = new JPanel();
        jTextField1  = new JTextField("set PomTime in milis");
        jTextField2  = new JTextField();
        jTextField3  = new JTextField();
        jTextField3.addKeyListener(this);
        jTextField1.setColumns(14); jTextField2.setColumns(10); jTextField3.setColumns(10);
        containerBorderLayout = new BorderLayout();
        container.setLayout(containerBorderLayout);
         
//-------------------------BUTTONS----------------------------------------------        
        jButtonArrayList = new ArrayList();
        jButtonArrayList.add(new JButton("START"));
        jButtonArrayList.add(new JButton("STOP"));
        jButtonArrayList.add(new JButton("SET requested time"));
        jButtonArrayList.add(new JButton("Add Pomodoro"));
        
        jButtonArrayList.get(0).setBackground(Color.GREEN);
        jButtonArrayList.get(1).setBackground(Color.RED);
        
        for(int i=0; i< jButtonArrayList.size(); i++){
            jButtonArrayList.get(i).addActionListener(this);
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
        jCenterPanel.add(jTextField1);
        jCenterPanel.add(jLabel);
        
        jBottomPanel.add(jLabel2);
        jBottomPanel.add(jTextField2);
        jBottomPanel.add(jLabel3);
        jBottomPanel.add(jTextField3);
        jBottomPanel.add(jButtonArrayList.get(3));
        jCenterPanel.add(jBottomPanel);
        container.add(jCenterPanel, BorderLayout.CENTER);
//container.add(jBottomPanel, BorderLayout.PAGE_END);
    }
//++++++++++++++++++++ METHODS OF POMODORO MAIN WINDOW +++++++++++++++++++++++++    
//ACTION LISTENER-----------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e){
        int i = 0;
        if      (e.getSource() == jButtonArrayList.get(0)){
            switch(buttonMode){
                case 1:{
                    pomodoroTimer.start();
                    timer.start();
                    //ponizsze poprawic i moze wrzucic to do jakiejs funkcji
                    jLabel.setText(pomodoroTimer.getActualTimeLeftString());
                    buttonMode = RESUME_BUTTON_MODE;
                    jButtonArrayList.get(0).setBackground(Color.ORANGE);
                    jButtonArrayList.get(0).setText("RESUME");
                    //TODO pomyslec nad napisaniem jakiejs funkcji obslugujacej to zmiane 
                }
                break;
                case 2:{
                    pomodoroTimer.resume();
                    if(pomodoroTimer.getIfTicking()){
                    jLabel.setText(pomodoroTimer.getActualTimeLeftString());
                    buttonMode = START_BUTTON_MODE;
                    jButtonArrayList.get(0).setBackground(Color.GREEN);
                    jButtonArrayList.get(0).setText("START");
                    }
                break;
                }
            }
        }
                
        else if (e.getSource() == jButtonArrayList.get(1)){
            pomodoroTimer.stop();
        }
        else if (e.getSource() == jButtonArrayList.get(2)){
            //tutaj mozna dodac try{}catch{} i wybierac tylko 5 elementowe
            //stringi
            //na ten moment implementuje lapanie tylko milisekund
            pomodoroTimer.setRequestedTime(Long.valueOf(jTextField1.getText()));
        }
        
        else if (e.getSource() == jButtonArrayList.get(3)){
            PomUnitArrlist.add(new PomodoroUnit(jTextField2.getText(), jTextField3.getText(), pomodoroTimer.getRequestedTime()));
                    //TEST
            System.out.println(PomUnitArrlist.get(i).getCategory());
            
            i++;
        }
        else if (e.getSource() == timer){
            if(pomodoroTimer.getActualTimeLeft() <= 0){
                pomodoroTimer.stop();
            }
            if(pomodoroTimer.getIfTicking()){
                jLabel.setText(pomodoroTimer.getActualTimeLeftString());
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            System.out.println("Naciśnięto Enter");
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("Naciśnięto \"A");
        }
    }
    @Override
    public void keyReleased(KeyEvent e){}
    @Override
    public void keyTyped(KeyEvent e){}
}