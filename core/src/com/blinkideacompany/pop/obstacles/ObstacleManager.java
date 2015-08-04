package com.blinkideacompany.pop.obstacles;

import android.graphics.Canvas;

import com.blinkideacompany.pop.Player;
import com.blinkideacompany.pop.obstacles.FallingBar;
import com.blinkideacompany.pop.obstacles.Obstacle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dgallagher on 7/5/15.
 */
public class ObstacleManager {

    ArrayList<Obstacle> obstacles;
    Player player;
    int screenWidth, screenHeight;
    boolean readyToAdd;
    public ObstacleManager(Player p, int w, int h) {
        obstacles = new ArrayList<>();
        player = p;
        screenWidth = w;
        screenHeight = h;
        readyToAdd = true;
    }

    private void addNewObstacle() {
        ObstacleType randObstacleType = ObstacleType.getRandomObstacleType();
        Obstacle newObstacle;
        switch (randObstacleType){
//            case SIMPLE_BOX_DODGE:
//                newObstacle = new SimpleBoxDodge(player, screenWidth, screenHeight);
//                break;
//            case SNIPER:
//                newObstacle = new Sniper(player, screenWidth, screenHeight);
//                break;
//            case GROWING_CIRCLE:
//                newObstacle=new GrowingCircle(player, screenWidth, screenHeight);
//                break;

            case FALLING_BAR:
                newObstacle=new FallingBar(player, screenWidth, screenHeight);
                break;
            default:
                newObstacle = null;
        }
        if(newObstacle != null) obstacles.add(newObstacle);
    }

    public void draw(Canvas canvas) {
        for(int i = 0; i<obstacles.size(); i++){
            obstacles.get(i).draw(canvas);
        }
    }

    public void update() {
        if(readyToAdd && obstacles.size() < 3){
            addNewObstacle();
            readyToAdd = false;
        }
        if(obstacles.size() > 0) {
            for (int i = 0; i < obstacles.size(); i++) {
                if (obstacles.get(i).update() || obstacles.get(i).finished) {
                    //Player hit

                    obstacles.remove(i--);
                    readyToAdd = true;
                }
            }
        }
    }

    public enum ObstacleType {
        //Declare Obstacle Types Here
        //SIMPLE_BOX_DODGE,
        //SNIPER,
        //GROWING_CIRCLE,
        FALLING_BAR;
        private static final ObstacleType[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();

        public static ObstacleType getRandomObstacleType() {
            return VALUES[RANDOM.nextInt(SIZE)];
        }
    }
}
