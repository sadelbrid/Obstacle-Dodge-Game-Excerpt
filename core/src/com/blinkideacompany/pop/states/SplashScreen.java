package com.blinkideacompany.pop.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends State {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private Timer t;
    private GameStateManager gsm;
    private BitmapFont font;
    private SpriteBatch batch;


    public SplashScreen() {
        Timer t=new Timer();
        //specify custom font from interal files

        batch = new SpriteBatch();

        gsm=new GameStateManager();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                gsm.changeState(new GameMenu());
            }
        }, SPLASH_DISPLAY_LENGTH);


    }

    @Override
    public void draw(ShapeRenderer s) {
        Label.LabelStyle ls = new Label.LabelStyle();


    }
}
