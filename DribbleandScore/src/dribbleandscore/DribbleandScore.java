/*
 *  author: Berke Soysal
 */
package dribbleandscore;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DribbleandScore extends Application {
    private Stage window;
    private Scene mainMenu,playGame,settings,howToPlay,credits,highScores;
    private ImageView background,background2,background3,background4,background5,background6;
    @Override
    public void start(Stage primaryStage) {
        
        window= primaryStage;
        window.getIcons().add(new Image(DribbleandScore.class.getResourceAsStream("images/logoicon.png")));
        background = new ImageView(new Image(DribbleandScore.class.getResourceAsStream("images/wallpaperWithText.png")));
        background2 = new ImageView(new Image(DribbleandScore.class.getResourceAsStream("images/wallpaperWithText.png")));
        background3 = new ImageView(new Image(DribbleandScore.class.getResourceAsStream("images/wallpaperWithText.png")));
        background4 = new ImageView(new Image(DribbleandScore.class.getResourceAsStream("images/wallpaperWithText.png")));
        background5 = new ImageView(new Image(DribbleandScore.class.getResourceAsStream("images/wallpaperWithText.png")));
        background6 = new ImageView(new Image(DribbleandScore.class.getResourceAsStream("images/wallpaperWithText.png")));
        /* main menu part */
        
        Button playGameBtn = new Button();
        playGameBtn.setText("Play Game");
        playGameBtn.setLayoutX(350);
        playGameBtn.setLayoutY(130);
        playGameBtn.setMinSize(100, 50);
        playGameBtn.setOnAction((event) -> window.setScene(playGame));
        
        Button settingsBtn  = new Button();
        settingsBtn.setText("Settings");
        settingsBtn.setLayoutX(350);
        settingsBtn.setLayoutY(190);
        settingsBtn.setMinSize(100, 50);
        settingsBtn.setOnAction((event) -> window.setScene(settings));
        
        Button infoBtn = new Button();
        infoBtn.setText("How to Play?");
        infoBtn.setLayoutX(350);
        infoBtn.setLayoutY(250);
        infoBtn.setMinSize(100, 50);
        infoBtn.setOnAction((event) -> window.setScene(howToPlay));
        
        Button highScoresBtn = new Button();
        highScoresBtn.setText("High Scores");
        highScoresBtn.setLayoutX(350);
        highScoresBtn.setLayoutY(310);
        highScoresBtn.setMinSize(100,50);
        highScoresBtn.setOnAction((event) -> window.setScene(highScores));
        
        Button creditsBtn = new Button();
        creditsBtn.setText("Credits");
        creditsBtn.setLayoutX(350);
        creditsBtn.setLayoutY(370);
        creditsBtn.setMinSize(100,50);
        creditsBtn.setOnAction((event) -> window.setScene(credits));
        
        //above ones were for main menu ,below ones are for play game.
        //int levelsUnlocked = DataManager.getLevelInfo();
        int levelsUnlocked = 73; //This information will come from DataManager, this is just for an example.
        Button[] levels = new Button[levelsUnlocked];
        int posX = 20;
        int posY = 180;
        for(int i = 0; i<levels.length; i++)
        {
            levels[i] = new Button();
            levels[i].setText("Level" + (i+1));
            levels[i].setLayoutX(posX);
            levels[i].setLayoutY(posY);
            if((i+1)%12==0)
            {
                posY+=30;
                posX=20;
            }
            else
                posX+=60;
        }
        
        
        
        
        
        Button playToMainBtn = new Button();
        playToMainBtn.setText("Go back to Main Menu");
        playToMainBtn.setLayoutX(350);
        playToMainBtn.setLayoutY(500);
        playToMainBtn.setMinSize(100,50);
        playToMainBtn.setOnAction((event) -> window.setScene(mainMenu));
        
        //below the how to play part!
        
        Button infoToMainBtn = new Button();
        infoToMainBtn.setText("Go Back to Main Menu");
        infoToMainBtn.setLayoutX(350);
        infoToMainBtn.setLayoutY(500);
        infoToMainBtn.setMinSize(100, 50);
        infoToMainBtn.setOnAction((event) -> window.setScene(mainMenu));
        
        Label howToPlayText= new Label("How to Play: Use arrows and W,A,S,D \n buttons to control to player. Escape from  \nall obstacles, then"
                + " click on the right \n time for position and power to shoot the ball!");
        Rectangle howToRec = new Rectangle(250,100);
        Rectangle outerRec = new Rectangle(260, 110);
        outerRec.setFill(Color.BLACK);
        howToRec.setFill(Color.BEIGE);
        
        StackPane howToPane = new StackPane();
        howToPane.setLayoutX(280);
        howToPane.setLayoutY(240);
        howToPane.getChildren().addAll(outerRec,howToRec,howToPlayText);
        
        
        
        //below ***** credits Part
        Button creditsToMainBtn = new Button();
        creditsToMainBtn.setText("Go Back to Main Menu");
        creditsToMainBtn.setLayoutX(350);
        creditsToMainBtn.setLayoutY(500);
        creditsToMainBtn.setMinSize(100, 50);
        creditsToMainBtn.setOnAction((event) -> window.setScene(mainMenu));
        
        Label creditsText= new Label("Batıkan Hayta\nUmut Balkan Sencer\nBerke Soysal\n"
                + "Boran Yıldırım");
        Rectangle creditsRec = new Rectangle(250,100);
        Rectangle outerRec2 = new Rectangle(260, 110);
        outerRec2.setFill(Color.BLACK);
        creditsRec.setFill(Color.BEIGE);
        
        StackPane creditsPane = new StackPane();
        creditsPane.setLayoutX(280);
        creditsPane.setLayoutY(240);
        creditsPane.getChildren().addAll(outerRec2,creditsRec,creditsText);
        
        /* SETTINGS PART BELOW
        
        
        
        */
        Button settingsToMainBtn=new Button();
        settingsToMainBtn.setText("Go Back to Main Menu");
        settingsToMainBtn.setLayoutX(350);
        settingsToMainBtn.setLayoutY(500);
        settingsToMainBtn.setMinSize(100, 50);
        settingsToMainBtn.setOnAction((event) -> window.setScene(mainMenu));
        
        final ToggleGroup skins = new ToggleGroup();
        RadioButton skin1 = new RadioButton();
        
        skin1.setText("Skin 1");
        skin1.setToggleGroup(skins);
        skin1.setLayoutX(200);
        skin1.setLayoutY(300);
        RadioButton skin2 = new RadioButton();
        skin2.setLayoutX(270);
        skin2.setLayoutY(300);
        skin2.setText("Skin 2");
        skin2.setToggleGroup(skins);
        
        
        Button resetHSBtn = new Button();
        resetHSBtn.setText("Click here to delete the High Scores");
        resetHSBtn.setLayoutX(350);
        resetHSBtn.setLayoutY(400);
        
        
        
        /* SETTINGS PART ABOVE!! 
        
        */
        
        //below are scenes
        
        Pane menuLayout = new Pane();
        menuLayout.getChildren().addAll(background,playGameBtn, settingsBtn, infoBtn, highScoresBtn, creditsBtn);
        mainMenu = new Scene(menuLayout,800,600);
        
        Pane playLayout = new Pane();
        playLayout.getChildren().addAll(background2,playToMainBtn);
        for (Button level : levels) {
            playLayout.getChildren().add(level);
        }
        
        playGame= new Scene(playLayout,800,600);
        
        Pane infoLayout = new Pane();
        infoLayout.getChildren().addAll(background3,howToPane,infoToMainBtn);
        howToPlay= new Scene(infoLayout,800,600);
        
        Pane creditsLayout = new Pane();
        creditsLayout.getChildren().addAll(background4,creditsPane,creditsToMainBtn);
        credits = new Scene(creditsLayout,800,600);
        
        Pane settingsLayout = new Pane();
        settingsLayout.getChildren().addAll(background5,skin1,skin2,resetHSBtn,settingsToMainBtn);
        settings = new Scene(settingsLayout);        
        window.setScene(mainMenu);
        window.setTitle("Dribble & Score!");
        window.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
