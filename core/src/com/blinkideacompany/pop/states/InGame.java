package com.blinkideacompany.pop.states;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.JoyStick;
import com.blinkideacompany.pop.Player;
import com.blinkideacompany.pop.obstacles.ObstacleManager;
import com.mygdx.game.MyGdxGame;

import java.util.Timer;
import java.util.TimerTask;


public class InGame extends State {

    private boolean gameStarted;

    public InGame() {
        gameStarted = false;
    }



    @Override
    public void draw(ShapeRenderer s) {

    }

    @Override
    public void update() {

    }


    public class MainScene {
        public Player player1;

        Timer logicTimer;
        TimerTask mainUpdate;
        TimerTask countdownTask;

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
        boolean touching = false;

        JoyStick joyStick = new JoyStick(player1, 0, 0);
        int radiusOfJoystick = joyStick.radius;

        ObstacleManager obstacleManager;

        public MainScene() {

            player1 = new Player(MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
            obstacleManager = new ObstacleManager(player1, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
            isCounting = true;
            isInGame = false;
            countdownCount = 4;
            countdownOutput = "4";
            countdownSize = 100;
            countdownAlpha = 255;
            //Setup timers
            final Timer handleUpdate = new Timer();
            mainUpdate = new TimerTask() {

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

                }

            };

            handleUpdate.scheduleAtFixedRate(mainUpdate, 0, 20);


            final Timer handleCountdown = new Timer();


            countdownTask = new TimerTask() {
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
                        countdownTask.cancel();
                    }

                }
            };
            handleCountdown.scheduleAtFixedRate(countdownTask, 0, 1000);

            handleUpdate.scheduleAtFixedRate(mainUpdate, 0, 20);


        }


        //public boolean onTouchEvent(MotionEvent event) {
        //player1.updateTouch(event);
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
//            //refer to getwidth()/2 and  getHeight() as the center of the joystick radius
//            return true;
//        }


        public void draw() {
            //Draw
            if (isCounting) {
                //Countdown.draw();
            }

            if (gameStarted) {
                //drawBoundary(canvas);
//                canvas.drawCircle(player1.x, player1.y, player1.size, paint);
//                obstacleManager.draw(canvas);
//                //joyStick.draw(canvas);
//                player1.joyStick.draw(canvas);
                //draw joystick travel point

            }
            //invalidate();
        }

    }
}

