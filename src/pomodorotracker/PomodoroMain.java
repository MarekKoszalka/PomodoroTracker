package pomodorotracker;
//TODO build the greatest pomodoro-tracker application and multiplatform !!!

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Marek Koszałka
 */
public class PomodoroMain extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PomodoroView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Pomodoro FXML");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}// TESTUJE GITHUBA Z ECLIPSEM