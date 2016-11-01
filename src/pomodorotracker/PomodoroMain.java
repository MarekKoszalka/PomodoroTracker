package pomodorotracker;
//TODO build the greatest pomodoro-tracker application and multiplatform !!!

/**
 *
 * @author Marek Koszałka
 */
public class PomodoroMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PomodoroModel mainModel = new PomodoroModel();
        PomodoroView mainView = new PomodoroView();
        PomodoroController mainController; 
        mainController = new PomodoroController(mainView, mainModel);
    }
}