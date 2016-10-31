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
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
            
public class PomodoroController implements ActionListener, KeyListener{ //TODO przerobic kod tak aby pasowal do konwencji MVC
//++++++++++++++++++++++++++++++++++++++++++++++++ VARIABLES AND OBJECTS +++++++
    private final PomodoroView view;
    private final PomodoroModel model;
    //--------------------------------------------------------------------------
    private int buttonMode;
    private Timer timer;
    private final CountdownTimer pomodoroTimer;
    //----------------------------------------------------- CONSTANS -----------
    private static final int START_BUTTON_MODE = 1;
    private static final int RESUME_BUTTON_MODE = 2;
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++ CONSTRUKTOR ++++++++    
    public PomodoroController(PomodoroView view, PomodoroModel model){
        this.view = view;
        this.model = model;
        initActionListeners(view);
        pomodoroTimer   = new CountdownTimer(10000);
        buttonMode      = START_BUTTON_MODE;
    }
    public void addPomUnit(PomUnit pomUnit){
        model.getPomUnitList().add(pomUnit);
    }
    private void initActionListeners(PomodoroView view){
        view.getButtonList().get(0);
    }
    private void updateView(){
    
    }
    @Override
    public void actionPerformed(ActionEvent e){
        int i = 0;
        if      (e.getSource() == view.getButtonFromList(0)){
            switch(buttonMode){
                case 1:{
                    pomodoroTimer.start();
                    timer.start();
                    //ponizsze poprawic i moze wrzucic to do jakiejs funkcji
                    view.getLabelFromList(0).setText(pomodoroTimer.getActualTimeLeftString());
                    buttonMode = RESUME_BUTTON_MODE;
                    view.getButtonFromList(0).setBackground(Color.ORANGE);
                    view.getButtonFromList(1).setText("RESUME");
                    //TODO pomyslec nad napisaniem jakiejs funkcji obslugujacej to zmiane 
                }
                break;
                case 2:{                    
                    if(pomodoroTimer.getIfTicking()){
                        pomodoroTimer.resume();
                    }
                break;
                }
            }
        }
                
        else if (e.getSource() == view.getButtonList().get(1)){
            pomodoroTimer.stop();
            view.getLabelFromList(0).setText(pomodoroTimer.getActualTimeLeftString());
            buttonMode = START_BUTTON_MODE;
            view.getButtonFromList(0).setBackground(Color.GREEN);
            view.getButtonFromList(0).setText("START");
        }
        else if (e.getSource() == view.getButtonList().get(2)){
            //tutaj mozna dodac try{}catch{} i wybierac tylko 5 elementowe
            //stringi
            //na ten moment implementuje lapanie tylko milisekund
            pomodoroTimer.setRequestedTime(Long.valueOf(view.getTextListFromList(0).getText()));
        }
        else if (e.getSource() == view.getButtonList().get(3)){
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