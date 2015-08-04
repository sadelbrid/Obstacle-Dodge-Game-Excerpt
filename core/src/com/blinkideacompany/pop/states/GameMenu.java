package com.blinkideacompany.pop.states;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.blinkideacompany.pop.Main;

import java.util.Timer;
import java.util.TimerTask;


public class GameMenu extends State implements InputProcessor{

    public boolean gameStarted=false;
    BitmapFont font;

    @Override
    public void draw() {

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
        if(gameStarted){
            return false;
        }

        else {


            float xTouchPos = screenX;
            float yTouchPos = screenY;

            //Check to see if first button hit
            if (xTouchPos >= (Main.WIDTH * 2.0 / 5.0) && xTouchPos <= (Main.WIDTH * 4.0 / 5.0)
                    && yTouchPos >= (Main.HEIGHT * 6.0 / 10.0) && yTouchPos <= (Main.HEIGHT * 7.0 / 10.0)) {
                //toGameScreen();

            }

            //Check to see if second button hit
            if (xTouchPos >= (Main.WIDTH * 2.0 / 5.0) && xTouchPos <= (Main.WIDTH * 4.0 / 5.0)
                    && yTouchPos >= (Main.HEIGHT * 3.0 / 10.0) && yTouchPos <= (Main.HEIGHT * 4.0 / 10.0)) {

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
