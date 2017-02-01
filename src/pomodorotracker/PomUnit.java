/*
 * To change this license header, choose License Headers in Project
 * Properties. To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodorotracker;


import java.time.LocalDate;

/**
 *
 * @author Marek
 */
public final class PomUnit {

    // ++++++++++++++++++++++++++++ VARIABLES AND OBJECTS ++++++++++
    private String    category;
    private String    description;
    private long      duration;   // in milliseconds
    private LocalDate date;

    // +++++++++++++++++++++++++++++++++++++ CONTRUCTORS ++++++++++++
    public PomUnit() {
    }
    public PomUnit(PomUnit pattern) {
        this.category = pattern.getCategory();
        this.description = pattern.getDescription();
        this.duration = pattern.getDuration();
        this.date = pattern.getDate();
    }
    public PomUnit(String category, String description, long duration) {
        this.category = category;
        this.description = description;
        this.duration = duration;
        this.date = LocalDate.now();
    }
    // +++++++++++++++++++++++++++++++ METHODS+++++++++++++++
    // ------------------------------- SETTERS---------------
    public void setCategory(String category) {
        this.category = category;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }
    public void setDuration(String durationS) {
        try {
            this.duration = Long.valueOf(durationS);
        } catch (NumberFormatException e) {
            System.out.println("Wystąpił błąd w Obiekcie " + this.toString() + "Błąd z rodzaju: " + e
                    + "Podany String: " + durationS + "nie może zostać " + "Skonwertowany na long");
        }
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    // ---------------------------------- GETTERS ---------------
    public String getCategory() {
        return this.category;
    }
    public String getDescription() {
        return this.description;
    }
    public long getDuration() {
        return this.duration;
    }
    public LocalDate getDate() {
        return this.date;
    }
}