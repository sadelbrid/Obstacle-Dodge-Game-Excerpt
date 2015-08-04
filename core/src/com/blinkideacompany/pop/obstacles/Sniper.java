package com.blinkideacompany.pop.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.blinkideacompany.pop.Player;

/**
 * Created by dgallagher on 7/2/15.
 */
public class Sniper extends Obstacle {

    Console console;
    Point sniperPoint = null;

    public Sniper(Player p, int w, int h) {
        super(p, w, h);

        int x, y;
        x = y = 0;

        console.println(screenHeight+","+screenWidth);


        Point point = randPoint(p);
        sniperPoint = point;


        parts.add(new Part(1, (int) (screenWidth * .1), Part.TYPE_CIRCLE, point.x, point.y));
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);

    }


    public void addPart(Part part) {
        parts.add(part);
    }

    @Override
    public boolean update() {
        console.println("sniper is here");
        parts.get(0).translateX((int) (screenWidth * .01));
        if (parts.get(0).center.y > screenHeight / 2 && parts.get(1).center.y < screenHeight / 2)
            finished = true;
        return parts.get(0).contains(player.x, player.y) || parts.get(1).contains(player.x, player.y);
    }

    @Override
    public void draw(Canvas canvas) {
        Log.i("sniperPos", sniperPoint.x + "," + sniperPoint.y);
        parts.get(0).draw(canvas, paint);
    }

    private Point randPoint(Player p) {
        int x = (int) Math.random() * (screenWidth / 5) + p.x;
        int y = (int) Math.random() * (screenHeight / 5) + p.y;
        return new Point(x, y);
    }
}