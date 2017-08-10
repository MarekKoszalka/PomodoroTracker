/*
 * Model represents an object or JAVA POJO carrying data. It can also
 * have logic to update controller if its data changes.
 */
package pomodorotracker;


import java.util.List;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 *
 * @author Marek
 */
public class PomodoroModel {

    private final DataFilesService        dfs;
    private SettingsFilesService          sfs;
    public ArrayList<String>              settingsList;
    private final List<PomUnit>           pomUnitList;
    private final ObservableList<PomUnit> pomUnitObsList;
    private Controller                    controller;

    public PomodoroModel(Controller controller) {
        this.controller = controller;
        dfs = new DataFilesService();
        pomUnitList = new ArrayList<>(dfs.loadData());
        pomUnitObsList = FXCollections.observableList(pomUnitList);
        pomUnitObsList.addListener(this.controller);
        sfs = new SettingsFilesService();
        settingsList = (ArrayList<String>) sfs.loadSettings();
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public List<PomUnit> getPomUnitList() {
        return this.pomUnitList;
    }
    public ObservableList<PomUnit> getPomUnitObsList() {
        return this.pomUnitObsList;
    }
    public PomUnit getPomUnitFromList(int index) {
        return this.pomUnitList.get(index);
    }
    public void addPomUnitToList(PomUnit pomUnit) {
        this.pomUnitList.add(pomUnit);
    }
    public void saveDataInFile() {
        dfs.saveData(pomUnitObsList);
    }
    public void saveSettingsInFile(ArrayList<String> settingsList) {
        System.out.println("PomodoroModel inner: \n");
        for (String string : settingsList) {
            System.out.println(string);
        }
        sfs.saveSettings(settingsList);
    }
    public ArrayList<String> getSettingsList() {
        return this.settingsList;
    }
}