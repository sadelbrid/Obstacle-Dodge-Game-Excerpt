package com.blinkideacompany.pop.states;

import java.util.Stack;

/**
 * Created by dgallagher on 8/4/15.
 */
public class GameStateManager {

    Stack<State> state;
    public int currentStateVal;
    public State currentState;


    public void changeState(State state){
        currentState=state;
    }



}
