/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodorotracker;

import java.time.LocalDate;
/**
 *
 * @author Marek
 */
public final class PomodoroUnit {
//++++++++++++++++++++++++++++++++++++++++++++++++++ VARIABLES AND OBJECTS +++++
    private String      category;
    private String      description;
    private long        duration;
    private LocalDate   date;
//+++++++++++++++++++++++++++++++++++++++++++++++++++++ CONTRUCTORS ++++++++++++
    public PomodoroUnit(String category, String description, long duration){
        this.category    = category;
        this.description = description;
        this.duration    = duration;
        this.date        = LocalDate.now();
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++ METHODS +++++++++++++++
    //-------------------------------------------------- SETTERS ---------------
    public void setCategory(String category){
        this.category = category;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setDuration(long duration){
        this.duration = duration;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    //-------------------------------------------------- GETTERS ---------------
    public String getCategory(){
        return this.category;
    }
    public String getDescription(){
        return this.description;
    }
    public long getDuration(){
        return this.duration;
    }
    public LocalDate getDate(){
        return this.date;
    }
}