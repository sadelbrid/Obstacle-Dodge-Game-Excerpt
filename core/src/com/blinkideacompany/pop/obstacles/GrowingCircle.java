package com.blinkideacompany.pop.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.blinkideacompany.pop.Player;

/**
 * Created by dgallagher on 7/23/15.
 */
public class GrowingCircle extends Obstacle {


    public GrowingCircle(Player p, int w, int h) {
        super(p, w, h);
        //spawn opposite of the player
        int buffer = 5;
        int xSpawn=0, ySpawn=0;

        //off screen top
        if(p.y<=buffer){

            if(p.x<=buffer) xSpawn=0;
            else if(p.x>=w-buffer) xSpawn=w;
            ySpawn=0;

        }
        //off screen bottom
        else if(p.y>=h-buffer){

            if(p.x<=buffer) xSpawn=0;
            else if(p.x>=w-buffer) xSpawn=w;
            ySpawn=h;

        }

        else{
            //spawn in opposite position
            xSpawn=p.y;
            ySpawn=p.x;
        }

        parts.add(new Part(1, 1, Part.TYPE_CIRCLE, xSpawn,ySpawn));
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        parts.get(0).draw(canvas, paint);
    }

    @Override
    public boolean update() {
        parts.get(0).radius += player.size * .20;
        if (parts.get(0).radius >= screenWidth * .85) finished = true;
        return parts.get(0).contains(player.x, player.y);
    }

}