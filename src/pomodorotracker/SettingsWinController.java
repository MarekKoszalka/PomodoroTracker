package pomodorotracker;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsWinController {

    private SettingsFilesService settingsFilesServie;
    private CountdownTimer       pomodoroTimer;
    @FXML
    private TextField            singlePomodoroDuration;
    @FXML
    private TextField            shortBreakDuration;
    @FXML
    private Button               saveSettingsButton;
    @FXML
    private Button               cancelSettingsButton;

    @FXML
    private void handleButtonAction(ActionEvent ae) {
        Stage stage = new Stage();
        if (ae.getSource() == saveSettingsButton) {
            System.out.print("pressed saveSettingsButton");
            stage = (Stage) saveSettingsButton.getScene().getWindow();
            stage.close();
        } else if (ae.getSource() == cancelSettingsButton) {
            System.out.println("pressed cancelSettingsButton");
            stage = (Stage) cancelSettingsButton.getScene().getWindow();
            stage.close();
        }
    }
    public void initialize() {
        settingsFilesServie = new SettingsFilesService();
    }
    public void setPomodoroTimer(CountdownTimer pomodoroTimer) {
        this.pomodoroTimer = pomodoroTimer;
        this.singlePomodoroDuration.setText(this.pomodoroTimer.getRequestedTimeS());
        this.shortBreakDuration.setText(pomodoroTimer.getShortBreakTimeS());
    }
}
