package com.blinkideacompany.pop.obstacles;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.Player;

import java.util.ArrayList;

/**
 * Created by dgallagher on 7/29/15.
 */
public class FallingBar extends Obstacle {

    public FallingBar(Player p, int w, int h, Obstacle ongoing) {
        super(p, w, h, ongoing);
        ArrayList<Part.Point> points = new ArrayList<Part.Point>();
        points.add(new Part.Point((int) (w * .2), (int) (h * .1)));
        points.add(new Part.Point((int) (w * .8), (int) (h * .1)));
        points.add(new Part.Point((int) (w * .8), (int) (h * .2)));
        points.add(new Part.Point((int) (w * .2), (int) (h * .2)));
        parts.add(new Part(points));
    }

    @Override
    public void draw(ShapeRenderer s) {
        parts.get(0).draw(s, this.color);
    }

    int rotatedBy = 0;
    double rotationConstant = .05;

    @Override
    public boolean update(float dt) {

        parts.get(0).rotate((float)(Math.PI/2));

        if (rotatedBy == 90) finished = true;

        return parts.get(0).contains(player.x, player.y);

    }

}
