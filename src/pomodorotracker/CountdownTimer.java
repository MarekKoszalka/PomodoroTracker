package pomodorotracker;
/**
 * @author Marek Koszalka from Poland
 */
class CountdownTimer{
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ CONSTANS ++++++++++
    private static final int TENTH_OF_SEC = 100;
//++++++++++++++++++++++++VARIABLES AND OBJECTS+++++++++++++++++++++++++++++++++
    private long startMoment;
    private long pauseTimeTotal;
    private long stopMoment;
    private long requestedTime; // this is requested time after... 
    private boolean isTicking;  //...which counting is finished.
    
//+++++++++++++++++++++++++CONSTRUCTOR++++++++++++++++++++++++++++++++++++++++++    
    public CountdownTimer(){
        startMoment     = 0;
        stopMoment      = 0;
        requestedTime   = 0;
        }
    public CountdownTimer(long requestedTime){
        startMoment     = 0;
        stopMoment      = 0;
        this.requestedTime   = requestedTime;      
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
    public boolean getIfTicking(){
        return this.isTicking;
    }
            
    public final long getActualTimeLeft(){
        long t = requestedTime - (System.currentTimeMillis() - startMoment ) + pauseTimeTotal;
        if(t >= 0);
            return (t);
    }
    public final String getActualTimeLeftString(){
        long minutes = getActualTimeLeft()/60000;
        long seconds = (getActualTimeLeft() - minutes*60000)/1000;
        return (minutes + ":" + seconds);
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