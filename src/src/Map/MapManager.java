package Map;

import java.util.ArrayList;

/**
 * Created by boranyildirim on 21.04.2017.
 */

public class MapManager {

    // attributes ----------

    // constant for width of the bitmap
    private final int WIDTH = 100;

    /* ArrayList for storing the info of obstacles
     * Object will be changed with Obstacle  */
    private ArrayList<Object> obstacles;

    // length of the map of the level
    private int distance;

    /*
    Store the bits of the coordinates of map.
    If that bit contains enemy, it will 1,
    if does not contain, it will 0.
     */
    private Boolean[][] bitmap;

    /*
     Map manager for Singleton Pattern, unique for all levels.
     */
    private static MapManager mapManagerInstance;


    // functions ----------

    // private constructor for Singleton Pattern
    // @param level = level of the current level,
    // the map difficulty differentiate according to level
    private MapManager(int level) {
        // construct Map.MapManager
        obstacles = new ArrayList<>();
        distance = setDistance(level);   // it will change according to the level
        bitmap = new Boolean[WIDTH][distance];
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

    // Returns the number of obstacles left in the obstacles ArrayList.
    public int obstacleLeft() {
        return obstacles.size();
    }

    // Returns the mapmInstance.
    public MapManager getMap() {
        return mapManagerInstance;
    }

    /* set the distance of the map according to level
      return the distance */
    private int setDistance(int level) {
        return 0;
    }
}
