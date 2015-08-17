package com.blinkideacompany.pop;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by dgallagher on 6/26/15.
 */
public class Player{
    public float x, y, size;
    public float x_vel, y_vel;
    private int speed;
    int screenWidth, screenHeight;
    double screenRatio;
    public JoyStick joyStick;
    public Player(int w, int h){
        screenWidth = w;
        screenHeight = h;
        x = screenWidth/2;
        y = screenHeight/2;
        x_vel = y_vel = 0;
        speed = 10;
        size = (int)(screenWidth*.05);
        screenRatio = (double)screenWidth/(double)screenHeight;
        joyStick = new JoyStick(this, screenWidth, screenHeight);
        Gdx.input.setInputProcessor(joyStick);
    }

    public void update(float dt){
        if(joyStick.touching && Math.sqrt(Math.pow(x_vel, 2) + Math.pow(y_vel, 2)) < joyStick.maxSpeed){
            x_vel += Math.cos(joyStick.angle)*speed;
            y_vel += Math.sin(joyStick.angle)*speed;
            Gdx.app.log("speed", "x: " + Float.toString(x_vel) + ", y: " + Float.toString(y_vel));
            if(Math.abs(x_vel)>joyStick.maxSpeed)x_vel = (x_vel > 0) ? joyStick.maxSpeed : -joyStick.maxSpeed;
            if(Math.abs(y_vel)>joyStick.maxSpeed)y_vel = (y_vel > 0) ? joyStick.maxSpeed : -joyStick.maxSpeed;
        }
        else{
            if(Math.abs(x_vel) <= joyStick.acceleration) x_vel = 0;
            else x_vel += (x_vel > 0) ? -joyStick.acceleration : joyStick.acceleration;
            if(Math.abs(y_vel) <= joyStick.acceleration) y_vel = 0;
            else y_vel += (y_vel > 0) ? -joyStick.acceleration : joyStick.acceleration;
        }

        //Update position
        x += x_vel*dt;
        y += y_vel*dt;

        if(y < 0) {
            y = 0;
            y_vel *= -1;
        }
        else if(y > screenHeight) {
            y = screenHeight;
            y_vel *= -1;
        }

        if(x < 0) {
                x = 0;
                x_vel *= -1;
            }
            else if(x > screenWidth){
                x = screenWidth;
                x_vel *= -1;
            }
        }

    public void draw(ShapeRenderer s){
        s.begin(ShapeRenderer.ShapeType.Filled);
        s.setColor(com.badlogic.gdx.graphics.Color.BLACK);
        s.circle(x,y,size);
        s.end();
    }
}
