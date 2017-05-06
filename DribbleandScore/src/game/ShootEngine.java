/*
author:berke
 */
package game;

import javafx.animation.Animation;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import data.DataManager;
/**
 *
 * @author berke
 */
public class ShootEngine extends Application {
    
    
    public static void main(String[] args){
        launch(args);
    }
    private int goalKeeperType;
    private boolean isEmpty=false;
    private int mouseCount=0; 
    private Stage window;
    private Scene start,shooting,end;
    private Rectangle directionBarRec,powerBarRec;
    private ImageView goalKeeper,goal;
    private ImageView ball,background;
    private Timeline shooter,hitsThePost;
    private Pane initialLayout;
    @Override
    public void start(Stage primaryStage)
    {
       window = primaryStage;
       ball= new ImageView(new Image(ShootEngine.class.getResourceAsStream("images/ball.png")));
       goalKeeper = new ImageView(new Image(ShootEngine.class.getResourceAsStream("images/goalKeeper.png")));
       goal = new ImageView(new Image(ShootEngine.class.getResourceAsStream("images/goal.png")));
       background = new ImageView(new Image(ShootEngine.class.getResourceAsStream("images/SeBg.jpg")));
       background.setFitWidth(800);
       background.setFitHeight(600);
       goal.setFitHeight(90);
       goal.setFitWidth(300);
       goal.setLayoutX(280);
       goal.setLayoutY(30);
       
       goalKeeper.setFitHeight(60);
       goalKeeper.setFitWidth(60);
       goalKeeper.setLayoutX(400);
       goalKeeper.setLayoutY(60);
       
       ball.setFitHeight(40);
       ball.setFitWidth(40);
       ball.setLayoutX(400);
       ball.setLayoutY(300);
       Rectangle outerRec = new Rectangle(170,60);
       outerRec.setFill(Color.BLACK);
       directionBarRec=  new Rectangle(160,50);
      
       directionBarRec.setFill(Color.RED);
       directionBarRec.setLayoutX(5);
       directionBarRec.setLayoutY(5);
       
       Pane directionBarPane = new Pane();
       directionBarPane.maxHeight(60);
       directionBarPane.maxWidth(170);
       
       directionBarPane.setLayoutX(50);
       directionBarPane.setLayoutY(520);
       directionBarPane.getChildren().addAll(outerRec,directionBarRec);
       //second shooting bar.
       
       Rectangle outerRec2 = new Rectangle(170,60);
       outerRec2.setFill(Color.BLACK);
       powerBarRec=  new Rectangle(160,50);
      
       powerBarRec.setFill(Color.RED);
       powerBarRec.setLayoutX(5);
       powerBarRec.setLayoutY(5);
       
       Pane powerBarPane = new Pane();
       powerBarPane.maxHeight(60);
       powerBarPane.maxWidth(170);
       
       powerBarPane.setLayoutX(350);
       powerBarPane.setLayoutY(520);
       powerBarPane.getChildren().addAll(outerRec2,powerBarRec);
       
       
      
    
        initialLayout = new Pane();
        initialLayout.getChildren().addAll(background,goal,goalKeeper,ball,directionBarPane,powerBarPane);
        start = new Scene(initialLayout,800,600);
       
        window.setScene(start);
        window.setTitle("Dribble and Score");
        window.show();
        
        Timeline direction = new Timeline(new KeyFrame(
        Duration.millis(50),
        ae -> barMovement(directionBarRec)));
        direction.setCycleCount(Animation.INDEFINITE);
        
        Timeline goalKeeperMove = new Timeline(new KeyFrame(
        Duration.millis(50),
        ae -> goalKeeperMovement(goalKeeperType)));
        goalKeeperMove.setCycleCount(Animation.INDEFINITE);
        goalKeeperType=2;
        direction.play();
        goalKeeperMove.play();
        Timeline power = new Timeline(new KeyFrame(
        Duration.millis(50),
        ae -> barMovement(powerBarRec)));
        power.setCycleCount(Animation.INDEFINITE);
        
        shooter = new Timeline(new KeyFrame(
        Duration.millis(40),
        e -> shootAnimation(ball,1)));
        shooter.setCycleCount(Animation.INDEFINITE);
        
        hitsThePost = new Timeline(new KeyFrame(
        Duration.millis(40),
        e -> shootAnimation(ball,-1)));
        hitsThePost.setCycleCount(Animation.INDEFINITE);
        
        start.setOnMouseClicked((event) -> {
       mouseCount++;
       if(mouseCount==1)
       {
           direction.stop();
           isEmpty=false;
           power.play();
       }
       else if(mouseCount==2)
       {
           power.stop();
           shooter.play();
       }
       });  
       
    }
    private boolean goesRight=true;
    public void goalKeeperMovement(int goalKeeperType)
    {
      if(goalKeeperType == 0) //lazy goalkeeper: just stands at his position.
      {
          
      }
      if(goalKeeperType == 1)
      if(goesRight)
       {
           goalKeeper.setLayoutX(goalKeeper.getLayoutX()+1);
           if(goalKeeper.getLayoutX()>500)
               goesRight=false;
       }
       else
       {
           goalKeeper.setLayoutX(goalKeeper.getLayoutX()-1);
           if(goalKeeper.getLayoutX()<300)
               goesRight=true;
       }
      else if(goalKeeperType == 2) //smart goal keeper: goes through the ball
      {
       
       
      if(ball.getLayoutX()-goalKeeper.getLayoutX()>0)
      {
          
          goalKeeper.setLayoutX(goalKeeper.getLayoutX()+1);
      
      }
      else if(ball.getLayoutX()-goalKeeper.getLayoutX()<0)
      {
          
          goalKeeper.setLayoutX(goalKeeper.getLayoutX()-1);
      }
          
      }
    }
    public void showText(int isGoal)
    {
        Label goalText = new Label();
        switch (isGoal) {
            case -1:
                goalText.setText("Bad Shot!");
                Media sound=new Media(new File("src/Sounds/badshot.mp3").toURI().toString());
                MediaPlayer mediaPlayer=new MediaPlayer(sound);
                mediaPlayer.play();
                break;
            case 0:
                goalText.setText("Saved by the goalkeeper!");
                Media sound2=new Media(new File("src/Sounds/aaaa.mp3").toURI().toString());
                MediaPlayer mediaPlayer2=new MediaPlayer(sound2);
                mediaPlayer2.play();
                
                break;
            case 2:
                goalText.setText("Oooh! It hits the post!");
                hitsThePost.play();
                // direkten çarpıp geri dönme hareketini başlat, kalan shot powerı al.
                // sol direkse sola sağsa sala harket ettir.
                break;
            default:
                goalText.setText("Gooooaaaal!!"); //Datamanager shoud make a save here
                Media sound4=new Media(new File("src/Sounds/Goal.mp3").toURI().toString());
                MediaPlayer mediaPlayer4=new MediaPlayer(sound4);
                mediaPlayer4.play();
                break;
        }
        goalText.setFont(new Font("Arial", 45));
        goalText.setLayoutX(200);
        goalText.setLayoutY(300);
        initialLayout.getChildren().add(goalText);

    }
    
    public void stopShoot()
       {
        if(shooter.getStatus().RUNNING==Animation.Status.RUNNING)
            shooter.stop();
        else if(hitsThePost.getStatus().RUNNING==Animation.Status.RUNNING)
            hitsThePost.stop();
       }
    public void barMovement(Rectangle barRec){
        System.out.println(ball.getLayoutX());
         

        if(!isEmpty)
        { 
            if(barRec.getWidth()<=0)
                isEmpty = true;
            else barRec.setWidth(barRec.getWidth()-10);
        }
        else
        {
            if(barRec.getWidth()>=160)
                isEmpty=false;
            
            else
                barRec.setWidth(barRec.getWidth()+10);
                
        }    
    }
    int shootDirection,directionCounter=0,powerTime=0;
    private double shootPower=1;
    
    public void shootAnimation(ImageView ball,int post){
        if(shootPower!=0)
            shootPower=getShootPower(powerTime);
        shootDirection=getShootDirection();
        if(post==1)
            ball.setLayoutY(ball.getLayoutY()-shootPower);
        else if(post==-1)
            ball.setLayoutY(ball.getLayoutY()+shootPower);
        ball.getTransforms().add(new Rotate(10,20,20));
        if(shootPower>1 ) //If no power, stop going right or left.
            if(post==1)
                ball.setLayoutX(ball.getLayoutX()+shootDirection);
            else if(post==-1)
                ball.setLayoutX(ball.getLayoutX()-shootDirection);
        powerTime++;
        int isGoal;
        
        if(ball.getLayoutY()<80 || shootPower<=0)
        { 
            stopShoot();
            if(ball.getBoundsInParent().intersects(goalKeeper.getBoundsInParent()))
            {
                isGoal=0;
            }
            else if(ball.getBoundsInParent().intersects(580,30,30,120) //30 90
                    || ball.getBoundsInParent().intersects(270,30,30,120)) //280 300
            {     isGoal=2; //hits post;
                 
            }
            else if(ball.getBoundsInParent().intersects(goal.getBoundsInParent()))
                isGoal=1;
            else
                isGoal=-1;
            if(post==1)
            showText(isGoal);
            
                
        }    
    }
    double getShootPower(double time)
    {
        int power=(int)powerBarRec.getWidth()/10;
        if(power>0)
                power-=time/10;
        if(power<0)
            power=0;
        return power;
    }
    int getShootDirection(){
        int direction = (int)directionBarRec.getWidth();
        direction = direction - 80;
        direction/=5;
        return direction;
    }
}
    

