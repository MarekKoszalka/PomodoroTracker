/*
 * To change this license header, choose License Headers in Project
 * Properties. To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodorotracker;


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Marek
 */
public class DataFilesService {

    private FileReader fReader = null;
    // private FileWriter fWriter = null;

    public DataFilesService() {
        try {
            fReader = new FileReader("src/PomodoroTrackerData.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Nie można było otworzyć pliku z danymi");
        }
    }
    public ArrayList<PomUnit> loadData() {
        ArrayList<PomUnit> listOfPomUnits = new ArrayList<PomUnit>();
        PomUnit bufferPomUnit = new PomUnit();
        // w tym momencie mamy otworzony nasz plik... ten plik
        // przechodzi przez
        // nasz FileReader bufor. Teraz trzeba użyć jakiejś funkcji
        // do
        // odczytywania poszczególnych znaków.
        // Czytanie w pętli (potem może być w czymś innym, jeśli
        // jest taka opcja)
        int charLoaded;
        int propertyCount = 0;
        String stringLoaded = "";
        try {
            do {
                charLoaded = (int) fReader.read();
                if ((charLoaded != ',') && (charLoaded != '\r') && (charLoaded != -1)) {
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
}