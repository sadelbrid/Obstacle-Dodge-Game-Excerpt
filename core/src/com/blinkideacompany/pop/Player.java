package com.blinkideacompany.pop;


/**
 * Created by dgallagher on 6/26/15.
 */
public class Player {
    public int x, y;
    double x_vel, y_vel;
    int screenWidth, screenHeight;
    double screenRatio;
    int size;
    JoyStick joyStick;
    public Player(int w, int h){
        screenWidth = w;
        screenHeight = h;
        x = screenWidth/2;
        y = screenHeight/2;
        x_vel = y_vel = 0;
        size = (int)(screenWidth*.05);
        screenRatio = (double)screenWidth/(double)screenHeight;
        joyStick = new JoyStick(this, screenWidth, screenHeight);
    }

    public void update(){
        if(joyStick.touching && Math.sqrt(Math.pow(x_vel, 2) + Math.pow(y_vel, 2)) < joyStick.maxSpeed){
            x_vel += 5*Math.cos(joyStick.angle);
            y_vel += -5*Math.sin(joyStick.angle);
//            x_vel += .001*(joyStick.pointTravelTo.x - x);
//            y_vel += .001*(joyStick.pointTravelTo.y - y);
            if(Math.abs(x_vel)>joyStick.maxSpeed)x_vel = (x_vel > 0) ? joyStick.maxSpeed : -joyStick.maxSpeed;
            if(Math.abs(y_vel)>joyStick.maxSpeed)y_vel = (y_vel > 0) ? joyStick.maxSpeed : -joyStick.maxSpeed;
        }
        else{
            if(Math.abs(x_vel) < joyStick.acceleration) x_vel = 0;
            else x_vel += (x_vel > 0) ? -joyStick.acceleration : joyStick.acceleration;
            if(Math.abs(y_vel) < joyStick.acceleration) y_vel = 0;
            else y_vel += (y_vel > 0) ? -joyStick.acceleration : joyStick.acceleration;
        }

        //Update position
        x += x_vel;
        y += y_vel;

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

    public void updateTouch(MotionEvent event){
        /*
        sin(theta) = x/h
        theta = atan(x/y)
         */
        switch (event.getAction()){
            case  MotionEvent.ACTION_MOVE:

                double deltaX = event.getX() - joyStick.x;
                double deltaY = event.getY() - joyStick.y;
                if(Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) > screenWidth*.05){
                    joyStick.touching = true;
                    joyStick.angle = Math.atan2(deltaY, deltaX);
                    if(event.getY() < joyStick.y){
                        joyStick.angle*=-1;
                    }
                    else{
                        joyStick.angle = 2*Math.PI - joyStick.angle;
                    }
                    joyStick.calculateTravelPoint();
                }
                else joyStick.touching = false;
                break;
            case MotionEvent.ACTION_UP:
                joyStick.touching = false;
                break;
        }
    }
}
