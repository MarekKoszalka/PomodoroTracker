package pomodorotracker;
// TODO build the greatest pomodoro-tracker application and
// multiplatform !!!


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Marek Koszałka
 */
public class PomodoroMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PomodoroView.fxml"));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("pomodorotracker/icon.png"));
        stage.setTitle("Pomodoro FXML");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }
    public static void main(String[] args) {
        launch(args);
    }
}