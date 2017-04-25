package Map;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by boranyildirim on 21.04.2017.
 *
 * !!!! IMPORTANT: STATIC FUNCTIONS AND VARIABLES WILL BE CHANGED
 *                 THEY ARE JUST FOR TESTING.
 */

public class MapManager {

    public static void main (String [] args) {
        MapManager map = init(10);
    }

    // attributes ----------

    private static final int WIDTH = 21;

    // length of the map of the level
    private static int distance;

    /*
    Store the bits of the coordinates of map.
    If that bit contains obstacle, it will 1,
    else if that bit contains mud, it will 2,
    if does not contain anything, it will 0.
     */
    private static byte[][] bitmap;


    // Map manager for Singleton Pattern, unique for all levels.
    private static MapManager mapManagerInstance;


    // functions ----------

    /* private constructor for Singleton Pattern
       @param level = level of the current level,
       the map difficulty differentiate according to level */
    private MapManager(int level) {
        // construct Map.MapManager
        distance = setDistance(level);
        bitmap = new byte[distance][WIDTH];
        generateMap();
    }

    /* Initialize the object as singleton pattern.
       Check whether the mapManagerInstance is null or not before the creation of new object.
       @param level = level of the current level,
       the map difficulty differentiate according to level */
    public static MapManager init(int level) {
        if (mapManagerInstance == null)
            mapManagerInstance = new MapManager(level);

        return mapManagerInstance;
    }

    private static void generateMap() {

        // fill the bitmap with mud
        addToMap((byte) 1, getObstacleNumber());

        // fill the bitmap with mud
        addToMap((byte) 2, getMudNumber());

        for (int i = 0; i < distance; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(bitmap[i][j]);
            }
            System.out.println();
        }
    }


    //Fill the array for adding a nonzero entry to bitmap
    private static void addToMap(byte type, int num) {
        // fill the bitmap
        for (int i = 0; i < num; i++) {
            // generate 2 random index for placing a nonzero entry
            int randX = ThreadLocalRandom.current().nextInt(0, distance);   // 0 to distance random
            int randY = ThreadLocalRandom.current().nextInt(0, WIDTH);      // 0 to WIDTH random

            // if the index is empty then fill it with @type
            if (bitmap[randX][randY] == 0)
                bitmap[randX][randY] = type;
            else {
                // the index is already filled, run loop one more time
                i--;
            }
        }
    }


    // return the number of obstacles should be in map
    private static int getObstacleNumber() {
        return distance * 3;
    }

    // returns the number of muds should be in map
    private static int getMudNumber() {
        return distance / 10;
    }

    // Returns the mapmInstance.
    public byte[][] getMap() {
        return bitmap;
    }

    /* set the distance of the map according to level
       return the distance
       NOTE: distance should be divisible by 3*/
    private int setDistance(int level) {
        if (level < 20) {   // easy
            distance = 21;
        }
        else if (level < 50) {  // moderate
            distance = 42;
        }
        else if (level < 100) { // hard
            distance = 84;
        }
        else {  // legacy
            distance = 168;
        }
        return distance;
    }
}
