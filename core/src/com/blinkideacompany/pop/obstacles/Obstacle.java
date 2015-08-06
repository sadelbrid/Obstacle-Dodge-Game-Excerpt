package com.blinkideacompany.pop.obstacles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.Player;

import java.util.ArrayList;

/**
 * Created by dgallagher on 7/2/15.
 */
public abstract class Obstacle {
    Obstacle ongoingObjects;
    ArrayList<Part> parts;
    Player player;
    boolean finished;
    int screenWidth;
    int screenHeight;
    Color color;

    public Obstacle(Player p, int w, int h, Obstacle ongoingObjects){
        player = p;
        this.ongoingObjects = ongoingObjects;
        color = new Color();
        screenWidth = w;
        screenHeight = h;
        parts = new ArrayList<Part>();
        finished = false;
    }

    public abstract boolean update(float dt);

    public abstract void draw(ShapeRenderer s);
}
