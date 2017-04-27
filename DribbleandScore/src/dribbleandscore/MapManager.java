package dribbleandscore;

import dribbleandscore.utils.GameType;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by boranyildirim on 21.04.2017.
 *
 * !!!! IMPORTANT: STATIC FUNCTIONS AND VARIABLES WILL BE CHANGED
 *                 THEY ARE JUST FOR TESTING.
 *
 *  The map is generated 3 way for single player [7][7][7]
 *  and for multiplayer 5 ways [7][7][7][7][7]
 */

public class MapManager {

    public static void main (String [] args) {
        MapManager map = init(GameType.SINGLEPLAYER,10);

        if (gameType == GameType.SINGLEPLAYER)
            printMap(SP_WIDTH);
        else
            printMap(MP_WIDTTH);
    }

    // attributes ----------

    // constant WIDTH for single player map.
    private static final int SP_WIDTH = 21;

    // constant WIDTH for multi player map.
    private static final int MP_WIDTTH = 35;

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

    private static GameType gameType;


    // functions ----------

    /* private constructor for Singleton Pattern
       @param level = level of the current level,
       the map difficulty differentiate according to level */
    private MapManager(GameType gameType, int level) {
        // construct Map.MapManager
        distance = setDistance(level);

        MapManager.gameType = gameType;

        if (gameType == GameType.SINGLEPLAYER) {
            bitmap = new byte[distance][SP_WIDTH];
        }
        else {
            bitmap = new byte[distance][MP_WIDTTH];
        }

        generateMap();
    }


    /* Initialize the object as singleton pattern.
       Check whether the mapManagerInstance is null or not before the creation of new object.
       @param level = level of the current level,
       the map difficulty differentiate according to level */
    public static MapManager init(GameType gameType, int level) {
        if (mapManagerInstance == null)
            mapManagerInstance = new MapManager(gameType, level);

        return mapManagerInstance;
    }


    // @TEST print the map
    private static void printMap(int width) {
        for (int i = 0; i < distance; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(bitmap[i][j]);
                if (j != 0 && (j + 1) % 7 == 0)
                    System.out.print("\t");
            }
            System.out.println();
        }
    }


    // fill the map with obstacles(1) and muds(2)
    private static void generateMap() {
        if (gameType == GameType.SINGLEPLAYER) {
            addToMap((byte) 1, getObstacleNumber(), SP_WIDTH);
            addToMap((byte) 2, getMudNumber(), SP_WIDTH);
        }
        else {
            addToMap((byte) 1, getObstacleNumber(), MP_WIDTTH);
            addToMap((byte) 2, getMudNumber(), MP_WIDTTH);
        }
    }


    //Fill the array for adding a nonzero entry to bitmap
    private static void addToMap(byte type, int num, int width) {
        // fill the bitmap
        for (int i = 0; i < num; i++) {
            // generate 2 random index for placing a nonzero entry
            int randX = ThreadLocalRandom.current().nextInt(0, distance);   // 0 to distance random
            int randY = ThreadLocalRandom.current().nextInt(0, width);      // 0 to SP_WIDTH random

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
       return the distance */
    private int setDistance(int level) {
        if (level < 20) {   // easy
            distance = 30;
        }
        else if (level < 50) {  // moderate
            distance = 60;
        }
        else if (level < 100) { // hard
            distance = 90;
        }
        else {  // legacy
            distance = 150;
        }
        return distance;
    }
}