package com.blinkideacompany.pop.obstacles;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.blinkideacompany.pop.Player;

/**
 * Created by Seth on 7/16/15.
 */
public class CircleShooter extends Obstacle {
    boolean readyToShoot;
    int numShooters;
    public CircleShooter(Player p, int w, int h) {
        super(p, w, h);
        readyToShoot = false;
        int distanceFromPlayer = (int)(screenWidth * .1);
        numShooters = 6;
        //Add shooters
        for(int i = 0; i < numShooters; i++){
            int x = (int)(Math.cos((2*Math.PI/numShooters)*i)*distanceFromPlayer) + player.x;
            int y = (int)(Math.sin((2*Math.PI/numShooters)*i)*distanceFromPlayer) + player.x;
            parts.add(new Part(3, 0, Part.TYPE_POLY, x, y));
        }
        paint.setARGB(255, 255, 0, 0);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public boolean update() {
        /*
                angle   rotation
                0       3PI/3
                PI/3    4PI/3
                2PI/3   5PI/3
                PI      6PI/3
                4PI/3   7PI/3
                5PI/3   8PI/3
         */

        //Track player
        for(int i = 0; i<numShooters; i++){
            parts.get(i).x_vel = (int)player.x_vel;
            parts.get(i).y_vel = (int)player.x_vel;
            parts.get(i).update();
        }

        if(!readyToShoot){
            readyToShoot = true;
            for(int i = 0; i<numShooters; i++){
                parts.get(i).changeSize(1);
                if(parts.get(i).rotation < (i+3)*Math.PI/3){
                    parts.get(i).rotate(.2);
                    readyToShoot = false;
                }
            }
        }
        else{

        }

        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        //draw shooters
        for(int i = 0; i< numShooters; i++){
            parts.get(i).draw(canvas, paint);
        }
    }
}
