package com.blinkideacompany.pop.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by dgallagher on 8/4/15.
 */
public class GameStateManager {

    private Stack<State> states;
    public int currentStateVal;
    public State currentState;

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

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }
}
