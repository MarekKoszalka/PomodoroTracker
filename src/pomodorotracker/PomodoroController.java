/*
 * Controller acts on both model and view.
 * It controls the data flow into model object and updates
 * the view whenever data changes. It keeps view and model separate.
 */
/*Implementation of Listener will be in Controller*/
package pomodorotracker;
/**
 *
 * @author Marek
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
            
public class PomodoroController implements ActionListener, KeyListener{ //TODO przerobic kod tak aby pasowal do konwencji MVC
//++++++++++++++++++++++++++++++++++++++++++++++++ VARIABLES AND OBJECTS +++++++
    private PomodoroView view;
    private PomodoroModel model;
    //--------------------------------------------------------------------------
    private int buttonMode;
    private Timer timer;
    private CountdownTimer pomodoroTimer;
    //----------------------------------------------------- CONSTANS -----------
    private static final int START_BUTTON_MODE = 1;
    private static final int PAUSE_BUTTON_MODE = 2;
    private static final int RESUME_BUTTON_MODE = 3;
    private static final int TENTH_OF_SEC = 100;
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++ CONSTRUKTOR ++++++++    
    public PomodoroController(PomodoroView view, PomodoroModel model){
        this.view = view;
        this.model = model;
        initActionListeners();
        timer = new Timer(TENTH_OF_SEC, this);
        pomodoroTimer   = new CountdownTimer(this.view, 10000);
        
        buttonMode      = START_BUTTON_MODE;
    }
    public void addPomUnit(PomUnit pomUnit){
        model.getPomUnitList().add(pomUnit);
    }
    private void initActionListeners(){
        for(int i=0; i < view.getButtonList().size(); i++){
            view.getButtonFromList(i).addActionListener(this);
        }
    }  
    @Override
    public void actionPerformed(ActionEvent e){
        if      (e.getSource() == view.getButtonFromList(0)){
            switch(buttonMode){/*TODO Dla czytelności kodu można cały ten blok
                                ze switchem umiescic w jakiejs funkcji.*/
                case START_BUTTON_MODE:{//TODO take this code to some function. to make this looks better, more clear.
                    pomodoroTimer.start();
                    timer.start();
                    view.getLabelFromList(0).setText(pomodoroTimer.getActualTimeLeftString());
                    buttonMode = PAUSE_BUTTON_MODE;
                    view.setStartButtonMode("PAUSE");
                    //TODO pomyslec nad napisaniem jakiejs funkcji obslugujacej to zmiane 
                }
                break;
                case PAUSE_BUTTON_MODE:{
                    pomodoroTimer.pause();
                    view.getLabelFromList(0).setText(pomodoroTimer.getActualTimeLeftString());
                    buttonMode = RESUME_BUTTON_MODE;
                    view.setStartButtonMode("RESUME");
                }
                break;
                case RESUME_BUTTON_MODE:{                                   
                    pomodoroTimer.resume();
                    view.getLabelFromList(0).setText(pomodoroTimer.getActualTimeLeftString());
                    buttonMode = PAUSE_BUTTON_MODE;
                    view.setStartButtonMode("PAUSE");
                break;
                }
            }
        }
        else if (e.getSource() == view.getButtonFromList(1)){
            pomodoroTimer.stop();
            view.getLabelFromList(0).setText(pomodoroTimer.getRequestedTimeS());
            buttonMode = START_BUTTON_MODE;
            view.setStartButtonMode("START");
            /*TODO zaimplementowac funkcjonalnosc ktora dodaje pomodoro w 
            przypadku zakończenia go przed ustalonym czasem... dodać zapytanie
            czy użytkownik NA PEWNO chce zakończyć pomodoro przed czasem*/ 
            //ten przycisk nie zmienia swojej funkcjonalnosci, zawsze robi to samo
        }
        else if (e.getSource() == view.getButtonFromList(2)){
            pomodoroTimer.setRequestedTime(Long.valueOf(view.getTextListFromList(0).getText()));
            view.getLabelFromList(0).setText(pomodoroTimer.getRequestedTimeS());
        }
        else if (e.getSource() == view.getButtonFromList(3)){
            model.getPomUnitList().add(new PomUnit(view.getTextListFromList(1).getText(),
                                    view.getTextListFromList(2).getText(),
                                    pomodoroTimer.getRequestedTime()));
        }
        else if (e.getSource() == timer){
            if(pomodoroTimer.getActualTimeLeft() <= 0){
                pomodoroTimer.stop();
            }
            if(pomodoroTimer.getIfTicking()){
                view.getLabelFromList(0)
                    .setText(pomodoroTimer.getActualTimeLeftString());
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
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e){}
}