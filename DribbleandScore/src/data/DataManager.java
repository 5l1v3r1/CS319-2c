/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author batikan
 */
import utils.GameType;
import java.io.*;
import java.util.Scanner;
import java.net.URL;
import javax.swing.ImageIcon;


/**
 * Created by batikan on 27.04.2017.
 */
public class DataManager {
    private static ImageIcon characterImage;
    private static int unLevels;
    private String[] highScores;
    private String[] settings;
    private URL url,url2;
    private File scorefile,configFile;

    //Data Manager
    public DataManager() {

        highScores = new String[10];
        settings = new String[3];

        //open and set the file for highscore
        url = getClass().getResource("highscores.txt");
        scorefile = new File(url.getPath());
        try (Scanner fileScanner = new Scanner(scorefile)) {
            for (int i = 0; i < 10; i++) {
                if (fileScanner.hasNext())
                    highScores[i] = fileScanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //file for settings
        url2 = getClass().getResource("settings.txt");
        configFile = new File(url2.getPath());
        try (Scanner confScanner = new Scanner(configFile)) {
            for (int j = 0; j < 3; j++) {
                if (confScanner.hasNext())
                    settings[j] = confScanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        characterImage = new ImageIcon(settings[0]);
        unLevels = Integer.parseInt(settings[2]); //Number of unlocked level

    }


    //getSettingsData
    public String[] getSettingsData(){
        return settings;
    }//end of getSettingData

    //unlockLevel
    public void unlockLevel(){
        unLevels++;
        settings[2] = Integer.toString(unLevels);
    }//end of unlocklevel

    //getHighScores
    public String[] getHighScores(){
        return highScores;
    }//end of getHighScores

    //SaveHighScore
    public void saveHighScore(int score, String name){
        try (Scanner fileScanner = new Scanner(scorefile)) {
            for (int i = 0; i < 10; i++) {
                String data = fileScanner.nextLine();
                String[] parts = data.split(",");
                int levelScores = Integer.parseInt(parts[0]);
                if (levelScores < score) {
                    highScores[level - 1] = score + "," + name;
                    this.updateFile();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of saveHighScore

    //update File
    public void updateFile(){
        try{
            PrintWriter writer = new PrintWriter(scorefile, "UTF-8");
            for(int i =0; i<10; i++)
                writer.println(highScores[i]);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }//end of update file

    public void updateSettings(){
        try{
            PrintWriter writer = new PrintWriter(configFile, "UTF-8");
            for (int i = 0; i<3; i++)
                writer.println(settings[i]);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }//end of updateSettings

    //setPlayerImage
    public void setPlayerImage(String imageName) {
        settings[0] = "../images/" + imageName;
        characterImage = new ImageIcon(settings[0]);

        updateSettings();
    }//end of setPlayerImage

    //getPlayerImage
    public ImageIcon getPlayerImage(){
        return characterImage;
    }//end of getPlayerImage

    //reset HighScore
    public void resetHighScore(){
        for(int i = 0; i < 10; i++)
            highScores[i] = 0 + "," + 0;
        this.updateFile();
    }//end of resetHighScore
}//end of DataManager
