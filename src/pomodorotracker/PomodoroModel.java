/*
 * Model represents an object or JAVA POJO carrying data.
 * It can also have logic to update controller if its data changes.
 */
package pomodorotracker;

import java.util.ArrayList;
/**
 *
 * @author Marek
 */
public class PomodoroModel {
    private final ArrayList<PomUnit> pomUnitArrList;
    PomodoroModel(){
        pomUnitArrList = new ArrayList();
    }
    public ArrayList getPomUnitList(){
        return this.pomUnitArrList;
    }
    public void addPomUnitToList(PomUnit pomUnit){
        this.pomUnitArrList.add(pomUnit);
        //TODO ta funkcja powinna odświeżać VIEW po dodaniu POMa
    }
}