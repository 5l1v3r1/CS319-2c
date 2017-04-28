/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dribbleandscore;

/**
 *
 * @author batikan 
 */
import javax.swing.ImageIcon;

/**
 * Created by batikan on 27.04.2017.
 */
public class DataManager {

    private static int level;
    public static ImageIcon[] characterImage;
    public static ImageIcon[] obstaclesImage;
    private int score;
    private final int[] highScores = new int [10]; // Stores highscore of  each level & num of level = 10
    public String [] settings;

    public static void main(String[] args) {

        //TESTING THE METHODS
        DataManager data = new DataManager();


        int[] scores = {5, 2, 4, 1, 6, 2, 4, 8, 3, 15}; // HER LEVELIN HIGH SCORE Sİ
        int[] defHscore = data.getHighScores();
        for(int i= 0; i<10; i++){ //Default Highscore print
            System.out.print(defHscore[i] + " ");

        }
        System.out.println();
        data.unlockLevel(); //unlock 1 level
        data.unlockLevel();
        data.unlockLevel();
        data.saveHighScore(scores); // LEVELLERIN HİGH SCORELERINI HİGHSCOREYE ATma
        int [] hscores = data.getHighScores();
        for(int i= 0; i<10; i++){ //Highscore print
            System.out.print(hscores[i] + " ");
        }

        data.setCharacterImage(3); // Gives error
        data.setCharacterImage(1);


    }

    public DataManager() {
        score = 0;
        level = 0; //Number of unlocked level
        characterImage = new ImageIcon[2];
        for(int i = 0; i< 10; i++){
            highScores[i] = score;
        }

    }

    public String[] getSettingsData(){
        return settings;
    }

    public void unlockLevel(){
        level++;
    }

    public void setCharacterImage(int index) {
        /*IMPORTANT: I assumed that we have 3 different images for character. If index is >=3 give error message
          Images are used for demonstration, it will changed to the appropriate images
        */

        if (index >= 3)
            System.out.println("Index is higher than number of character images");
        else if (index == 0) //assing image1 to character
            characterImage[0] = new ImageIcon("image1.png");
        else if (index == 1) // assing image2 to character
            characterImage[1] = new ImageIcon("image2.png");
        else if (index == 2) // assing image3 to character
            characterImage[2] = new ImageIcon("image3.png");


    }

    public int[] getHighScores(){
        return highScores;
    }

    public void saveHighScore(int [] scores){
        for(int i = 0; i<level; i++) { //put highscores for locked levels
            highScores[i] = scores[i]; //for each level it saves highscore of this level
        }
    }

    public void setSettingsData(String [] settings){
        this.settings = settings;
    }






}