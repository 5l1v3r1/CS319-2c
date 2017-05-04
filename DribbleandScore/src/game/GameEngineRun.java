package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by boranyildirim on 4.05.2017.
 */
public class GameEngineRun extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        root.setId("pane");
        ImageView background = new ImageView(new Image(GameEngineRun.class.getResourceAsStream("images/SeBg.jpg")));
        background.setFitWidth(800);
        background.setFitHeight(600);

        Pane initialLayout = new Pane();
        initialLayout.getChildren().addAll(background);
        Scene scene = new Scene(initialLayout,800,600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
