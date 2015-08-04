package com.blinkideacompany.pop.states;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.blinkideacompany.pop.JoyStick;
import com.blinkideacompany.pop.obstacles.ObstacleManager;
import com.blinkideacompany.pop.Player;

import java.util.Timer;


public class InGame extends Activity {
    public int canvasWidth;
    public int canvasHeight;
    Paint paint;
    //custom class for debugging console messages
    Console console;

    private boolean gameStarted = false;

    public InGame() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MainScene(this));
    }


    public class MainScene extends View {
        public Player player1;
        int WIDTH = this.getResources().getDisplayMetrics().widthPixels;
        int HEIGHT = this.getResources().getDisplayMetrics().heightPixels;
        Console console = new Console();
        Timer logicTimer;
        Runnable mainUpdate;
        Runnable countdownTask;

        float centerHeightOfJoystick;
        float centerWidthOfJoystick;
        float rightRadius;
        float leftRadius;
        float topRadius;
        float bottomRadius;

        int countdownCount;
        int countdownSize;
        int countdownAlpha;
        boolean isCounting;
        boolean isInGame;
        String countdownOutput;
        boolean touchInBounds;
        boolean touching=false;

        JoyStick joyStick = new JoyStick(player1, 0, 0);
        int radiusOfJoystick = joyStick.radius;

        ObstacleManager obstacleManager;

        public MainScene(Context context) {
            super(context);
            player1 = new Player(WIDTH, HEIGHT);
            obstacleManager = new ObstacleManager(player1, WIDTH, HEIGHT);
            isCounting = true;
            isInGame = false;
            countdownCount = 4;
            countdownOutput = "4";
            countdownSize = 100;
            countdownAlpha = 255;
            //Setup timers
            final Handler handleUpdate=new Handler();
            mainUpdate = new Runnable() {
                @Override
                public void run() {
                    //Main logic update to be done
                    //...
                    if (isCounting && !isInGame) {
                        countdownSize += 5;
                        countdownAlpha -= 4;
                    }
                    if (gameStarted) {
                        player1.update();
                        obstacleManager.update();
                    }
                    handleUpdate.postDelayed(this,20);
                }

            };




            final Handler handleCountdown=new Handler();


            countdownTask = new Runnable() {
                @Override
                public void run() {
                    countdownSize = 100;
                    countdownAlpha = 255;
                    countdownCount--;
                    if (countdownCount >= 1) {
                        countdownOutput = Integer.toString(countdownCount);
                    } else if (countdownCount == 0) {
                        countdownOutput = "Go!";
                    } else {
                        isCounting = false;
                        isInGame = true;
                        //game started bool
                        gameStarted = true;
                        handleCountdown.removeCallbacks(this);
                    }
                    handleCountdown.postDelayed(this,1000);
                }
            };

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    handleCountdown.postDelayed(countdownTask,1000);
                    handleUpdate.postDelayed(mainUpdate,20);
                }
            });





        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            player1.updateTouch(event);
//            //getting coordinates of touch events
//            float xTouch = event.getX();
//            float yTouch = event.getY();
//
//            player1.update(touching);
//            //if the the touch event is in bounds of the joystick
//            if ((yTouch <= topRadius) && (yTouch >= bottomRadius) && (xTouch >= leftRadius) && (xTouch <= rightRadius)) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_MOVE:
//                        touching = true;
//                        player1.xVel = (int) (xTouch - centerWidthOfJoystick) / 6;
//                        player1.yVel = (int) (yTouch - centerHeightOfJoystick) / 6;
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        touching = false;
//                        player1.xVel = player1.yVel = 0;
//                        break;
//                }
//                touchInBounds = true;
//            } else {
//                console.println("no touch");
//                touchInBounds = false;
//                touching=false;
//
//            }
            //refer to getwidth()/2 and  getHeight() as the center of the joystick radius
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //Draw
            if (isCounting) {
                Countdown.draw(canvas, countdownOutput, countdownAlpha, countdownSize, WIDTH, HEIGHT);
            }

            if (gameStarted) {
                //drawBoundary(canvas);
                canvas.drawCircle(player1.x, player1.y, player1.size, paint);
                obstacleManager.draw(canvas);
                //joyStick.draw(canvas);
                player1.joyStick.draw(canvas);
                //draw joystrick travel point

            }
            invalidate();
        }

    }
}

