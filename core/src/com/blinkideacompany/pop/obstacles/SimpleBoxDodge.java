package com.blinkideacompany.pop.obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.Player;

/**
 * Created by Seth on 7/2/15.
 */
public class SimpleBoxDodge extends Obstacle {
    public SimpleBoxDodge(Player p, int w, int h, Obstacle ongoing){
        super(p, w, h, ongoing);
        parts.add(new Part(4, (int) (screenWidth * .1), Part.TYPE_POLY, screenWidth / 2, 0));
        parts.add(new Part(4, (int) (screenWidth * .1), Part.TYPE_POLY, screenWidth / 2, screenHeight));
        color.set(Color.RED);
    }

    @Override
    public boolean update(float dt){
        parts.get(0).translateY((int) (screenHeight * .01));
        parts.get(0).changeSize(2);
        parts.get(0).rotate(-.05f);
        parts.get(1).translateY(-(int) (screenHeight * .01));
        parts.get(1).changeSize(2);
        parts.get(1).rotate(.05f);
        if(parts.get(0).center.y > screenHeight/2 && parts.get(1).center.y < screenHeight/2) finished = true;
        return parts
                .get(0)
                .contains(player.x, player.y)
                || parts.get(1).contains(player.x, player.y);
    }


    @Override
    public void draw(ShapeRenderer s) {
        parts.get(0).draw(s, color);
        parts.get(1).draw(s, color);
    }
}
