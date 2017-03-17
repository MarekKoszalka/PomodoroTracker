package pomodorotracker;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SettingsFilesService {

    private FileReader     fReader    = null;
    private BufferedReader buffReader = null;
    private FileWriter     fWriter    = null;
    private BufferedWriter buffWriter = null;

    public SettingsFilesService() {
    }
    // TODO decide what way should be settings passed in program
    public List<String> loadSettings() {
        try {
            fReader = new FileReader("src/pomodoroTrackerSettings.txt");
            buffReader = new BufferedReader(fReader);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Can not open file with settings ");
        }
        ArrayList<String> SettingsList = new ArrayList<String>();
        try {
            String buffString;
            do {
                buffString = buffReader.readLine();
                if (buffString != null)
                    SettingsList.add(buffString);
            } while (buffString != null);
        } catch (IOException e) {
            System.out.println("Error: problems during reading the file");
            e.printStackTrace();
        }
        return SettingsList;
    }
    public void saveSettings(List<String> settingsList) {
        try {
            fWriter = new FileWriter("src/pomodoroTrackerSettings.txt");
            buffWriter = new BufferedWriter(fWriter);
        } catch (IOException e) {
            System.out.println("Error: Can not save data to file with settings");
        }
        try {
            for (int i = 0; i < settingsList.size(); i++) {
                buffWriter.write(settingsList.get(i));
                System.out.println("wnetrze SFS: " + settingsList.get(i));
                if (i + 1 < settingsList.size())
                    buffWriter.write(System.lineSeparator());
            }
            buffWriter.close();
            fWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Problems with writing settings to the file");
            e.printStackTrace();
        }
    }
}
