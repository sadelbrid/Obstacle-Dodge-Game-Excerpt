package com.blinkideacompany.pop.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blinkideacompany.pop.JoyStick;
import com.blinkideacompany.pop.Player;
import com.blinkideacompany.pop.obstacles.ObstacleManager;
import com.mygdx.game.MyGdxGame;

public class InGame extends State {
    private boolean gameStarted;
    private ShapeRenderer shapeRenderer;
    ObstacleManager obstacleManager;
    Player player;
    float countdown;
    public InGame(GameStateManager gsm, ShapeRenderer sr) {
        super(gsm);
        shapeRenderer =sr;
        gameStarted = false;
        player = new Player(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        obstacleManager = new ObstacleManager(player, shapeRenderer, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
        countdown = 4;
    }

    @Override
    public void update(float dt, ShapeRenderer s) {
        if(!gameStarted) countdown -= dt;
        gameStarted = countdown < 1.0f;
        if(gameStarted) {
            player.update();
            obstacleManager.update(dt);
        }
    }

    @Override
    public void handleInput(ShapeRenderer s){

    }

    @Override
    public void render(SpriteBatch sb){
        if(gameStarted){
            obstacleManager.draw(shapeRenderer);
        }
    }

    @Override
    public void dispose(){

    }
}

