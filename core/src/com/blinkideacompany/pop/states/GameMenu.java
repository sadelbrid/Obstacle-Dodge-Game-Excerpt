package com.blinkideacompany.pop.states;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;

import java.util.Timer;
import java.util.TimerTask;


public class GameMenu extends State implements InputProcessor{

    public boolean gameStarted=false;
    BitmapFont font;



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
        if(gameStarted){
            return false;
        }

        else {


            float xTouchPos = screenX;
            float yTouchPos = screenY;

            //Check to see if first button hit
            if (xTouchPos >= (MyGdxGame.WIDTH * 2.0 / 5.0) && xTouchPos <= (MyGdxGame.WIDTH * 4.0 / 5.0)
                    && yTouchPos >= (MyGdxGame.HEIGHT * 6.0 / 10.0) && yTouchPos <= (MyGdxGame.HEIGHT * 7.0 / 10.0)) {
                //toGameScreen();

            }

            //Check to see if second button hit
            if (xTouchPos >= (MyGdxGame.WIDTH * 2.0 / 5.0) && xTouchPos <= (MyGdxGame.WIDTH * 4.0 / 5.0)
                    && yTouchPos >= (MyGdxGame.HEIGHT * 3.0 / 10.0) && yTouchPos <= (MyGdxGame.HEIGHT * 4.0 / 10.0)) {

            }

            if (xTouchPos >= 200 && xTouchPos <= 400 && yTouchPos >= 200 && yTouchPos <= 400) {
                //toGameScreen();
                gameStarted=true;
            }

           

        }
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

    @Override
    public void draw(ShapeRenderer s) {
        s.begin(ShapeRenderer.ShapeType.Filled);
        s.setColor(com.badlogic.gdx.graphics.Color.BLACK);
        s.circle(100,100,100);
        s.end();
    }

    @Override
    public void update() {

    }


    public class MainScene  {

        Timer logicTimer;
        TimerTask mainUpdate;

        public MainScene() {

            //Setup timers
            logicTimer = new Timer();
            mainUpdate = new TimerTask() {
                @Override
                public void run() {
                    //Main logic update to be done
                    //...
                }
            };
            logicTimer.schedule(mainUpdate, 0, 20);

        }
        
    }
}
