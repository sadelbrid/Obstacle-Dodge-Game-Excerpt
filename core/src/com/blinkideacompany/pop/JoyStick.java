package com.blinkideacompany.pop;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

/**
 * Created by dgallagher on 6/26/15.
 */
public class JoyStick {
    int x, y;
    int radius;
    int screenWidth, screenHeight;
    int maxSpeed;
    double acceleration;
    double angle;
    Paint paint;
    Player player;
    boolean touching;
    Point pointTravelTo;
    double cornerAngleRightTop, cornerAngleLeftTop, cornerAngleLeftBottom, cornerAngleRightBottom;
    public JoyStick(Player p, int w, int h){
        screenWidth = w;
        screenHeight =h;
        x = w/2;
        y = (int)(h*.8);
        radius = (int)(h*.1);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        player = p;
        touching = false;
        maxSpeed = (int)(50*(double)w/(double)h);
        acceleration = 1;
        angle = 0;
        pointTravelTo = new Point(0,0);
        cornerAngleRightTop = Math.atan2(y, x);
        cornerAngleLeftTop = Math.atan2(y, -x);
        cornerAngleLeftBottom = 2*Math.PI  - Math.atan2(screenHeight-y, -x);
        cornerAngleRightBottom = 2*Math.PI - Math.atan2(screenHeight-y, x);
    }

    public void update(MotionEvent event){
        double event_x = event.getX();
        double event_y = event.getX();
        switch(event.getAction()){
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
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

    public void draw(Canvas canvas) {
        if (touching) paint.setColor(Color.GREEN);
        else paint.setColor(Color.RED);
        canvas.drawCircle(x, y, radius, paint);
        canvas.drawCircle(pointTravelTo.x, pointTravelTo.y, 60, paint);
    }
}