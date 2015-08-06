package com.blinkideacompany.pop.states;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.JoyStick;
import com.blinkideacompany.pop.Player;
import com.blinkideacompany.pop.obstacles.ObstacleManager;
import com.mygdx.game.MyGdxGame;

import java.util.Timer;
import java.util.TimerTask;

public class InGame extends State {
    private boolean gameStarted;
    ObstacleManager obstacleManager;
    Player player;
    float countdown;
    public InGame(GameStateManager gsm) {
        super(gsm);
        gameStarted = false;
        obstacleManager = new ObstacleManager(player, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        countdown = 4;
    }

    @Override
    public void draw(ShapeRenderer s) {

    }

    @Override
    public void update(float dt) {
        if(!gameStarted) countdown -= dt;
        gameStarted = countdown < 1.0f;
        if(gameStarted) {
            player.update();
            obstacleManager.update();
        }
    }

    @Override
    public void handleInput(){

    }

    @Override
    public void render(SpriteBatch sb){

    }

    @Override
    public void dispose(){

    }
}

