/*
 * Model represents an object or JAVA POJO carrying data.
 * It can also have logic to update controller if its data changes.
 */
package pomodorotracker;

import java.util.List;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;
/**
 *
 * @author Marek
 */
public class PomodoroModel{
    private final DataFilesService dfs;
    private final List<PomUnit> pomUnitList;
    private final ObservableList<PomUnit> pomUnitObsList;
    
    public PomodoroModel(){
        dfs = new DataFilesService();
        pomUnitList = new ArrayList<>();
        pomUnitObsList = FXCollections.observableList(pomUnitList);
        //pomUnitObsList.addListener();
        
       /* PomUnit testUnit = new PomUnit("testowa", "to tylko testowy Unit", 30000);
        PomUnit testUnit2= new PomUnit("testowa2", "to tylko kolejny testowy junit",10000);
        
        pomUnitArrList.add(testUnit);
        pomUnitArrList.add(testUnit2);    */
    }
    public List getPomUnitList(){
        return this.pomUnitList;
    }
    public PomUnit getPomUnitFromList(int index){
        return this.pomUnitList.get(index);
    }
    public void addPomUnitToList(PomUnit pomUnit){
        this.pomUnitList.add(pomUnit);
        //TODO ta funkcja powinna odświeżać VIEW po dodaniu POMa
        //warto pobawić się w @Observable
    }
}