package pomodorotracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
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
    private final JButton[] jButtonArray;
    private final JPanel jCenterPanel;
    private final JLabel jLabel;
    private final PomodoroTimer pomodoroTimer;
    private Timer timer;
    int buttonsCount = 3;
//++++++++++++++++++++++++CONSTRUCTOR+++++++++++++++++++++++++++++++++++++++++++
    public PomodoroMainWindow(){
        pomodoroTimer = new PomodoroTimer(100000);
        timer = new javax.swing.Timer(ONE_SEC, this);
        buttonMode = START_BUTTON_MODE;
//------------------------CONTAINER AND LAYOUTS-???-----------------------------
        container = this.getContentPane();
        jCenterPanel = new JPanel();
        containerBorderLayout = new BorderLayout();
        container.setLayout(containerBorderLayout);
//-------------------------BUTTONS----------------------------------------------        
        jButtonArray = new JButton[buttonsCount];
        jButtonArray[0] = new JButton("START");
        jButtonArray[1] = new JButton("STOP");
        jButtonArray[2] = new JButton("RESUME");
        jButtonArray[0].setBackground(Color.GREEN);
        jButtonArray[1].setBackground(Color.RED);
        jButtonArray[2].setBackground(Color.ORANGE);
        jButtonArray[0].addActionListener(this);
        jButtonArray[1].addActionListener(this);
        jButtonArray[2].addActionListener(this);
        container.add(jCenterPanel, BorderLayout.CENTER);
//------------------------------------------------------------------------------        
        for(int i=0; i < buttonsCount; i++){
            jCenterPanel.add(jButtonArray[i]);
        }
        jLabel = new JLabel();
        jCenterPanel.add(jLabel);
    }
//++++++++++++++++++++METHODS OF POMODORO MAIN WINDOW+++++++++++++++++++++
    
//ACTION LISTENER-----------------------------------------------
    @Override
    //TODO dodać jakis Timer-odswiezajacy wartosc czasu...
    //TODO dodać formatowanie czasu z milisekund na jakiś przyjazny format !
    public void actionPerformed(ActionEvent e){
        if      (e.getSource() == jButtonArray[0]){
            
            switch(buttonMode){
                case 1:{
                    pomodoroTimer.start();
                    timer.start();
                    jLabel.setText(String.valueOf((pomodoroTimer.getActualTimeLeft()/1000)));
                    buttonMode = RESUME_BUTTON_MODE;
                    jButtonArray[0].setBackground(Color.ORANGE);
                    jButtonArray[0].setText("RESUME");
                    //TODO pomyslec nad napisaniem jakiejs funkcji obslugujacej to zmiane 
                }
                break;
                case 2:{
                    if(pomodoroTimer.getIfTicking()){
                    jLabel.setText(String.valueOf(pomodoroTimer.getActualTimeLeft()/1000));
                    buttonMode = START_BUTTON_MODE;
                    //TODO dodac zmiane na START button ponownie
                    }
                }
            }
        }
                
        else if (e.getSource() == jButtonArray[1]){
            pomodoroTimer.stop();
        }
        else if (e.getSource() == jButtonArray[2]){
            pomodoroTimer.resume();
        }
        else if (e.getSource() == timer){
            if(pomodoroTimer.getIfTicking()){
                jLabel.setText(String.valueOf(pomodoroTimer.getActualTimeLeft()/1000));
            }
        }
    }
}