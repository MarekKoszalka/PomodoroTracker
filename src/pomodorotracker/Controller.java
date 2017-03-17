/*
 * To change this license header, choose License Headers in Project
 * Properties. To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodorotracker;


import java.awt.event.ActionListener;
import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.Timer;

/**
 *
 * @author Marek
 */
public class Controller implements ActionListener, ListChangeListener<PomUnit> {

    public PomodoroModel                 model;
    public Timer                         timer;
    public CountdownTimer                pomodoroTimer;
    private int                          buttonMode;
    private static final int             START_BUTTON_MODE  = 1;
    private static final int             PAUSE_BUTTON_MODE  = 2;
    private static final int             RESUME_BUTTON_MODE = 3;
    private static final int             TENTH_OF_SEC       = 100;
    @FXML
    private Button                       startButton;
    @FXML
    private Button                       stopButton;
    @FXML
    private Button                       settingsButton;
    @FXML
    private Label                        leftTime;
    @FXML
    private Pane                         settingsPane;
    @FXML
    private TableView<PomUnit>           tableOfPomUnits;
    @FXML
    private TableColumn<PomUnit, String> categoryCol;
    @FXML
    private TableColumn<PomUnit, String> descriptionCol;
    @FXML
    private TableColumn<PomUnit, String> durationCol;
    @FXML
    private TableColumn<PomUnit, String> dateCol;
    @FXML
    private TextField                    durationTextF;
    @FXML
    private TextField                    categoryTextF;
    @FXML
    private TextField                    descriptionTextF;

    @FXML
    private void handleButtonAction(ActionEvent ae) {
        if (ae.getSource() == settingsButton) {
            this.initSettingsWindow();
        }
    }
    private void initSettingsWindow() {
        Stage stage;
        Parent root;
        stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SettingsWindow.fxml"));
            root = (Parent) fxmlLoader.load();
            SettingsWinController settController = fxmlLoader.<SettingsWinController>getController();
            settController.setPomodoroTimer(this.pomodoroTimer);
            settController.setModelRef(this.model);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(settingsButton.getScene().getWindow());
            stage.getIcons().add(new Image("pomodorotracker/icon.png"));
            stage.setTitle("Settings");
            stage.showAndWait();
        } catch (IOException IOE) {
            System.out.println("IOException: Nie udało się otworzyć/wczytać SettingsWindow.fxml");
            IOE.printStackTrace();
        }
    }
    @FXML
    private void handleStartButtonAction(ActionEvent event) {
        System.out.println("Przed switch(buttonMode)");
        switch (buttonMode) {/*
                              * TODO Dla czytelności kodu można cały
                              * ten blok ze switchem umiescic w
                              * jakiejs funkcji.
                              */
        case START_BUTTON_MODE: {// TODO take this code to some
                                 // function. to make this looks
                                 // better, more clear.
            pomodoroTimer.start();
            System.out.println("after PomodoroTimer.start()   before timer.start()");
            timer.start();
            setLeftTimeLabel(pomodoroTimer.getActualTimeLeftString());
            buttonMode = PAUSE_BUTTON_MODE;
            setStartButtonMode("PAUSE");
            System.out.println("Wykonano całe START_BUTTON_MODE");
            // TODO pomyslec nad napisaniem jakiejs funkcji
            // obslugujacej to zmiane
        }
            break;
        case PAUSE_BUTTON_MODE: {
            pomodoroTimer.pause();
            leftTime.setText(pomodoroTimer.getActualTimeLeftString());
            buttonMode = RESUME_BUTTON_MODE;
            setStartButtonMode("RESUME");
        }
            break;
        case RESUME_BUTTON_MODE: {
            pomodoroTimer.resume();
            leftTime.setText(pomodoroTimer.getActualTimeLeftString());
            buttonMode = PAUSE_BUTTON_MODE;
            setStartButtonMode("PAUSE");
            break;
        }
        }
    }
    @FXML
    private void handleStopButtonAction(ActionEvent event) {
        System.out.println("You clicked STPOO me!");
        pomodoroTimer.stop();
        leftTime.setText(pomodoroTimer.getRequestedTimeS());
        buttonMode = START_BUTTON_MODE;
        setStartButtonMode("START");
    }
    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        System.out.println("Wowołano funkcję: handleAddButtonAction");
        model.addPomUnitToList(new PomUnit(this.categoryTextF.getText(), this.descriptionTextF.getText(),
                Long.valueOf(this.durationTextF.getText())));
        double prefHeight = this.tableOfPomUnits.getPrefHeight();
        this.tableOfPomUnits.setPrefHeight(prefHeight - 1);
        this.tableOfPomUnits.setPrefHeight(prefHeight);
    }
    public void initialize() {
        buttonMode = START_BUTTON_MODE;
        timer = new Timer(TENTH_OF_SEC, this);
        pomodoroTimer = new CountdownTimer(10000);
        model = new PomodoroModel(this);
        pomodoroTimer.setAllSettings(model.getSettingsList());
        categoryCol.setCellValueFactory(new PropertyValueFactory<PomUnit, String>("category"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<PomUnit, String>("description"));
        durationCol.setCellValueFactory(new PropertyValueFactory<PomUnit, String>("duration"));
        dateCol.setCellValueFactory(new PropertyValueFactory<PomUnit, String>("date"));
        tableOfPomUnits.setItems(model.getPomUnitObsList());
    }
    public void setLeftTimeLabel(String value) {
        leftTime.setText(value);
    }
    public void setStartButtonMode(String modeName) {
        switch (modeName) {
        case "START":
            startButton.setText(modeName);
            break;
        case "PAUSE":
            startButton.setText(modeName);
            break;
        case "RESUME":
            startButton.setText(modeName);
            break;
        }
    }
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Platform.runLater(() -> {
            refreshingLabelTick(e);
        });
    }
    private void refreshingLabelTick(java.awt.event.ActionEvent e) {
        if (e.getSource() == timer) {
            if (pomodoroTimer.getActualTimeLeft() <= 0) {
                pomodoroTimer.stop();
            }
            if (pomodoroTimer.getIfTicking()) {
                setLeftTimeLabel(pomodoroTimer.getActualTimeLeftString());
            }
        }
    }
    @Override
    public void onChanged(Change<? extends PomUnit> change) {
        System.out.println("Wywolano funkcje OnChanged");
        model.saveDataInFile();
    };
    public PomodoroModel passModelRef(){
        return this.model;
    }
}