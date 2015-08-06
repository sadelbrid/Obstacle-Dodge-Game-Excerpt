package com.blinkideacompany.pop.obstacles;

import com.blinkideacompany.pop.Player;

/**
 * Created by Seth on 7/2/15.
 */
public class SimpleBoxDodge extends Obstacle {
    public SimpleBoxDodge(Player p, int w, int h){
        super(p, w, h);
        parts.add(new Part(4, (int) (screenWidth * .1), Part.TYPE_POLY, screenWidth / 2, 0));
        parts.add(new Part(4, (int) (screenWidth * .1), Part.TYPE_POLY, screenWidth / 2, screenHeight));
    }

    @Override
    public boolean update(){
        parts.get(0).translateY((int) (screenHeight * .01));
        parts.get(0).changeSize(2);
        parts.get(0).rotate(-.05);
        parts.get(1).translateY(-(int) (screenHeight * .01));
        parts.get(1).changeSize(2);
        parts.get(1).rotate(.05);
        if(parts.get(0).center.y > screenHeight/2 && parts.get(1).center.y < screenHeight/2) finished = true;
        return parts.get(0).contains(player.x, player.y) || parts.get(1).contains(player.x, player.y);
    }

    @Override
    public void draw() {

        parts.get(0).draw(s);
        parts.get(1).draw(s);

    }


}
