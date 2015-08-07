package com.blinkideacompany.pop.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dgallagher on 7/5/15.
 */
public class ObstacleManager {
    ArrayList<Obstacle> obstacles;
    Obstacle ongoingObjects;
    Player player;
    int screenWidth, screenHeight;
    boolean readyToAdd;

    public ObstacleManager(Player p, ShapeRenderer sr, int w, int h) {
        obstacles = new ArrayList<Obstacle>();
        ongoingObjects = new Obstacle(p, w, h, ongoingObjects) {
            @Override
            public boolean update(float dt) {
                for(int i = 0; i<parts.size(); i++){
                    parts.get(i).update();
                    if(parts.get(i).center.x < 0 ||parts.get(i).center.x > screenWidth
                            || parts.get(i).center.y < 0 || parts.get(i).center.y > screenHeight){
                        parts.remove(i--);
                    }
                }
                return false;
            }

            @Override
            public void draw(ShapeRenderer s) {
                for(int i = 0; i<parts.size(); i++){
                    parts.get(i).draw(s, parts.get(i).color);
                }
            }
        };

        player = p;
        screenWidth = w;
        screenHeight = h;
        readyToAdd = true;
    }

    private void addNewObstacle() {
        ObstacleType randObstacleType = ObstacleType.getRandomObstacleType();
        Obstacle newObstacle;
        switch (randObstacleType){
            case SIMPLE_BOX_DODGE:
                newObstacle = new SimpleBoxDodge(player, screenWidth, screenHeight, ongoingObjects);
                break;
            case CIRCLE_SHOOTER:
                newObstacle = new CircleShooter(player, screenWidth, screenHeight, ongoingObjects);
                break;
//            case SNIPER:
//                newObstacle = new Sniper(player, screenWidth, screenHeight, ongoingObjects);
//                break;
//            case GROWING_CIRCLE:
//                newObstacle=new GrowingCircle(player, screenWidth, screenHeight, ongoingObjects);
//                break;

//            case FALLING_BAR:
//                newObstacle=new FallingBar(player, screenWidth, screenHeight, ongoingObjects);
//                break;
            default:
                newObstacle = null;
        }
        if(newObstacle != null) obstacles.add(newObstacle);
    }

    public void draw(ShapeRenderer s) {
        for(int i = 0; i<obstacles.size(); i++){
            obstacles.get(i).draw(s);
        }
    }

    public void update(float dt) {
        if(readyToAdd && obstacles.size() < 3){
            addNewObstacle();
            readyToAdd = false;
        }
        if(obstacles.size() > 0) {
            for (int i = 0; i < obstacles.size(); i++) {
                if (obstacles
                        .get(i)
                        .update(dt)
                        || obstacles.get(i).finished) {
                    //Player hit
                    obstacles.remove(i--);
                    readyToAdd = true;
                }
            }
        }
    }

    public enum ObstacleType {
        //Declare Obstacle Types Here
        SIMPLE_BOX_DODGE,
        CIRCLE_SHOOTER;
        //SNIPER,
        //GROWING_CIRCLE,
        //FALLING_BAR;
        private static final ObstacleType[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();

        public static ObstacleType getRandomObstacleType() {
            return VALUES[RANDOM.nextInt(SIZE)];
        }
    }
}
