package game;

import data.DataManager;
import gameobj.Bonus;
import gameobj.MainCharacter;
import gameobj.Obstacle;
import javafx.scene.image.Image;
import map.MapManager;
import utils.GameType;

/**
 * Created by boranyildirim on 3.05.2017.
 */
public class GameEngine {

    // attributes --------

    //
    private Obstacle[] obstacleInMap;
    private boolean collisionHit;
    private GameType gameType;

    private int level;

    private Image grass;

    private int obstacleLeft;

    private boolean isPaused;

    private DataManager dataManager;

    private Bonus[] bonusInMap;

    private static GameEngine engine;

    private MainCharacter [] characters;

    private MapManager map;

    private String[] settings;


    // functions --------

    // private constructor for singleton pattern
    private GameEngine(GameType gameType) {
        //init data manager and then load the settings to engine
        dataManager = new DataManager();
        settings = dataManager.getSettingsData();

        // init map instance
        map = MapManager.init(gameType, Integer.parseInt(settings[2]));

        /*
        map.printMap(21);
        System.out.println();
        for (int x = 0; x < 3 ; x++)
            System.out.println(settings[x]);*/
        placeObjectsToMap();
    }

    /* Initialize the object as singleton pattern.
       Check whether the GameEngine is null or not before the creation of new object.
       @param gameType = current game type,
       @param level = level of the current level */
    public static GameEngine init(GameType gameType) {
        if (engine == null)
            engine = new GameEngine(gameType);

        return engine;
    }

    private void placeObjectsToMap() {
        byte[][] bitmap = map.getMap();

        int i_len = bitmap.length;
        int j_len = bitmap[1].length;

        // this two variables are for waiting a second to draw one more row
        // in the for loop you can see what happens
        long t, end;

        for (int i = 0; i < i_len; i++) {

            t = System.currentTimeMillis(); // get current time
            end = t + 1000;     // add 1 second to current time
            // run 1 second and show grass in every where
            while (System.currentTimeMillis() < end) {
                //draw grass
            }

            // if the first 7bits include a nonzero then draw it resume like this. 7 7 7
            // if one object is drawed it must jump to next 7.
            // check in single player for if 2 of them is obstacle then don't look 3rd
            // check in multi player for if 3 of them is obstacle then don't look 4th and 5th
            for (int j = 0; j < j_len; j++) {
                if (bitmap[i][j] == 1) {
                    // draw obstacle
                }
                else if (bitmap[i][j] == 2) {
                    // draw mud
                }
                else if (bitmap[i][j] == 3) {
                    // draw bonus
                }
            }
        }

    }
}
