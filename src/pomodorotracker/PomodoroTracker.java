package pomodorotracker;
//TODO build the greatest pomodoro-tracker application and multiplatform !!!
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Marek
 */
public class PomodoroTracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PomodoroMainWindow mainWindow = new PomodoroMainWindow();   //View
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setTitle("PomodoroTracker");
        
        
        MainWinController mainController = new MainWinController(); //Controller
/*poniższa linijka ustawia rozmiar okna maksymalny dla dowolnego
wyswietlacza, ale nie ustawia FULL SCREEN mode.*//*following line of code is
setting maximum size of window for any screen, but its not setting
Full Screen Mode*/
        mainWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//setting full-screen mode  
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}