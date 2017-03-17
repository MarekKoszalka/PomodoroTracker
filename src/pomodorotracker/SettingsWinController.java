package pomodorotracker;


import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsWinController {

    private PomodoroModel     model;
    private CountdownTimer    pomodoroTimer;
    private ArrayList<String> settingsList;
    @FXML
    private TextField         singlePomodoroDuration;
    @FXML
    private TextField         shortBreakDuration;
    @FXML
    private Button            saveSettingsButton;
    @FXML
    private Button            cancelSettingsButton;

    @FXML
    private void handleButtonAction(ActionEvent ae) {
        Stage stage = new Stage();
        if (ae.getSource() == saveSettingsButton) {
            System.out.println("SettingsWinController inner: \n");
            this.settingsList = this.getAllSettings();
            for (String string : settingsList) {
                System.out.println(string);
            }
            model.saveSettingsInFile(this.getAllSettings());
            pomodoroTimer.setAllSettings(this.getAllSettings());
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
        settingsList = new ArrayList<String>();
    }
    public void setPomodoroTimer(CountdownTimer pomodoroTimer) {
        this.pomodoroTimer = pomodoroTimer;
        this.singlePomodoroDuration.setText(this.pomodoroTimer.getRequestedTimeS());
        this.shortBreakDuration.setText(pomodoroTimer.getShortBreakTimeS());
    }
    public void setModelRef(PomodoroModel model) {
        this.model = model;
    }
    private ArrayList<String> getAllSettings(){
        ArrayList<String> buffSettingsList = new ArrayList<String>();
        buffSettingsList.add(String.valueOf(CountdownTimer.FormatTimeStringtoLong(this.singlePomodoroDuration.getText())));
        buffSettingsList.add(String.valueOf(CountdownTimer.FormatTimeStringtoLong(this.shortBreakDuration.getText())));
        return buffSettingsList;
    }
}
