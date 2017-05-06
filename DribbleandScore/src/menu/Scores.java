package menu;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package menu;

/**
 *
 * @author batikan
 */

public class Scores {
    private String name;
    private int score;
    
    public Scores(){
        this.name = "";
        this.score = 0;
    }
    public Scores(String name, int score){
        this.name = name;
        this.score = score;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }
    
}
