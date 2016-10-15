package pomodorotracker;
/**
 * @author Marek Koszalka from Poland
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class PomodoroTimer /*implements ActionListener*/{
//++++++++++++++++++++++++VARIABLES AND OBJECTS+++++++++++++++++++++++++++++++++
    private long startMoment;
    private long pauseTimeTotal;
    private long stopMoment;
    private long requestedTime;          // time is requested time after which counting is finished.
    private boolean isTicking;
    
//+++++++++++++++++++++++++CONSTRUCTOR++++++++++++++++++++++++++++++++++++++++++    
    public PomodoroTimer(){
        startMoment     = 0;
        stopMoment      = 0;
        requestedTime   = 0;
        }
    public PomodoroTimer(long rTime){
        startMoment     = 0;
        stopMoment      = 0;
        requestedTime   = rTime;
      
        }
//+++++++++++++++++++++++++METHODS++++++++++++++++++++++++++++++++++++++++++++++
    //--------------SETTERS-----------------------------------------------------
    public final void setRequestedTime(int t){
        requestedTime = t;
    }
    
    private void setIfTicking(boolean b){
        this.isTicking = b;
    }
    //--------------GETTERS-----------------------------------------------------
    public boolean getIfTicking(){
        return this.isTicking;
    }
            
    public final long getActualTimeLeft(){
        long t = requestedTime - (System.currentTimeMillis() - startMoment ) + pauseTimeTotal;
        if(t >= 0);
            return (t);
    }
    //--------------OTHER METHODS-----------------------------------------------
    public final void start(){
        pauseTimeTotal = 0;
        
        this.setIfTicking(true);
        this.startMoment = System.currentTimeMillis();
    }
    public final void resume(){
        if(!getIfTicking()){
            pauseTimeTotal = pauseTimeTotal + (System.currentTimeMillis() - stopMoment);
        }
        this.setIfTicking(true);
    }
    public final void stop(){
        stopMoment = System.currentTimeMillis();
        this.setIfTicking(false);
    }
}