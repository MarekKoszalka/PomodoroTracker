package pomodorotracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
    private static final int ONE_SEC = 1000;
    private int buttonMode;
    private static final int START_BUTTON_MODE = 1;
    private static final int RESUME_BUTTON_MODE = 2;
    private final Container container;
    private final BorderLayout containerBorderLayout;
    //TODO zamienic te tablice buttonow na LISTę
    private final ArrayList<JButton> jButtonArrayList;
    private final JPanel jCenterPanel;
    private final JLabel jLabel;
    private final PomodoroTimer pomodoroTimer;
    private Timer timer;
//++++++++++++++++++++++++CONSTRUCTOR+++++++++++++++++++++++++++++++++++++++++++
    public PomodoroMainWindow(){
        pomodoroTimer = new PomodoroTimer(10000);
        timer = new javax.swing.Timer(ONE_SEC, this);
        buttonMode = START_BUTTON_MODE;
//------------------------CONTAINER AND LAYOUTS-???-----------------------------
        container = this.getContentPane();
        jCenterPanel = new JPanel();
        containerBorderLayout = new BorderLayout();
        container.setLayout(containerBorderLayout);
         
//-------------------------BUTTONS----------------------------------------------        
        jButtonArrayList = new ArrayList();
        jButtonArrayList.add(new JButton("START"));
        jButtonArrayList.add(new JButton("STOP"));
        
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
        jCenterPanel.add(jLabel);
        container.add(jCenterPanel, BorderLayout.CENTER);
    }
//++++++++++++++++++++METHODS OF POMODORO MAIN WINDOW+++++++++++++++++++++
    
//ACTION LISTENER-----------------------------------------------
    @Override
    //TODO dodać formatowanie czasu z milisekund na jakiś przyjazny format !
    public void actionPerformed(ActionEvent e){
        if      (e.getSource() == jButtonArrayList.get(0)){
            switch(buttonMode){
                case 1:{
                    pomodoroTimer.start();
                    timer.start();
                    jLabel.setText(String.valueOf((pomodoroTimer.getActualTimeLeft()/1000)));
                    buttonMode = RESUME_BUTTON_MODE;
                    jButtonArrayList.get(0).setBackground(Color.ORANGE);
                    jButtonArrayList.get(0).setText("RESUME");
                    //TODO pomyslec nad napisaniem jakiejs funkcji obslugujacej to zmiane 
                }
                break;
                case 2:{
                    pomodoroTimer.resume();
                    if(pomodoroTimer.getIfTicking()){
                    jLabel.setText(String.valueOf(pomodoroTimer.getActualTimeLeft()/1000));
                    buttonMode = START_BUTTON_MODE;
                    jButtonArrayList.get(0).setBackground(Color.GREEN);
                    jButtonArrayList.get(0).setText("START");
                    }
                }
            }
        }
                
        else if (e.getSource() == jButtonArrayList.get(1)){
            pomodoroTimer.stop();
        }
        else if (e.getSource() == timer){
            if(pomodoroTimer.getActualTimeLeft() <= 0){
                pomodoroTimer.stop();
            }
            if(pomodoroTimer.getIfTicking()){
                jLabel.setText(String.valueOf(pomodoroTimer.getActualTimeLeft()/1000));
            }
        }
    }
}