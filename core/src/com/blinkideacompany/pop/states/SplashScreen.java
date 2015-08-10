package com.blinkideacompany.pop.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
