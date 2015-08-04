package com.blinkideacompany.pop.obstacles;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.blinkideacompany.pop.Player;

import java.util.ArrayList;

/**
 * Created by dgallagher on 7/2/15.
 */
public abstract class Obstacle {
    ArrayList<Part> parts;
    Player player;
    Paint paint;
    boolean finished;
    int screenWidth;
    int screenHeight;

    public Obstacle(Player p, int w, int h){
        player = p;
        screenWidth = w;
        screenHeight = h;
        parts = new ArrayList<Part>();
        paint = new Paint();
        finished = false;
    }

    public abstract boolean update();

    public abstract void draw();
}
