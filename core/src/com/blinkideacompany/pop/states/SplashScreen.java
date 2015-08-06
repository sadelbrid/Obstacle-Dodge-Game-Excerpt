package com.blinkideacompany.pop.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Timer;

public class SplashScreen extends State {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private Timer t;
    private GameStateManager gsm;
    private BitmapFont font;
    private SpriteBatch batch;
    public ShapeRenderer s;


    public SplashScreen() {
        t=new Timer();
        //specify custom font from interal files
        s=new ShapeRenderer();
        batch = new SpriteBatch();
        gsm=new GameStateManager();

    }

    @Override
    public void draw(ShapeRenderer s) {

        Texture t=new Texture("badlogic.jpg");
        batch.begin();
        batch.draw(t, Gdx.graphics.getWidth()/2-t.getWidth()/2,Gdx.graphics.getHeight()/2-t.getHeight()/2);
        batch.end();


    }

    @Override
    public void update() {
        try {
            Thread.sleep(2000);
            gsm.changeState(new GameMenu());
        }
        catch(Exception e){

        }

    }
}
