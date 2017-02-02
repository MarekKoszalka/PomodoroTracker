/*
 * To change this license header, choose License Headers in Project
 * Properties. To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodorotracker;


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marek
 */
public class DataFilesService {

    private FileReader     fReader    = null;
    // private BufferedReader buffReader = null;
    private FileWriter     fWriter    = null;
    private BufferedWriter buffWriter = null;

    public DataFilesService() {
    }
    public ArrayList<PomUnit> loadData() {
        try {
            this.fReader = new FileReader("src/PomodoroTrackerData.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Nie można było otworzyć pliku z danymi");
        }
        ArrayList<PomUnit> listOfPomUnits = new ArrayList<PomUnit>();
        PomUnit bufferPomUnit = new PomUnit();
        int charLoaded;
        int propertyCount = 0;
        String stringLoaded = "";
        try {
            do {
                charLoaded = (int) fReader.read();
                if ((charLoaded != ',') && (charLoaded != '\n') && (charLoaded != -1)) {
                    stringLoaded += (char) charLoaded;
                } else {
                    switch (propertyCount) {
                    case 0:
                        bufferPomUnit.setCategory(stringLoaded);
                        break;
                    case 1:
                        bufferPomUnit.setDescription(stringLoaded);
                        break;
                    case 2:
                        bufferPomUnit.setDuration(stringLoaded);
                        break;
                    case 3:
                        stringLoaded = "";// ta linijka powinna zostac
                                          // usunieta gdy czytanie
                                          // LocalDate bedzie juz
                                          // zaimplementowanie
                        bufferPomUnit.setDate(LocalDate.now());
                        break;
                    default:
                        break;
                    }
                    stringLoaded = "";
                    if (propertyCount < 3)
                        propertyCount++;
                    else {
                        listOfPomUnits.add(new PomUnit(bufferPomUnit));
                        propertyCount = 0;
                    }
                }
            } while (charLoaded != -1);
        } catch (IOException e) {
            System.out.println("Nastąpił błąd podczas wczytywania znaku z pliku");
        }
        return listOfPomUnits;
    }
    public void saveData(List<PomUnit> listPomUnit) {
        try {
            this.fWriter = new FileWriter("src/PomodoroTrackerData.txt");
            this.buffWriter = new BufferedWriter(fWriter);
            for (int index = 0; index < listPomUnit.size(); index++) {
                buffWriter.write(listPomUnit.get(index).getCategory());
                buffWriter.write(",");
                buffWriter.write(listPomUnit.get(index).getDescription());
                buffWriter.write(",");
                buffWriter.write(String.valueOf(listPomUnit.get(index).getDuration()));
                buffWriter.write(",");
                buffWriter.write(String.valueOf(listPomUnit.get(index).getDate()));
                if (index < listPomUnit.size() - 1) {
                    buffWriter.newLine();
                }
            }
            buffWriter.close();
            fWriter.close();
        } catch (IOException IOe) {
            System.out.println("Przechwycono IOException w funkcji saveData();");
        }
    }
}