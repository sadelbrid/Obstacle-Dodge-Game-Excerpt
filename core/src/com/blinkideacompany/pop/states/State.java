package com.blinkideacompany.pop.states;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by dgallagher on 8/4/15.
 */
public abstract class State {

    public String name;

    public abstract void draw(ShapeRenderer s);

    public abstract void update();
}
