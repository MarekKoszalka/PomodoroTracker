package pomodorotracker;

import java.util.Formatter;

/**
 * @author Marek Koszalka from Poland
 */
class CountdownTimer{
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ CONSTANS ++++++++++

//++++++++++++++++++++++++VARIABLES AND OBJECTS+++++++++++++++++++++++++++++++++
    private long startMoment;
    private long pauseTimeTotal;
    private long stopMoment;
    private long requestedTime; // this is requested time after... 
    private boolean isTicking;  //...which counting is finished.
    
//+++++++++++++++++++++++++CONSTRUCTOR++++++++++++++++++++++++++++++++++++++++++    
    public CountdownTimer(){
        this.startMoment     = 0;
        this.stopMoment      = 0;
        this.requestedTime   = 0;
        }
    public CountdownTimer(long requestedTime){
        this.startMoment     = 0;
        this.stopMoment      = 0;
        this.requestedTime   = requestedTime;      
        }
    public CountdownTimer(PomodoroView view, long requestedTime){
        this.startMoment     = 0;
        this.stopMoment      = 0;
        this.pauseTimeTotal = 0;
        this.requestedTime   = requestedTime;
        view.getLabelFromList(0).setText(this.getActualTimeLeftString());
    }
//+++++++++++++++++++++++++METHODS++++++++++++++++++++++++++++++++++++++++++++++
    //--------------SETTERS-----------------------------------------------------
    public final void setRequestedTime(long t){
        requestedTime = t;
    }
    
    private void setIfTicking(boolean b){
        this.isTicking = b;
    }
    //--------------GETTERS-----------------------------------------------------
    public long getRequestedTime(){
        return this.requestedTime;
    }
    public String getRequestedTimeS(){
        return String.valueOf(this.requestedTime);
    }
    public boolean getIfTicking(){
        return this.isTicking;
    }
            
    public final long getActualTimeLeft(){
        long t = requestedTime - (System.currentTimeMillis() - startMoment ) + pauseTimeTotal;
        if(t > 0){
            return t;
        }
        else if(!isTicking){
            return requestedTime;
        }
        else return 0;
    }
    public final String getActualTimeLeftString(){ //TODO napisać funkcje konwertujaca minuty do STRINGA lepiej
        //uzywajac formattera jakiegos
        long minutes = getActualTimeLeft()/60000;
        long seconds = (getActualTimeLeft() - minutes*60000)/1000;
        Formatter fmt = new Formatter();
        fmt.format("%02d:%02d", minutes, seconds); //TODO poprawic format
        String formattedTime = fmt.toString();
        fmt.close();        
        return formattedTime;        
    }
    //--------------OTHER METHODS-----------------------------------------------
    public final void start(){
        pauseTimeTotal = 0;
        this.startMoment = System.currentTimeMillis();
        this.setIfTicking(true);
    }
    public final void pause(){//this switch button mode to RESUME
        stopMoment = System.currentTimeMillis();
        this.setIfTicking(false);
    }
    public final void resume(){
        if(!getIfTicking()){
            pauseTimeTotal = pauseTimeTotal + (System.currentTimeMillis() - stopMoment);
        }
        this.setIfTicking(true);
    }
    public final void stop(){//this switch button mode to START
        stopMoment = System.currentTimeMillis();
        this.setIfTicking(false);
    }
}