package com.blinkideacompany.pop;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;

/**
 * Created by dgallagher on 6/26/15.
 */
public class JoyStick implements InputProcessor {
    int x, y;
    public int radius;
    int screenWidth, screenHeight;
    int maxSpeed;
    double acceleration;
    double angle;
    Player player;
    boolean touching;
    GridPoint2 pointTravelTo;
    double cornerAngleRightTop, cornerAngleLeftTop, cornerAngleLeftBottom, cornerAngleRightBottom;

    ShapeRenderer sh;

    public JoyStick(Player p, int w, int h){

        screenWidth = w;
        screenHeight =h;
        x = w/2;
        y = (int)(h*.8);
        radius = (int)(h*.1);
//        paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10);
        player = p;
        touching = false;
        maxSpeed = (int)(50*(double)w/(double)h);
        acceleration = 1;
        angle = 0;
        pointTravelTo = new GridPoint2(0, 0);
        cornerAngleRightTop = Math.atan2(y, x);
        cornerAngleLeftTop = Math.atan2(y, -x);
        cornerAngleLeftBottom = 2*Math.PI  - Math.atan2(screenHeight-y, -x);
        cornerAngleRightBottom = 2*Math.PI - Math.atan2(screenHeight-y, x);
    }


    public void calculateTravelPoint(){
        if(angle > cornerAngleRightBottom || angle < cornerAngleRightTop){ //right
            pointTravelTo.x = screenWidth;
            pointTravelTo.y = (int)(y-x*Math.tan(angle));
        }
        else if(angle > cornerAngleLeftBottom){//Bottom
            pointTravelTo.x = 2*(int)(Math.tan((angle - cornerAngleLeftBottom)/2.0)*((screenHeight-y)));
            pointTravelTo.y = screenHeight-60;
        }
        else if(angle > cornerAngleLeftTop){ //left
            pointTravelTo.x = 0;
            pointTravelTo.y = (int)(y-Math.tan(angle)*-x);
        }
        else{ //top
            pointTravelTo.x = x + (int)((double)y/Math.tan(angle));
            pointTravelTo.y = 0;
        }
    }

    public void draw() {
//        if (touching) paint.setColor(Color.GREEN);
//        else paint.setColor(Color.RED)
        sh = new ShapeRenderer();

        sh.circle(pointTravelTo.x, pointTravelTo.y, 60);
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