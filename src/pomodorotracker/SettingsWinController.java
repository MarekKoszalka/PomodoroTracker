package pomodorotracker;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsWinController {

    @FXML
    private Button saveSettingsButton;
    @FXML
    private Button cancelSettingsButton;

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
            System.out.println("wymuszam działanie czegoś");
        }
    }
    public void initialize() {
    }
}
