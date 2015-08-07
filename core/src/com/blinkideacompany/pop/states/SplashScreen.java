package com.blinkideacompany.pop.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Timer;

public class SplashScreen extends State {
    /** Duration of wait **/
    private final float SPLASH_DISPLAY_LENGTH = 2.0f;
    private BitmapFont font;
    public ShapeRenderer s;
    private float timeSinceCreation;
    public SplashScreen(GameStateManager gsm) {
        super(gsm);
        timeSinceCreation = 0;
        //specify custom font from internal files
        s=new ShapeRenderer();
    }

    @Override
    public void update(float dt, ShapeRenderer s) {
        timeSinceCreation += dt;
        if(timeSinceCreation > SPLASH_DISPLAY_LENGTH){
            gsm.set(new GameMenu(gsm));
        }
    }

    @Override
    protected void handleInput(ShapeRenderer s){

    }

    @Override
    public void render(SpriteBatch sb){

    }

    @Override
    public void dispose(){

    }
}
