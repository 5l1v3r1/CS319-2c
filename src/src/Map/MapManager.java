package Map;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by boranyildirim on 21.04.2017.
 */

public class MapManager {

    // attributes ----------

    // constant for width and height of the bitmap
    private final int WIDTH = 100;

    // length of the map of the level
    private int distance;

    /*
    Store the bits of the coordinates of map.
    If that bit contains obstacle, it will 1,
    else if that bit contains mud, it will 2,
    if does not contain anything, it will 0.
     */
    private char[][] bitmap;

    // background grass
    private Graphics2D grass;


    // Map manager for Singleton Pattern, unique for all levels.
    private static MapManager mapManagerInstance;


    // functions ----------

    /* private constructor for Singleton Pattern
       @param level = level of the current level,
       the map difficulty differentiate according to level */
    private MapManager(int level) {
        // construct Map.MapManager
        distance = setDistance(level);
        bitmap = new char[WIDTH][distance];

    }

    /*Initialize the object as singleton pattern.
    Check whether the mapManagerInstance is null or not before the creation of new object. */
    // @param level = level of the current level,
    // the map difficulty differentiate according to level
    public MapManager init(int level) {
        if (mapManagerInstance == null)
            mapManagerInstance = new MapManager(level);

        return mapManagerInstance;
    }

    private void generateMap() {

    }

    private int getObstacleNumber() {
        return distance * 2;
    }

    private int getMudNumber() {
        return distance / 10;
    }

    // Returns the number of obstacles left in the obstacles ArrayList.
    public int obstacleLeft() {
        return 0;
    }

    // Returns the mapmInstance.
    public MapManager getMap() {
        return mapManagerInstance;
    }

    /* set the distance of the map according to level
       return the distance */
    private int setDistance(int level) {
        if (level < 20) {   // easy
            distance = 20;
        }
        else if (level < 50) {  // moderate
            distance = 50;
        }
        else if (level < 100) { // hard
            distance = 100;
        }
        else {  // legacy
            distance = 200;
        }
        return distance;
    }
}
