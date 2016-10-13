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
import javax.swing.JLabel;

/**
 * @author Marek
 */
public class PomodoroMainWindow extends JFrame implements ActionListener{
//++++++++++++++++++++++++VARIABLES AND OBJECTS+++++++++++++++++++++++++++++++++
    private final Container container;
    private final BorderLayout containerBorderLayout;
    private final JButton[] jButtonArray;
    private final JPanel jCenterPanel;
    private final JLabel jLabel;
    private final PomodoroTimer pomodoroTimer;
    int buttonsCount = 2;
//++++++++++++++++++++++++CONSTRUCTOR+++++++++++++++++++++++++++++++++++++++++++
    public PomodoroMainWindow(){
        pomodoroTimer = new PomodoroTimer();
//------------------------CONTAINER AND LAYOUTS-???-----------------------------
        container = this.getContentPane();
        jCenterPanel = new JPanel();
        containerBorderLayout = new BorderLayout();
        container.setLayout(containerBorderLayout);
//-------------------------BUTTONS----------------------------------------------        
        jButtonArray = new JButton[buttonsCount];
        jButtonArray[0] = new JButton("START");
        jButtonArray[1] = new JButton("STOP");
        jButtonArray[0].setBackground(Color.GREEN);
        jButtonArray[1].setBackground(Color.RED);
        jButtonArray[0].addActionListener(this);
        jButtonArray[1].addActionListener(this);
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
        if      (e.getSource()==jButtonArray[0]){
            pomodoroTimer.start();
            jLabel.setText(String.valueOf((pomodoroTimer.getActualTimeLeft()/1000)));
        }
                
        else if (e.getSource()==jButtonArray[1]){
            pomodoroTimer.stop();
        }
    }
}