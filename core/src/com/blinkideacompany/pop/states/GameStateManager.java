package com.blinkideacompany.pop.states;

import java.util.ArrayList;

/**
 * Created by dgallagher on 8/4/15.
 */
public class GameStateManager {

    ArrayList<State> state;
    public State currentState;

    public void changeState(State state){
        currentState=state;
    }



}
