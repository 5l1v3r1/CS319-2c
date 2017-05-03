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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author berke
 */
public class ShootEngine extends Application {
    
    
    public static void main(String[] args){
        launch(args);
    }
     private boolean isEmpty=false;
    private int mouseCount=0; 
    private Stage window;
    private Scene start,shooting,end;
    private Rectangle directionBarRec,powerBarRec;
    private ImageView goalKeeper,goal;
    private ImageView ball;
    @Override
    public void start(Stage primaryStage)
    {
       window = primaryStage;
       ball= new ImageView(new Image(ShootEngine.class.getResourceAsStream("images/ball.png")));
       goalKeeper = new ImageView(new Image(ShootEngine.class.getResourceAsStream("images/goalKeeper.png")));
       goal = new ImageView(new Image(ShootEngine.class.getResourceAsStream("images/goal.png")));
       
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
       
       
      
    
        Pane initialLayout = new Pane();
        initialLayout.getChildren().addAll(ball,goal,goalKeeper,directionBarPane,powerBarPane);
        start = new Scene(initialLayout,800,600);
       
        window.setScene(start);
        window.setTitle("Dribble and Score");
        window.show();
        
        Timeline direction = new Timeline(new KeyFrame(
        Duration.millis(50),
        ae -> barMovement(directionBarRec)));
        direction.setCycleCount(Animation.INDEFINITE);
        
        direction.play();
        
        Timeline power = new Timeline(new KeyFrame(
        Duration.millis(40),
        ae -> barMovement(powerBarRec)));
        power.setCycleCount(Animation.INDEFINITE);
        
        Timeline shooter = new Timeline(new KeyFrame(
        Duration.millis(40),
        e -> shootAnimation(ball)));
        shooter.setCycleCount(Animation.INDEFINITE);
        
        
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
   
    public void barMovement(Rectangle barRec){
         System.out.println(barRec.getWidth());
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
    double shootPower;
    public void shootAnimation(ImageView ball){
        shootPower=getShootPower(powerTime);
        shootDirection=getShootDirection();
        System.out.println(shootPower);
        ball.setLayoutY(ball.getLayoutY()-shootPower);
        if(shootPower>1 ) //If no power, stop going right or left.
            ball.setLayoutX(ball.getLayoutX()+shootDirection);
        powerTime++;
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
    

