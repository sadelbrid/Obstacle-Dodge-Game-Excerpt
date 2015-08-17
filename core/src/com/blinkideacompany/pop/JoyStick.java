package com.blinkideacompany.pop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;

/**
 * Created by dgallagher on 6/26/15.
 */
public class JoyStick implements InputProcessor {
    public int x, y;
    public int radius;
    int screenWidth, screenHeight;
    int maxSpeed;
    double acceleration;
    double angle;
    Player player;
    boolean touching;
    GridPoint2 pointTravelTo;
    double cornerAngleRightTop, cornerAngleLeftTop, cornerAngleLeftBottom, cornerAngleRightBottom;

    public JoyStick(Player p, int w, int h){
        screenWidth = w;
        screenHeight = h;
        x = w/2;
        y = (int)(h*.1);
        radius = (int)(h*.05);
        player = p;
        touching = false;
        maxSpeed = (int)(250*(double)w/(double)h);
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

    public void draw(ShapeRenderer sr) {
        sr.begin();
        sr.set(ShapeRenderer.ShapeType.Line);
        sr.setColor(0.0f,0.0f, 1.0f, 1.0f);
        sr.circle(x, y, radius);
        sr.end();
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
        //flip y touch
        screenY = screenHeight - screenY;
        double deltaX = screenX - x;
        double deltaY = screenY - y;
        if (Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) > screenWidth * .05) {
            touching = true;
            angle = Math.atan2(deltaY, deltaX);
            if (screenY < y) {
                angle *= -1;
                angle = 2*Math.PI - angle;
            }
            //calculateTravelPoint();
        } else touching = false;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touching = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //flip y touch
        screenY = screenHeight - screenY;
        double deltaX = screenX - x;
        double deltaY = screenY - y;
        if (Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) > screenWidth * .05) {
            touching = true;
            angle = Math.atan2(deltaY, deltaX);
            if (screenY < y) {
                angle *= -1;
                angle = 2*Math.PI - angle;
            }
            //calculateTravelPoint();
        } else touching = false;
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