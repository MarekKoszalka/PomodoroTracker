package pomodorotracker;


import java.util.ArrayList;
import java.util.Formatter;

/**
 * @author Marek Koszalka from Poland
 */
public class CountdownTimer {
    // +++++++++++++ CONSTANS ++++++++++++++++++++++++++++

    // +++++++++++++++VARIABLES AND OBJECTS+++++++++++++++
    private long      startMoment;
    private long      pauseTimeTotal;
    private long      stopMoment;
    private long      requestedTime;         // this is requested time
                                             // after...
    private long      shortBreakTime;        // ...which counting is
                                             // finished.
    /**
     * this variable describes how many short breaks user will have
     * before longer one
     */
    private int       shortBreaksLimitAmount;
    private int       shortBreaksCount;
    private boolean   isTicking;
    private TimerMode currentMode;

    // TODO implement break times etc. switching-ticking
    private enum TimerMode {
        Pomodoro, ShortBreak, LongBreak
    }

    // +++++++++++++++++++++++++CONSTRUCTOR++++++++++++++++++++++++++++++++++++++++++
    public CountdownTimer() {
        this.shortBreaksLimitAmount = 4;
        this.startMoment = 0;
        this.stopMoment = 0;
        this.pauseTimeTotal = 0;
        this.requestedTime = 0;
        this.currentMode = TimerMode.Pomodoro;
    }
    public CountdownTimer(long requestedTime) {
        this.shortBreaksLimitAmount = 4;
        this.startMoment = 0;
        this.stopMoment = 0;
        this.pauseTimeTotal = 0;
        this.requestedTime = requestedTime;
        this.currentMode = TimerMode.Pomodoro;
    }
    // +++++++++++++++++++++++++METHODS++++++++++++++++++++++++++++++++++++++++++++++
    // --------------SETTERS-----------------------------------------------------
    public final void setRequestedTime(long t) {
        requestedTime = t;
    }
    private void setIfTicking(boolean b) {
        this.isTicking = b;
        if (b == false)
            this.setMode();
    }
    public void setShortBreakTime(long shortBreakTime) {
        this.shortBreakTime = shortBreakTime;
    }
    public void setAllSettings(ArrayList<String> settingsList) {
        System.out.println("CountdowneTimer inner: \n");
        for (String string : settingsList) {
            System.out.println(string);
        }
        this.setRequestedTime(Long.valueOf(settingsList.get(0)));
        this.setShortBreakTime(Long.valueOf(settingsList.get(1)));
    }
    // --------------GETTERS-----------------------------------------------------
    public long getRequestedTime() {
        return this.requestedTime;
    }
    public String getRequestedTimeS() {
        return this.FormatLongToTimeString(this.requestedTime);
    }
    public boolean getIfTicking() {
        return this.isTicking;
    }
    public long getShortBreakTime() {
        return shortBreakTime;
    }
    public String getShortBreakTimeS() {
        return this.FormatLongToTimeString(this.shortBreakTime);
    }
    public final long getActualTimeLeft() {
        long t = requestedTime - (System.currentTimeMillis() - startMoment) + pauseTimeTotal;
        if (t > 0) {
            return t;
        } else if (isTicking && t <= 0) {
            this.setIfTicking(false);
            return requestedTime;
        } else if (!isTicking) {
            return requestedTime;
        } else
            return 0;
    }
    public final String getActualTimeLeftString() { // TODO napisać
                                                    // funkcje
                                                    // konwertujaca
                                                    // minuty do
                                                    // STRINGA lepiej
        // uzywajac formattera jakiegos
        long minutes = getActualTimeLeft() / 60000;
        long seconds = (getActualTimeLeft() - minutes * 60000) / 1000;
        Formatter fmt = new Formatter();
        fmt.format("%02d:%02d", minutes, seconds); // TODO poprawic
                                                   // format
        String formattedTime = fmt.toString();
        fmt.close();
        return formattedTime;
    }
    // --------------OTHER
    // METHODS-----------------------------------------------
    public final void start() {
        pauseTimeTotal = 0;
        this.startMoment = System.currentTimeMillis();
        this.setIfTicking(true);
    }
    public final void pause() {// this switch button mode to RESUME
        stopMoment = System.currentTimeMillis();
        this.setIfTicking(false);
    }
    public final void resume() {
        if (!getIfTicking()) {
            pauseTimeTotal = pauseTimeTotal + (System.currentTimeMillis() - stopMoment);
        }
        this.setIfTicking(true);
    }
    public final void stop() {// this switch button mode to START
        stopMoment = System.currentTimeMillis();
        this.setIfTicking(false);
    }
    private final String FormatLongToTimeString(long duration) {
        long minutes = duration / 60000;
        long seconds = (duration - minutes * 60000) / 1000;
        Formatter fmt = new Formatter();
        fmt.format("%02d:%02d", minutes, seconds);
        String formattedDuration = fmt.toString();
        fmt.close();
        return formattedDuration;
    }
    public static long FormatTimeStringtoLong(String formattedDuration) {
        long minutes = Long.valueOf(formattedDuration.substring(0, 2));
        long seconds = Long.valueOf(formattedDuration.substring(3, 5));
        return (long) (60000 * minutes + 1000 * seconds);
    }
    private void setMode() {
        switch (currentMode) {
        case Pomodoro:
            if (shortBreaksCount < shortBreaksLimitAmount)
                currentMode = TimerMode.ShortBreak;
            else if (currentMode.equals(TimerMode.Pomodoro) && shortBreaksCount >= shortBreaksLimitAmount)
                currentMode = TimerMode.LongBreak;
            break;
        case ShortBreak:
        case LongBreak:
            currentMode = TimerMode.Pomodoro;
            break;
        }
        /*
         * if(currentMode.equals(TimerMode.Pomodoro) &&
         * shortBreaksCount < shortBreaksLimitAmount) currentMode =
         * TimerMode.ShortBreak; else
         * if(currentMode.equals(TimerMode.Pomodoro) &&
         * shortBreaksCount >= shortBreaksLimitAmount) currentMode =
         * TimerMode.LongBreak; else if
         */
    }
}