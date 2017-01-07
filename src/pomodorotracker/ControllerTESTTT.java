/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodorotracker;

import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.swing.Timer;

/**
 *
 * @author Marek
 */
public class ControllerTESTTT implements ActionListener{
    
    PomodoroModel model;
    public Timer timer;
    public CountdownTimer pomodoroTimer;
    private int buttonMode;
   
    private static final int START_BUTTON_MODE = 1;
    private static final int PAUSE_BUTTON_MODE = 2;
    private static final int RESUME_BUTTON_MODE = 3;
    private static final int TENTH_OF_SEC = 100;
    
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Label leftTime;
    
    @FXML
    private void handleStartButtonAction(ActionEvent event) {   
        System.out.println("Przed switch(buttonMode)");
        switch(buttonMode){/*TODO Dla czytelności kodu można cały ten blok
                            ze switchem umiescic w jakiejs funkcji.*/
            case START_BUTTON_MODE:{//TODO take this code to some function. to make this looks better, more clear.
                
                pomodoroTimer.start();
                System.out.println("after PomodoroTimer.start()   before timer.start()");
                timer.start();
                setLeftTimeLabel(pomodoroTimer.getActualTimeLeftString());
                buttonMode = PAUSE_BUTTON_MODE;
                setStartButtonMode("PAUSE");
                System.out.println("Wykonano całe START_BUTTON_MODE");                
//TODO pomyslec nad napisaniem jakiejs funkcji obslugujacej to zmiane 
            }
            break;
            case PAUSE_BUTTON_MODE:{
                pomodoroTimer.pause();
                leftTime.setText(pomodoroTimer.getActualTimeLeftString());
                buttonMode = RESUME_BUTTON_MODE;
                setStartButtonMode("RESUME");
            }
            break;
            case RESUME_BUTTON_MODE:{                                   
                pomodoroTimer.resume();
                leftTime.setText(pomodoroTimer.getActualTimeLeftString());
                buttonMode = PAUSE_BUTTON_MODE;
                setStartButtonMode("PAUSE");
            break;
            }
        }   
    }
    
    @FXML
    private void handleStopButtonAction(ActionEvent event){
        System.out.println("You clicked STPOO me!");
            pomodoroTimer.stop();
            leftTime.setText(pomodoroTimer.getRequestedTimeS());
            buttonMode = START_BUTTON_MODE;
            setStartButtonMode("START");
    }
    
    public void setLeftTimeLabel(String value){
        leftTime.setText(value);
    }
    
    public void initialize() {
        buttonMode      = START_BUTTON_MODE;
        timer = new Timer(TENTH_OF_SEC, this);
        pomodoroTimer   = new CountdownTimer(10000);
        model = new PomodoroModel();
    }
    
    public void setStartButtonMode(String modeName){
        switch(modeName){
            case "START":
                startButton.setText(modeName);
                break;
            case "PAUSE":
                startButton.setText(modeName);
                break;
            case "RESUME":
                startButton.setText(modeName);
                break;
        }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Platform.runLater( ()->{
            refreshingLabelTick(e);
        });
    }
    
    private void refreshingLabelTick(java.awt.event.ActionEvent e){
        if(e.getSource() == timer){
            
            if(pomodoroTimer.getActualTimeLeft() <= 0){
                pomodoroTimer.stop();
            }
            
            if(pomodoroTimer.getIfTicking()){
                setLeftTimeLabel(pomodoroTimer.getActualTimeLeftString());
            }
        }
    };
    
    
}
