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
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JLabel;
/**
 * @author Marek
 */
public class PomodoroMainWindow extends JFrame implements ActionListener{
//++++++++++++++++++++++++VARIABLES AND OBJECTS+++++++++++++++++++++++++++++++++
    private static final int TENTH_OF_SEC = 100;
    private int buttonMode;
    private static final int START_BUTTON_MODE = 1;
    private static final int RESUME_BUTTON_MODE = 2;
    private final Container container;
    private final BorderLayout containerBorderLayout;
    //TODO zamienic te tablice buttonow na LISTę
    private final ArrayList<JButton> jButtonArrayList;
    private final JPanel jCenterPanel, jBottomPanel;
    private final JLabel jLabel;
    private final JTextField jTextField;
    private final PomodoroTimer pomodoroTimer;
    private Timer timer;
//++++++++++++++++++++++++CONSTRUCTOR+++++++++++++++++++++++++++++++++++++++++++
    public PomodoroMainWindow(){
        pomodoroTimer   = new PomodoroTimer(10000);
        timer           = new javax.swing.Timer(TENTH_OF_SEC, this);
        buttonMode      = START_BUTTON_MODE;
//------------------------CONTAINER AND LAYOUTS-???-----------------------------
        container    = this.getContentPane();
        jCenterPanel = new JPanel();
        jBottomPanel = new JPanel();
        jTextField   = new JTextField("set PomTime in milis");
        jTextField.setColumns(14);
        containerBorderLayout = new BorderLayout();
        container.setLayout(containerBorderLayout);
         
//-------------------------BUTTONS----------------------------------------------        
        jButtonArrayList = new ArrayList();
        jButtonArrayList.add(new JButton("START"));
        jButtonArrayList.add(new JButton("STOP"));
        jButtonArrayList.add(new JButton("SET requested time"));
        
        jButtonArrayList.get(0).setBackground(Color.GREEN);
        jButtonArrayList.get(1).setBackground(Color.RED);
        
        for(int i=0; i< jButtonArrayList.size(); i++){
            jButtonArrayList.get(i).addActionListener(this);
            jButtonArrayList.get(i).setPreferredSize(new Dimension(350, 130));
            jButtonArrayList.get(i).setFont(new Font("Times New Roman", Font.BOLD, 25));
            jCenterPanel.add(jButtonArrayList.get(i));
        }
        jLabel = new JLabel();
        jLabel.setFont(new Font("Times New Roman", Font.BOLD, 80));
        jLabel.setText("00:00");
        jCenterPanel.add(jTextField);
        jCenterPanel.add(jLabel);
        container.add(jCenterPanel, BorderLayout.CENTER);
        container.add(jBottomPanel, BorderLayout.PAGE_END);
    }
//++++++++++++++++++++METHODS OF POMODORO MAIN WINDOW+++++++++++++++++++++
    
//ACTION LISTENER-----------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e){
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
            pomodoroTimer.setRequestedTime(Long.valueOf(jTextField.getText()));
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
}