/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodorotracker;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 *
 * @author Marek
 */
public class PomDataSingleDisplayer extends JPanel{
    private JLabel categoryTextF;
    private JLabel descriptionTextF;
    private JLabel durationTextF;
    private JLabel dateTextF;
    private JPanel panelToDisplay;
    
    public PomDataSingleDisplayer(PomodoroUnit pomU) {
        dateTextF = new JLabel(String.valueOf(pomU.getDate()));
        
        categoryTextF = new JLabel(pomU.getCategory());
        
        descriptionTextF = new JLabel(pomU.getDescription());
        
        durationTextF = new JLabel(String.valueOf(pomU.getDuration()));
        
        panelToDisplay = new JPanel();
        panelToDisplay.setBorder(BorderFactory.createLineBorder(Color.yellow, 3, true));
        panelToDisplay.add(dateTextF);
        panelToDisplay.add(categoryTextF);
        panelToDisplay.add(descriptionTextF);
        panelToDisplay.add(durationTextF);
    }
    public JPanel getPanelToDisplay(){
        return this.panelToDisplay;
    }
}
