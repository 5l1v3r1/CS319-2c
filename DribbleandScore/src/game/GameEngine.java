/**
 *
 * @author berke
 */
package game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import gameobj.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import map.MapManager;
import utils.GameType;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import data.DataManager;

public class GameEngine extends Application{


    public static void main(String[] args)
    {
        launch(args);
    }
    // public GameEngine(GameType gameType){
    // gameType= this.gameType;
    // map = MapManager.init(gameType, 2);
    // }
    private Rectangle card;
    private Stage window,shootingStage;
    private Scene game;
    private ImageView gameCharacter;
    private ImageView obstacleView; // could be more than 1
    private ImageView bonus;   //could be more than 1
    private ImageView background;
    private Timeline changeDirection, moveObstacle,scoreProgress;
    private KeyCode keyPress;
    private ImageView referee;
    private Pane gameLayout;
    private Label score;
    private int scoreCounter=0;
    private ArrayList<Obstacle> list;
    private ArrayList<Obstacle> refereeList;
    private int direction=0;

    private MapManager map;
    @Override
    public void start(Stage primaryStage)
    {
        map = MapManager.init(GameType.SINGLEPLAYER, 10);
        score = new Label();
        refereeList = new ArrayList<>();
        shootingStage = new Stage();
        window = primaryStage;

        background = new ImageView(new Image(GameEngine.class.getResourceAsStream("images/SeBg.jpg")));
        gameCharacter= new ImageView(new Image(GameEngine.class.getResourceAsStream("images/gameCharacter.png")));
        obstacleView= new ImageView(new Image(GameEngine.class.getResourceAsStream("images/mud.png")));
        referee = new ImageView(new Image(GameEngine.class.getResourceAsStream("images/referee.png")));
        //initial layout for game Character
        gameCharacter.setLayoutX(350);
        gameCharacter.setLayoutY(300);
        //size of gameCharacter
        gameCharacter.setFitHeight(210);
        gameCharacter.setFitWidth(120);

        list = new ArrayList<>();
        runMap();

        scoreProgress = new Timeline(new KeyFrame(
                Duration.millis(50),
                ae -> scoreChange(scoreCounter)));
        scoreProgress.setCycleCount(Animation.INDEFINITE);

        scoreProgress.play();

        changeDirection = new Timeline(new KeyFrame(
                Duration.millis(50),
                ae -> changeDir(gameCharacter,direction)));
        changeDirection.setCycleCount(Animation.INDEFINITE);


        gameLayout = new Pane();
        gameLayout.getChildren().addAll(background,gameCharacter);
        game = new Scene(gameLayout,800,600);

        for (int i = 0; i < list.size(); i++) {

            long t = System.currentTimeMillis(); // get current time
            long end = t + 1000;

            while (System.currentTimeMillis() < end) {
                // move the current obstacles before sending new ones
                moveObstacle = new Timeline(new KeyFrame(
                        Duration.millis(50),
                        ae -> moveObst()));
                moveObstacle.setCycleCount(Animation.INDEFINITE);
                moveObstacle.play();

            }

            if (i % 3 == 1)
                genObst(list.get(i), 40);
            else if (i % 3 == 2)
                genObst(list.get(i), 200);
            else if (i % 3 == 0)
                genObst(list.get(i), 360);
        }

        game.setOnKeyPressed((KeyEvent e) -> {
                    KeyCode keyPress = e.getCode();

                    if(keyPress == KeyCode.LEFT)
                    {   direction=1;
                        changeDirection.play();
                    }


                    else if(keyPress == KeyCode.RIGHT)
                    {
                        direction=2;
                        changeDirection.play();
                    }

                }
        );

        window.setScene(game);
        window.setTitle("Dribble and Score!");
        window.show();
    }
    private int counter=0;

    public void genObst(Obstacle obstacle, int xLoc)
    {
        if(obstacle.getType()==1)
            obstacle.setImage(new ImageView(new Image(GameEngine.class.getResourceAsStream("images/mud.png"))));
        else if(obstacle.getType()==2)
            obstacle.setImage(new ImageView(new Image(GameEngine.class.getResourceAsStream("images/referee.png"))));
        obstacle.getImageView().setFitHeight(70);
        obstacle.getImageView().setFitWidth(70);
        obstacle.getImageView().setLayoutX(30+xLoc);
        obstacle.getImageView().setLayoutY(40);
        gameLayout.getChildren().add(obstacle.getImageView());
    }

    public void moveObst()
    {
        for(Obstacle obst : list)
        {
            Label label1= new Label();
            if(obst.getImageView().getLayoutY()>=-40)
                obst.getImageView().setLayoutY(obst.getImageView().getLayoutY()+2);
            if(gameCharacter.getBoundsInParent().intersects(obst.getImageView().getBoundsInParent()))
            {
                if(obst.getType()==1)
                    decreaseScore(50);
                else if(obst.getType()==2)
                {
                    if(obst.getHit()==false)
                    {
                        showCard();
                        obst.setHit(true);
                    }
                }
                else if(obst.getType()==3)
                    window.hide();

                list.remove(obst);
            }
// else
            // quitGame();
        }
    }

    private int cards=0;
    public void showCard()
    {
<<<<<<< HEAD
        card = new Rectangle(30,45);
        card.setLayoutX(650);
        card.setLayoutY(30);
        card.setFill(Color.YELLOW);
        cards++;
        if(cards<2)
            gameLayout.getChildren().add(card);
        else
            window.hide();
        //quit
=======
       card = new Rectangle(30,45);
       card.setLayoutX(650);
       card.setLayoutY(30);
       card.setFill(Color.YELLOW);
       cards++;
       if(cards<2){         
           gameLayout.getChildren().add(card);
           //New added
           Media sound=new Media(new File("src/Sounds/Referee.mp3").toURI().toString());
           MediaPlayer mediaPlayer=new MediaPlayer(sound);
           mediaPlayer.play();
           //ends new added
       }
       else
         window.hide();
         //quit  
>>>>>>> e14d33708ab72a9181dfdaa14a28b3a795b4f047
    }
    public void changeDir(ImageView player,int direction){
        counter+=15;
        if(counter<150)
        {

            if(direction==1)
                player.setLayoutX(player.getLayoutX()-15);
            else if(direction==2)
                player.setLayoutX(player.getLayoutX()+15);
        }
        else
        {
            counter=0;
            changeDirection.stop();
        }

    }
    public void scoreChange(int scoreCnt){
        String Scr = Integer.toString(scoreCounter);

        if(scoreCounter%10==0)
        {
            score.setLayoutX(700);
            score.setLayoutY(70);

            if(gameLayout.getChildren().contains(score));
            gameLayout.getChildren().remove(score);
            score.setText(Scr);

            gameLayout.getChildren().add(score);
        }

        scoreCounter++;
    }
    public void decreaseScore(int score)
    {
        scoreCounter-=5;
    }

    private void runMap() {
        byte[][] bitmap = map.getMap();

        int i_len = bitmap.length;
        int j_len = bitmap[1].length;

        // this two variables are for waiting a second to draw one more row
        // in the for loop you can see what happens
        long t, end;

        for (int i = 0; i < i_len; i++) {

            // if the first 7bits include a nonzero then draw it resume like this. 7 7 7
            // if one object is drawn it must jump to next 7.
            // check in single player for if 2 of them is obstacle then don't look 3rd
            // check in multi player for if 3 of them is obstacle then don't look 4th and 5th
            byte lineCount = 0;
            for (int j = 0; j < j_len; j++) {
                if (lineCount != (j_len / 2 + 1)) {
                    if (bitmap[i][j] == 1) {
                        // draw obstacle
                        Obstacle obs = new Obstacle();
                        obs.setType(1);
                        list.add(obs);
                        lineCount++;
                        j = j + (7 - j % 7);
                    } else if (bitmap[i][j] == 2) {
                        // draw mud
                        Obstacle ref = new Obstacle();
                        ref.setType(2);
                        list.add(ref);
                        lineCount++;
                        j = j + (7 - j % 7);
                    } else if (bitmap[i][j] == 3) {
                        // draw bonus
                        lineCount++;
                        j = j + (7 - j % 7);
                    }
                }
            }
        }
    }
}
