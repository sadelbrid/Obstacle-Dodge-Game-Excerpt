package com.blinkideacompany.pop.obstacles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.blinkideacompany.pop.Player;

import java.util.ArrayList;

/**
 * Created by dgallagher on 7/29/15.
 */
public class FallingBar extends Obstacle {

    public FallingBar(Player p, int w, int h) {
        super(p, w, h);


        ArrayList<Part.Point> points = new ArrayList<Part.Point>();
        points.add(new Part.Point((int) (w * .2), (int) (h * .1)));
        points.add(new Part.Point((int) (w * .8), (int) (h * .1)));
        points.add(new Part.Point((int) (w * .8), (int) (h * .2)));
        points.add(new Part.Point((int) (w * .2), (int) (h * .2)));

        parts.add(new Part(points));
        paint = new Paint();
        paint.setColor(Color.DKGRAY);
        paint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        parts.get(0).draw(canvas, paint);
    }

    int rotatedBy = 0;
    double rotationConstant = .05;

    @Override
    public boolean update() {

        parts.get(0).rotate(Math.PI/2);

        if (rotatedBy == 90) finished = true;

        return parts.get(0).contains(player.x, player.y);

    }
}
