package DataManager;


import utils.GameType;
import java.io.*;
import java.util.Scanner;
import java.net.URL;
import javax.swing.ImageIcon;


/**
 * Created by batikan on 27.04.2017.
 */
public class DataManager {
    public static ImageIcon characterImage;
    private static int unLevels;
    private int score;
    private final String[] highScores = new String[10];
    public  final String [] settings = new String[6];
    private URL url,url2;
    private File scorefile,configFile;

    public static void main(String[] args) {
        //Testing
        DataManager test = new DataManager();
        test.saveHighScore(32,10);
        test.saveHighScore(54,10);
        test.saveHighScore(15,2);
        test.saveHighScore(51,1);
        test.saveHighScore(32,1);

        String [] abc = test.getHighScores();
        for(int i = 0; i<10; i++){
            System.out.println(abc[i]);
        }
        /*ABOUT SETTING.TXT
        FIRST LINE INDICATES CHARACTER IMAGE NAME
        2ND,3RD,4TH,5TH INDICATES WASD AND ARROW KEY ADJUSTMENT(NOT COMPLETED)
        LAST LINE INDICATES SINGLE PLAYER OR MULTIPLAYER OPTION 0 = SINGLE PLAYER, 1 IS MULTIPLAYER
        */

        System.out.println("Settings");
        String [] aSet = test.getSettingsData();
        for (int x = 0; x<6 ; x++)
            System.out.println(aSet[x]);

        System.out.println("Game Type Test");

        System.out.println("Game is: " + test.getGameType());

        System.out.println("Change game type test");
        GameType game = GameType.MULTIPLAYER;
        test.setGameType(game);
        System.out.println(test.getGameType());


    }//end of main

    //Data Manager
    public DataManager() {

        //open and set the file for highscore
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
        //file for settings
        url2 = getClass().getResource("settings.txt");
        configFile = new File(url2.getPath());
        Scanner confScanner = null;
        try{
            confScanner = new Scanner(configFile);
            for(int j = 0; j <6; j++) {
                if(confScanner.hasNext())
                    settings[j] = confScanner.nextLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            confScanner.close();
        }

        characterImage = new ImageIcon(settings[0]);
        GameType gameType = GameType.SINGLEPLAYER;
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

    //SaveHighScore
    public void saveHighScore(int score, int level){
        Scanner fileScanner = null;
        try{
            fileScanner = new Scanner(scorefile);
            for(int i = 0; i < 10; i++){
                String data = fileScanner.nextLine();
                String[] parts = data.split(",");
                int levelScores = Integer.parseInt(parts[i]);
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

    //GameType
    public GameType getGameType(){
        if(Integer.parseInt(settings[5]) == 0)
            return GameType.SINGLEPLAYER;
        else
            return GameType.MULTIPLAYER;

    }//end of GameType

    public void updateSettings(){
        try{
            PrintWriter writer = new PrintWriter(configFile, "UTF-8");
            for (int i = 0; i<6; i++)
                writer.println(settings[i]);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }//end of updateSettings

    //setGameType
    public void setGameType(GameType gameType){

        if(gameType.equals("SINGLEPLAYER")){
            settings[5] = "0";
        }
        else
            settings[5] = "1";
        updateSettings();
    }//end of setGameType

    //setPlayerImage
    public void setPlayerImage(String [] settings){
        characterImage = new ImageIcon(settings[0]);
    }//end of setPlayerImage

    //getPlayerImage
    public ImageIcon getPlayerImage(){
        return characterImage;
    }//end of getPlayerImage

    //reset HighScore
    public void resetHighScore(){
        for(int i = 0; i<10; i++)
        highScores[i] = 0 + "," + (i+1);
    }//end of resetHighScıre
}//end of DataManager