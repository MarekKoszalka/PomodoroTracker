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
public class PomodoroModel{
    
    private final ArrayList<PomUnit> pomUnitArrList;
    
    public PomodoroModel(){
               
        pomUnitArrList = new ArrayList();
       /* PomUnit testUnit = new PomUnit("testowa", "to tylko testowy Unit", 30000);
        PomUnit testUnit2= new PomUnit("testowa2", "to tylko kolejny testowy junit",10000);
        
        pomUnitArrList.add(testUnit);
        pomUnitArrList.add(testUnit2);    */
    }
    public ArrayList getPomUnitList(){
        return this.pomUnitArrList;
    }
    public PomUnit getPomUnitFromList(int index){
        return this.pomUnitArrList.get(index);
    }
    public void addPomUnitToList(PomUnit pomUnit){
        this.pomUnitArrList.add(pomUnit);
        //TODO ta funkcja powinna odświeżać VIEW po dodaniu POMa
    }
}