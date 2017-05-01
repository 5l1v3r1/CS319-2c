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
    private ImageView ball;
    @Override
    public void start(Stage primaryStage)
    {
       window = primaryStage;
       ball= new ImageView(new Image(ShootEngine.class.getResourceAsStream("images/ball.png")));
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
       initialLayout.getChildren().addAll(ball,directionBarPane,powerBarPane);
       start = new Scene(initialLayout,800,600);
       
       window.setScene(start);
       window.setTitle("Dribble and Score");
       window.show();
       Timeline direction = new Timeline(new KeyFrame(
        Duration.millis(50),
        ae -> doSomething(directionBarRec)));
        direction.setCycleCount(Animation.INDEFINITE);
        
        direction.play();
       Timeline power = new Timeline(new KeyFrame(
        Duration.millis(50),
        ae -> doSomething(powerBarRec)));
        power.setCycleCount(Animation.INDEFINITE);
        
        
        start.setOnMouseClicked((event) -> {
       mouseCount++;
       if(mouseCount==1)
       {
           direction.stop();
           isEmpty=false;
           power.play();
       }
       else if(mouseCount==2)
           power.stop();
      
       });
       
       
       
    }
   
    public void doSomething(Rectangle barRec){
        
        if(!isEmpty)
        { 
            barRec.setWidth(barRec.getWidth()-10);
            if(barRec.getWidth()==0)
                isEmpty = true;
        }
        else
        {
            barRec.setWidth(barRec.getWidth()+10);
                if(barRec.getWidth()==160)
                    isEmpty=false;
        }    
        } 
}
