package DataManager;

import java.io.*;
import java.util.Scanner;
import java.net.URL;


/**
 * Created by batikan on 27.04.2017.
 */
public class DataManager {

    private static int unLevels;
    private int score;
    private final String[] highScores = new String[10];
    public String [] settings;
    private URL url;
    private File scorefile;

    public static void main(String[] args) {
        DataManager test = new DataManager();
        test.saveHighScore(32,10);
        test.saveHighScore(54,10);
        test.saveHighScore(15,2);
        test.saveHighScore(51,1);
        String [] abc = test.getHighScores();
        for(int i = 0; i<10; i++){
            System.out.println(abc[i]);
        }


    }//end of main

    //Data Manager
    public DataManager() {
        //open and set the file
        url = getClass().getResource("highscores.txt");
        scorefile = new File(url.getPath());

        Scanner fileScanner = null;
        try{
            fileScanner = new Scanner(scorefile);
            for(int i = 0; i<10; i++) {
                if (fileScanner.hasNext())
                    highScores[i] = fileScanner.nextLine();
            }
            }catch(IOException e) {
                    e.printStackTrace();
            }finally{
                fileScanner.close();
            }

        score = 0;
        unLevels = 1; //Number of unlocked level

    }


    //getSettingsData
    public String[] getSettingsData(){
        return settings;
    }//end of getSettingData

    //unlockLevel
    public void unlockLevel(){
        unLevels++;
    }//end of unlocklevel

    //getHighScores
    public String[] getHighScores(){
        return highScores;
    }//end of getHighScores

    public void saveHighScore(int score, int level){
        Scanner fileScanner = null;
        try{
            fileScanner = new Scanner(scorefile);
            for(int i = 0; i < 10; i++){
                String data = fileScanner.nextLine();
                String[] parts = data.split(",");
                int levelScores = Integer.parseInt(parts[0]);
                if(levelScores < score){
                    highScores[level-1] = score + "," + level;
                    this.updateFile();
                    break;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileScanner.close();
        }
    }//end of saveHighScore

    public void updateFile(){
        try{
            PrintWriter writer = new PrintWriter("highscores.txt", "UTF-8");
            for(int i =0; i<10; i++)
                writer.println(highScores[i]);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void setSettingsData(String [] settings){
        this.settings = settings;
    }






}