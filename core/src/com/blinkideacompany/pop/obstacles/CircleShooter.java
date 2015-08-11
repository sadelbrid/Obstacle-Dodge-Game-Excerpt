package com.blinkideacompany.pop.obstacles;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.Player;

/**
 * Created by Seth on 7/16/15.
 */
public class CircleShooter extends Obstacle{
    boolean readyToShoot;
    boolean finishing;
    int numShooters;
    int activeShooter;
    int bulletSpeed;
    float timerTemp;
    float rotation = 5f;
    float growth = 15f;
    public CircleShooter(Player p, int w, int h, Obstacle ongoing) {
        super(p, w, h, ongoing);
        readyToShoot = false;
        int distanceFromPlayer = (int)(screenWidth * .3);
        numShooters = 6;
        activeShooter = 0;
        bulletSpeed = 10;
        timerTemp = 0;
        finishing = false;
        //Add shooters
        for(int i = 0; i < numShooters; i++){
            int x = (int)(Math.cos((2*Math.PI/numShooters)*i)*distanceFromPlayer) + player.x;
            int y = (int)(Math.sin((2*Math.PI/numShooters)*i)*distanceFromPlayer) + player.y;
            parts.add(new Part(3, 0, Part.TYPE_POLY, x, y));
        }
        color.set(1,0,0,1);
    }

    @Override
    public boolean update(float dt) {
        boolean isHit = false;
        //Track player
        for(int i = 0; i<numShooters; i++){
            parts.get(i).x_vel = player.x_vel;
            parts.get(i).y_vel = player.y_vel;
            parts.get(i).update(dt);
        }

        if(!readyToShoot){
            readyToShoot = true;
            for(int i = 0; i<numShooters; i++){
                parts.get(i).changeSize(growth*dt);
                if(parts.get(i).rotation < (i+3)*Math.PI/3){
                    parts.get(i).rotate(rotation*dt);
                    readyToShoot = false;
                }
            }
            if(readyToShoot) shoot();
        }
        else{
            if(!finishing) {
                //Update shooters
                for (int i = 0; i < numShooters; i++) {
                    parts.get(i).rotateAboutPoint(player.x, player.y, 2*dt);
                }
                //shoot
                timerTemp = (timerTemp > .25) ? shoot() : timerTemp + dt;
            }
            else{
                for(int i = 0; i<numShooters; i++){
                    parts.get(i).changeSize(-growth*dt);
                    parts.get(i).rotate(rotation*dt);
                    if(parts.get(i).radius <= 0) finished=true;
                }
            }
            for(int i = 0; i < ongoingObjects.parts.size(); i++)
                if(ongoingObjects.parts.get(i).contains(player.x, player.y)){
                    isHit = true;
                    break;
                }
        }
        return isHit;
    }

    @Override
    public void draw(ShapeRenderer s) {
        //draw shooters
        for(int i = 0; i< parts.size(); i++){
            parts.get(i).draw(s, this.color);
        }
    }

    private float shoot(){
        //Add bullet
        ongoingObjects.parts.add(new Part(1, (int) (screenWidth * .01),
                Part.TYPE_CIRCLE, parts.get(activeShooter).center.x,
                (int) parts.get(activeShooter).center.y));
        //Set velocity
        ongoingObjects.parts.get(ongoingObjects.parts.size()-1).x_vel = (int) (Math.cos(parts.get(activeShooter).rotation)*bulletSpeed);
        ongoingObjects.parts.get(ongoingObjects.parts.size()-1).y_vel = (int)(Math.sin(parts.get(activeShooter).rotation)*bulletSpeed);
        ongoingObjects.parts.get(ongoingObjects.parts.size()-1).setColor(CircleShooter.this.color);
        activeShooter++;
        if(activeShooter == numShooters-1){
            activeShooter = 0;
            CircleShooter.this.finishing = true;
        }
        return 0.0f;
    }
}
