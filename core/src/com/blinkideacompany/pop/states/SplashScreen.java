package com.blinkideacompany.pop.states;

import com.badlogic.gdx.Gdx;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends State {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private Timer t;
    private GameStateManager gsm;


    public SplashScreen() {
        Timer t=new Timer();
        gsm=new GameStateManager();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                gsm.changeState(new GameMenu());
            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(0,0,1,1);
    }
}
