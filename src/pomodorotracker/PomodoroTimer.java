package pomodorotracker;
/**
 * @author Marek Koszalka from Poland
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class PomodoroTimer /*implements ActionListener*/{
    private long startMoment;
    private long pauseTimeTotal;
    private long stopMoment;
    private long requestedTime;          // time is tirequestedme after which counting is finished.
    private long elapsedTime;
    private boolean isTicking;
    
//+++++++++++++++++++++++++CONSTRUCTOR++++++++++++++++++++++++++++++++++++++++++    
    public PomodoroTimer(){
        startMoment       = 0;
        finishMoment      = 0;
        requestedTime   = 0;
        elapsedTime     = 0;
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
    private boolean getIfTicking(){
        return this.isTicking;
    }
            
    public final long getActualTimeLeft(){
        long t = requestedTime - (System.currentTimeMillis() - startTime ) + pauseTimeTotal;
        if(t >= 0);
            return (t);
    }
    //--------------OTHER METHODS-----------------------------------------------
    public final void start(){
        if(!getIfTicking()){
            pauseTimeTotal += System.currentTimeMillis() - stopMoment;
        }
        this.setIfTicking(true);
        this.startMoment = System.currentTimeMillis();
    }
    //TODO trzeba popracować nad logiką tego timerka... zamysł dobry, ale trzeba dopracować szczegóły
    public final void stop(){
        stopMoment = System.currentTimeMillis();
        this.setIfTicking(false);
    }
    /*@Override
    public void actionPerformed(ActionEvent e){
    }*/
}