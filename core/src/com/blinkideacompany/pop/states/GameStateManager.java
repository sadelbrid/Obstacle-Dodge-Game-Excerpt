package com.blinkideacompany.pop.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Stack;

/**
 * Created by dgallagher on 8/4/15.
 */
public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State s){
        states.push(s);
    }

    public void pop(){
        states.pop();
    }

    public void set(State s){
        states.pop();
        states.push(s);
    }

    public void update(float dt, ShapeRenderer s){
        states.peek().update(dt, s);
    }

    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }
}
